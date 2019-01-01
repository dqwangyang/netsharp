package org.netsharp.wx.sdk.ep.accesstoken;

import java.util.Date;

import org.netsharp.util.StringManager;

public class AccessToken {

	private String corpid ;       //in
	private String corpsecret ;   //in
	private String access_token;  //out
	private int expires_in;       //out 凭证的有效时间（秒）
	private Date createTime;      //customer

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

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public int getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public final boolean getIsExpired() {
		
		if (StringManager.isNullOrEmpty(this.getAccess_token())) {
			return true;
		}

		if (getCreateTime() == null) {
			return true;
		}

		// 提前一分钟让token过期，并重新获取
		long ts = new Date().getTime() - getCreateTime().getTime();
		if (ts > 7200 * 1000 - 60 * 1000) { 
			return true;
		}

		return false;
	}
}
