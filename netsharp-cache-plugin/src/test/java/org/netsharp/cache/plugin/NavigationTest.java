package org.netsharp.cache.plugin;

import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.organization.base.IOperationTypeService;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.OperationType;
import org.netsharp.panda.plugin.base.IPNavigationItemService;
import org.netsharp.panda.plugin.base.IPNavigationService;
import org.netsharp.panda.plugin.entity.PNavigation;
import org.netsharp.panda.plugin.entity.PNavigationItem;
import org.netsharp.resourcenode.IResourceNodeService;
import org.netsharp.resourcenode.entity.ResourceNode;

public class NavigationTest {

	@Test
	public void run() {
		
		IResourceNodeService nodeService = ServiceFactory.create(IResourceNodeService.class);
		IPNavigationService treeService = ServiceFactory.create(IPNavigationService.class);
		IOperationTypeService operationTypeService = ServiceFactory.create(IOperationTypeService.class);
		IPNavigationItemService navigationItemService = ServiceFactory.create(IPNavigationItemService.class);
		PNavigation tree = treeService.byPath("panda/navigation/platform");

		OperationType operationType = operationTypeService.byCode(OperationTypes.view);
		ResourceNode resourceNode = nodeService.byCode("cache-plugin-list");
		PNavigationItem node = new PNavigationItem();
		{
			node.toNew();
			node.setParent("UI_Tool");
			node.setCode("cache-plugin-list");
			node.setName( "缓存管理");
			node.setUrl("/cacheplugin/list");
			node.setSeq(1000);
			node.setPathId(tree.getId());
			node.setOperationType(operationType);
			node.setResourceNode(resourceNode);
		}
		navigationItemService.save(node);
	}
	

}
