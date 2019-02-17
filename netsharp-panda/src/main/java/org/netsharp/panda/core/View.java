package org.netsharp.panda.core;

import org.netsharp.base.IPersistableService;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.entity.IPersistable;
import org.netsharp.panda.controls.other.Div;
import org.netsharp.panda.controls.toolbar.Toolbar;
import org.netsharp.panda.controls.utility.UrlHelper;
import org.netsharp.panda.core.comunication.IHtmlWriter;
import org.netsharp.panda.entity.PPart;
import org.netsharp.panda.plugin.ToolbarService;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.plugin.base.IPathServcie;
import org.netsharp.plugin.entity.Pathable;
import org.netsharp.util.StringManager;

public class View extends Div {
	
	protected PPart context;
	protected Toolbar toolbar;
	protected IPersistableService<IPersistable> service;

	protected void initToolbar() {

		if (StringManager.isNullOrEmpty(context.getToolbar())) {
			return;
		}

		toolbar = ToolbarService.create(this.context.getWorkspace().getResourceNode(), context.getToolbar(), this.getJsInstance());
		if(toolbar.getControls().size() > 0){

			toolbar.setId(this.getJsInstance() + "Toolbar");
			toolbar.populateSubs(this);
			this.getControls().add(toolbar);

			String prefix = this.getJsInstance();
			String str = toolbar.getId(prefix);
			
	    	IPathServcie pst = ServiceFactory.create(IPathServcie.class);
	    	Pathable pathable = pst.byPath(context.getToolbar(), PToolbar.class.getName());
	    	
	    	if(pathable != null){
	    		this.addJscript("            " + this.getJsInstance() + ".context.toolbarId =\""+pathable.getId()+"\";", JscriptType.Initialize);
	    	}
			this.addJscript("            " + this.getJsInstance() + ".context.toolbar = new org.netsharp.controls.Toolbar();", JscriptType.Initialize);
			this.addJscript("            " + this.getJsInstance() + ".context.toolbar.items = " + str, JscriptType.Initialize);
			this.addJscript("            ", JscriptType.Initialize);
			this.addJscript("            // ", JscriptType.Initialize);

			str = toolbar.getIds();
			this.addJscript("            " + this.getJsInstance() + ".collectStatusControl(" + str + "); ", JscriptType.Initialize);
			//this.addJscript("            " + this.getJsInstance() + ".setState(); ", JscriptType.Initialize);
		}
	}

	public String getJsInstance() {
		return "controller" + context.getCode();
	}

	public String getJsController() {
		if (StringManager.isNullOrEmpty(this.context.getJsController())) {
			return this.getClass().getName();
		} else {
			return this.context.getJsController();
		}
	}

	protected void addJscript(String jscript, JscriptType jsType) {

	}

	protected void importJsing(IHtmlWriter writer) {
		
	}

	protected void importJs(IHtmlWriter writer) {

		writer.write(UrlHelper.getVersionScript("/panda-res/js/panda.controls.js"));
		writer.write(UrlHelper.getVersionScript("/panda-res/js/panda.sd.js"));
	}
	
	protected void importExtendJs(IHtmlWriter writer){
		
		if (StringManager.isNullOrEmpty(this.context.getImports())) {
			return;
		}

		String[] imports = this.context.getImports().split("\\|");

		for (String imp : imports) {
			writer.write("    " + UrlHelper.getVersionScript(imp));
		}
	}

	public void ClearCache() {
		// HtmlPageFactory.Clear();
	}

	public PPart getContext() {
		return this.context;
	}

	public void setContext(PPart context) {
		this.context = context;
	}

	public IPersistableService<IPersistable> getService() {

		if (service == null) {
			service = ServiceFactory.create(this.context.getService());
		}

		return service;
	}
}
