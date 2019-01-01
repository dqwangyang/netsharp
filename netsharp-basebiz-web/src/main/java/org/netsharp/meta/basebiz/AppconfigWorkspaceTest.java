package org.netsharp.meta.basebiz;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.appconfig.Appconfig;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.OrderbyMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;

public class AppconfigWorkspaceTest extends WorkspaceCreationBase {

	@Override
	@Before
	public void setup() {

		urlList = "/system/appconfig/list";
		urlForm = "/system/appconfig/form";
		entity = Appconfig.class;
		formPartName = listPartName =  "系统选项";
		resourceNodeCode = Appconfig.class.getSimpleName();
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
		addColumn(datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 100);
		addColumn(datagrid, "groupName", "分组", ControlTypes.TEXT_BOX, 150, false, null, null, OrderbyMode.ASC);
		addColumn(datagrid, "code", "编码", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "value", "值", ControlTypes.TEXT_BOX, 300);
		addColumn(datagrid, "defaultValue", "初始值", ControlTypes.TEXT_BOX, 300);
		addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 150);
		addColumn(datagrid, "memoto", "备注", ControlTypes.TEXT_BOX, 300);
		addColumn(datagrid, "system", "系统", ControlTypes.BOOLCOMBO_BOX, 80);
		addColumn(datagrid, "visible", "显示", ControlTypes.BOOLCOMBO_BOX, 80);
		addColumn(datagrid, "resourceNode.pathName", "资源", ControlTypes.TEXT_BOX, 200);
		return datagrid;
	}

	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "code", "编码", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "name", "名称", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "groupName", "分组", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "memoto", "备注", ControlTypes.TEXT_BOX);
		return queryProject;
	}

	@Override
	protected PForm createForm(ResourceNode node) {

		PForm form = super.createForm(node);
		form.setColumnCount(3);

		addFormField(form, "groupName", "分组", ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "code", "编码", ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "name", "名称", ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "value", "选项值", ControlTypes.TEXT_BOX, false, false);
		addFormField(form, "defaultValue", "初始值", ControlTypes.TEXT_BOX, false, false);
		addFormFieldRefrence(form, "resourceNode.pathName", "资源节点", null, ResourceNode.class.getSimpleName(), true, false);
		addFormField(form, "memoto", "备注", ControlTypes.TEXTAREA, false, false);
		addFormField(form, "system", "系统", ControlTypes.SWITCH_BUTTON, false, false);
		addFormField(form, "visible", "显示", ControlTypes.SWITCH_BUTTON, false, false);
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
