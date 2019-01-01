package org.netsharp.panda.plugin.doozer;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.panda.controls.tree.TreeNode;
import org.netsharp.panda.controls.tree.TreeNodeState;
import org.netsharp.panda.core.PandaUrlHelper;
import org.netsharp.panda.plugin.entity.PNavigationItem;
import org.netsharp.panda.session.PermissionHelper;
import org.netsharp.plugin.core.IDoozer;
import org.netsharp.plugin.entity.Codonable;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.StringManager;

public class DoozerNavigationItem implements IDoozer {

	public Object buildItem(Object caller, ResourceNode node, Codonable codon, List<Object> subItems) {

		if (codon.getDisabled()) {
			return null;
		}

		if (node == null) {
			node = codon.getResourceNode();
		}
		
		boolean isPermission = PermissionHelper.isPermission(node, codon.getOperationId(), codon.getOperationType(), codon.getOperationType2());

		if (!isPermission) {
			return null;
		}

		TreeNode treeNode = this.populate((PNavigationItem)codon);
		
		if (subItems != null) {
			for (Object sub : subItems) {
				if (sub != null) {
					treeNode.getChildren().add((TreeNode) sub);
				}
			}
		}

		return treeNode;
	}
	
	private TreeNode populate(PNavigationItem codon){
		
		if(!StringManager.isNullOrEmpty(codon.getUrl())){
			TreeNode treeNode = new TreeNode();
			{
				treeNode.url = PandaUrlHelper.getUrl(codon.getUrl());
				treeNode.id = codon.getCode();
				treeNode.text = codon.getName();
				treeNode.iconCls = codon.getIcon();
				treeNode.attributes.setUrl(codon.getUrl());
				treeNode.attributes.setState(TreeNodeState.open);
				treeNode.attributes.setOpenMode(codon.getOpenMode());
				treeNode.attributes.setWindowWidth(codon.getWindowWidth());
				treeNode.attributes.setWindowHeight(codon.getWindowHeight());
			}
			
			return treeNode;
		}
		
		//
		ArrayList<TreeNode> subNodes = new ArrayList<TreeNode>();
		
		for (Codonable c : codon.getChildrens()) {
			
			PNavigationItem subCodon = (PNavigationItem) c;
			boolean isPermission = this.isPermission(subCodon);
			if (!isPermission) {
				continue;
			}
			
			TreeNode subNode = this.populate(subCodon);
			if(subNode != null){
				subNodes.add(subNode);
			}
		}
		
		if(subNodes.size()==0){
			return null;
		}
		
		TreeNode treeNode = new TreeNode();
		{
			treeNode.url = PandaUrlHelper.getUrl(codon.getUrl());
			treeNode.id = codon.getCode();
			treeNode.text = codon.getName();
			treeNode.iconCls = codon.getIcon();
			treeNode.attributes.setUrl(codon.getUrl());
			treeNode.attributes.setState(TreeNodeState.open);
			treeNode.attributes.setOpenMode(codon.getOpenMode());
			treeNode.attributes.setWindowWidth(codon.getWindowWidth());
			treeNode.attributes.setWindowHeight(codon.getWindowHeight());
			treeNode.setChildren(subNodes);
		}
		
		return treeNode;
	}
	
	private boolean isPermission(PNavigationItem cncodon){
		return PermissionHelper.isPermission(cncodon.getResourceNode(), cncodon.getOperationId(), cncodon.getOperationType(), cncodon.getOperationType2());
	}
}
