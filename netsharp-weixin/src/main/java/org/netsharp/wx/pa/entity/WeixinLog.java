package org.netsharp.wx.pa.entity;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

/*粉丝请求日志*/
@Table(name="wx_pa_log",header="微信访问日志")
public class WeixinLog  extends WeixinEntity {
	
	private static final long serialVersionUID = -5351718286452312352L;
	
	@Column(name = "msg_type", header = "消息类型")
	private String msgType;
	
	@Column(name = "msg_id", header = "消息Id")
	private String msgId;
	
	@Column(name = "to_user_name", header = "接收用户")
	private String toUserName;
	
	@Column(name = "from_user_name", header = "发送用户")
	private String fromUserName;
	
	@Column(name = "hit", header = "hit")
	private Boolean hit;
	
	@Column(name = "keywords",size=500, header = "系统数据")
	private String keywords;
	
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	public String getToUserName() {
		return toUserName;
	}
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}
	public String getFromUserName() {
		return fromUserName;
	}
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public Boolean getHit() {
		return hit;
	}
	public void setHit(Boolean hit) {
		this.hit = hit;
	}
}
