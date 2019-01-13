package org.netsharp.meta.basebiz.organization;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.base.IOperationTypeService;
import org.netsharp.organization.controller.organization.OrganizationEmployeeListPart;
import org.netsharp.organization.controller.organization.OrganizationFormPart;
import org.netsharp.organization.controller.organization.OrganizationTreePart;
import org.netsharp.organization.controller.position.OperationPositionPart;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.OperationType;
import org.netsharp.organization.entity.Organization;
import org.netsharp.organization.entity.OrganizationEmployee;
import org.netsharp.organization.entity.OrganizationFunction;
import org.netsharp.organization.entity.Position;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DatagridAlign;
import org.netsharp.panda.dic.DockType;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.panda.entity.PPart;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.entity.PWorkspace;
import org.netsharp.panda.plugin.base.IPToolbarService;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.resourcenode.entity.ResourceNode;

public class OrganizationWorkspaceTest extends WorkspaceCreationBase {
	
	@Before
	public void setup() {

		urlList = "/system/organization/tree";
		urlForm = "/system/organization/form";
		formToolbarPath = "panda/organization/form/edit";
		
		formServiceController = OrganizationFormPart.class.getName();
		formJsController = OrganizationFormPart.class.getName();
		formJsImport = "/panda-bizbase/organization/org-organization-form-part.js";
		
		entity = Organization.class;
		meta = MtableManager.getMtable(entity);
		resourceNodeCode = entity.getSimpleName();
		formPartName = listPartName = meta.getName();
		this.listToolbarPath="panda/position/list/edit";
		this.listPartImportJs="/panda-bizbase/position/position-list-part.js";
		
	}
	
	@Test
	@Override
	public void run() {
		this.createToolbar();
		this.createFormToolbar();
		this.createTreeWorkspace();
		this.createFormWorkspace();
		this.createPermissionWorkspace();
	}
	
	public void createToolbar() {
		
		ResourceNode node = resourceService.byCode(resourceNodeCode);
		
		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setBasePath("panda/tree");
			toolbar.setPath("panda/tree/organization");
			toolbar.setName("组织机构");
			toolbar.setResourceNode(node);
		}

