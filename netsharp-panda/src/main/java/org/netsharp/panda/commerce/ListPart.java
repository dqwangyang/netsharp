package org.netsharp.panda.commerce;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.netsharp.base.IPersistableService;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.BusinessException;
import org.netsharp.core.Column;
import org.netsharp.core.DataTable;
import org.netsharp.core.ExcelImportException;
import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.core.Oql;
import org.netsharp.core.Pagination;
import org.netsharp.core.Paging;
import org.netsharp.core.Row;
import org.netsharp.entity.Entity;
import org.netsharp.entity.IPersistable;
import org.netsharp.panda.commerce.base.DatagridPartBase;
import org.netsharp.panda.controls.query.QueryPanel;
import org.netsharp.panda.controls.utility.UrlHelper;
import org.netsharp.panda.core.JscriptType;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.entity.PRowStyler;
import org.netsharp.panda.json.DatagridResultJson;
import org.netsharp.persistence.oql.parser.set.OqlStruct;
import org.netsharp.persistence.oql.parser.table.TableParser;
import org.netsharp.util.ExceptionUtil;
import org.netsharp.util.JsonManage;
import org.netsharp.util.ReflectManager;
import org.netsharp.util.StringManager;

public class ListPart extends DatagridPartBase {

	protected String queryUrl;
	protected String filter = "";
	private PQueryProject pqueryProject;
	protected String urlFilter = "";

	public Object query() throws IOException {

		this.pdatagrid = this.context.getDatagrid();
		Paging paging = null;
		String strPageIndex = getRequest("page");
		String sort = getRequest("sort");
		String order = getRequest("order");
		String rows = getRequest("rows");
		String qps = getRequest("qps");
		if(!StringManager.isNullOrEmpty(qps)){

			String qpsJson = URLDecoder.decode(getRequest("qps"), "UTF-8");
			List<FilterParameter> fpList = JsonManage.deSerializeList(FilterParameter.class, qpsJson);
			
			logger.debug(fpList);
		}
		
		if (!StringManager.isNullOrEmpty(strPageIndex)) {

			int pageIndex = StringManager.isNullOrEmpty(strPageIndex) ? 1 : Integer.valueOf(strPageIndex);
			if (pageIndex == 0) {

				pageIndex = 1;
			}

			int pageSize = StringManager.isNullOrEmpty(rows) ? this.pdatagrid.getPageSize() : Integer.valueOf(rows);
			paging = new Paging();
			{
				paging.setPageNo(pageIndex);
				paging.setPageSize(pageSize);
			}
		}

		String filter = getRequest("filter");
		if (!StringManager.isNullOrEmpty(filter)) {

			filter = URLDecoder.decode(filter, "UTF-8");
			filter = filter.replace('|', '=');
		}

		ArrayList<String> filters = new ArrayList<String>();
		if (!StringManager.isNullOrEmpty(filter)) {

			filters.add(filter);
		}

		String defaultFilter = this.getDefaultFilter();
		if (!StringManager.isNullOrEmpty(defaultFilter)) {

			filters.add(defaultFilter);
		}

		String extraFilter = this.getExtraFilter();
		if (!StringManager.isNullOrEmpty(extraFilter)) {

			filters.add(extraFilter);
		}

		String datagridFilter = this.context.getDatagrid().getFilter();
		logger.debug("query datagridFilter:" + datagridFilter);
		if (!StringManager.isNullOrEmpty(filter) && !StringManager.isNullOrEmpty(datagridFilter)) {

			datagridFilter = datagridFilter.replace("1=0", "1=1");
		}

		if (!StringManager.isNullOrEmpty(datagridFilter)) {

			filters.add(datagridFilter);
		}

		filter = StringManager.join(" and ", filters);
		logger.debug("query filter:" + filter);
		Oql oql = new Oql();
		{
			PDatagrid dgp = this.context.getDatagrid();
			String entityId = dgp.getEntityId();
			if (StringManager.isNullOrEmpty(entityId)) {

				entityId = this.getContext().getEntityId();
			}
			oql.setSelects(dgp.getSelectedFields());
			oql.setEntityId(entityId);
			oql.setFilter(filter);
			oql.setPaging(paging);
			oql.setOrderby(order);
		}

		if (StringManager.isNullOrEmpty(sort)) {

			String defaultSort = this.pdatagrid.getSortName();
			if (!StringManager.isNullOrEmpty(defaultSort)) {

				oql.setOrderby(defaultSort);
			} else {

				oql.setOrderby(this.context.getDatagrid().getOrderby());
			}

		} else {

			oql.setOrderby(sort + " " + order);
		}

		Pagination pag = this.doQuery(oql);
		oql.setPaging( pag.getPaging( ));
		Object json = this.serialize(pag.getRows(), oql);
		return json;
	}

