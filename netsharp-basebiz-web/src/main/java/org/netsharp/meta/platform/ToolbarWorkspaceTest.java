package org.netsharp.meta.platform;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.OperationType;
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
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.ReflectManager;

public class ToolbarWorkspaceTest extends WorkspaceCreationBase {

	@Override
	@Before
	public void setup() {

		// 在子类中重定义
		urlList = "/platform/toolbar/list";
		urlForm = "/platform/toolbar/form";

		entity = PToolbar.class;
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = "工具栏";
		resourceNodeCode = PToolbar.class.getSimpleName();
	}

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.setPageSize(10);
			datagrid.setOrderby("createTime desc");
		}
		
		PDatagridColumn column = addColumn(datagrid, "id", "id", ControlTypes.TEXT_BOX, 150);{
			
			column.setAlign(DatagridAlign.CENTER);
		}
		addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "toolbarType", "类型", ControlTypes.ENUM_BOX, 100);
		addColumn(datagrid, "path", "path", ControlTypes.TEXT_BOX, 250);
		addColumn(datagrid, "basePath", "basePath", ControlTypes.TEXT_BOX, 250);
		addColumn(datagrid, "resourceNode.pathName", "资源路径", ControlTypes.TEXT_BOX, 300);
		return datagrid;
	}

	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "id", "id", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "name", "名称", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "path", "路径", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "basePath", "父路径", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "toolbarType", "类型", ControlTypes.ENUM_BOX);
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
		addFormField(form, "name", "名称", ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "toolbarType", "类型", ControlTypes.ENUM_BOX, true, false);
		addFormFieldRefrence(form, "resourceNode.pathName", "资源节点", null, ResourceNode.class.getSimpleName(), true, false);
		field = addFormField(form, "path", "path", ControlTypes.TEXT_BOX, false, false);{
			field.setFullColumn(true);
		}
		field = addFormField(form, "basePath", "basePath", ControlTypes.TEXT_BOX, false, false);{
			field.setFullColumn(true);
		}
		field = addFormField(form, "memoto", "备注", ControlTypes.TEXT_BOX, false, false);{
			field.setFullColumn(true);
		}
		return form;
	}

	@Override
	protected void addDetailGridPart(PWorkspace workspace) {

		ResourceNode node = this.resourceService.byCode(PToolbarItem.class.getSimpleName());
		PDatagrid datagrid = new PDatagrid(node, "工具栏按钮");
		datagrid.setShowTitle(true);
		PDatagridColumn column = null;
		addColumn(datagrid, "code", "编码", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 100);
		column = addColumn(datagrid, "icon", "图标", ControlTypes.TEXT_BOX, 80);{
			column.setAlign(DatagridAlign.CENTER);
			column.setFormatter("if(value){ return '<span class=\\''+value+'\\'>&nbsp;</span>';}");
		}
		addColumn(datagrid, "command", "事件", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "popup", "有下拉项", ControlTypes.BOOLCOMBO_BOX, 80);
		addColumn(datagrid, "splitter", "分隔", ControlTypes.BOOLCOMBO_BOX, 80);
		addColumn(datagrid, "base", "继承上级", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "parent", "显示上级", ControlTypes.TEXT_BOX, 100);
		column = addColumn(datagrid, "seq", "顺序", ControlTypes.NUMBER_BOX, 80);{
			column.setAlign(DatagridAlign.CENTER);
		}
		addColumn(datagrid, "disabled", "停用", ControlTypes.BOOLCOMBO_BOX, 80);

		addColumn(datagrid, "resourceNode.pathName", "资源节点", ControlTypes.REFERENCE_BOX, 300);
		addColumn(datagrid, "operationType.name", "操作类型", ControlTypes.REFERENCE_BOX, 100);
		addColumn(datagrid, "operationType2.name", "操作类型2", ControlTypes.REFERENCE_BOX, 100);
		column = addColumn(datagrid, "operationId", "operationId", ControlTypes.NUMBER_BOX, 150);{
			
			column.setAlign(DatagridAlign.CENTER);
		}

		PPart part = new PPart();
		{
			part.toNew();
			part.setCode("items");
			part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
			part.setRelationRole("items");
			part.setResourceNode(node);
			part.setPartTypeId(org.netsharp.panda.dic.PartType.DETAIL_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
			part.setToolbar("panda/datagrid/detail");
			part.setWindowWidth(850);
			part.setWindowHeight(400);
			PForm form = this.createDetailGridForm(node);
			part.setForm(form);
		}

		workspace.getParts().add(part);

		// 设置表头表单样式
		part = workspace.getParts().get(0);
		part.setDockStyle(DockType.TOP);
		part.setStyle("height:200px");
	}
	
	protected PForm createDetailGridForm(ResourceNode node){
		
		PForm form = new PForm();{

			form.setResourceNode(node);
			form.toNew();
			form.setColumnCount(3);
			form.setName("工具栏项目");
		}
		addFormField(form, "code", "编码",null, ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "name", "名称",null, ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "icon", "图标",null, ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "className", "颜色",null, ControlTypes.TEXT_BOX, false, false);
		addFormField(form, "command", "事件",null, ControlTypes.TEXT_BOX, false, false);
		addFormField(form, "seq", "顺序",null, ControlTypes.NUMBER_BOX, true, false);
		addFormField(form, "operationId", "操作Id",null, ControlTypes.TEXT_BOX, false, false);
		addFormField(form, "base", "继承上级",null, ControlTypes.TEXT_BOX, false, false);
		addFormField(form, "parent", "显示上级",null, ControlTypes.TEXT_BOX, false, false);

		addFormFieldRefrence(form, "resourceNode.pathName", "资源节点", null, ResourceNode.class.getSimpleName(), false, false);
		addFormFieldRefrence(form, "operationType.name", "操作类型", null, OperationType.class.getSimpleName(), false, false);
		addFormFieldRefrence(form, "operationType2.name", "操作类型2", null, OperationType.class.getSimpleName(), false, false);
		
		addFormField(form, "popup", "有下拉项",null, ControlTypes.CHECK_BOX, false, false);
		addFormField(form, "splitter", "分隔",null, ControlTypes.CHECK_BOX, false, false);
		addFormField(form, "disabled", "停用",null, ControlTypes.CHECK_BOX, false, false);

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