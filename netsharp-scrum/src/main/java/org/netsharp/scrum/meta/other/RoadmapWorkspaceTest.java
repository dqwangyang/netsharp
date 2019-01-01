package org.netsharp.scrum.meta.other;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.base.IOperationService;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DockType;
import org.netsharp.panda.dic.PartType;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PPart;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.entity.PWorkspace;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.scrum.entity.Roadmap;
import org.netsharp.scrum.entity.RoadmapDetail;

public class RoadmapWorkspaceTest extends WorkspaceCreationBase {

	@Before
	public void setup() {
		// 在子类中重定义
		entity = Roadmap.class;
		meta = MtableManager.getMtable(entity);
		meta = MtableManager.getMtable(entity);
		listPartName =formPartName= "计划路线图";
		resourceNodeCode = Roadmap.class.getSimpleName();
		
		this.urlList="/scrum/roadmap/list";
		this.urlForm="/scrum/roadmap/form";
		
		this.listPartImportJs="/addins/scrum/RoadmapListPart.js";
		this.listPartJsController="org.netsharp.scrum.web.RoadmapListPart";
	}
	
	@Override
	protected void createFormWorkspace() {

		ResourceNode node = resourceService.byCode(this.resourceNodeCode);

		PForm pform = this.createForm(node);

		PWorkspace workspace = new PWorkspace();
		{
			workspace.toNew();
			workspace.setResourceNode(node);
			workspace.setName(meta.getName());
			workspace.setUrl(urlForm);
		}

		PPart part = new PPart();
		{
			part.toNew();
			part.setCode("roadmapForm");
			part.setResourceNode(node);
			part.setPartTypeId(PartType.FORM_PART.getId());
			part.setForm(pform);
			part.setDockStyle(DockType.TOP);
			part.setToolbar("panda/form/edit");
		}
		workspace.getParts().add(part);
		this.addDetailGridPart(workspace);
		workspaceService.save(workspace);
	}
	
	@Override
	protected void addDetailGridPart(PWorkspace workspace) {

		addRoadmapDetailPart(workspace);

//		PPart part = workspace.getParts().get(0);
//		{
//			part.setCode("employee");
//			part.setStyle("height:300px;");
//			part.setDockStyle(DockType.TOP);
//		}
	}
	
	private void addRoadmapDetailPart(PWorkspace workspace) {

		ResourceNode node = resourceService.byCode(RoadmapDetail.class.getSimpleName());
		PDatagrid datagrid = this.createDetailGrid(node);
		PPart part = new PPart();
		{
			part.toNew();
			part.setName("路线图明细");
			part.setCode("details");
			part.setParentCode("roadmapForm");
			part.setRelationRole("details");
			part.setResourceNode(node);
			part.setPartTypeId(PartType.DETAIL_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
			part.setToolbar("panda/datagrid/detail");

			part.setWindowWidth(600);
			part.setWindowHeight(400);

			PForm form = this.createDetailGridForm(node);
			part.setForm(form);
		}
		workspace.getParts().add(part);

	}
	
	protected PForm createDetailGridForm(ResourceNode node) {

		PForm form = new PForm();
		form.setResourceNode(node);
		form.toNew();
		form.setColumnCount(1);
		form.setName("路线图明细");
		addFormField(form, "name", "名称", null, ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "status", "状态", null, ControlTypes.ENUM_BOX, true, false);
		addFormField(form, "date", "完成月份", null, ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "seq", "顺序", null, ControlTypes.NUMBER_BOX, true, false);
		addFormField(form, "memoto", "描述", null, ControlTypes.TEXTAREA, false, false);

		return form;
	}

	protected PDatagrid createDetailGrid(ResourceNode node) {

		PDatagrid datagrid = new PDatagrid();
		{
			datagrid.toNew();
			datagrid.setResourceNode(node);
			datagrid.setName("项目跟进");
		}


		addColumn(datagrid, "seq", "顺序", ControlTypes.NUMBER_BOX, 60, false);
		addColumn(datagrid, "memoto", "描述", ControlTypes.TEXT_BOX, 800, false);
		
		addColumn(datagrid, "updateTime", "修改日期", ControlTypes.TEXT_BOX, 100, false);
		addColumn(datagrid, "updator", "修改人", ControlTypes.TEXT_BOX, 80, false);
		addColumn(datagrid, "createTime", "创建日期", ControlTypes.TEXT_BOX, 100, false);
		addColumn(datagrid, "creator", "创建人", ControlTypes.TEXT_BOX, 80, false);

		return datagrid;
	}

	@Override
	protected PForm createForm(ResourceNode node) {
		
		PForm form = new PForm(node, "项目列表");
		form.setColumnCount(3);
		
		String groupName = null;
		// 基本信息
		addFormField(form, "code", "编码",groupName, ControlTypes.TEXT_BOX, false, true);	
		addFormField(form, "name", "名称",groupName, ControlTypes.TEXT_BOX, false);

        // 操作信息
		addFormField(form, "creator", "创建人", groupName, ControlTypes.TEXT_BOX, false, true);
		addFormField(form, "createTime", "创建时间", groupName, ControlTypes.DATE_BOX, false, true);
		addFormField(form, "updator", "最后修改人", groupName, ControlTypes.TEXT_BOX, false, true);
		addFormField(form, "updateTime", "最后修改时间",groupName, ControlTypes.DATE_BOX, false, true);

		return form;
	}

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {
		PDatagrid datagrid = super.createDatagrid(node);
		datagrid.setToolbar("panda/datagrid/row/edit");
		addColumn(datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 100,true);
		// 基本信息
		addColumn(datagrid, "code", "编码", ControlTypes.TEXT_BOX, 100, true);
		addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 120, true);
		
		// 操作信息
		addColumn(datagrid, "creator", "创建人", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATE_BOX, 120, false);
		addColumn(datagrid, "updator", "最后修改人", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "updateTime", "最后修改时间", ControlTypes.DATE_BOX, 120, false);
		
		return datagrid;
	}

	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = new PQueryProject();
		{
			queryProject.toNew();
			queryProject.setName(meta.getName());
			queryProject.setResourceNode(node);
		}
		queryProject.setColumnCount(3);
		
		addQueryItem(queryProject, "code", "编码", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "name", "名称", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "creator", "创建人", ControlTypes.TEXT_BOX);

		return queryProject;
	}

	@Test
	public void operation() {
		
		ResourceNode node = resourceService.byCode(this.resourceNodeCode);
		IOperationService service = ServiceFactory.create(IOperationService.class);
		service.addOperation(node, OperationTypes.view);
		service.addOperation(node, OperationTypes.add);
		service.addOperation(node, OperationTypes.update);
	}
}