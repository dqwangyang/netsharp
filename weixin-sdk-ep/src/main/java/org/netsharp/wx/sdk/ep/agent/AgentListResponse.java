package org.netsharp.wx.sdk.ep.agent;

import java.util.List;

import org.netsharp.wx.sdk.ep.Response;

public class AgentListResponse extends Response {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5184383628963700719L;
	private List<Agent> agentlist;

	public List<Agent> getAgentlist() {
		return agentlist;
	}

	public void setAgentlist(List<Agent> agentlist) {
		this.agentlist = agentlist;
	}
	
}
