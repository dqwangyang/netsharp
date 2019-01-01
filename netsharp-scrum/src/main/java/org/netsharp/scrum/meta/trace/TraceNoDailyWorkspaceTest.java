package org.netsharp.scrum.meta.trace;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.Employee;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.OrderbyMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.scrum.entity.TraceNoDaily;

public class TraceNoDailyWorkspaceTest extends WorkspaceCreationBase {
	
	@Override
	@Before
	public void setup() {		
		// 在子类中重定义
		urlList = "/scrum/notracedaily/list";
		urlForm = "/scrum/notracedaily/form";
		
		entity = TraceNoDaily.class;
		this.resourceNodeCode="notracedaily";
		meta = MtableManager.getMtable(entity);
		listPartName =formPartName= "每日未跟进";
	
	} 

	@Override
	@Test
	public void run() {
		this.createListWorkspace();
		this.createFormWorkspace();
	}

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {
		
		PDatagrid datagrid = super.createDatagrid(node);
		datagrid.setToolbar("panda/datagrid/row/edit");
		datagrid.setPageSize(25);
		
		addColumn(datagrid, "date", "跟进日期", ControlTypes.DATE_BOX, 150);
		addColumn(datagrid, "tracor.name", "未跟进人", ControlTypes.TEXT_BOX, 150);
		addColumn(datagrid, "organization.name", "所属部门", ControlTypes.TEXT_BOX, 150);
		PDatagridColumn column = addColumn(datagrid, "createTime", "跟进时间", ControlTypes.DATETIME_BOX, 150);
		
		column.setOrderbyMode(OrderbyMode.DESC);
		
		return datagrid;
	}
	
	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = new PQueryProject();
		{
			queryProject.toNew();
			queryProject.setName("项目跟进");
			queryProject.setResourceNode(node);
		}

		addQueryItem(queryProject, "date", "跟进日期", ControlTypes.DATE_BOX);
		addRefrenceQueryItem(queryProject, "tracor.name", "未跟进人", Employee.class.getSimpleName());
		addRefrenceQueryItem(queryProject, "tracor.path_name", "所属部门", "Organization-Department");
		
		return queryProject;
	}
	
	public void doOperation() {

		ResourceNode node = resourceService.byCode(resourceNodeCode);
		operationService.addOperation(node, OperationTypes.view);
	}
}
