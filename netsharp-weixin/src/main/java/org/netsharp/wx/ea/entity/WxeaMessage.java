package org.netsharp.wx.ea.entity;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Subs;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.BizEntity;

/**
 * 微信应用配置，不同的应用
 */
@Table(name = "wx_ea_message", header = "企业号消息配置")
public class WxeaMessage extends BizEntity {

	private static final long serialVersionUID = 7219736327204750969L;
	
	@Column(name = "wxpa_configuration_id")
	private Integer wxpaConfigurationId;
	
	//应用
	@Reference(foreignKey = "wxpaConfigurationId")
	private WxeaApp wxpaConfiguration;
	
	@Column(name = "entity_name")
	private String entityName;
	
	@Column(name = "msg_content")
	private String msgContent;
	
	@Column(name = "to_user", size = 2000)
	private String toUser;
	
	@Subs(subType = WxeaMessageReciver.class, foreignKey = "pmConfigId", header="固定消息接收人")
	private List<WxeaMessageReciver> fixReceivers = new ArrayList<WxeaMessageReciver>();

	public Integer getWxpaConfigurationId() {
		return wxpaConfigurationId;
	}

	public void setWxpaConfigurationId(Integer wxpaConfigurationId) {
		this.wxpaConfigurationId = wxpaConfigurationId;
	}

	public WxeaApp getWxpaConfiguration() {
		return wxpaConfiguration;
	}

	public void setWxpaConfiguration(WxeaApp wxpaConfiguration) {
		this.wxpaConfiguration = wxpaConfiguration;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	public String getToUser() {
		return toUser;
	}

	public void setToUser(String toUser) {
		this.toUser = toUser;
	}

	public List<WxeaMessageReciver> getFixReceivers() {
		return fixReceivers;
	}

	public void setFixReceivers(List<WxeaMessageReciver> fixReceivers) {
		this.fixReceivers = fixReceivers;
	}
}
