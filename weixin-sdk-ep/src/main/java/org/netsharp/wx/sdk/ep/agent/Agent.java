package org.netsharp.wx.sdk.ep.agent;

public class Agent {
	
	private Integer agentid;         //应用id
	private String name;             //应用名称
	private String square_logo_url;  //方形头像url
	private String round_logo_url;   //圆形头像url
	
	public Integer getAgentid() {
		return agentid;
	}
	public void setAgentid(Integer agentid) {
		this.agentid = agentid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSquare_logo_url() {
		return square_logo_url;
	}
	public void setSquare_logo_url(String square_logo_url) {
		this.square_logo_url = square_logo_url;
	}
	public String getRound_logo_url() {
		return round_logo_url;
	}
	public void setRound_logo_url(String round_logo_url) {
		this.round_logo_url = round_logo_url;
	}
	
}
