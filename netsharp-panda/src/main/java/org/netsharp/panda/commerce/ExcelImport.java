package org.netsharp.panda.commerce;

import java.io.BufferedInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.base.IPersistableService;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.DataTable;
import org.netsharp.core.ExcelImportException;
import org.netsharp.core.QueryParameters;
import org.netsharp.core.Row;
import org.netsharp.core.convertor.ITypeConvertor;
import org.netsharp.core.convertor.TypeConvertorFactory;
import org.netsharp.entity.IPersistable;
import org.netsharp.panda.base.IPPartService;
import org.netsharp.panda.core.View;
import org.netsharp.panda.entity.PPart;
import org.netsharp.panda.servlet.invoke.rest.Result;
import org.netsharp.util.ExceptionUtil;
import org.netsharp.util.ReflectManager;
import org.netsharp.util.StringManager;

import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;

public class ExcelImport {

	protected static final Log logger = LogFactory.getLog(ExcelImport.class.getName());
	
	private HttpServletRequest httpServletRequest;
	private Integer vid;
	private PPart context;
	private IPPartService partService = ServiceFactory.create(IPPartService.class);

	public ExcelImport(HttpServletRequest request, HttpServletResponse response) {

		this.vid = Integer.parseInt(request.getParameter("vid"));
		this.httpServletRequest = request;
		this.context = partService.byId(vid);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void start() {

		try {

			DiskFileItemFactory fac = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(fac);
			upload.setHeaderEncoding("UTF-8");
			List fileList = upload.parseRequest(this.httpServletRequest);
			Iterator<FileItem> it = fileList.iterator();
			while (it.hasNext()) {

				FileItem item = it.next();
				if (!item.isFormField()) {

					BufferedInputStream inputSteam = new BufferedInputStream(item.getInputStream());
					Workbook workbook = Workbook.getWorkbook(inputSteam);
					if (workbook.getNumberOfSheets() <= 0) {

						throw new ExcelImportException("您所选择的文件不正确！");
					}

					Map<String, String> fields = this.getFields();
					String[] headers = fields.keySet().toArray(new String[0]);
					this.validateExcel(workbook, headers);

					Sheet sheet = workbook.getSheet(0);
					Cell[] headCells = sheet.getRow(0);
					List<IPersistable> entities = new ArrayList<IPersistable>();
					IPersistableService<IPersistable> service = ServiceFactory.create(this.context.getService());
					IPersistable entity = null;

					int rowCount = sheet.getRows();
					if (rowCount > 3000) {
						throw new ExcelImportException("导入记录数过大，不能超过3000条!");
					}
					for (int rowIndex = 1; rowIndex < rowCount; rowIndex++) {

						entity = service.newInstance();
						Cell[] cells = sheet.getRow(rowIndex);
						int cellCount = cells.length;
						for (int cellIndex = 0; cellIndex < cellCount; cellIndex++) {

							Cell cell = cells[cellIndex];
							String content = cell.getContents();
							if (StringManager.isNullOrEmpty(content)) {

								continue;
							}

							if (cell.getType() == CellType.DATE) {

								Date d = ((DateCell) cell).getDate();
								DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								content = dateFormat.format(d);
							}

							String field = fields.get(headCells[cellIndex].getContents().trim());
							if (field.indexOf(".") > 0) {

								this.setRefEntity(entity, field, content);

							} else {

								Class<?> fieldType = (Class<?>) ReflectManager.getField(entity.getClass(), field).getGenericType();
								Object value = this.convert(fieldType, content);
								entity.set(field, value);
							}
						}
						entity.toNew();
						entities.add(entity);
					}

					this.invoking(entities);
				}
			}

		} catch (Exception e) {

			throw new ExcelImportException("数据导入错误：" + e.getLocalizedMessage());
		}
	}

	/**
	 * 设置引用实体值
	 * 
	 * @param entity
	 * @param field
	 * @param content
	 */
	private void setRefEntity(IPersistable entity, String field, String content) {

		try {

			int lastDotIndex = field.lastIndexOf(".");
			String property = field.substring(0, lastDotIndex);
			Field propertyField = ReflectManager.getField(entity.getClass(), property);
			Class<?> refFieldClass = (Class<?>) propertyField.getGenericType();

			// 获取引用实体
			String upperCaseField = this.toUpperCaseFirstOne(property);
			String methodName = "get" + upperCaseField;
			Method method = entity.getClass().getDeclaredMethod(methodName);
			IPersistable refEntity = (IPersistable) method.invoke(entity);
			if (refEntity == null) {

				refEntity = (IPersistable) ReflectManager.newInstance(refFieldClass);
			}

			String refField = field.substring(lastDotIndex + 1);
			Class<?> refFieldType = (Class<?>) ReflectManager.getField(refEntity.getClass(), refField).getGenericType();
			Object value = this.convert(refFieldType, content);
			refEntity.set(refField, value);

			methodName = "set" + upperCaseField;
			method = entity.getClass().getDeclaredMethod(methodName, refFieldClass);
			method.invoke(entity, refEntity);

		} catch (Exception e) {

			throw new ExcelImportException("数据导入错误：" + e.getMessage());
		}
	}

	/**
	 * 类型转换
	 * 
	 * @param type
	 * @param value
	 * @return
	 */
	private Object convert(Class<?> type, String value) {

		Object obj = null;
		if (type.isEnum()) {

			Enum<?>[] enumElems = (Enum<?>[]) type.getEnumConstants();
			Field textField = null;
			try {

				textField = type.getDeclaredField("text");
			} catch (Exception ex) {

				String message = "枚举" + type.getName() + "没有text属性";
				logger.debug(message);
			}
			for (int i = 0; i < enumElems.length; i++) {

				Enum<?> enumElem = enumElems[i];
				if (textField != null) {
					try {

						textField.setAccessible(true);
						String text = (String) textField.get(enumElem);
						if (value.equals(text)) {

							obj = enumElem;
							break;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} else {

			ITypeConvertor convertor = TypeConvertorFactory.create(type);
			obj = convertor.fromString(value);
		}
		return obj;
	}

	/**
	 * 首字母转大写
	 * 
	 * @param s
	 * @return
	 */
	private String toUpperCaseFirstOne(String s) {
		if (Character.isUpperCase(s.charAt(0))) {

			return s;
		} else {

			return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
		}
	}

	/**
	 * 校验EXCEL文件基本格式
	 * 
	 * @param workbook
	 */
	private void validateExcel(Workbook workbook, String[] headers) {

		Sheet sheet = null;
		if (workbook != null && workbook.getNumberOfSheets() > 0) {

			sheet = workbook.getSheet(0);
			if (sheet == null || sheet.getColumns() != headers.length) {

				throw new ExcelImportException("您所选择的文件不正确！");
			}

			Cell[] rows = sheet.getRow(0);
			if (rows == null || rows.length == 0) {

				throw new ExcelImportException("您所选择的文件不正确！");
			}

			for (int index = 0; index < headers.length; index++) {

				if (!headers[index].trim().equals(rows[index].getContents().trim())) {

					throw new ExcelImportException("您所选择的文件不正确！");
				}
			}

			if (sheet.getRows() < 1) {

				throw new ExcelImportException("您所选择的文件无要导入的数据！");
			}
		}
	}

	/**
	 * 加载要导入的字段
	 */
	private Map<String, String> getFields() {

		try {

			String cmdText = "select * from pa_datagrid_column where is_import=1 and datagridId = ?";
			QueryParameters qps = new QueryParameters();
			{

				qps.add("@datagridId", this.context.getDatagridId(), Types.INTEGER);
			}

			IPersistableService<IPersistable> service = ServiceFactory.create(IPersistableService.class);
			DataTable table = service.executeTable(cmdText, qps);

			Map<String, String> maps = new LinkedHashMap<String, String>();
			for (Row row : table) {

				maps.put(row.getString("header"), row.getString("property_name"));
			}

			return maps;
		} catch (Exception e) {
			throw new ExcelImportException("数据导入错误：" + e.getMessage());
		}
	}

	public void invoking(List<IPersistable> entities) {

		IPPartService service = ServiceFactory.create(IPPartService.class);
		PPart ppart = service.byId(this.vid);

		String serviceController = ppart.getPartType().getServiceController();
		if (!StringManager.isNullOrEmpty(ppart.getServiceController())) {
			serviceController = ppart.getServiceController();
		}

		Object serviceObject = ReflectManager.newInstance(serviceController);
		String methodName = "importExcel";
		Method method = ReflectManager.getMethods(serviceObject.getClass(), methodName);
		if (serviceObject instanceof View) {
			View view = (View) serviceObject;
			view.setContext(ppart);
		}

		Class<?>[] pts = method.getParameterTypes();
		Result<Object> result = new Result<Object>();
		try {

			Object obj = ReflectManager.invoke(serviceObject, methodName, pts, entities);
			result.setData(obj);

		} catch (Exception ex) {

			throw new ExcelImportException(ExceptionUtil.extractMsg(ex));
		}

	}
}
