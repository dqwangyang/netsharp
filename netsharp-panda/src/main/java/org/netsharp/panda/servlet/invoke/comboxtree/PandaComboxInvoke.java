package org.netsharp.panda.servlet.invoke.comboxtree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.netsharp.base.IPersistableService;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.panda.base.IPReferenceService;
import org.netsharp.panda.controls.utility.UrlHelper;
import org.netsharp.panda.core.HttpContext;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PReference;
import org.netsharp.panda.json.TreeResultJson;
import org.netsharp.panda.servlet.filters.LogFilter;
import org.netsharp.panda.servlet.invoke.PandaContext;
import org.netsharp.panda.servlet.invoke.PandaInvoke;
import org.netsharp.panda.servlet.invoke.PandaInvokeType;
import org.netsharp.panda.session.PandaSessionPersister;
import org.netsharp.util.JsonManage;
import org.netsharp.util.StringManager;

public class PandaComboxInvoke extends PandaInvoke {

	private HttpContext context;
	private PReference pr;

	public PandaComboxInvoke() {

		this.filters.add(new LogFilter(PandaInvokeType.comboxtree));

	}

	public void doProcessRequest(PandaContext pandaContext) {

		this.context = HttpContext.getCurrent();

		String id = context.getRequest().getParameter("id2");

		// id="9";
		IPReferenceService referenceService = ServiceFactory.create(IPReferenceService.class);
		pr = referenceService.byId(id);
		
		//安全验证
		if(pandaContext.getIsAuthorization() && !PandaSessionPersister.logined()) {
			
			this.renderAuthorization();
			
			return;
		} 

		// ui条件
		String filter = context.getRequest().getParameter("filter");
		List<String> ss = new ArrayList<String>();
		if (!StringManager.isNullOrEmpty(filter) && !StringManager.equals("null", filter, true)) {
			filter = UrlHelper.decode(filter);
			filter = filter.replaceAll("----", "'");
			filter = filter.replaceAll("____", "=");
			ss.add(filter);
		}

		// 参照配置条件
		if (!StringManager.isNullOrEmpty(pr.getFilter())) {
			ss.add(pr.getFilter());
		}

		// 父节ID
		String parentId = context.getRequest().getParameter("parentId");

		// 处理异步加载
		if (!StringManager.isNullOrEmpty(parentId) && !StringManager.equals("null", parentId, true)) {

			ss.add("parent_id=" + parentId);
		} else {

			ss.add("(parent_id =0 or parent_id is null)");
		}

		Oql oql = new Oql();
		{
			oql.setEntityId(pr.getEntityId());
			oql.setSelects("id,isLeaf," + pr.getIntelligentFields());

			if (ss.size() > 0) {
				oql.setFilter(StringManager.join(" AND ", ss));
			}
		}

		// 排序
		if (context.getRequest().getParameter("order") != null) {
			String orderby = context.getRequest().getParameter("order") + " "
					+ context.getRequest().getParameter("sort").toUpperCase();
			oql.setOrderby(orderby);
		}

		IPersistableService<?> service = ServiceFactory.create(pr.getService());

		List<?> list = service.queryList(oql);
		this.render(list, oql);
	}

	private void render(List<?> list, Oql oql) {

		TreeResultJson result = new TreeResultJson(list, oql.getEntityId());
		Object obj = result.parse();
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
}