		PToolbarItem item = null;
		item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("pathCode");
			item.setName("路径");
			item.setCommand("{controller}.pathCode();");
			item.setOperationTypeId(-1L);
			item.setSeq(9998);
			item.setIcon("fa fa-recycle");
			//item.setDisabled(true);
			toolbar.getItems().add(item);
		}
		item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("doCreateWeiXin");
			item.setName("微信");
			item.setCommand("{controller}.doCreateWeiXin();");
			item.setOperationTypeId(-1L);
			item.setSeq(9999);
			item.setIcon("fa fa-weixin");
			//item.setDisabled(true);
			toolbar.getItems().add(item);
		}

		IPToolbarService toolbarService = ServiceFactory.create(IPToolbarService.class);
		toolbarService.save(toolbar);
	}
	
	public void createFormToolbar() {
		
		ResourceNode node = resourceService.byCode(resourceNodeCode);
		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setBasePath("panda/form/edit");
			toolbar.setPath(formToolbarPath);
			toolbar.setName("组织机构表单工具栏");
			toolbar.setResourceNode(node);
		}

		PToolbarItem item = null;
		item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("disabled");
			item.setName("停用");
			item.setCommand("{controller}.disabled();");
			item.setOperationTypeId(-1L);
			item.setSeq(3);
			item.setIcon("fa fa-close");
			toolbar.getItems().add(item);
		}
		IPToolbarService toolbarService = ServiceFactory.create(IPToolbarService.class);
		toolbarService.save(toolbar);
	}
	
	public void createTreeWorkspace() {

		ResourceNode node = resourceService.byCode(resourceNodeCode);
		IOperationTypeService operationTypeService = ServiceFactory.create(IOperationTypeService.class);
		OperationType operationType = operationTypeService.byCode(OperationTypes.view);

		PWorkspace workspace = new PWorkspace();
		{
			workspace.toNew();
			workspace.setResourceNode(node);
			workspace.setOperationType(operationType);
			workspace.setOperationTypeId(operationType.getId());
			workspace.setName("组织机构工作区");
			workspace.setUrl(urlList);
		}

		PPart part = new PPart();
		{
			part.toNew();
			part.setCode("OrganizationTree");
			part.setPartTypeId(org.netsharp.panda.dic.PartType.TREE_PART.getId());
			part.setDockStyle(DockType.LEFT);
			part.setStyle("width:300px;");
			part.setResourceNode(node);
			part.setToolbar("panda/tree/organization");
			part.setUrl(this.urlForm);
			//part.setAutoQuery(false);
			part.setServiceController(OrganizationTreePart.class.getName());
			part.setJsController(OrganizationTreePart.class.getName());
		}
		workspace.getParts().add(part);

		ResourceNode node2 = resourceService.byCode(OrganizationEmployee.class.getSimpleName());
		PDatagrid datagrid = this.createDatagrid(node2);
		part = new PPart();
		{
			part.toNew();
			part.setCode("employees");
			part.setParentCode("OrganizationTree");
			part.setPartTypeId(org.netsharp.panda.dic.PartType.DATAGRID_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
			part.setRelationRole("organizationId");
			part.setJsController("org.OrganizationEmployeeList.controller.ListPart");
			part.setServiceController(OrganizationEmployeeListPart.class.getName());
			part.setImports("/panda-bizbase/organization/org-organization-employee-list-part.js");
			part.setResourceNode(node2);
		}

		workspace.getParts().add(part);

		workspaceService.save(workspace);
	}

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.setSortName("employee.entryDate desc");
			datagrid.setShowTitle(true);
			datagrid.setName("员工列表");
		}
		
		PDatagridColumn column = null;
		addColumn(datagrid, "employee.name", "名称", ControlTypes.TEXT_BOX, 120, true);
		addColumn(datagrid, "employee.mobile", "手机", ControlTypes.TEXT_BOX, 120, true);
		addColumn(datagrid, "employee.email", "邮件", ControlTypes.TEXT_BOX, 200, false);
		addColumn(datagrid, "employee.nativePlace", "籍贯", ControlTypes.TEXT_BOX, 150);
		addColumn(datagrid, "employee.education", "学历", ControlTypes.TEXT_BOX, 150);
		addColumn(datagrid, "employee.entryDate", "入职日期", ControlTypes.DATE_BOX, 150, false);
		addColumn(datagrid, "employee.quitDate", "离职日期", ControlTypes.DATE_BOX, 150,false);
		addColumn(datagrid, "employee.department.name", "部门", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "employee.post.name", "岗位", ControlTypes.TEXT_BOX, 100);
		
		column = addColumn(datagrid, "updator", "最后修改人", ControlTypes.TEXT_BOX, 100, false);{
			
			column.setAlign(DatagridAlign.CENTER);
		}
		column = addColumn(datagrid, "updateTime", "最后修改时间", ControlTypes.DATETIME_BOX, 150, false);{
			
			column.setAlign(DatagridAlign.CENTER);
		}
		column = addColumn(datagrid, "creator", "创建人", ControlTypes.TEXT_BOX, 100, false);{
			
			column.setAlign(DatagridAlign.CENTER);
		}
		column = addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATETIME_BOX, 150, false);{
			
			column.setAlign(DatagridAlign.CENTER);
		}
		
		column = addColumn(datagrid, "employeeId", "employeeId", ControlTypes.DATETIME_BOX, 160, false);
		{
			column.setSystem(true);
			column.setVisible(false);
		}

		datagrid.setQueryProject(this.createQueryProject(node));
		datagrid.setFilter("employee.disabled=0");

		return datagrid;
	}

	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "employee.name", "姓名", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "employee.mobile", "手机", ControlTypes.TEXT_BOX);
		return queryProject;
	}
	
	@Override
	protected PForm createForm(ResourceNode node) {

		PForm form = new PForm(node, this.formPartName);
		{
			form.setColumnCount(1);
		}
		
		PFormField field = null;

		addFormField(form, "name", "名称", null, ControlTypes.TEXT_BOX, true);
		addFormField(form, "organizationType", "组织类型", null, ControlTypes.ENUM_BOX, true,false);
		addFormFieldRefrence(form, "organizationFunction.name", "业务类型", null,OrganizationFunction.class.getSimpleName(),false, false);
		field = addFormFieldRefrence(form, "position.name", "职务", null,Position.class.getSimpleName(),false, false);
		{
			field.setTooltip("组织类型为岗位时才使用");
		}
		addFormField(form, "pathCode", "分类码", null, ControlTypes.TEXT_BOX, false,true);
		addFormField(form, "disabled", "停用", null, ControlTypes.CHECK_BOX, false,true);
		
		addFormField(form, "updator", "最后修改人", null, ControlTypes.TEXT_BOX, false,true);
		addFormField(form, "updateTime", "最后修改时间", null, ControlTypes.DATETIME_BOX, false,true);
		addFormField(form, "creator", "创建人", null, ControlTypes.TEXT_BOX, false,true);
		addFormField(form, "createTime", "创建时间", null, ControlTypes.DATETIME_BOX, false,true);

		return form;
	}
	
	public void createPermissionWorkspace() {
		
		String urlPermissionForm = "/position/permission/form";
		
		ResourceNode node = resourceService.byCode(OrganizationEmployee.class.getSimpleName());
		IOperationTypeService operationTypeService = ServiceFactory.create(IOperationTypeService.class);
		OperationType operationType = operationTypeService.byCode(OperationTypes.view);

		PWorkspace workspace = new PWorkspace();
		{
			workspace.toNew();
			workspace.setResourceNode(node);
			workspace.setOperationType(operationType);
			workspace.setOperationTypeId(operationType.getId());
			workspace.setName("职务授权");
			workspace.setUrl(urlPermissionForm);
		}

		PPart part = new PPart();
		{
			part.toNew();
			part.setCode("Permission");
			part.setPartTypeId(org.netsharp.panda.dic.PartType.DATAGRID_PART.getId());
			part.setDockStyle(DockType.DOCUMENTHOST);
			part.setResourceNode(node);
			part.setServiceController(OperationPositionPart.class.getName());
			part.setJsController(OperationPositionPart.class.getName());
			part.setImports("/panda-bizbase/position/position-authorization-list-part.js");
		}
		workspace.getParts().add(part);
		workspaceService.save(workspace);
	}
	
	@Override
	protected void doOperation() {
		
		ResourceNode node = this.getResourceNode();
		operationService.addOperation(node,OperationTypes.view);
		operationService.addOperation(node,OperationTypes.add);
		operationService.addOperation(node,OperationTypes.update);
		operationService.addOperation(node,OperationTypes.delete);
	}
}