	/* 获取客户段传递的额外的参数，需要重写 */
	protected String getExtraFilter() {
		return "";
	}

	protected Object serialize(List<?> rows,Oql oql) {

		EasyuiDatagridResult result = new EasyuiDatagridResult();
		{
			result.setRows(rows);
			result.setFooter(this.getFooter(oql));
			if (oql.getPaging() != null) {

				result.setTotal(oql.getPaging().getTotalCount());
			}
		}

		DatagridResultJson parser = new DatagridResultJson(result, pdatagrid);
		Object json = parser.parse();
		return json;
	}

	protected List<?> getFooter(Oql oql) {

		List<PDatagridColumn> columns = this.pdatagrid.getSumFields();
		if (columns.size() == 0) {

			return null;
		}

		String entityId = this.pdatagrid.getEntityId();
		Mtable meta = MtableManager.getMtable(entityId);
		List<String> ss = new ArrayList<String>();
		for (PDatagridColumn c : columns) {

			Column column = meta.getPropertyOrColumn(c.getPropertyName());
			ss.add("SUM(ifnull(" + meta.getCode() + "." + column.getColumnName() + ",0)) AS " + c.getPropertyName());
		}
		String fields = StringManager.join(",", ss);
		TableParser parser = new TableParser();
		OqlStruct struct = parser.parseMain(oql);
		StringBuilder builder = new StringBuilder();
		{
			builder.append("SELECT ");
			builder.append(fields);
			builder.append(" FROM " + struct.Mtable.getTableName() + " AS " + struct.Mtable.getCode() + " ");
			if (!StringManager.isNullOrEmpty(struct.Joins)) {

				builder.append(struct.Joins + " ");
			}

			if (!StringManager.isNullOrEmpty(struct.Wheres)) {

				builder.append(" WHERE " + struct.Wheres);
			}
		}

		List<Object> rows = new ArrayList<Object>();
		Class<?> serviceType = this.getServiceType();
		IPersistableService<?> service = (IPersistableService<?>) ServiceFactory.create(serviceType);
		DataTable table = service.executeTable(builder.toString(), oql.getParameters());
		for (Row row : table) {

			Map<String, Object> item = row.getInnerValues();
			rows.add(item);
		}
		return rows;
	}

	public void export() throws Exception {

		String sort = getRequest("sort");
		String order = getRequest("order");
		String filter = getRequest("filter");
		if (!StringManager.isNullOrEmpty(filter)) {

			filter = filter.replace('_', '=');
			filter = filter.replace('|', '=');
			filter = URLDecoder.decode(filter, "UTF-8");
			filter = filter.replace('_', '=');
			filter = filter.replace('|', '=');
			logger.debug("export filter:" + filter);
		}

		ArrayList<String> filters = new ArrayList<String>();
		if (!StringManager.isNullOrEmpty(filter)) {

			filters.add(filter);
		}

		String defaultFilter = this.getDefaultFilter();
		if (!StringManager.isNullOrEmpty(defaultFilter)) {

			filters.add(defaultFilter);
		}

		String datagridFilter = this.context.getDatagrid().getFilter();
		logger.debug("query datagridFilter:" + datagridFilter);
		if (!StringManager.isNullOrEmpty(datagridFilter)) {

			filters.add(datagridFilter);
		}

		filter = StringManager.join(" and ", filters);
		Oql oql = new Oql();
		{
			PDatagrid dgp = this.context.getDatagrid();
			oql.setSelects(dgp.getSelectedFields());
			oql.setEntityId(this.context.getEntityId());
			oql.setFilter(filter);
			oql.setOrderby(order);
		}

		if (StringManager.isNullOrEmpty(sort)) {

			oql.setOrderby(this.context.getDatagrid().getOrderby());
		} else {

			oql.setOrderby(sort + " " + order);
		}

		Pagination pag = doQuery(oql);
		ExcelExportor expotor = new ExcelExportor();
		expotor.setDatagrid(this.context.getDatagrid());
		expotor.export(pag.getRows());
	}

	public Pagination doQuery(Oql oql) {

		Class<?> serviceType = this.getServiceType();
		IPersistableService<?> service = (IPersistableService<?>) ServiceFactory.create(serviceType);
		Pagination pag = service.queryPaging(oql);
		return pag;
	}

	private Class<?> getServiceType() {

		Class<?> serviceType = null;
		if (StringManager.isNullOrEmpty(this.context.getService())) {

			serviceType = IPersistableService.class;
		} else {

			serviceType = ReflectManager.getType(this.context.getService());
			if (serviceType == null) {

				serviceType = IPersistableService.class;
			}
		}

		return serviceType;
	}

