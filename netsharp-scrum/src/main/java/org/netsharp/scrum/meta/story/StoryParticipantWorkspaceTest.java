package org.netsharp.scrum.meta.story;

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
import org.netsharp.scrum.entity.Story;
import org.netsharp.scrum.entity.StoryParticipant;

public class StoryParticipantWorkspaceTest extends WorkspaceCreationBase {
	
	@Override
	@Before
	public void setup() {		
		// 在子类中重定义
		urlList = "/scrum/participant/list";
//		urlForm = "/story/participant/form";
		
		entity = StoryParticipant.class;
		meta = MtableManager.getMtable(entity);
		listPartName =formPartName= "任务参与情况";
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
		datagrid.setPageSize(25);
		datagrid.setToolbar("panda/datagrid/row/edit");
		
		addColumn(datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 100,true);
		addColumn(datagrid, "story.name", "任务", ControlTypes.TEXT_BOX, 200, true);
		addColumn(datagrid, "participant.name", "参与者", ControlTypes.TEXT_BOX, 100, true);
		addColumn(datagrid, "estimateHours", "估时", ControlTypes.TEXT_BOX, 100, true);
		addColumn(datagrid, "story.status", "任务状态", ControlTypes.TEXT_BOX, 100, true);
		PDatagridColumn column = addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATE_BOX, 150);
		column.setOrderbyMode(OrderbyMode.DESC);
		
		return datagrid;
	}
	
	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = new PQueryProject();
		{
			queryProject.toNew();
			queryProject.setName("迭代");
			queryProject.setResourceNode(node);
		}
		addRefrenceQueryItem(queryProject, "story.name", "任务", Story.class.getSimpleName());
		addRefrenceQueryItem(queryProject, "participant.name", "参与者", Employee.class.getSimpleName());
		addQueryItem(queryProject, "createTime", "跟进时间", ControlTypes.TEXT_BOX);
		return queryProject;
	}
	
	protected void doOperation() {

		ResourceNode node = this.getResourceNode();
		operationService.addOperation(node,OperationTypes.view);
	}
}
