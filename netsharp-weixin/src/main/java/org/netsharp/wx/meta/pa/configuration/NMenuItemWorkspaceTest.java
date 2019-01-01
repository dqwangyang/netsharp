package org.netsharp.wx.meta.pa.configuration;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.OrderbyMode;
import org.netsharp.panda.dic.PartType;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.wx.pa.entity.NMenuItem;
import org.netsharp.wx.pa.entity.PublicAccount;
import org.netsharp.wx.pa.web.MenuItemTreegridPart;

public class NMenuItemWorkspaceTest extends WorkspaceCreationBase {

	@Override
	@Before
	public void setup() {

		urlList = "/wx/menuitem/list";
		urlForm = "/wx/menuitem/form";
		
		entity = NMenuItem.class;
		meta = MtableManager.getMtable(entity);
		resourceNodeCode = NMenuItem.class.getSimpleName();
		formPartName = listPartName = meta.getName();
		
		listPartType = PartType.TREEGRID_PART.getId();
		
		listPartJsController = MenuItemTreegridPart.class.getName();
		listPartServiceController = MenuItemTreegridPart.class.getName();
		
		listPartImportJs = "/addins/weixin/js/menu-treegrid-part.js";
		listToolbarPath="weixin/menu/toolbar";
	}

	@Test
	public void run() {
		createListToolbar();
		createListWorkspace();
		createFormWorkspace();
	}
	
	public void createListToolbar() {

		ResourceNode node = resourceService.byCode(resourceNodeCode);
		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setBasePath("panda/datagrid/edit");
			toolbar.setPath(this.listToolbarPath);
			toolbar.setName("微信菜单列表");
			toolbar.setResourceNode(node);
		}

		addToolbarItem(toolbar, "generate", "同步","fa fa-upload", "generate()",null,20);
		toolbarService.save(toolbar);
	}
	
	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {
		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		PQueryItem queryItem = addRefrenceQueryItem(queryProject, "publicAccount.name", "公众号", PublicAccount.class.getSimpleName());{
			
			queryItem.setRequired(true);
		}
		return queryProject;
	}
	

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {
		
		PDatagrid datagrid = super.createDatagrid(node);
		datagrid.setAutoQuery(false);
		PDatagridColumn column = null;
		
		this.addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "publicAccount.name", "公众号", ControlTypes.TEXT_BOX, 100);
		this.addColumn(datagrid, "reply", "回复关键字", ControlTypes.TEXT_BOX, 100);
		this.addColumn(datagrid, "url", "url", ControlTypes.TEXT_BOX, 400);
		this.addColumn(datagrid, "oauthType", "OAuth模式", ControlTypes.ENUM_BOX, 400);
		this.addColumn(datagrid, "state", "state", ControlTypes.TEXT_BOX, 400);
		column = this.addColumn(datagrid, "seq", "顺序", ControlTypes.TEXT_BOX, 60);{
			column.setOrderbyMode(OrderbyMode.ASC);
		}
		column = this.addColumn(datagrid, "parentId", "parentId", ControlTypes.TEXT_BOX, 40);
		{
			column.setSystem(true);
			column.setVisible(false);
		}
		return datagrid;
	}

	// 创建详细页面
	@Override
	protected PForm createForm(ResourceNode node) {
		
		PForm form = new PForm(node, meta.getName());
		form.setColumnCount(1);
		addFormFieldRefrence(form, "publicAccount.name", "公众号", "基本信息", PublicAccount.class.getSimpleName(), true, false);
		addFormField(form, "name", "名称", "基本信息", ControlTypes.TEXT_BOX, true);
		addFormField(form, "reply", "回复关键字", "基本信息", ControlTypes.TEXT_BOX, false);
		addFormField(form, "url", "url", "基本信息", ControlTypes.TEXT_BOX, false);
		addFormField(form, "seq", "顺序", "基本信息", ControlTypes.TEXT_BOX, false);
		addFormField(form, "oauthType", "OAuth模式", "OAuth(服务号使用)", ControlTypes.ENUM_BOX, false);
		addFormField(form, "state", "state", "OAuth(服务号使用)", ControlTypes.TEXT_BOX, false);
				
		return form;
	}

	@Override
	public void doOperation() {

		ResourceNode node = this.getResourceNode();
		operationService.addOperation(node, OperationTypes.view);
		operationService.addOperation(node, OperationTypes.add);
		operationService.addOperation(node, OperationTypes.update);
		operationService.addOperation(node, OperationTypes.delete);
	}
}