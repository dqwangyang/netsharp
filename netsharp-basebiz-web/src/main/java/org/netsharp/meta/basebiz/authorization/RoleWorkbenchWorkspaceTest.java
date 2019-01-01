package org.netsharp.meta.basebiz.authorization;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.RoleWorkbench;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.OpenMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.resourcenode.entity.ResourceNode;

public class RoleWorkbenchWorkspaceTest extends WorkspaceCreationBase {

	@Before
	public void setup() {

		entity = RoleWorkbench.class;
		urlList = "/system/workbench/list";
		urlForm = "/system/workbench/from";
		listPartName = formPartName = "工作台配置";
		meta = MtableManager.getMtable(entity);
		resourceNodeCode = RoleWorkbench.class.getSimpleName();
		openWindowWidth = 650;
		openWindowHeight = 550;
		formOpenMode = OpenMode.WINDOW;
	}

	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.toNew();
			datagrid.setResourceNode(node);
			datagrid.setToolbar("panda/datagrid/row/edit");
			datagrid.setName("工作台列表");
		}
		addColumn(datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 100);
		addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "path", "路径", ControlTypes.TEXT_BOX, 150);
		addColumn(datagrid, "memoto", "备注", ControlTypes.TEXT_BOX, 300);
		return datagrid;
	}

	protected PForm createForm(ResourceNode node) {

		PForm form = super.createForm(node);
		form.setColumnCount(1);
		addFormField(form, "path", "路径", null, ControlTypes.TEXT_BOX, true);
		addFormField(form, "name", "名称", null, ControlTypes.TEXT_BOX, true);
		PFormField filed = addFormField(form, "memoto", "备注", null, ControlTypes.TEXTAREA, false);
		{

			filed.setFullColumn(true);
			filed.setHeight(100);
			filed.setWidth(300);
		}
		return form;
	}

	@Override
	protected void doOperation() {

		ResourceNode node = this.getResourceNode();
		operationService.addOperation(node, OperationTypes.view);
		operationService.addOperation(node, OperationTypes.add);
		operationService.addOperation(node, OperationTypes.update);
		operationService.addOperation(node, OperationTypes.delete);
	}
}
