package org.netsharp.meta.platform;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DatagridAlign;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.plugin.dic.ToolbarType;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.resourcenode.entity.Plugin;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.web.PluginListPart;

public class PluginWorkspaceTest extends WorkspaceCreationBase {

	@Override
	@Before
	public void setup() {

		// 在子类中重定义
		urlList = "/platform/plugin/list";
		urlForm = "/platform/plugin/form";

		entity = Plugin.class;
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = Plugin.class.getSimpleName();

		this.listPartJsController = PluginListPart.class.getName();
		this.listPartServiceController = PluginListPart.class.getName();
		this.listPartImportJs = "/panda-platform/js/plugin-list-part.js";
		this.listToolbarPath = "panda/plugin/datagrid";
	}
	
	@Test
	public void run() {
		
		createListToolbar();
		createListWorkspace();
		createFormWorkspace();
	}
	
	public void createListToolbar(){
		
		ResourceNode node = resourceService.byCode(resourceNodeCode);
		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setBasePath("panda/datagrid/edit");
			toolbar.setPath(listToolbarPath);
			toolbar.setName("插件列表");
			toolbar.setResourceNode(node);
			toolbar.setToolbarType(ToolbarType.PLATFORM);
		}

		addToolbarItem( toolbar, "install", "安装", "fa fa-gears", "install()", null, 100);
		addToolbarItem( toolbar, "uninstall", "卸载", "fa fa-trash", "uninstall()", null, 111);
		addToolbarItem( toolbar, "sql", "导出脚本", "fa fa-file-code-o", "sql()", null, 112);
		toolbarService.save(toolbar);
	}

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.setPageSize(25);
			datagrid.setOrderby("createTime desc");
		}
		
		PDatagridColumn column = addColumn(datagrid, "id", "id", ControlTypes.TEXT_BOX, 150);{
			
			column.setAlign(DatagridAlign.CENTER);
		}
		addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 100);
		column = addColumn(datagrid, "author", "作者", ControlTypes.TEXT_BOX, 100);{
			
			column.setAlign(DatagridAlign.CENTER);
		}
		column = addColumn(datagrid, "version", "版本", ControlTypes.TEXT_BOX, 80);{
			
			column.setAlign(DatagridAlign.CENTER);
		}
		column = addColumn(datagrid, "icon", "图标", ControlTypes.TEXT_BOX, 80);{
			
			column.setAlign(DatagridAlign.CENTER);
		}
		column = addColumn(datagrid, "disabled", "停用", ControlTypes.CHECK_BOX, 60);{
			
			column.setAlign(DatagridAlign.CENTER);
		}
		addColumn(datagrid, "url", "url", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "memoto", "备注", ControlTypes.TEXT_BOX, 300);
		return datagrid;
	}

	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = new PQueryProject();
		{
			queryProject.toNew();
			queryProject.setName(this.meta.getName() + "查询");
			queryProject.setResourceNode(node);
		}

		PQueryItem item = new PQueryItem();
		{
			item.toNew();
			item.setPropertyName("name");
			item.setHeader("名称");
			item.setControlType(ControlTypes.TEXT_BOX);
		}
		queryProject.getQueryItems().add(item);

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

		addFormField(form, "code", "编码", ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "name", "名称", ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "author", "作者", ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "version", "版本", ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "icon", "图标", ControlTypes.TEXT_BOX, false, false);
		addFormField(form, "url", "url", ControlTypes.TEXT_BOX, false, false);
		addFormField(form, "memoto", "备注", ControlTypes.TEXT_BOX, false, false);
		addFormField(form, "disabled", "停用", ControlTypes.CHECK_BOX, true, false);

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