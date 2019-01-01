package org.netsharp.panda.servlet.invoke.reference;

import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.netsharp.base.IPersistableService;
import org.netsharp.base.IReferenceFilterBuilder;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.core.Paging;
import org.netsharp.core.annotations.Column;
import org.netsharp.panda.base.IPReferenceService;
import org.netsharp.panda.commerce.EasyuiDatagridResult;
import org.netsharp.panda.controls.utility.UrlHelper;
import org.netsharp.panda.core.HttpContext;
import org.netsharp.panda.core.PandaException;
import org.netsharp.panda.core.comunication.IRequest;
import org.netsharp.panda.dic.IntelligentMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PReference;
import org.netsharp.panda.json.DatagridResultJson;
import org.netsharp.panda.servlet.filters.LogFilter;
import org.netsharp.panda.servlet.invoke.PandaContext;
import org.netsharp.panda.servlet.invoke.PandaInvoke;
import org.netsharp.panda.servlet.invoke.PandaInvokeType;
import org.netsharp.panda.session.PandaSessionPersister;
import org.netsharp.util.JsonManage;
import org.netsharp.util.ReflectManager;
import org.netsharp.util.StringManager;

/// <summary>
/// 参照数据源
/// </summary>
public class PandaReferneceInvokde extends PandaInvoke {

	private HttpContext context;
	private PReference pr;

	public PandaReferneceInvokde() {
		this.filters.add(new LogFilter(PandaInvokeType.reference));
	}

	public void doProcessRequest(PandaContext pandaContext) {

		this.context = HttpContext.getCurrent();
		IRequest request = context.getRequest();
		String id = request.getParameter("id");
		IPReferenceService referenceService = ServiceFactory.create(IPReferenceService.class);
		if (StringManager.isNullOrEmpty(id)) {
			String code = request.getParameter("code");
			//没有code的场景
			pr = referenceService.byCode(code);
		} else {
			pr = referenceService.byId(id);
		}
		
		//安全验证
		if(pandaContext.getIsAuthorization() && !PandaSessionPersister.logined()) {
			this.renderAuthorization();
			return;
		} 

		// ui条件
		String filter = request.getParameter("filter");
		List<String> ss = new ArrayList<String>();
		if (!StringManager.isNullOrEmpty(filter) && !StringManager.equals("null", filter, true)) {

			filter = UrlHelper.decode(filter);
			filter = filter.replaceAll("----", "'");
			filter = filter.replaceAll("____", "=");// QueryControlFactory.referenceBox
			ss.add(filter);
		}

		// 参照配置条件
		if (!StringManager.isNullOrEmpty(pr.getFilter())) {

			ss.add(pr.getFilter());
		}

		// 参照列表配置条件
		if (!StringManager.isNullOrEmpty(pr.getDataGrid().getFilter())) {

			ss.add(pr.getDataGrid().getFilter());
		}

		// 模糊查询条件
		String intelligentFilter = getIntelligentFilter();
		if (!StringManager.isNullOrEmpty(intelligentFilter)) {

			ss.add(intelligentFilter);
		}

		// 运行时查询条件构造IReferenceFilterBuilder
		if (!StringManager.isNullOrEmpty(pr.getFilterBuilder())) {

			IReferenceFilterBuilder filterBuilder = (IReferenceFilterBuilder) ReflectManager
					.newInstance(pr.getFilterBuilder());
			String builderFilter = filterBuilder.builderFilter();
			if (!StringManager.isNullOrEmpty(builderFilter)) {

				ss.add(builderFilter);
			}
		}

		String entityId = pr.getDataGrid().getEntityId();
		if (StringManager.isNullOrEmpty(entityId)) {
			throw new PandaException(
					"参照列表的entityId为空,参照名称：" + pr.getName() + ",参照id:" + pr.getId() + "，列表id:" + pr.getDatagridId());
		}

		// order by
		List<String> orderbys = new ArrayList<String>();
		if (!StringManager.isNullOrEmpty(pr.getOrderby())) {
			orderbys.add(pr.getOrderby());
		}
		if (!StringManager.isNullOrEmpty(pr.getDataGrid().getOrderby())) {
			orderbys.add(pr.getDataGrid().getOrderby());
		}

		Oql oql = new Oql();
		{
			oql.setEntityId(entityId);
			oql.setSelects(pr.getDataGrid().getSelectedFields());
			if (ss.size() > 0) {

				oql.setFilter(StringManager.join(" AND ", ss));
			}

			oql.setOrderby(StringManager.join(",", orderbys));
		}

		// 排序
		if (request.getParameter("order") != null) {

			String orderby = request.getParameter("order") + " " + request.getParameter("sort").toUpperCase();
			oql.setOrderby(orderby);
		} else if (!StringManager.isNullOrEmpty(pr.getDataGrid().getSortName())) {

			String orderby = " " + pr.getDataGrid().getSortName();
			oql.setOrderby(orderby);
		} else {

			oql.setOrderby(pr.getDataGrid().getOrderby());
		}

		// 分页
		String strPageIndex = request.getParameter("page");
		if (!StringManager.isNullOrEmpty(strPageIndex)) {

			int pageIndex = StringManager.isNullOrEmpty(strPageIndex) ? 1 : Integer.valueOf(strPageIndex).intValue();
			if (pageIndex == 0) {

				pageIndex = 1;
			}

			strPageIndex = request.getParameter("rows");
			int pageSize = StringManager.isNullOrEmpty(strPageIndex) ? 10 : Integer.valueOf(strPageIndex).intValue();
			Paging paging = new Paging();
			{
				paging.setPageNo(pageIndex);
				paging.setPageSize(pageSize);
			}
			oql.setPaging(paging);
		}

		IPersistableService<?> service = ServiceFactory.create(pr.getService());
		List<?> list = service.queryList(oql);
		this.render(list, oql);
	}

