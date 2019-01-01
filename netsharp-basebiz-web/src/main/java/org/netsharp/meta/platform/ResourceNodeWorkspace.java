package org.netsharp.meta.platform;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DatagridAlign;
import org.netsharp.panda.dic.OrderbyMode;
import org.netsharp.panda.dic.PartType;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.plugin.dic.ToolbarType;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.utils.enums.EnumUtil;
import org.netsharp.resourcenode.dic.ResourceType;
import org.netsharp.resourcenode.entity.Plugin;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.web.ResourceNodeFormPart;
import org.netsharp.web.ResourceNodeTreegridPart;

public class ResourceNodeWorkspace extends WorkspaceCreationBase {

	@Override
	@Before
	public void setup() {

		// 在子类中重定义
		urlList = "/platform/resourceNode/tree";
		urlForm = "/platform/resourceNode/form";

		entity = ResourceNode.class;
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = ResourceNode.class.getSimpleName();

		listPartType = PartType.TREEGRID_PART.getId();

		this.listToolbarPath = "panda/resource/datagrid";
		this.listPartServiceController = ResourceNodeTreegridPart.class.getName();
		this.listPartJsController = ResourceNodeTreegridPart.class.getName();
		this.listPartImportJs = "/panda-platform/js/resourcenode-list-part.js";
		
		this.formServiceController=ResourceNodeFormPart.class.getName();
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
			toolbar.setName("资源节点列表");
			toolbar.setResourceNode(node);
			toolbar.setToolbarType(ToolbarType.PLATFORM);
		}

		addToolbarItem( toolbar, "sql", "导出脚本", "fa fa-file-code-o", "sql()", null, 112);
		toolbarService.save(toolbar);
	}

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		
		PDatagridColumn column = null;

		addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 200);
		column = addColumn(datagrid, "id", "id", ControlTypes.TEXT_BOX, 120);{
			column.setAlign(DatagridAlign.CENTER);
		}
		column = addColumn(datagrid, "resourceType", "类型", ControlTypes.ENUM_BOX, 80);{
			column.setAlign(DatagridAlign.CENTER);
			String formatter = EnumUtil.getColumnFormatter(ResourceType.class);
			column.setFormatter(formatter);
		}
		addColumn(datagrid, "code", "编码", ControlTypes.TEXT_BOX, 150);
		column = addColumn(datagrid, "seq", "顺序", ControlTypes.TEXT_BOX, 60);{
			column.setOrderbyMode(OrderbyMode.ASC);
		}
		
		addColumn(datagrid, "entityId", "entityId", ControlTypes.TEXT_BOX, 300);
		addColumn(datagrid, "service", "service", ControlTypes.TEXT_BOX, 300);
		addColumn(datagrid, "pathName", "pathName", ControlTypes.TEXT_BOX, 300);
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

	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "name", "名称", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "entityId", "实体", ControlTypes.TEXT_BOX);

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

		PFormField formField = null;
		addFormField(form, "code", "编码", ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "name", "名称", ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "resourceType", "类型", ControlTypes.ENUM_BOX, true, false);
		addFormFieldRefrence(form, "plugin.name", "插件", null, Plugin.class.getSimpleName(),false, false);

		addFormField(form, "parentId", "parentId", ControlTypes.TEXT_BOX, false, false);
		addFormField(form, "seq", "顺序", ControlTypes.NUMBER_BOX, true, false);

		formField = addFormField(form, "entityId", "entityId", ControlTypes.TEXT_BOX, false, false);{
			formField.setFullColumn(true);
		}
		formField = addFormField(form, "service", "service", ControlTypes.TEXT_BOX, false, false);{
			formField.setFullColumn(true);
		}
		formField = addFormField(form, "memoto", "备注", ControlTypes.EDITOR, false, false);{
			
			formField.setHeight(250);
			formField.setFullColumn(true);
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
