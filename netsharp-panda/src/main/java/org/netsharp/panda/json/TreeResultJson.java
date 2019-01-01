package org.netsharp.panda.json;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.core.Category;
import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.panda.controls.tree.TreeNode;
import org.netsharp.panda.controls.tree.TreeNodeState;
import org.netsharp.panda.core.PandaException;

public class TreeResultJson {
	
	private List<?> items     = null;
	private Mtable mtable     = null;
	private Category category = null;
	private List<TreeNode> nodes = new ArrayList<TreeNode>();
	
	public TreeResultJson(List<?> items,String entityId){
		
		this.items    = items;
		this.mtable   = MtableManager.getMtable(entityId);
		this.category = mtable.getCategory();
		if(category==null){
			
			throw new PandaException("非分类实体，不能使用TreePart," + entityId);
		}
	}
	
	public List<TreeNode> parse(){
		
		for(Object obj : this.items){
			
			TreeNode node= this.convert(obj);
			this.nodes.add(node);
		}
		
		return this.nodes;
	}
	
	private TreeNode convert(Object obj){
		
		TreeNode node = new TreeNode();
		node.id   = this.mtable.getId(obj).toString();
		node.text = this.category.getNameValue(obj);
		if(category.isLeaf(obj)){
			node.state= TreeNodeState.open;
		}
		else{
			node.state= TreeNodeState.closed;
		}
		
		return node;
	}
}
