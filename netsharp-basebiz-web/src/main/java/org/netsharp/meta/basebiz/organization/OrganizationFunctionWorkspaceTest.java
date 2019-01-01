package org.netsharp.meta.basebiz.organization;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.OrganizationFunction;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.OpenMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PForm;
import org.netsharp.resourcenode.entity.ResourceNode;

public class OrganizationFunctionWorkspaceTest extends WorkspaceCreationBase {

	@Before
	public void setup() {

		entity = OrganizationFunction.class;
		urlList = "/system/organizationfunction/list";
		urlForm = "/system/organizationfunction/form";
		listPartName = formPartName = "业务类型";
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = OrganizationFunction.class.getSimpleName();
		openWindowWidth = 450;
		openWindowHeight = 300;
		formOpenMode = OpenMode.WINDOW;
	}

	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.toNew();
			datagrid.setResourceNode(node);
			datagrid.setToolbar("panda/datagrid/row/edit");
			datagrid.setName("业务类型列表");
		}
		addColumn(datagrid, "creator", "操作", ControlTypes.OPERATION_COLUMN, 100);
		addColumn(datagrid, "id", "id", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "code", "编码", ControlTypes.TEXT_BOX, 150);
		addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATETIME_BOX, 130);
		return datagrid;
	}

	protected PForm createForm(ResourceNode node) {

		PForm form = super.createForm(node);
		form.setColumnCount(1);
		addFormField(form, "code", "编码", null, ControlTypes.TEXT_BOX, true);
		addFormField(form, "name", "名称", null, ControlTypes.TEXT_BOX, true);
		return form;
	}

	@Override
	protected void doOperation() {

		ResourceNode node = this.getResourceNode();
		operationService.addOperation(node, OperationTypes.view);
		operationService.addOperation(node, OperationTypes.add);
		operationService.addOperation(node, OperationTypes.update);
		//operationService.addOperation(node, OperationTypes.delete);
	}
}
