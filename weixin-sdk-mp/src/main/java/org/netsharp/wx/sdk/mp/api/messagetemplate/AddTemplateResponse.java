package org.netsharp.wx.sdk.mp.api.messagetemplate;

import org.netsharp.wx.sdk.mp.api.Response;

public class AddTemplateResponse extends Response {
	
	private String template_id;
	
    public AddTemplateResponse() {
    	
    }

	public String getTemplate_id() {
		return template_id;
	}

	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}
}