package org.netsharp.panda.ui;

import org.junit.Assert;
import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.base.IPWorkspaceService;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.entity.PWorkspace;

public class CompositeOneTest {
	
	String url = "/salesorder/list";
	IPWorkspaceService workspaceService=ServiceFactory.create(IPWorkspaceService.class);
	
    @Test
    public void run(){
    	
    	PWorkspace workspace = workspaceService.byUrl(this.url);
    	
    	PDatagrid datagrid = workspace.getParts().get(0).getDatagrid();
    	
    	Assert.assertNotNull(datagrid.getQueryProjectId());
    	
    	PQueryProject queryProject = datagrid.getQueryProject();
    	Assert.assertNotNull(queryProject);
    }
}
