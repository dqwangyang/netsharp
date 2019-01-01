package org.netsharp.panda.plugin.entity;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.panda.plugin.doozer.DoozerToolbarItem;
import org.netsharp.plugin.core.Doozer;
import org.netsharp.plugin.entity.Codonable;

@Table(name="rs_toolbar_item",orderBy="seq")
@Doozer(type=DoozerToolbarItem.class)
public class PToolbarItem extends Codonable {
	
	private static final long serialVersionUID = 3656136368346022215L;

	@Column(name="icon",header="图标")
	private String icon;
	
	@Column(name="class_name",header="样式名称：",size =512)
	private String className;
	
	@Column(name="command",header="事件",size =512)
	private String command;
	
	@Column(name="splitter",header="分隔")
	private boolean splitter = false;
	
	@Column(name="popup",header="下拉项")
	private boolean popup = false;
	
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public boolean isSplitter() {
		return splitter;
	}
	public void setSplitter(boolean splitter) {
		this.splitter = splitter;
	}
	public boolean isPopup() {
		return popup;
	}
	public void setPopup(boolean popup) {
		this.popup = popup;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
}
