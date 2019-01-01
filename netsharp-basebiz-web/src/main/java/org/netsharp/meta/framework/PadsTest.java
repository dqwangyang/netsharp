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
import org.netsharp.panda.core.pad.PadNavigation;
import org.netsharp.panda.plugin.base.IPPadsService;
import org.netsharp.panda.plugin.entity.PPad;
import org.netsharp.panda.plugin.entity.PPads;
import org.netsharp.resourcenode.IResourceNodeService;
import org.netsharp.resourcenode.entity.ResourceNode;

public class PadsTest {
	
	IPPadsService padsService = ServiceFactory.create(IPPadsService.class);
	IResourceNodeService resourceNodeService = ServiceFactory.create(IResourceNodeService.class);
	
	@Before
	public void setup(){
		
		List<Mtable> metas = new ArrayList<Mtable>();
		
		metas.add(MtableManager.getMtable(PPads.class));
		metas.add(MtableManager.getMtable(PPad.class));
		
    	IDb db=DbFactory.create();
    	for(Mtable meta : metas){
    		db.reCreateTable(meta);
    	}
	}
	
	@Test
	public void create(){
		
		ResourceNode node = resourceNodeService.byCode("panda");
		
		PPads pads = new PPads();{
			
			pads.setPath("panda/workbench/pad");
			pads.setCode("pads");
			pads.setName("导航菜单");
			pads.setResourceNode(node);
			pads.toNew();
		}
		
		PPad item = null;
		
		item = new PPad();{
			item.toNew();
			
			item.setName("导航菜单");
			item.setCode("navigationPad");
			item.setType(PadNavigation.class.getName());
			item.setResourceNode(node);
			
			pads.getItems().add(item);
		}
		
		padsService.save(pads);
	}
}