	public boolean delete(String ids) {

		this.getService();
		String[] arr = ids.split("_");
		String entityId = this.context.getResourceNode().getEntityId();
		for (String id : arr) {

			Entity entity = (Entity) ReflectManager.newInstance(entityId);
			{
				entity.toDeleted();
				entity.setId(Long.valueOf(id));
			}
			this.service.save(entity);
		}
		return true;
	}

	public boolean disabled(String ids, boolean isDisabled) {

		String entityId = this.context.getResourceNode().getEntityId();
		Oql oql = new Oql();
		{
			oql.setEntityId(entityId);
			oql.setSelects("*");
			oql.setFilter("ID IN ('" + ids.replaceAll("_", "','") + "')");
		}

		List<?> items = this.getService().queryList(oql);
		for (Object row : items) {

			IPersistable p = (IPersistable) row;
			p.toPersist();
			p.set("disabled", isDisabled);
			this.service.save(p);
		}
		return true;
	}

	public boolean edit(String ids) {

		return true;
	}

	public boolean audit(String ids) {

		return true;
	}

	public boolean importExcel(List<IPersistable> entities) {

		try {
			if (!this.validateImportExcel(entities)) {

				return false;
			}
			this.getService().saves(entities);
			return true;
		} catch (Exception e) {

			throw new ExcelImportException(ExceptionUtil.extractMsg(e));
		}

	}

	public boolean validateImportExcel(List<IPersistable> list) {

		return true;
	}

	/* 下载excel模板 */
	public void downTemplate() throws Exception {

		String fields = this.getExcelFields();
		Oql oql = new Oql();
		{
			oql.setSelects(fields);
			oql.setEntityId(this.context.getEntityId());
			oql.setFilter("1<>1");
		}
		Pagination pag = doQuery(oql);
		ExcelExportor expotor = new ExcelExportor();
		expotor.setImport(true);
		expotor.setDatagrid(this.context.getDatagrid());
		expotor.export(pag.getRows());
	}

	/* 得到excel模板相关字段 */
	private String getExcelFields() {

		String excelFields = "";
		try {

			this.getService();
			Integer datagridId = this.context.getDatagridId().intValue();
			String cmdText = "select * from ui_pa_datagrid_column where is_import=1 and datagrid_id=" + datagridId;
			DataTable datatable = this.service.executeTable(cmdText, null);
			Iterator<Row> rows = datatable.iterator();
			while (rows.hasNext()) {

				Row row = rows.next();
				excelFields += "," + row.getString("property_name");
			}
			excelFields = StringManager.isNullOrEmpty(excelFields) ? excelFields : excelFields.replaceFirst(",", "");
		} catch (Exception e) {

			throw new BusinessException(e.getMessage());
		}
		return excelFields;
	}

