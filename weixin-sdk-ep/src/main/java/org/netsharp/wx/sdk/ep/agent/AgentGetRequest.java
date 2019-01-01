package org.netsharp.wx.sdk.ep.agent;

import org.netsharp.wx.sdk.ep.Request;

public class AgentGetRequest extends Request<AgentGetResponse> {

	private Integer agentid;

	public AgentGetRequest() {
		super();
		this.responseType = AgentGetResponse.class;
	}

	@Override
	protected AgentGetResponse doResponse() {
		
		AgentGetResponse response =this.executeHttpGet();

		return response;
	}

	@Override
	public String getUrl() {
		return "https://qyapi.weixin.qq.com/cgi-bin/agent/get?access_token=" + this.getAccessToken()+"&agentid="+this.getAgentid();
	}

	public Integer getAgentid() {
		return agentid;
	}

	public void setAgentid(Integer agentid) {
		this.agentid = agentid;
	}
}