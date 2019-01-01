package org.netsharp.panda.core.pad;

import org.netsharp.panda.controls.Control;
import org.netsharp.panda.controls.tree.Tree;

public class PadPlatform implements IPad {
	
	public static String AccordionPath = "Panda/Platform/PadTree";

	public Control create() {

		// Tree tree = TreeService.Create(AccordionPath, null, null);
		// {
		// tree.Id = "pioneerTree";
		// tree.OnClick =
		// "function(node){workbench.openWorkspace(node.text,node.attributes.url,'',true);}";
		// }

		Tree tree = null;

		return tree;
	}

	public boolean condition() {
		return true;
	}
}
