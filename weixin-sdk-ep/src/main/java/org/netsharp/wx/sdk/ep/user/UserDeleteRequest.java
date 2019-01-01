package org.netsharp.wx.sdk.ep.user;

import org.netsharp.wx.sdk.ep.Request;

public class UserDeleteRequest extends Request<UserDeleteResponse> {

	private String userid;

	public UserDeleteRequest() {
		super();
		this.responseType = UserDeleteResponse.class;
	}

	@Override
	protected UserDeleteResponse doResponse() {

		UserDeleteResponse response = this.executeHttpGet();

		return response;
	}

	@Override
	public String getUrl() {
		return "https://qyapi.weixin.qq.com/cgi-bin/user/delete?access_token=" + this.getAccessToken() + "&userid=" + this.getUserid();
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

}