	/*
	 * ASP端： 网格的默认条件有几个： 1.在部件的Filter字段设置的条件 2.控制器DefaultFilter设置的条件（一般是子类重写）
	 * 3.DatagridProject的Memoto字段设置的条件（将来要被废弃掉）
	 * 
	 * 上面三个条件合并放到DatagridPart的filter字段上。
	 * 
	 * Js端： 1.defaultFilter存放的是上述的filter属性 2.queryUrl存放的是数据查询的Url，不处理Filter
	 * 3.客户端查询的时候，会重新组织filter，defaultFilter是一项，同时还考虑客户端扩展传递过来的条件
	 * 4.客户端查询会把queryUrl和客户端组织的查询条件形成一个新的Url
	 */
	@Override
	protected void render() {

		this.urlFilter = this.getRequest("filter");
		if (StringManager.isNullOrEmpty(this.urlFilter)) {

			this.urlFilter = "";
		} else {

			try {
				this.urlFilter = URLDecoder.decode(this.urlFilter, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		this.pdatagrid = this.context.getDatagrid();
		if (this.pdatagrid != null) {

			this.pqueryProject = this.pdatagrid.getQueryProject();
		}

		this.renderQueryProject();
		this.initToolbar();
		super.render();
		this.renderDatagrid();
	}

	protected void renderQueryProject() {

		if (this.pqueryProject == null) {

			return;
		}

		QueryPanel queryPanel = new QueryPanel();
		{
			queryPanel.setQueryProject(this.pqueryProject);
			queryPanel.setDatagridId(this.getJsInstance());
			queryPanel.setPdatagrid(pdatagrid);
		}
		this.controls.add(queryPanel);
	}

	protected void renderDatagrid() {

		this.setId("datagrid");
		this.setClassName("page-content");
		datagrid.pageSize = this.pdatagrid.getPageSize();
		datagrid.pageList = "[10,15,20,25,30,50,100,200,500,1000]";
		datagrid.pagination = this.pdatagrid.isPagination();
		datagrid.fitColumns = this.pdatagrid.isFit();
		datagrid.selectOnCheck = true;
		datagrid.checkOnSelect = true;
		datagrid.remoteSort = true;
		datagrid.nowrap = pdatagrid.isNowrap();
		datagrid.striped = true;
		datagrid.rownumbers = true;
		datagrid.singleSelect = this.pdatagrid.isSingleSelect();

		ArrayList<String> ss = new ArrayList<String>();
		if (!StringManager.isNullOrEmpty(context.getFilter())) {

			ss.add(context.getFilter());
		}

		// 从Url中传递过来的过滤条件
		String urlFilter = getRequest("filter");
		if (!StringManager.isNullOrEmpty(urlFilter)) {

			try {

				urlFilter = urlFilter.replace('_', '=');
				urlFilter = URLDecoder.decode(urlFilter, "UTF-8");
				logger.debug("renderDatagrid urlFilter:" + urlFilter);
			} catch (UnsupportedEncodingException e) {

				e.printStackTrace();
			}
			ss.add(urlFilter);
		}

		this.filter = StringManager.join(" AND ", ss);
		if (pdatagrid.isAutoQuery()) {

			if (StringManager.isNullOrEmpty(filter)) {

				datagrid.url = this.getQueryUrl();
			} else {

				datagrid.url = this.getQueryUrl() + "&filter=" + filter;
			}
		}
	}

	public String getQueryUrl() {

		if (StringManager.isNullOrEmpty(this.queryUrl)) {

			queryUrl = "/panda/rest/service?vid=" + this.context.getId() + "&method=query";
			queryUrl = UrlHelper.getUrl(queryUrl);
		}
		return queryUrl;
	}

	public String getDefaultFilter() {

		return null;
	}

	public String getFormUrl() {

		return this.getContext().getUrl();
	}

	@Override
	protected void addJscript() {

		super.addJscript();
		String queryUrl = this.getQueryUrl();
		if (StringManager.isNullOrEmpty(queryUrl)) {

			queryUrl = "null";
		} else {

			queryUrl = "\"" + queryUrl + "\"";
		}

		String defaultFilter = this.getDefaultFilter();
		if (StringManager.isNullOrEmpty(defaultFilter)) {

			defaultFilter = "null";
		} else {

			defaultFilter = "\"" + defaultFilter + "\"";
		}

		String formUrl = this.getFormUrl();
		if (StringManager.isNullOrEmpty(formUrl)) {

			formUrl = "null";
		} else {

			formUrl = "\"" + formUrl + "\"";
		}

		this.addJscript("        " + getJsInstance() + ".context.queryUrl=" + queryUrl + ";", JscriptType.Header);
		this.addJscript("        " + getJsInstance() + ".context.defaultFilter=" + defaultFilter + ";", JscriptType.Header);
		this.addJscript("        " + getJsInstance() + ".context.urlFilter=\"" + urlFilter + "\";", JscriptType.Header);
		this.addJscript("        " + getJsInstance() + ".context.formUrl=" + formUrl + ";", JscriptType.Header);
		this.addJscript("        " + getJsInstance() + ".context.openMode=\"" + this.context.getOpenMode().getValue() + "\";", JscriptType.Header);
		this.addJscript("        " + getJsInstance() + ".context.windowWidth=\"" + this.context.getWindowWidth() + "\";", JscriptType.Header);
		this.addJscript("        " + getJsInstance() + ".context.windowHeight=\"" + this.context.getWindowHeight() + "\";", JscriptType.Header);
//		this.addJscript("        " + getJsInstance() + ".context.datagridId=\"" + this.context.getDatagridId() + "\";", JscriptType.Header);
		this.addJscript("        " + getJsInstance() + ".context.queryprojectId=\"" + this.context.getDatagrid().getQueryProjectId() + "\";",
				JscriptType.Header);
		this.addJscript("        " + getJsInstance() + ".context.lazy=" +  this.context.getDatagrid().getLazy() + ";", JscriptType.Header);
		if (this.pdatagrid.getStylers().size() > 0) {

			this.addJscript("        " + getJsInstance() + ".rowStyler = function(index, row) {", JscriptType.Header);

			for (PRowStyler styler : this.pdatagrid.getStylers()) {
				this.addJscript("            if( " + styler.getRowCondition() + " ){", JscriptType.Header);
				this.addJscript("                return '" + styler.getRowStyle() + "';", JscriptType.Header);
				this.addJscript("            }", JscriptType.Header);
			}

			// this.addJscript("            alert('dd');", JscriptType.Header);
			// this.addJscript("            return 'background-color:#f6f6f6;color:#000000';",
			// JscriptType.Header);
			this.addJscript("            return 'color:#000000';", JscriptType.Header);
			this.addJscript("        }", JscriptType.Header);

			datagrid.rowStyler = "function(index, row) { return " + getJsInstance() + ".rowStyler(index, row);}";
		}
	}
}
