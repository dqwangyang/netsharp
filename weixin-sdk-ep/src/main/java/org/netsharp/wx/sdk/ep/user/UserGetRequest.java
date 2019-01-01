package org.netsharp.wx.sdk.ep.user;

import org.netsharp.wx.sdk.ep.Request;

public class UserGetRequest extends Request<UserGetResponse> {

	private String userid;

	public UserGetRequest() {
		super();
		this.responseType = UserGetResponse.class;
	}

	@Override
	protected UserGetResponse doResponse() {

		UserGetResponse response = this.executeHttpGet();

		return response;
	}

	@Override
	public String getUrl() {
		return "https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token=" + this.getAccessToken() + "&userid=" + this.userid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

}
