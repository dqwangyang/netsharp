package org.netsharp.wx.sdk.ep.user;

import org.netsharp.wx.sdk.ep.Response;

public class UserInviteResponse extends Response {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4595155830200931257L;
	private Integer type;//1:微信邀请 2.邮件邀请

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}
