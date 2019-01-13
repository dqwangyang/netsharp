package org.netsharp.meta.framework;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.db.DbFactory;
import org.netsharp.db.IDb;
import org.netsharp.organization.entity.Operation;
import org.netsharp.organization.entity.OperationType;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.panda.plugin.entity.PZone;
import org.netsharp.plugin.bean.Bean;
import org.netsharp.plugin.bean.BeanPath;
import org.netsharp.resourcenode.IResourceNodeService;
import org.netsharp.resourcenode.dic.ResourceType;
import org.netsharp.resourcenode.entity.Plugin;
import org.netsharp.resourcenode.entity.ResourceNode;

public class ResourceNodeTest {
	
	IResourceNodeService service = ServiceFactory.create(IResourceNodeService.class);

	@Before
	public void setup() {

		List<Mtable> metas = new ArrayList<Mtable>();

		metas.add(MtableManager.getMtable(ResourceNode.class));
		metas.add(MtableManager.getMtable(Plugin.class));
		metas.add(MtableManager.getMtable(Operation.class));
		metas.add(MtableManager.getMtable(OperationType.class));
		metas.add(MtableManager.getMtable(PToolbar.class));
		metas.add(MtableManager.getMtable(PToolbarItem.class));
		metas.add(MtableManager.getMtable(PZone.class));
		metas.add(MtableManager.getMtable(BeanPath.class));
		metas.add(MtableManager.getMtable(Bean.class));
		
		IDb db = DbFactory.create();
		for (Mtable meta : metas) {
			db.reCreateTable(meta);
		}
	}
	
	@Test
	public void run(){
		this.top();
		this.framework();
	}
	
	public void top(){

		ResourceNode node = new ResourceNode();
		{
			node.toNew();
			node.setCode("panda");
			node.setName("业务平台");
			node.setResourceType(ResourceType.CAT);
		}

		service.save(node);
	}

	
	public void framework() {

		ResourceNode parentNode = service.byCode("panda");
		Long parentId = parentNode.getId();
		
		ResourceNode node = null;

		node = new ResourceNode();
		{
			node.toNew();
			node.setParentId(parentId);
			node.setCode("workbench");
			node.setName("工作台");
			node.setResourceType(ResourceType.ADDIN);
		}
		
		Plugin plugin = new Plugin();{
			plugin.toNew();
			plugin.setAuthor("netsharp");
			plugin.setUrl("http://www.netsharp.org");
			plugin.setName("工作台");
		}
		
		node.setPlugin(plugin);
		service.save(node);

	}
}
