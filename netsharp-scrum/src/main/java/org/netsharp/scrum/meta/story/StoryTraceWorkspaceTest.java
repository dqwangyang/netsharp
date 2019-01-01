package org.netsharp.scrum.meta.story;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.base.IPReferenceService;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.OpenMode;
import org.netsharp.panda.dic.OrderbyMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.entity.PReference;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.scrum.entity.Project;
import org.netsharp.scrum.entity.Story;
import org.netsharp.scrum.entity.StoryTrace;
import org.netsharp.scrum.web.StoryTraceFormPart;

public class StoryTraceWorkspaceTest extends WorkspaceCreationBase {
	
	@Override
	@Before
	public void setup() {		
		// 在子类中重定义
		urlList = "/scrum/trace/list";
		urlForm = "/scrum/trace/form";
		
		
		entity = StoryTrace.class;
		this.resourceNodeCode=entity.getSimpleName();
		meta = MtableManager.getMtable(entity);
		listPartName =formPartName= "项目跟进";
		
		this.formServiceController=StoryTraceFormPart.class.getName();
		this.formJsController=StoryTraceFormPart.class.getName();
		this.formJsImport="/addins/scrum/StoryTraceFormPart.js";
		
		formOpenMode = OpenMode.WINDOW;
		openWindowWidth = 1050;
		openWindowHeight = 500;
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
		
		addRowStyler(datagrid,"row.importance == '重要' || row.story_importance == '重要'","color:red;");
		
		addColumn(datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 100,true);
		addColumn(datagrid, "story.project.name", "项目", ControlTypes.TEXT_BOX, 150);
		addColumn(datagrid, "importance", "重要性", ControlTypes.ENUM_BOX, 100);
		addColumn(datagrid, "story.name", "任务", ControlTypes.TEXT_BOX, 150);
		addColumn(datagrid, "organization.name", "部门", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "creator", "创建人", ControlTypes.TEXT_BOX, 150);
		addColumn(datagrid, "content", "内容", ControlTypes.TEXTAREA, 500);
		PDatagridColumn column = addColumn(datagrid, "createTime", "跟进时间", ControlTypes.DATETIME_BOX, 150);
		column.setOrderbyMode(OrderbyMode.DESC);
		
		column = addColumn(datagrid, "story.importance", "重要性", ControlTypes.ENUM_BOX, 100);{
			
			column.setVisible(false);
			column.setSystem(true);
		}
		
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

		addRefrenceQueryItem(queryProject, "story.name", "任务", Story.class.getSimpleName());
		addQueryItem(queryProject, "creator", "跟进人", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "createTime", "跟进时间", ControlTypes.DATE_BOX);
		addQueryItem(queryProject, "importance", "重要性", ControlTypes.ENUM_BOX);
		addRefrenceQueryItem(queryProject, "organization.pathName", "部门", "Organization-Department");
		addRefrenceQueryItem(queryProject, "story.project.name", "项目", Project.class.getSimpleName());
		return queryProject;
	}

	@Override
	protected PForm createForm(ResourceNode node) {
		PForm form = new PForm(node, MtableManager.getMtable(entity).getName());
		form.setColumnCount(3);
		
		PFormField field = null;

		field = addFormField(form, "story.name", "任务", ControlTypes.REFERENCE_BOX, true, false);
		{
			IPReferenceService referenceService = ServiceFactory.create(IPReferenceService.class);
			PReference reference = referenceService.byCode(Story.class.getSimpleName());
			//Assert.isTrue(reference!=null);
			field.setReference(reference);
		}
		
		 addFormField(form, "creator", "跟进人", ControlTypes.TEXT_BOX, false, true);
		 
		 addFormField(form, "createTime", "跟进时间", ControlTypes.TEXT_BOX, false, true);
		 addFormField(form, "importance", "重要性", ControlTypes.ENUM_BOX, true, false);
		field = addFormField(form, "content", "内容", ControlTypes.TEXTAREA, false, false);
		field.setHeight(250);
		field.setWidth(600);
		return form;
	}
	
	public void doOperation() {

		ResourceNode node = resourceService.byCode(resourceNodeCode);
		operationService.addOperation(node, OperationTypes.view);
		operationService.addOperation(node, OperationTypes.add);
	}
}
