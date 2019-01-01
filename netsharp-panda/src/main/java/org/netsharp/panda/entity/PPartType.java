package org.netsharp.panda.entity;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.BizEntity;
import org.netsharp.panda.dic.DockType;

@Table(name = "ui_pa_part_type",header="部件类型")
public class PPartType extends BizEntity {

	private static final long serialVersionUID = 948989219868739528L;
	
	@Column(name="dock_style",header="停靠位置")
	protected DockType dockStyle;
	
	@Column(name="js_controller",header="js控制器",size=200)
	protected String jsController;
	
	@Column(name="service_controller",header="java控制器",size=200)
	protected String serviceController;
	
	@Column(name="toolbar",header="工具栏")
	protected String toolbar;
	
	@Column(name="style",header="样式（一般设置宽度和高度）")
	protected String style;
	
	@Column(name="auxilary",header="辅部件")
	protected boolean auxilary = false;
	
	@Column(name="main",header="主部件")
	protected boolean main = false;
	
	public DockType getDockStyle() {
		return dockStyle;
	}
	public void setDockStyle(DockType dockStyle) {
		this.dockStyle = dockStyle;
	}
	public String getJsController() {
		return jsController;
	}
	public void setJsController(String jsController) {
		this.jsController = jsController;
	}
	public String getServiceController() {
		return serviceController;
	}
	public void setServiceController(String serviceController) {
		this.serviceController = serviceController;
	}
	public String getToolbar() {
		return toolbar;
	}
	public void setToolbar(String toolbar) {
		this.toolbar = toolbar;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public boolean isAuxilary() {
		return auxilary;
	}
	public void setAuxilary(boolean auxilary) {
		this.auxilary = auxilary;
	}
	public boolean isMain() {
		return main;
	}
	public void setMain(boolean main) {
		this.main = main;
	}
}
