package org.netsharp.panda.core.workbench;

import org.netsharp.application.Application;
import org.netsharp.panda.controls.layout.LayoutPanel;
import org.netsharp.panda.controls.layout.LayoutRegion;
import org.netsharp.panda.controls.other.Body;
import org.netsharp.panda.controls.tab.Tab;
import org.netsharp.panda.controls.utility.UrlHelper;
import org.netsharp.panda.core.HttpContext;
import org.netsharp.panda.core.JscriptType;
import org.netsharp.panda.core.Workspace;
import org.netsharp.panda.core.comunication.IHtmlWriter;
import org.netsharp.panda.core.comunication.IRequest;
import org.netsharp.panda.session.PandaSessionPersister;

public class Workbench extends Workspace {

	Boolean isTabs = true;// 是否多文档模式
	private String favicon = "/favicon.ico";
	
	@Override
	public void preInitialize() {
		
		if(!PandaSessionPersister.logined()) {
			IRequest request = HttpContext.getCurrent().getRequest();
			String url = "/nav/panda-bizbase/authorization/login";
			request.sendRedirect(url);
		}
	}

	@Override
	protected void doInitialize() {

		Body layout = this.body;
		layout.getInnerValues().put("isTabs", isTabs.toString());
		layout.setClassName("easyui-layout");
		layout.getControls().add(new WorkbenchHeader());
		layout.getControls().add(new WorkbenchPadHost());

		LayoutPanel center = new LayoutPanel();
		{
			center.setId("center1");
			center.region = LayoutRegion.Center;
		}

		if (isTabs) {
			Tab tab = new Tab();
			{
				tab.setId("tabs");
				tab.fit = true;
				tab.tabHeight = 35;
			}
			center.getControls().add(tab);
		}
		layout.getControls().add(center);
	}

	@Override
	public void initialize() {

		super.initialize();
		this.title = Application.getInstance().getName();
	}

	@Override
	protected void importCss(IHtmlWriter writer) {
		
		super.importCss(writer);
		writer.write("    <link href='" + UrlHelper.getUrl("/panda-res/css/workbench.css") + "' rel='stylesheet' type='text/css' />");
	}

	@Override
	protected void importJs(IHtmlWriter writer) {

		super.importJs(writer);
		writer.write("    <link href='" + UrlHelper.getUrl(this.favicon) + "' rel='shortcut icon' type='image/x-icon' />");
		writer.write(UrlHelper.getVersionScript("/panda-res/js/workbench.js", false));
	}
	
	@Override
	protected void addJscript() {
		
		super.addJscript();
		this.addJscript("        var workbench = new org.netsharp.panda.Workbench();", JscriptType.Header);
		this.addJscript("       $(function(){", JscriptType.Header);
		this.addJscript("        workbench.init();", JscriptType.Header);
		this.addJscript("       });", JscriptType.Header);
	}
}
