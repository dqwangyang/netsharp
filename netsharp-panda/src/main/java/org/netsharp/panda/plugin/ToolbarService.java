package org.netsharp.panda.plugin;

import java.util.List;

import org.netsharp.panda.controls.toolbar.Toolbar;
import org.netsharp.panda.controls.toolbar.ToolbarItem;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.plugin.core.AddInTree;
import org.netsharp.resourcenode.entity.ResourceNode;

public class ToolbarService {
	
	public static Toolbar create(ResourceNode node,String path, Object caller) {
		
		Toolbar toolbar = new Toolbar();
		List<Object> items = AddInTree.buildItems(PToolbar.class,node, path, caller);
		for (Object obj : items) {
			ToolbarItem item = (ToolbarItem) obj;
			if(item != null){
				toolbar.getControls().add(item);
			}
		}

		return toolbar;
	}
}