package org.netsharp.panda.commerce;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.core.convertor.impl.DateConvertor;
import org.netsharp.entity.IPersistable;
import org.netsharp.panda.core.HttpContext;
import org.netsharp.panda.core.comunication.IResponse;
import org.netsharp.panda.core.comunication.ServletRequest;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.utils.Exportor;
import org.netsharp.panda.utils.TypedField;
import org.netsharp.panda.utils.XlsExportor;
import org.netsharp.util.CodeUtil;
import org.netsharp.util.JavaEnumManager;

public class ExcelExportor {

	protected static final Log logger = LogFactory.getLog(ExcelExportor.class.getName());
	private PDatagrid datagrid;

	private boolean isImport = false;

	public void export(List<?> table) throws Exception {

		//String nameString = this.datagrid.getResourceNode().getName().replace('-', '_') + CodeUtil.generateTimedNo(20);
		String nameString = CodeUtil.generateTimedNo(20);
		String fileName = nameString + ".xls";
		String filePath = HttpContext.getCurrent().getRequest().getContextPath() + "/download/" + fileName;
		String realPath = HttpContext.getCurrent().getContext().getRealPath(filePath);
		
		logger.debug("fileName：=======" + fileName);
		logger.debug("filePath：=======" + filePath);
		logger.debug("realPath：=======" + realPath);
		
		createExcel(table, realPath);
		downloadExcel(fileName, realPath);
	}

	private void createExcel(List<?> table, String fileNameWithPath) {

		logger.debug("文件路径：=======" + fileNameWithPath);
		File file = new File(fileNameWithPath);

		Exportor exporter;
		Log logger = LogFactory.getLog(ExcelExportor.class);
		try {

			exporter = new XlsExportor(file, "list");
			exporter.setColumns(this.getColumns());
			exporter.prepare();

			DateConvertor convertor = new DateConvertor();
			DecimalFormat df = new DecimalFormat("0.00");
			for (int rowIndex = 0; rowIndex < table.size(); rowIndex++) {

				IPersistable p = (IPersistable) table.get(rowIndex);
				for (PDatagridColumn column : this.datagrid.getColumns()) {

					Object value = p.get(column.getPropertyName());
					if (value == null) {
						value = null;
					} else if (value instanceof Date) {
						value = convertor.toString(value);
					} else if (value.getClass().isEnum()) {
						value = JavaEnumManager.getText((Enum<?>) value);
					} else if (value instanceof BigDecimal) {
						value = df.format(value);
					}

					exporter.writeDataCell(rowIndex, column.getHeader(), value);
				}
			}

			exporter.close();

		} catch (Exception e) {

			e.printStackTrace();
			logger.warn("导出异常" + e.getMessage());
		}

	}

	private ArrayList<TypedField> getColumns() {

		TypedField column = null;
		ArrayList<TypedField> columns = new ArrayList<TypedField>();
		List<PDatagridColumn> metaCols = this.datagrid.getColumns();
		for (PDatagridColumn col : metaCols) {

			column = new TypedField(col.getHeader(), "str");
			if (this.isImport) {

				if (col.isImported()) {

					columns.add(column);
				}
			} else {

				columns.add(column);
			}
		}

		return columns;
	}

	private void downloadExcel(String fileName, String filePathWithName) throws Exception {

		IResponse response = HttpContext.getCurrent().getResponse();
		ServletRequest request = (ServletRequest) HttpContext.getCurrent().getRequest();
		response.reset();
		response.setContentType("application/vnd.ms-excel;charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment;filename=" + this.getStr(request.request, fileName));

		InputStream data = null;

		try {

			File file = new File(filePathWithName);
			data = new FileInputStream(file.getAbsolutePath());
			byte[] buffer = new byte[1024];
			int n;
			OutputStream out = response.getOutputStream();
			while ((n = data.read(buffer)) > 0) {
				out.write(buffer, 0, n);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (data != null) {
				try {
					data.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	private String getStr(HttpServletRequest request, String realFileName) throws Exception {
		String browName = null;
		String clientInfo = request.getHeader("User-agent");
		// logger.debug(clientInfo);
		if (clientInfo != null && clientInfo.indexOf("MSIE") > 0) {//
			// IE采用URLEncoder方式处理
			if (clientInfo.indexOf("MSIE 6") > 0 || clientInfo.indexOf("MSIE 5") > 0) {// IE6，用GBK，此处实现由局限性
				browName = new String(realFileName.getBytes("GBK"), "ISO-8859-1");
			} else {// ie7+用URLEncoder方式
				browName = java.net.URLEncoder.encode(realFileName, "UTF-8");
			}
		} else {// 其他浏览器
			browName = new String(realFileName.getBytes("GBK"), "ISO-8859-1");
		}
		return browName;
	}

	public PDatagrid getDatagrid() {
		return datagrid;
	}

	public void setDatagrid(PDatagrid datagrid) {
		this.datagrid = datagrid;
	}

	public boolean isImport() {
		return isImport;
	}

	public void setImport(boolean isImport) {
		this.isImport = isImport;
	}
}
