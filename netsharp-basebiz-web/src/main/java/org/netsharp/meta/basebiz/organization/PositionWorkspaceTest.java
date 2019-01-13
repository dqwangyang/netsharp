package org.netsharp.meta.basebiz.organization;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.base.IOperationTypeService;
import org.netsharp.organization.controller.position.OperationPositionPart;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.OperationType;
import org.netsharp.organization.entity.Position;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DockType;
import org.netsharp.panda.dic.OpenMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PPart;
import org.netsharp.panda.entity.PWorkspace;
import org.netsharp.panda.plugin.base.IPToolbarService;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.resourcenode.entity.ResourceNode;

public class PositionWorkspaceTest extends WorkspaceCreationBase {

	String urlPermissionForm = "/position/permission/form";

	@Before
	@Override
	public void setup() {

		urlList = "/system/position/list";
		urlForm = "/system/position/form";
		entity = Position.class;
		meta = MtableManager.getMtable(entity);
		openWindowWidth = 450;
		openWindowHeight = 300;
		formOpenMode = OpenMode.WINDOW;
		resourceNodeCode = entity.getSimpleName();
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		this.listToolbarPath = "panda/position/list/edit";
		this.listPartImportJs = "/panda-bizbase/position/position-list-part.js";
		this.listPartJsController = OperationPositionPart.class.getName();
		this.listPartJsController = OperationPositionPart.class.getName();
	}

	@Test
	public void createToolbar() {

		ResourceNode node = resourceService.byCode(resourceNodeCode);

		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setBasePath("panda/datagrid/edit");
			toolbar.setPath("panda/position/list/edit");
			toolbar.setName("职务授权");
			toolbar.setResourceNode(node);
		}

		PToolbarItem item = null;
		item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("doAuthorization");
			item.setName("授权");
			item.setCommand("{controller}.doAuthorization();");
			item.setOperationTypeId(-1L);
			item.setSeq(9999);
			item.setIcon("fa fa-user-o");
			toolbar.getItems().add(item);
		}

		IPToolbarService toolbarService = ServiceFactory.create(IPToolbarService.class);
		toolbarService.save(toolbar);
	}

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		datagrid.setToolbar("panda/datagrid/row/edit");

		addColumn(datagrid, "creator", "操作", ControlTypes.OPERATION_COLUMN, 100);
		addColumn(datagrid, "id", "id", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "code", "编码", ControlTypes.TEXT_BOX, 120);
		addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 120);
		addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATETIME_BOX, 130);
		return datagrid;
	}

	@Override
	protected PForm createForm(ResourceNode node) {

		PForm form = new PForm(node, this.formPartName);
		form.setColumnCount(1);

		addFormField(form, "code", "编码", null, ControlTypes.TEXT_BOX, true);
		addFormField(form, "name", "名称", null, ControlTypes.TEXT_BOX, true);

		return form;
	}

	public void createPermissionWorkspace() {
		ResourceNode node = resourceService.byCode(Position.class.getSimpleName());
		IOperationTypeService operationTypeService = ServiceFactory.create(IOperationTypeService.class);
		OperationType operationType = operationTypeService.byCode(OperationTypes.view);

		PWorkspace workspace = new PWorkspace();
		{
			workspace.toNew();
			workspace.setResourceNode(node);
			workspace.setOperationType(operationType);
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
			part.setImports("/panda-bizbase/position/position.authorization.js");
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
		//operationService.addOperation(node,OperationTypes.delete);
	}
}
