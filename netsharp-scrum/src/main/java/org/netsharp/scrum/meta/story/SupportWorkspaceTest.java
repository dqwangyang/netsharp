package org.netsharp.scrum.meta.story;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.base.IOperationService;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.Employee;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DatagridAlign;
import org.netsharp.panda.dic.DockType;
import org.netsharp.panda.dic.OrderbyMode;
import org.netsharp.panda.dic.PartType;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.panda.entity.PPart;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.entity.PWorkspace;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.scrum.entity.Support;
import org.netsharp.scrum.web.SupportFormPart;
import org.netsharp.util.ReflectManager;

public class SupportWorkspaceTest extends WorkspaceCreationBase {
	@Override
	@Before
	public void setup() {
		// 在子类中重定义
		urlList = "/scrum/support/list";
		urlForm = "/scrum/support/form";

		entity = Support.class;
		resourceNodeCode=entity.getSimpleName();
		meta = MtableManager.getMtable(entity);
		listPartName =formPartName= "项目支持";
	}

	@Override
	@Test
	public void run() {
		this.createListWorkspace();
		this.createFormWorkspace();
	}

	@Override
	protected void createFormWorkspace() {
		Mtable meta = MtableManager.getMtable(this.entity);
		ResourceNode node = resourceService.byCode(resourceNodeCode);

		//Assert.isTrue(node != null);

		PForm pform = this.createForm(node);

		PWorkspace workspace = new PWorkspace();
		{
			workspace.toNew();
			workspace.setResourceNode(node);
			workspace.setName(listPartName);
			workspace.setUrl(urlForm);
		}

		PPart part = new PPart();
		{
			part.toNew();
			part.setCode(ReflectManager.getFieldName(meta.getCode()));
			part.setResourceNode(node);
			part.setName("基本信息");
			part.setPartTypeId(PartType.FORM_PART.getId());
			part.setForm(pform);
			part.setDockStyle(DockType.DOCUMENTHOST);
			part.setToolbar(formToolbarPath);
			if (formServiceController != null && formServiceController.isEmpty() != true) {
				part.setServiceController(formServiceController);
			}
			part.setServiceController(SupportFormPart.class.getName());
		}
		workspace.getParts().add(part);

		this.addDetailGridPart(workspace);

		workspaceService.save(workspace);
	}

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		datagrid.setPageSize(25);
		datagrid.setToolbar("panda/datagrid/row/edit");
		addRowStyler(datagrid,"row.importance == '重要'","color:red;");
		addColumn(datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 100,true);
		addColumn(datagrid, "code", "编码", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "content", "内容", ControlTypes.TEXT_BOX, 300);
		addColumn(datagrid, "type", "支持类型", ControlTypes.ENUM_BOX, 100);
		addColumn(datagrid, "status", "状态", ControlTypes.ENUM_BOX, 100);
		addColumn(datagrid, "importance", "重要性", ControlTypes.ENUM_BOX, 100);
		addColumn(datagrid, "urgency", "紧急性", ControlTypes.ENUM_BOX, 100);
		addColumn(datagrid, "estimateHours", "估时(小时)", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "actualHours", "耗时(小时)", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "putor.name", "提出人", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "organization.name", "部门", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "owner.name", "处理人", ControlTypes.TEXT_BOX, 100);
		PDatagridColumn column = addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATE_BOX, 150);
		column.setOrderbyMode(OrderbyMode.DESC);
		addColumn(datagrid, "creator", "创建人", ControlTypes.TEXT_BOX, 150);
		return datagrid;
	}

	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = new PQueryProject();
		{
			queryProject.toNew();
			queryProject.setName("支持 ");
			queryProject.setResourceNode(node);
			queryProject.setColumnCount(10);
		}
		
		addQueryItem(queryProject, "code", "编码", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "name", "支持名称", ControlTypes.TEXT_BOX);
		addRefrenceQueryItem(queryProject, "organization.pathName", "部门", "Organization-Department");
		addRefrenceQueryItem(queryProject, "owner.name", "负责人", Employee.class.getSimpleName());
		addRefrenceQueryItem(queryProject, "putor.name", "提出人", Employee.class.getSimpleName());
		addQueryItem(queryProject, "type", "支持类型", ControlTypes.ENUM_BOX);
		addQueryItem(queryProject, "status", "项目状态", ControlTypes.ENUM_BOX);
		addQueryItem(queryProject, "importance", "重要性", ControlTypes.ENUM_BOX);
		addQueryItem(queryProject, "urgency", "紧急性", ControlTypes.ENUM_BOX);
		addQueryItem(queryProject, "creator", "创建人", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "createTime", "创建时间", ControlTypes.DATE_BOX);
		
		return queryProject;
	}

	@Override
	protected PForm createForm(ResourceNode node) {
		
		PForm form = new PForm(node, MtableManager.getMtable(entity).getName());
		form.setColumnCount(3);

		PFormField field = null;

		addFormField(form, "code", "编码", ControlTypes.TEXT_BOX, true, true);
		addFormField(form, "name", "名称", ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "status", "状态", ControlTypes.ENUM_BOX, true, false);
		field = addFormField(form, "type", "支持类型", ControlTypes.ENUM_BOX, true, false);
		field = addFormField(form, "importance", "重要性", ControlTypes.ENUM_BOX, true, false);
		field = addFormField(form, "urgency", "紧急性", ControlTypes.ENUM_BOX, true, false);
		field = addFormField(form, "estimateHours", "估时（小时）",  ControlTypes.DECIMAL_BOX, true, false);
		field = addFormField(form, "actualHours", "耗时（小时）",ControlTypes.DECIMAL_BOX, false, false);
		addFormFieldRefrence(form, "putor.name", "提出人", null, Employee.class.getSimpleName(), true, false);
		addFormFieldRefrence(form, "owner.name", "处理人", null, Employee.class.getSimpleName(), true, false);
		addFormFieldRefrence(form, "sender.name", "抄送人", null, Employee.class.getSimpleName(), false, false);
		addFormField(form, "filePath", "附件",ControlTypes.OSS_UPLOAD, false, false);

		field = addFormField(form, "content", "提出人说明", ControlTypes.TEXTAREA, false, false);
		{
			field.setHeight(200);
			field.setFullColumn(true);
		}

		field = addFormField(form, "service", "支持人说明", ControlTypes.TEXTAREA, false, false);
		{
			field.setHeight(200);
			field.setFullColumn(true);
		}

		return form;
	}
	
	@Override
	protected void addDetailGridPart(PWorkspace workspace) {

		ResourceNode node = this.resourceService.byCode("support-Attachment");
		PDatagrid datagrid = new PDatagrid(node, null);
		datagrid.setShowTitle(false);
		datagrid.setShowCheckbox(false);
		datagrid.setSingleSelect(false);
		datagrid.setReadOnly(true);

		PDatagridColumn column = null;
		column = addColumn(datagrid, "path", "操作", ControlTypes.TEXT_BOX, 80);{
			
			column.setAlign(DatagridAlign.CENTER);
			column.setFormatter("return '<a target=_blank href=\'+row.path+\' >查看</a>';");
		}
		addColumn(datagrid, "alias", "名称", ControlTypes.NUMBER_BOX, 200);
		PPart part = new PPart();
		{
			part.setCode("attachments");
			part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
			part.setRelationRole("attachments");
			part.setResourceNode(node);
			part.setName("附件");
			part.setPartTypeId(PartType.DETAIL_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
		}
		workspace.getParts().add(part);
	}

	@Test
	public void operation() {

		ResourceNode node = resourceService.byCode(this.resourceNodeCode);
		
		IOperationService service = ServiceFactory.create(IOperationService.class);
		service.addOperation(node, OperationTypes.view);
		service.addOperation(node, OperationTypes.add);
		service.addOperation(node, OperationTypes.update);
		service.addOperation(node, OperationTypes.attachment);
	}
}
