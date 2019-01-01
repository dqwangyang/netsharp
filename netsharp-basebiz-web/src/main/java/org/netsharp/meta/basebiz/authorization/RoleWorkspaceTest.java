package org.netsharp.meta.basebiz.authorization;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.controller.RoleListPart;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.OperationType;
import org.netsharp.organization.entity.Role;
import org.netsharp.organization.entity.RoleGroup;
import org.netsharp.organization.entity.RoleWorkbench;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.core.PandaException;
import org.netsharp.panda.dic.OpenMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.plugin.dic.ToolbarType;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.resourcenode.entity.ResourceNode;

public class RoleWorkspaceTest extends WorkspaceCreationBase {

	@Before
	public void setup() {

		entity = Role.class;
		urlList = "/system/role/list";
		urlForm = "/system/role/from";
		listPartName = formPartName = "角色管理";
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = Role.class.getSimpleName();
		openWindowWidth = 650;
		openWindowHeight = 550;
		formOpenMode = OpenMode.WINDOW;

		listPartJsController = RoleListPart.class.getName();
		listPartImportJs = "/panda-bizbase/role/org-role-list-part.js";
	}

	@Test
	public void datagrid_row_edit() {

		ResourceNode node = resourceService.byCode(resourceNodeCode);
		OperationType otView = operationTypeService.byCode(OperationTypes.view);

		if (otView == null) {
			throw new PandaException("操作类型不能为空");
		}

		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setBasePath("panda/datagrid/row/edit");
			toolbar.setPath("sytem/role/row/edit");
			toolbar.setName("表格操作");
			toolbar.setResourceNode(node);
			toolbar.setToolbarType(ToolbarType.BASE);
		}

		PToolbarItem item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("accredit");
			item.setIcon("fa fa-file-text-o");
			item.setName("授权");
			item.setOperationType(otView);
			item.setSeq(10);
			item.setCommand("{controller}.accredit()");
			toolbar.getItems().add(item);
		}

		toolbarService.save(toolbar);
	}

	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.toNew();
			datagrid.setResourceNode(node);
			datagrid.setToolbar("sytem/role/row/edit");
			datagrid.setName("角色列表");
		}
		addColumn(datagrid, "creator", "操作", ControlTypes.OPERATION_COLUMN, 100);
		addColumn(datagrid, "id", "id", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "roleGroup.name", "分组", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "code", "编码", ControlTypes.TEXT_BOX, 150);
		addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "workbench.name", "工作台", ControlTypes.TEXT_BOX, 150);
		addColumn(datagrid, "memoto", "备注", ControlTypes.TEXT_BOX, 300);
		addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATETIME_BOX, 130);
		return datagrid;
	}

	protected PForm createForm(ResourceNode node) {

		PForm form = super.createForm(node);
		form.setColumnCount(1);
		PFormField field = addFormFieldRefrence(form, "roleGroup.name", "分组", null, RoleGroup.class.getSimpleName(),
				true, false);
		{

			field.setControlType(ControlTypes.COMBOTREE_BOX);
		}

		addFormField(form, "code", "编码", null, ControlTypes.TEXT_BOX, true);
		addFormField(form, "name", "名称", null, ControlTypes.TEXT_BOX, true);
		addFormFieldRefrence(form, "workbench.name", "工作台", null, RoleWorkbench.class.getSimpleName(), false, false);
		PFormField filed = addFormField(form, "memoto", "备注", null, ControlTypes.TEXTAREA, false);
		{

			filed.setFullColumn(true);
			filed.setHeight(100);
			filed.setWidth(300);
		}
		return form;
	}

	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "code", "编码", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "name", "姓名", ControlTypes.TEXT_BOX);
		addRefrenceQueryItem(queryProject, "roleGroup.name", "分组", RoleGroup.class.getSimpleName());
		return queryProject;
	}

	@Override
	protected void doOperation() {

		ResourceNode node = this.getResourceNode();
		operationService.addOperation(node, OperationTypes.view);
		operationService.addOperation(node, OperationTypes.add);
		operationService.addOperation(node, OperationTypes.update);
	}
}
