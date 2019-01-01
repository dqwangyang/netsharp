package org.netsharp.panda.core;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.base.IPQueryProjectService;
import org.netsharp.panda.controls.other.Body;
import org.netsharp.panda.controls.query.QueryPanel;
import org.netsharp.panda.entity.PQueryProject;

public class AdvancedQueryProjectWorkspace extends Workspace{

	IPQueryProjectService queryService = ServiceFactory.create(IPQueryProjectService.class);
	
	@Override
	protected void doInitialize() {
		
		String id = getRequest("id");
		String datagridId = getRequest("datagridId");
		Body layout = this.body;{
			layout.setClassName("easyui-layout");
			layout.setStyle("padding-left:25px;");
		}
		
		Integer queryId = Integer.parseInt(id);
		PQueryProject pqueryProject = queryService.byId(queryId);
		QueryPanel queryPanel = new QueryPanel();
		{
			queryPanel.setQueryProject(pqueryProject);
			queryPanel.setDatagridId(datagridId);
		}
		layout.getControls().add(queryPanel);
	}
	
	
	protected void addJscript() {
		
		this.addJscript("        var queryController = new org.netsharp.panda.QueryController();", JscriptType.Header);
	}
}
