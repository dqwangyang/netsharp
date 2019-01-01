package org.netsharp.sms.mandao.entity;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

/*短信发送日志*/
@Table(name = "sys_sms_log",header="短信日志")
public class SmsLog extends Entity {

	private static final long serialVersionUID = 834815590520584866L;

	@Column(name = "mobiles", size = 20000)
	private String mobile;

	@Column(size = 1000)
	private String content;

	private String type;

	@Column(name="channel")
	private String channel;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}
}
