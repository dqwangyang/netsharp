package org.netsharp.meta.platform;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.Operation;
import org.netsharp.organization.entity.OperationType;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.OrderbyMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;

public class OperationWorkspaceTest extends WorkspaceCreationBase {

	@Override
	@Before
	public void setup() {

		// 在子类中重定义
		urlList = "/platform/operation/list";
		urlForm = "/platform/operation/form";

		entity = Operation.class;
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = entity.getSimpleName();
	}

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.setOrderby("createTime desc");
		}
		
		PDatagridColumn column = null;

		addColumn(datagrid, "id", "id", ControlTypes.TEXT_BOX, 150);
		addColumn(datagrid, "code", "编码", ControlTypes.TEXT_BOX, 150);
		addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "operationType.name", "操作类型", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "resourceNode.code", "资源编码", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "resourceNode.pathName", "资源路径", ControlTypes.TEXT_BOX, 300);
		addColumn(datagrid, "resourceNode.entityId", "entityId", ControlTypes.TEXT_BOX, 300);
		addColumn(datagrid, "fieldGeteway", "字段权限", ControlTypes.TEXT_BOX, 80);
		addColumn(datagrid, "updator", "最后修改人", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "updateTime", "最后修改时间", ControlTypes.DATETIME_BOX, 150);
		addColumn(datagrid, "creator", "创建人", ControlTypes.TEXT_BOX, 100);
		column = addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATETIME_BOX, 150);
		{
			column.setOrderbyMode(OrderbyMode.DESC);
		}

		return datagrid;
	}

	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "id", "id", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "code", "编码", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "name", "名称", ControlTypes.TEXT_BOX);
		addRefrenceQueryItem(queryProject, "resourceNode.pathName", "资源",ResourceNode.class.getSimpleName());
		addRefrenceQueryItem(queryProject, "operationType.name", "操作类型",OperationType.class.getSimpleName());
		return queryProject;
	}

	@Override
	protected PForm createForm(ResourceNode node) {

		PForm form = new PForm();
		{
			form.toNew();
			form.setResourceNode(node);
			form.setName(this.meta.getName() + "表单");
			form.setColumnCount(3);
		}

		addFormField(form, "code", "编码", ControlTypes.TEXT_BOX, false, true);
		addFormField(form, "name", "名称", ControlTypes.TEXT_BOX, false, true);
		addFormFieldRefrence(form, "resourceNode.pathName", "资源节点", null, ResourceNode.class.getSimpleName(), true, false);
		addFormFieldRefrence(form, "operationType.name", "操作类型", null, PDatagrid.class.getSimpleName(), true, false);
		addFormField(form, "fieldGeteway", "字段权限", ControlTypes.CHECK_BOX, true, false);

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