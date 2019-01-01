package org.netsharp.wx.sdk.ep.menu;

import org.netsharp.wx.sdk.ep.Request;


public class MenuGetRequest extends Request<MenuGetResponse> {
	
	private Integer agentid;

	public MenuGetRequest() {
		super();
		this.responseType = MenuGetResponse.class;
	}
	
	@Override
	protected MenuGetResponse doResponse() {
		
		MenuGetResponse response =this.executeHttpGet();
		return response;
	}


	@Override
	public String getUrl() {
		return "https://qyapi.weixin.qq.com/cgi-bin/menu/get?access_token="+this.getAccessToken()+"&agentid="+this.getAgentid();
	}

	public Integer getAgentid() {
		return agentid;
	}

	public void setAgentid(Integer agentid) {
		this.agentid = agentid;
	}
}
