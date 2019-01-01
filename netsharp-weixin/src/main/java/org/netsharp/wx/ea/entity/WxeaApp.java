package org.netsharp.wx.ea.entity;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.BizEntity;

/**
 * 微信应用配置，同一个企业号下，不同的应用corpid相同，但是corpsecret各不相同
 */
@Table(name = "wx_ea_configuration", header = "微信应用配置")
public class WxeaApp extends BizEntity {

	private static final long serialVersionUID = -3184625124915438033L;
	
	@Column(name="agent_id",header="应用Id")
	private Integer agentId;
	
	@Column(name="corp_id")
	private String corpid;
	
	@Column(name="corpsecret",size=200)
	private String corpsecret;

	public String getCorpid() {
		return corpid;
	}

	public void setCorpid(String corpid) {
		this.corpid = corpid;
	}

	public String getCorpsecret() {
		return corpsecret;
	}

	public void setCorpsecret(String corpsecret) {
		this.corpsecret = corpsecret;
	}

	public Integer getAgentId() {
		return agentId;
	}

	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}

}
