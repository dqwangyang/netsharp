package org.netsharp.meta.platform;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DatagridAlign;
import org.netsharp.panda.dic.DockType;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PPart;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.entity.PWorkspace;
import org.netsharp.panda.plugin.entity.PNavigation;
import org.netsharp.panda.plugin.entity.PNavigationItem;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.ReflectManager;

public class TreeWorkspaceTest extends WorkspaceCreationBase {

	@Before
	public void setup() {

		urlList = "/platform/ptree/list";
		urlForm = "/platform/ptree/form";
		entity = PNavigation.class;
		meta = MtableManager.getMtable(entity);
		resourceNodeCode = entity.getSimpleName();
		formPartName = listPartName = "导航菜单";
	}

	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		PDatagridColumn column = addColumn(datagrid, "id", "id", ControlTypes.NUMBER_BOX, 150, false);{
			
			column.setAlign(DatagridAlign.CENTER);
		}
		addColumn(datagrid, "name", "模块名称", ControlTypes.TEXT_BOX, 120, false);
		addColumn(datagrid, "path", "路径", ControlTypes.TEXT_BOX, 300);
		addColumn(datagrid, "resourceNode.pathName", "资源节点", ControlTypes.TEXT_BOX, 250);
		return datagrid;
	}

	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "id", "id", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "name", "名称", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "path", "路径", ControlTypes.TEXT_BOX);

		return queryProject;
	}

	@Override
	protected PForm createForm(ResourceNode node) {

		PForm form = new PForm(node, this.formPartName);
		{
			form.setColumnCount(1);
		}

		addFormField(form, "name", "名称", null, ControlTypes.TEXT_BOX, true);
		addFormField(form, "path", "路径", null, ControlTypes.TEXT_BOX, true);
		return form;
	}

	@Override
	protected void addDetailGridPart(PWorkspace workspace) {

		ResourceNode node = resourceService.byCode(PNavigationItem.class.getSimpleName());
		PDatagrid datagrid = new PDatagrid(node, "菜单子项");{

			datagrid.setShowTitle(true);
			datagrid.setShowCheckbox(true);
			datagrid.setSingleSelect(false);
			datagrid.setReadOnly(true);
		}
		
		addColumn(datagrid, "code", "编码", ControlTypes.TEXT_BOX, 150);
		addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "url", "url", ControlTypes.TEXT_BOX, 300);
		addColumn(datagrid, "parent", "显示上级Code", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "seq", "显示顺序", ControlTypes.NUMBER_BOX, 80);
		addColumn(datagrid, "disabled", "停用", ControlTypes.BOOLCOMBO_BOX, 80);

		//
		PPart part = new PPart();
		{
			part.toNew();

			part.setCode("treeNodes");
			part.setParentCode(ReflectManager.getFieldName(PNavigation.class.getSimpleName()));
			part.setRelationRole("treeNodes");
			part.setResourceNode(node);
			part.setPartTypeId(org.netsharp.panda.dic.PartType.DETAIL_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
			part.setStyle("height:400px;");
			part.setToolbar("panda/datagrid/detail");
			part.setWindowWidth(600);
			part.setWindowHeight(400);
			PForm form = this.createDetailGridForm(node);
			part.setForm(form);
		}

		workspace.getParts().add(part);

		// 设置表头表单样式
		part = workspace.getParts().get(0);
		part.setDockStyle(DockType.TOP);
		part.setStyle("height:150px");
	}
	

	protected PForm createDetailGridForm(ResourceNode node){
		
		PForm form = new PForm();
		form.setResourceNode(node);
		form.toNew();
		form.setColumnCount(1);
		form.setName("菜单子项");
		addFormField(form, "code", "编码",null, ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "name", "名称",null, ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "url", "路径",null, ControlTypes.TEXT_BOX, false, false);
		addFormField(form, "parent", "上级编码",null, ControlTypes.TEXT_BOX, false, false);
		addFormField(form, "seq", "显示顺序",null, ControlTypes.NUMBER_BOX, true, false);
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