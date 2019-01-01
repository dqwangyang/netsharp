package org.netsharp.cache.plugin;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.cache.plugin.entity.CachePlugin;
import org.netsharp.cache.plugin.web.CachePluginListPart;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DatagridAlign;
import org.netsharp.panda.dic.QueryTextOperation;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.plugin.base.IPToolbarService;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.resourcenode.entity.ResourceNode;

public class CachePluginWorkspaceTest extends WorkspaceCreationBase{
	
	protected IPToolbarService toolBarservice = ServiceFactory.create(IPToolbarService.class);

	@Before
	public void setup() {
		urlList = "/cacheplugin/list";
		entity = CachePlugin.class;
		meta = MtableManager.getMtable(entity);
		listPartName = formPartName = meta.getName();
		resourceNodeCode = "cache-plugin-list";
		
		listPartImportJs = "/panda-platform/js/cache-plugin-list-part.js";
		listPartJsController = CachePluginListPart.class.getName();
		listPartServiceController = CachePluginListPart.class.getName();
		
		listToolbarPath="/cacheplugin/list/edit";
	}
	
	@Test
    public void run() {
    	super.run();
    	this.createListToolbar();
    }
    
    private void createListToolbar(){
    	
    	ResourceNode node = resourceService.byCode(resourceNodeCode);
		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setPath(listToolbarPath);
			toolbar.setName("缓存管理列表工具栏");
			toolbar.setResourceNode(node);
		}
		
		PToolbarItem item = null;
		item = new PToolbarItem();{
			
			item.toNew();
			item.setCode("setOutTimeAtList");
			item.setIcon("fa fa-trash-o");
			item.setName("清空缓存");
			item.setCommand("{controller}.flushDB();");
			item.setOperationTypeId(-1);
			item.setSeq(1);
			toolbar.getItems().add(item);
		}
//		
//		item = new PToolbarItem();
//		{
//
//			item.toNew();
//			item.setCode("reload");
//			item.setIcon("fa fa-refresh");
//			item.setName("刷新");
//			item.setCommand("{controller}.reload();");
//			item.setOperationTypeId(-1L);
//			item.setSeq(2);
//			toolbar.getItems().add(item);
//		}

		item = new PToolbarItem();
		{

			item.toNew();
			item.setCode("close");
			item.setIcon("fa fa-close");
			item.setName("关闭");
			item.setCommand("{controller}.close();");
			item.setOperationTypeId(-1);
			item.setSeq(3);
			toolbar.getItems().add(item);
		}
		
		toolBarservice.save(toolbar);
    }
	
	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {
		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.setReadOnly(true);
			datagrid.setSingleSelect(true);
		}
		
		PDatagridColumn column = null;
		column = addColumn(datagrid, "fullKey", "缓存Key", ControlTypes.TEXT_BOX, 600);
		column = addColumn(datagrid, "id", "操作", ControlTypes.TEXT_BOX, 140);
		{
			
			column.setFormatter("return controllercachePluginList.createButton(value,row,index); ");
			column.setAlign(DatagridAlign.CENTER);
		}
		return datagrid;
	}
	
	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {
		PQueryProject queryProject = new PQueryProject();
		{
			queryProject.toNew();
			queryProject.setName("查询");
			queryProject.setResourceNode(node);
		}
		PQueryItem item = new PQueryItem();
		{
			item.toNew();
			item.setPropertyName("fullKey");
			item.setHeader("缓存Key");
			item.setOperation(QueryTextOperation.CONTAINS.name());
			item.setControlType(ControlTypes.TEXT_BOX);
		}
		queryProject.getQueryItems().add(item);
		
		return queryProject;
	}
	
	protected void doOperation() {
		ResourceNode node = resourceService.byCode(resourceNodeCode);
		operationService.addOperation(node, OperationTypes.view);
	}
}
