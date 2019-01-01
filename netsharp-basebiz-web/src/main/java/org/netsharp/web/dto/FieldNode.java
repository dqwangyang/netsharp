package org.netsharp.web.dto;

import java.util.ArrayList;

import org.netsharp.panda.controls.tree.TreeNodeState;

public class FieldNode {

	private Long id;
	
	private String text;
	
	private String iconCls;
	
	private TreeNodeState state;
	
	private FieldNodeAttributes attributes = new FieldNodeAttributes();

	private ArrayList<FieldNode> children = null;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public TreeNodeState getState() {
		return state;
	}

	public void setState(TreeNodeState state) {
		this.state = state;
	}

	public FieldNodeAttributes getAttributes() {
		return attributes;
	}

	public void setAttributes(FieldNodeAttributes attributes) {
		this.attributes = attributes;
	}

	public ArrayList<FieldNode> getChildren() {
		if (children == null) {
			this.children = new ArrayList<FieldNode>();
		}
		return children;
	}

	public void setChildren(ArrayList<FieldNode> children) {
		this.children = children;
	}
}
