package org.netsharp.wx.sdk.ep.user;

import org.netsharp.wx.sdk.ep.Request;

public class UserLoginRequest extends Request<UserLoginResponse> {

	private String json;

	public UserLoginRequest() {
		super();
		this.responseType = UserLoginResponse.class;
	}

	@Override
	protected UserLoginResponse doResponse() {

		UserLoginResponse response = this.executeHttpPost(json);

		return response;
	}

	@Override
	public String getUrl() {
		return "https://qyapi.weixin.qq.com/cgi-bin/service/get_login_info?access_token=" + this.getAccessToken();
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

}
