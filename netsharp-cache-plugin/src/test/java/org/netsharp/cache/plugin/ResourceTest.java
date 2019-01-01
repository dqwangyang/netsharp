package org.netsharp.cache.plugin;

import org.netsharp.cache.plugin.base.ICachePluginService;
import org.netsharp.cache.plugin.entity.CachePlugin;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.meta.base.ResourceCreationBase;
import org.netsharp.resourcenode.IResourceNodeService;
import org.netsharp.resourcenode.entity.ResourceNode;

public class ResourceTest extends ResourceCreationBase {

	IResourceNodeService service = ServiceFactory.create(IResourceNodeService.class);
	
	@Override
	protected void createResourceNodeVouchers(ResourceNode node) {
		
		node = service.byCode("UI_Tool");
		ResourceNode resourceNode = this.createResourceNodeVoucher(CachePlugin.class.getName(), "缓存管理", "cache-plugin-list", ICachePluginService.class.getName(), node.getId());
		resourceNode.setSeq(100);
	}
}
