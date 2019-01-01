package org.netsharp.panda.plugin;

import java.util.ArrayList;

import org.netsharp.panda.controls.tree.Tree;
import org.netsharp.panda.controls.tree.TreeNode;
import org.netsharp.panda.plugin.entity.PNavigation;
import org.netsharp.plugin.core.AddInTree;
import org.netsharp.resourcenode.entity.ResourceNode;

public class NavigationManager {
	
	@SuppressWarnings("unchecked")
	public static Tree create(String treePath, Object caller, ResourceNode node){
		
		Tree tree = new Tree();{
	        Object treeNodes = AddInTree.buildItems(PNavigation.class,node,treePath, null);
	        tree.nodes = (ArrayList<TreeNode>)treeNodes;
	        tree.getInnerValues().put("isMenu", "true");
		}
        return tree;
	}
}
