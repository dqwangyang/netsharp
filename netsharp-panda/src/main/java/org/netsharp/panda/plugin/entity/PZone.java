package org.netsharp.panda.plugin.entity;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.panda.plugin.doozer.DoozerZone;
import org.netsharp.plugin.core.Doozer;
import org.netsharp.plugin.entity.Codonable;

@Table(name="rs_workbench_zone",orderBy="seq")
@Doozer(type=DoozerZone.class)
public class PZone  extends Codonable{

	private static final long serialVersionUID = -2187928608679125992L;

	@Column(name = "js_controller",size=500, header = "jsController")
	private String jsController;
	
	@Column(name = "service_controller",size=500, header = "serviceController")
	private String serviceController;
	
	@Column(name = "style_path",size=500, header = "stylePath")
	private String stylePath;
	
	@Column(name = "script_path",size=500, header = "scriptPath")
	private String scriptPath;

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

	public String getStylePath() {
		return stylePath;
	}

	public void setStylePath(String stylePath) {
		this.stylePath = stylePath;
	}

	public String getScriptPath() {
		return scriptPath;
	}

	public void setScriptPath(String scriptPath) {
		this.scriptPath = scriptPath;
	}
}
