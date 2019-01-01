package org.netsharp.meta.basebiz.authorization;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.RoleGroup;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.OpenMode;
import org.netsharp.panda.dic.PartType;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.resourcenode.entity.ResourceNode;

public class RoleGroupWorkspaceTest extends WorkspaceCreationBase{

	@Before
	public void setup() {

		entity = RoleGroup.class;
		urlList = "/system/rolegroup/list";
		urlForm = "/system/rolegroup/from";
		listPartName = formPartName = "角色分组";
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = RoleGroup.class.getSimpleName();
		openWindowWidth = 450;
		openWindowHeight = 350;
		formOpenMode = OpenMode.WINDOW;
		
		listPartType = PartType.TREEGRID_PART.getId();
		
		listToolbarPath = "/system/rolegroup/category/toolbar";
	}
	
	@Test
	public void createToolbar() {
		ResourceNode node = this.getResourceNode();
		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setBasePath("panda/datagrid/edit");
			toolbar.setPath(listToolbarPath);
			toolbar.setName("同步路径");
			toolbar.setResourceNode(node);
		}
		addToolbarItem(toolbar, "pathCode", "同步路径", "fa-recycle", "pathCode()", null, 5);
		addToolbarItem(toolbar, "reload", "刷新", "fa-refresh", "reload()", null, 6);
		toolbarService.save(toolbar);
	}

	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.toNew();
			datagrid.setResourceNode(node);
			datagrid.setToolbar("panda/datagrid/row/edit");
			datagrid.setName("角色分组列表");
		}
		
		PDatagridColumn column = null;
		addColumn(datagrid, "creator", "操作", ControlTypes.OPERATION_COLUMN, 100);
		addColumn(datagrid, "code", "编码", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "memoto", "备注", ControlTypes.TEXT_BOX, 300);
		addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATETIME_BOX, 130);
		column = addColumn(datagrid, "parentId", "parentId", ControlTypes.TEXT_BOX, 100);
		{
			column.setSystem(true);
			column.setVisible(false);
		}
		column = addColumn(datagrid, "isLeaf", "isLeaf", ControlTypes.TEXT_BOX, 100);
		{
			column.setSystem(true);
			column.setVisible(false);
		}
		return datagrid;
	}

	protected PForm createForm(ResourceNode node) {

		PForm form = super.createForm(node);
		form.setColumnCount(1);
		addFormField(form, "code", "编码", null, ControlTypes.TEXT_BOX, true);
		addFormField(form, "name", "名称", null, ControlTypes.TEXT_BOX, true);
		PFormField filed = addFormField(form, "memoto", "备注", null, ControlTypes.TEXTAREA, false);{
			
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
	}
}
