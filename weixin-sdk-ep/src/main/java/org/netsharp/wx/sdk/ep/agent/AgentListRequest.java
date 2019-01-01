package org.netsharp.wx.sdk.ep.agent;

import org.netsharp.wx.sdk.ep.Request;

public class AgentListRequest  extends Request<AgentListResponse> {

	public AgentListRequest() {
		super();
		this.responseType = AgentListResponse.class;
	}

	@Override
	protected AgentListResponse doResponse() {
		
		AgentListResponse response =this.executeHttpGet();

		return response;
	}

	@Override
	public String getUrl() {
		return "https://qyapi.weixin.qq.com/cgi-bin/agent/list?access_token=" + this.getAccessToken();
	}
}