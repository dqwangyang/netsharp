package org.netsharp.meta.platform.data;

import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.organization.base.IOperationService;
import org.netsharp.organization.base.IOperationTypeService;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.OperationType;
import org.netsharp.panda.core.PandaException;
import org.netsharp.panda.plugin.base.IPToolbarService;
import org.netsharp.panda.plugin.dic.ToolbarType;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.resourcenode.IResourceNodeService;
import org.netsharp.resourcenode.entity.ResourceNode;

public class PlatformToolbarTest {

	IPToolbarService service = ServiceFactory.create(IPToolbarService.class);
	IResourceNodeService resourceNodeService = ServiceFactory.create(IResourceNodeService.class);
	IOperationTypeService operationTypeService = ServiceFactory.create(IOperationTypeService.class);
	
	String resourceNodeCode ="platform-tool";

	@Test
	public void run() {
		
		this.platformBaseToolbar();
		this.listPartToolbar();
		this.formPartToolbar();
		this.detailPartToolbar();
	}

	public void formPartToolbar() {
		
		ResourceNode node = this.resourceNodeService.byCode(resourceNodeCode);
		if(node==null) {
			throw new PandaException("未能查询到资源节点："+resourceNodeCode);
		}

		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setBasePath("panda/platform/base");
			toolbar.setPath("panda/form/readonly");
			toolbar.setName("只读列表");
			toolbar.setResourceNode(node);
			toolbar.setToolbarType(ToolbarType.BASE);
		}

		PToolbarItem item = null;
		item = new PToolbarItem();
		{

			item.toNew();
			item.setCode("fields");
			item.setIcon("fa fa-table");
			item.setName("表单设置");
			item.setCommand(null);
			item.setOperationTypeId(-1L);
			item.setParent("platform");
			item.setSeq(4);
			item.setCommand("{controller}.Sd.setFields();");
			toolbar.getItems().add(item);
		}
		item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("sql");
			item.setIcon("fa fa-file-o");
			item.setName("导出实体脚本");
			item.setCommand(null);
			item.setOperationTypeId(-1L);
			item.setParent("platform");
			item.setSeq(98);
			item.setCommand("{controller}.Sd.exportEntitySql();");
			toolbar.getItems().add(item);
		}
		service.save(toolbar);
	}

	public void detailPartToolbar() {

		ResourceNode node = this.resourceNodeService.byCode(resourceNodeCode);
		if(node==null) {
			throw new PandaException("未能查询到资源节点："+resourceNodeCode);
		}

		PToolbar toolbar = new PToolbar();
		{

			toolbar.toNew();
			toolbar.setBasePath("panda/platform/base");
			toolbar.setPath("panda/datagrid/detail");
			toolbar.setName("子表");
			toolbar.setResourceNode(node);
			toolbar.setToolbarType(ToolbarType.PLATFORM);
		}
		PToolbarItem item = null;
		item = new PToolbarItem();
		{

			item.toNew();
			item.setCode("fields");
			item.setIcon("fa fa-table");
			item.setName("栏目设置");
			item.setCommand(null);
			item.setOperationTypeId(-1L);
			item.setParent("platform");
			item.setSeq(4);
			item.setCommand("{controller}.Sd.setFields();");
			toolbar.getItems().add(item);
		}

		service.save(toolbar);
	}

	public void listPartToolbar() {

		ResourceNode node = this.resourceNodeService.byCode(resourceNodeCode);
		if(node==null) {
			throw new PandaException("未能查询到资源节点："+resourceNodeCode);
		}

		PToolbar toolbar = new PToolbar();
		{

			toolbar.toNew();
			toolbar.setBasePath("panda/platform/base");
			toolbar.setPath("panda/datagrid/readonly");
			toolbar.setName("只读列表");
			toolbar.setResourceNode(node);
			toolbar.setToolbarType(ToolbarType.PLATFORM);
		}

		PToolbarItem item = null;

		item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("fields");
			item.setIcon("fa fa-table");
			item.setName("栏目设置");
			item.setCommand(null);
			item.setOperationTypeId(-1L);
			item.setParent("platform");
			item.setSeq(4);
			item.setCommand("{controller}.Sd.setFields();");
			toolbar.getItems().add(item);
		}
		
		item = new PToolbarItem();
		{

			item.toNew();
			item.setCode("fields_query");
			item.setIcon("fa fa-search");
			item.setName("查询设置");
			item.setCommand(null);
			item.setOperationTypeId(-1L);
			item.setParent("platform");
			item.setSeq(5);
			item.setCommand("{controller}.Sd.setQuery();");
			toolbar.getItems().add(item);
		}

		service.save(toolbar);
	}

	public void platformBaseToolbar() {

		ResourceNode node = this.resourceNodeService.byCode("platform-tool");
		IOperationService operationService = ServiceFactory.create(IOperationService.class);
		operationService.addOperation(node, OperationTypes.operation);
		
		IOperationTypeService operationTypeService = ServiceFactory.create(IOperationTypeService.class);
		OperationType opt = operationTypeService.byCode(OperationTypes.operation);

		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setPath("panda/platform/base");
			toolbar.setName("平台工具");
			toolbar.setResourceNode(node);
			toolbar.setToolbarType(ToolbarType.PLATFORM);
		}

		PToolbarItem item = new PToolbarItem();
		{

			item.toNew();
			item.setCode("platform");
			item.setIcon("fa fa-cog");
			item.setName("平台工具");
			item.setCommand(null);
			item.setResourceNode(node);
			item.setOperationTypeId(opt.getId());
			item.setPopup(true);
			item.setSeq(1000);
			toolbar.getItems().add(item);
		}


		item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("part");
			item.setIcon("fa fa-columns");
			item.setName("工作区设置");
			item.setCommand(null);
			item.setOperationTypeId(-1L);
			item.setParent("platform");
			item.setSeq(1);
			item.setCommand("{controller}.Sd.setPart();");
			toolbar.getItems().add(item);
		}

		item = new PToolbarItem();
		{

			item.toNew();
			item.setCode("toolbar");
			item.setIcon("fa fa-window-maximize");
			item.setName("工具栏设置");
			item.setCommand(null);
			item.setOperationTypeId(-1L);
			item.setParent("platform");
			item.setSeq(2);
			item.setCommand("{controller}.Sd.setToolbar();");
			toolbar.getItems().add(item);
		}

		item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("sql");
			item.setIcon("fa fa-file-o");
			item.setName("导出部件脚本");
			item.setCommand(null);
			item.setOperationTypeId(-1L);
			item.setParent("platform");
			item.setSeq(99);
			item.setCommand("{controller}.Sd.exportSql();");
			toolbar.getItems().add(item);
		}
		item = new PToolbarItem();
		{

			item.toNew();
			item.setCode("cleanCache");
			item.setIcon("fa fa-close");
			item.setName("清除缓存");
			item.setCommand(null);
			item.setOperationTypeId(-1L);
			item.setParent("platform");
			item.setSeq(100);
			item.setCommand("{controller}.Sd.cleanCache();");
			toolbar.getItems().add(item);
		}
		service.save(toolbar);
	}
}
