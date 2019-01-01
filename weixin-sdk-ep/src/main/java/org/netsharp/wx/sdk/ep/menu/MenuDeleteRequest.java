package org.netsharp.wx.sdk.ep.menu;

import org.netsharp.wx.sdk.ep.Request;

public class MenuDeleteRequest extends Request<MenuDeleteResponse> {
	
	private Integer agentid;

	public MenuDeleteRequest() {
		super();
		this.responseType = MenuDeleteResponse.class;
	}
	
	@Override
	protected MenuDeleteResponse doResponse() {
		
		MenuDeleteResponse response =this.executeHttpGet();
		return response;
	}


	@Override
	public String getUrl() {
		return "https://qyapi.weixin.qq.com/cgi-bin/menu/delete?access_token="+this.getAccessToken()+"&agentid="+this.getAgentid();
	}

	public Integer getAgentid() {
		return agentid;
	}

	public void setAgentid(Integer agentid) {
		this.agentid = agentid;
	}
}
