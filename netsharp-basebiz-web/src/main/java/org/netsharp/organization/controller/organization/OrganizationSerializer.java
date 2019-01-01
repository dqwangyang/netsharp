package org.netsharp.organization.controller.organization;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.organization.entity.Organization;
import org.netsharp.panda.controls.tree.TreeNode;
import org.netsharp.panda.controls.tree.TreeNodeState;

public class OrganizationSerializer {
	private List<Organization> items     = null;
	private List<TreeNode> nodes = new ArrayList<TreeNode>();
	
	public OrganizationSerializer(List<Organization> items){
		
		this.items    = items;
	}
	
	public List<TreeNode> parse(){
		
		for(Organization obj : this.items){
			
			TreeNode node= this.convert(obj);
			
			this.nodes.add(node);
		}
		
		return this.nodes;
	}
	
	private TreeNode convert(Organization obj){
		
		TreeNode node = new TreeNode();
		String value = obj.getOrganizationType().getValue().toString();
		node.attributes.setTag(value);
		
		node.id   = obj.getId().toString();
		node.text = obj.getName();
		
		if(obj.getIsLeaf()){
			node.state= TreeNodeState.open;
			node.iconCls ="icon-user";
		}
		else{
			node.state= TreeNodeState.closed;
		}
		
		return node;
	}
}
