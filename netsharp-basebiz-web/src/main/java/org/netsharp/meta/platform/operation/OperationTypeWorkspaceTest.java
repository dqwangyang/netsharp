package org.netsharp.meta.platform.operation;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.OperationType;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.OrderbyMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;

public class OperationTypeWorkspaceTest extends WorkspaceCreationBase{
	

	@Override
	@Before
	public void setup() {

		urlList = "/system/operationType/list";
		urlForm = "/system/operationType/form";
		entity = OperationType.class;

		meta = MtableManager.getMtable(entity);
		listPartName = formPartName = meta.getName();
		resourceNodeCode = OperationType.class.getSimpleName();
	}

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		addColumn(datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 100);
		addColumn(datagrid, "code", "编码", ControlTypes.TEXT_BOX, 150);
		addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 150);
		addColumn(datagrid, "creator", "创建人", ControlTypes.TEXT_BOX, 150);
		addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATETIME_BOX, 150,false,null,null,OrderbyMode.DESC);
		datagrid.setToolbar("panda/datagrid/row/edit");
		return datagrid;
	}

	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject,"code","编码",ControlTypes.TEXT_BOX);
		addQueryItem(queryProject,"name","名称",ControlTypes.TEXT_BOX);
		return queryProject;
	}

	@Override
	protected PForm createForm(ResourceNode node) {

		PForm form = new PForm(node, MtableManager.getMtable(entity).getName());
		form.setColumnCount(2);

		addFormField(form, "code", "编码", ControlTypes.TEXT_BOX, false, false);
		addFormField(form, "name", "名称", ControlTypes.TEXT_BOX, false, false);
		return form;
	}

	@Override
	protected void doOperation() {
		ResourceNode node = getResourceNode();
		operationService.addOperation(node,OperationTypes.view);
		operationService.addOperation(node,OperationTypes.add);
		operationService.addOperation(node,OperationTypes.update);
		operationService.addOperation(node,OperationTypes.delete);
	}
}
