package org.netsharp.panda.controls.tree;

import org.netsharp.panda.dic.OpenMode;

public class TreeNodeAttributes {
	
	private String url;
	private TreeNodeState state;
	private String tag;
	private OpenMode openMode = OpenMode.OPEN;
	private Integer windowWidth;
	private Integer windowHeight;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public TreeNodeState getState() {
		return state;
	}
	public void setState(TreeNodeState state) {
		this.state = state;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public OpenMode getOpenMode() {
		return openMode;
	}
	public void setOpenMode(OpenMode openMode) {
		this.openMode = openMode;
	}
	public Integer getWindowWidth() {
		return windowWidth;
	}
	public void setWindowWidth(Integer windowWidth) {
		this.windowWidth = windowWidth;
	}
	public Integer getWindowHeight() {
		return windowHeight;
	}
	public void setWindowHeight(Integer windowHeight) {
		this.windowHeight = windowHeight;
	}
	
}
