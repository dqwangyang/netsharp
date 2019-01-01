package org.netsharp.wx.ea.entity;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;
import org.netsharp.organization.entity.Employee;

@Table(name = "wx_ea_message_receiver", header = "企业号消息接收人")
public class WxeaMessageReciver extends Entity {

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -1774687311711457661L;

	@Column(name = "pmconfig_id",header="平台消息id")
	private Integer pmConfigId;
	
	@Reference(foreignKey="pmConfigId")
    @JsonIgnore
	private WxeaMessage pmConfig;
	
	@Column(name = "receiver_id",header="接收人id")
	private Integer receiverId;
	
	@Reference(foreignKey="receiverId")
	private Employee receiver;

	public Integer getPmConfigId() {
		return pmConfigId;
	}

	public void setPmConfigId(Integer pmConfigId) {
		this.pmConfigId = pmConfigId;
	}
	
	public WxeaMessage getPmConfig() {
		return pmConfig;
	}

	public void setPmConfig(WxeaMessage pmConfig) {
		this.pmConfig = pmConfig;
	}

	public Integer getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(Integer receiverId) {
		this.receiverId = receiverId;
	}

	public Employee getReceiver() {
		return receiver;
	}

	public void setReceiver(Employee receiver) {
		this.receiver = receiver;
	}
}
