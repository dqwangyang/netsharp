package org.netsharp.sms.mandao.entity;

import org.netsharp.sms.client.SmsChannelType;

public class SmsContext {

	private SmsChannelType smsChannelType;
	private String uid;
	private String pwd;
	private String signature;

	public SmsChannelType getSmsChannelType() {
		return smsChannelType;
	}

	public void setSmsChannelType(SmsChannelType smsChannelType) {
		this.smsChannelType = smsChannelType;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

}