	private void render(List<?> list, Oql oql) {

		EasyuiDatagridResult result = new EasyuiDatagridResult();
		{
			result.setRows(list);
			result.setTotal(oql.getPaging().getTotalCount());
		}

		DatagridResultJson parser = new DatagridResultJson(result, pr.getDataGrid());
		Object obj = parser.parse();
		String json = JsonManage.serialize2(obj);
		context.getResponse().write(json);
	}
	
	private void renderAuthorization() {
		
	    PDatagrid datagrid = pr.getDataGrid();
		HashMap<String, Object> obj = new HashMap<String, Object>();
		{
			obj.put("id", -1);
		}
		
		for(int i=0;i<datagrid.getColumns().size();i++) {
			
			PDatagridColumn column = datagrid.getColumns().get(i);
			
			int x = i %2;
			if(x==0) {
				obj.put(column.getPropertyName(),"请重新登录！");
			}else {
				obj.put(column.getPropertyName(),"您已经长时间未登录！");
			}
		}
		
		HashMap<String, Object> maps = new HashMap<String, Object>();
		{
			maps.put("total", 1);
			maps.put("rows", new Object[] {obj});
		}
		
		String json = JsonManage.serialize2(maps);
		context.getResponse().write(json);
	}

	@SuppressWarnings("deprecation")
	private String getIntelligentFilter() {

		String q = context.getRequest().getParameter("q");
		if (q == null) {

			return null;
		}

		q = URLDecoder.decode(q).trim();
		String filter = this.getIntelligentFilter(q);
		return filter;
	}

	private String getIntelligentFilter(String q) {

		List<String> filters = new ArrayList<String>();
		String[] fields = pr.getIntelligentFields() == null ? null : pr.getIntelligentFields().split(",");
		if (!StringManager.isNullOrEmpty(q) && fields != null && fields.length > 0) {

			for (String field : fields) {

				if (StringManager.isNullOrEmpty(field)) {

					continue;
				}
				String matchField = "";
				field = getDbField(field, pr.getEntityId());
				if (pr.getIntelligentMode() != null) {

					if (pr.getIntelligentMode() == IntelligentMode.EQUALS) {

						matchField = field + " = '" + q + "'";
					} else if (pr.getIntelligentMode() == IntelligentMode.LEFT) {

						matchField = field + " like '" + q + "%'";
					} else if (pr.getIntelligentMode() == IntelligentMode.RIGHT) {

						matchField = field + " like '%" + q + "'";
					} else if (pr.getIntelligentMode() == IntelligentMode.LIKE) {

						matchField = field + " like '%" + q + "%'";
					}

				} else {

					matchField = field + " like '" + q + "%'";
				}
				filters.add(matchField);
			}
		}

		if (filters.size() > 0) {

			return "(" + StringManager.join(" OR ", filters) + ")";
		} else {

			return null;
		}
	}

	private String getDbField(String field, String entityId) {

		Class<?> clazz = null;
		try {

			clazz = Class.forName(entityId);
			Field f = clazz.getDeclaredField(field);
			Column annotation = f.getAnnotation(Column.class);
			if (annotation != null) {

				return annotation.name() != null && !annotation.name().equals("") ? annotation.name() : field;
			} else {

				return field;
			}
		} catch (Exception e) {

			if (clazz.getSuperclass() != null) {

				return getDbField(field, clazz.getSuperclass().getName());
			} else {

				return field;
			}
		}
	}
}
