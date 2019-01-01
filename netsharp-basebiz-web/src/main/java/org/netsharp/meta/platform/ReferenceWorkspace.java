package org.netsharp.meta.platform;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.entity.PReference;
import org.netsharp.resourcenode.entity.ResourceNode;

public class ReferenceWorkspace extends WorkspaceCreationBase {

	@Override
	@Before
	public void setup() {

		// 在子类中重定义
		urlList = "/platform/reference/list";
		urlForm = "/platform/reference/form";

		entity = PReference.class;
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName =  meta.getName();
		resourceNodeCode = PReference.class.getSimpleName();
	}

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.setOrderby("createTime desc");
		}
		
		addColumn(datagrid, "id", "id", ControlTypes.TEXT_BOX, 150);
		addColumn(datagrid, "code", "编码", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 150);
		addColumn(datagrid, "dataGrid.name", "表格方案", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "resourceNode.code", "资源编码", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "resourceNode.pathName", "资源路径", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "resourceNode.entityId", "entityId", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "intelligentMode", "匹配模式", ControlTypes.ENUM_BOX, 100);
		addColumn(datagrid, "intelligentFields", "匹配字段", ControlTypes.TEXT_BOX, 150);
		addColumn(datagrid, "filter", "过滤条件", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "defaulted", "默认", ControlTypes.BOOLCOMBO_BOX, 80);
		addColumn(datagrid, "popup", "弹出", ControlTypes.BOOLCOMBO_BOX, 80);
		addColumn(datagrid, "lazy", "懒加载", ControlTypes.BOOLCOMBO_BOX, 80);
		return datagrid;
	}

	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "id", "id", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "code", "编码", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "name", "名称", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "resourceNode.entityId", "实体", ControlTypes.TEXT_BOX);
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
		PFormField field = null;
		addFormField(form, "code", "编码", ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "name", "名称", ControlTypes.TEXT_BOX, true, false);
		addFormFieldRefrence(form, "dataGrid.name", "表格方案", null, PDatagrid.class.getSimpleName(), true, false);
		addFormFieldRefrence(form, "resourceNode.pathName", "资源节点", null, ResourceNode.class.getSimpleName(), true, false);
		addFormField(form, "intelligentMode", "匹配模式", ControlTypes.ENUM_BOX, true, false);
		addFormField(form, "intelligentFields", "匹配字段", ControlTypes.TEXT_BOX, false, false);
		addFormField(form, "defaulted", "默认", ControlTypes.CHECK_BOX, false, false);
		addFormField(form, "popup", "弹出", ControlTypes.CHECK_BOX, false, false);
		addFormField(form, "lazy", "懒加载", ControlTypes.CHECK_BOX, false, false);
		field = addFormField(form, "filter", "过滤条件", ControlTypes.TEXT_BOX, false, false);{
			field.setFullColumn(true);
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