package org.netsharp.panda.controls.tree;

import java.util.ArrayList;

/*easyui 树节点数据源对象*/
public class TreeNode
{
    public String id;
    public String text;
    public String url;
    public String iconCls;
    public TreeNodeState state;
    public TreeNodeAttributes attributes = new TreeNodeAttributes();

    private ArrayList<TreeNode> children = null;
    

    @Override
    public String toString()
    {
        return text + ":" + url;
    }


	public ArrayList<TreeNode> getChildren() {
		if(children == null ){
			this.children= new ArrayList<TreeNode>();
		}
		return children;
	}


	public void setChildren(ArrayList<TreeNode> children) {
		this.children = children;
	}
	
	
}