package org.netsharp.panda.plugin.doozer;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.controls.accordion.AccordionItem;
import org.netsharp.panda.controls.tree.Tree;
import org.netsharp.panda.plugin.NavigationManager;
import org.netsharp.panda.plugin.entity.PAccordionItem;
import org.netsharp.panda.session.PermissionHelper;
import org.netsharp.plugin.core.IDoozer;
import org.netsharp.plugin.entity.Codonable;
import org.netsharp.resourcenode.IResourceNodeService;
import org.netsharp.resourcenode.entity.ResourceNode;

public class DoozerAccordion implements IDoozer {
	
	IResourceNodeService resourceService = ServiceFactory.create(IResourceNodeService.class);
	public Object buildItem(Object caller, ResourceNode node, Codonable codon, List<Object> subItems) {
		
		if(codon.getDisabled()){
			return null;
		}

		if (node == null) {
			node = codon.getResourceNode();
		}

		boolean isPermission = PermissionHelper.isPermission(node, codon.getOperationId(), codon.getOperationType(), codon.getOperationType2());
		if (!isPermission) {
			return null;
		}

		PAccordionItem detail = (PAccordionItem) codon;
		AccordionItem item = new AccordionItem();
		{
			item.setCode(detail.getCode());
			item.title = detail.getName();
			item.iconCls = detail.getIcon();
			item.setId(detail.getId().toString());
		}

		String treePath = detail.getTreePath();
		Tree tree = NavigationManager.create(treePath, null, null);
		{
			//tree.OnClick = "function(node){if($(this).tree('isLeaf',node.target)==false){$(this).tree('toggle',node.target);}else{workbench.openWorkspace(node.text,node.attributes.url,'',true,node.id,node.attributes.openMode,node.attributes.windowWidth,node.attributes.windowHeight);}}";
		}
		
		if (tree.nodes.size() == 0) {
			return null;
		}

		item.getControls().add(tree);
		if (subItems == null) {
			subItems = new ArrayList<Object>();
		}
		subItems.add(item);
		return item;
	}
}