package org.netsharp.panda.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.netsharp.application.Application;
import org.netsharp.panda.controls.layout.LayoutPanel;
import org.netsharp.panda.controls.other.Body;
import org.netsharp.panda.controls.tab.TabItem;
import org.netsharp.panda.controls.utility.UrlHelper;
import org.netsharp.panda.core.comunication.IHtmlWriter;
import org.netsharp.panda.dic.DockType;
import org.netsharp.panda.dic.TabPosition;
import org.netsharp.panda.entity.PPart;

public class Workspace extends HtmlPage {
	
	private List<Part> parts = new ArrayList<Part>();

	@Override
	public void initialize() {

		this.doInitialize();

		super.initialize();

		this.addJscript();
	}

	protected void doInitialize() {
		
		Body layout = this.body;
		layout.setClassName("easyui-layout");

		HashMap<DockType, ArrayList<Part>> groups = WokspaceDockHelper.getDockTypeGroup(this);
		
		for(DockType dockType : groups.keySet()){
			
			ArrayList<Part> parts = groups.get(dockType);
			LayoutPanel panel = new LayoutPanel();
			{
				panel.setId(dockType.getText());
				panel.region = dockType.getText();
				panel.setStyle(parts.get(0).context.getStyle());
				panel.split = false;
			}
			this.createDockPannel(dockType, panel, parts);
			layout.getControls().add(panel);
		}
	}
	
	private void createDockPannel(DockType dockType,LayoutPanel panel,ArrayList<Part> parts){
		
		if (parts.size() == 1) {
			
			Part part = parts.get(0);
			PPart ppart = part.context;
			if (ppart.isHeaderVisible()) {
				panel.title = ppart.getName();
			}

			panel.getControls().add(part);
			this.parts.add(part);
			
		} else {
			
			Parts tab = new Parts();
			{
				tab.setId("tab" + dockType.getText());
				tab.tabPosition = TabPosition.bottom;
				tab.tabWidth = 115;
				tab.tabHeight = 31;
				tab.setStyle("width:auto;");
				tab.dockStyle = dockType;
				tab.workspace = this;
				tab.onSelect = "function(title,index){workspace.onTabSelect(title,index);}";
			}

			if (tab.dockStyle == DockType.DOCUMENTHOST) {
				tab.tabPosition = TabPosition.top;
			}

			tab.setSize();

			for (Part part : parts) {
				
				TabItem tabItem = new TabItem();
				{
					tabItem.setId("tab" + part.context.getCode());
					// IconCls = "icon-query-filter", //PartType需要有默认的图标
					tabItem.title = part.context.getName();
					tabItem.setStyle("padding-bottom:1px;"); // 需要根据不同的停靠，有不同的设置
				}

				tabItem.getControls().add(part);
				tab.getControls().add(tabItem);

				this.parts.add(part);
			}

			panel.getControls().add(tab);
		}
	}
	
	@Override
	protected void importCss(IHtmlWriter writer) {
		
		super.importCss(writer);
		writer.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
		writer.write("<meta name=\"Keywords\" content=\""+Application.getInstance().getName()+"\"/>");
		writer.write("<meta name=\"Description\" content=\""+Application.getInstance().getName()+"\"/>");
		writer.write(UrlHelper.getVersionCss("/package/font-awesome/css/font-awesome.min.css"));
		writer.write(UrlHelper.getVersionCss("/package/easyui/themes/material/easyui.css"));
		writer.write(UrlHelper.getVersionCss("/package/easyui/themes/easyui.extend.css"));
		writer.write(UrlHelper.getVersionCss("/package/easyui/themes/color.css"));
		writer.write(UrlHelper.getVersionCss("/package/easyui/themes/icon.css"));
		//writer.write(UrlHelper.getVersionCss("/panda-res/css/panda.css"));
	}

	@Override
	protected void importJs(IHtmlWriter writer) {
		
		for(Part part : this.parts){
			part.importJsing(writer);
		}

		//
		super.importJs(writer);

		writer.write(UrlHelper.getVersionScript("/package/easyui/jquery.min.js"));
		writer.write(UrlHelper.getVersionScript("/package/layer/layer.js"));
		writer.write(UrlHelper.getVersionScript("/package/easyui/jquery.easyui.min.js"));
		writer.write(UrlHelper.getVersionScript("/package/easyui/locale/easyui-lang-zh_CN.js"));
		writer.write(UrlHelper.getVersionScript("/package/easyui/jquery.easyui.extend.js"));
		writer.write(UrlHelper.getVersionScript("/package/lodop/LodopFuncs.js"));
		writer.write(UrlHelper.getVersionScript("/package/ajaxfileupload/ajaxfileupload.min.js"));
		writer.write(UrlHelper.getVersionScript("/package/jquery/jquery.md5.js"));
		writer.write(UrlHelper.getVersionScript("/panda-res/js/system.js"));
		writer.write(UrlHelper.getVersionScript("/panda-res/js/panda.core.js"));
		writer.write(UrlHelper.getVersionScript("/panda-res/js/panda.js"));
		//
		for(Part part : this.parts){
			part.importJs(writer);
		}
		
		for(Part part : this.parts){
			part.importExtendJs(writer);
		}
		
	}

	protected void addJscript() {
		this.addJscript("        ", JscriptType.Header);
		this.addJscript("        //", JscriptType.Header);

		this.addJscript("        var " + getJsController() + " = new org.netsharp.panda.core.Workspace();", JscriptType.Header);

		ArrayList<String> controllers = new ArrayList<String>();
		for (PPart part : getPworkspace().getParts()) {
			if (!part.isVisible()) {
				continue;
			}

			String jscontroller = "controller" + part.getCode();
			this.addJscript("        " + getJsController() + ".addpart('" + jscontroller + "'," + jscontroller + ");", JscriptType.Header);

			controllers.add(jscontroller);
			PPart parent = part.parent();
			if (parent != null) {
				
				if (!parent.isVisible()) {
					continue;
				}
				
				String partent = "controller" + parent.getCode();
				this.addJscript("        " + jscontroller + ".context.relationRole='" + part.getRelationRole() + "';", JscriptType.Header);
				this.addJscript("        " + jscontroller + ".parent=" + partent + ";", JscriptType.Header);
				this.addJscript("        " + partent + ".subs.push(" + jscontroller + ");", JscriptType.Header);
			}
		}

		for (String controller : controllers) {
			this.addJscript("            " + controller + ".onload();", JscriptType.Initialize);
		}
	}

	protected String getJsController() {
		return "workspace";
	}
}
