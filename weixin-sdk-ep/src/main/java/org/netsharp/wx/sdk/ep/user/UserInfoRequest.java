package org.netsharp.wx.sdk.ep.user;

import org.netsharp.wx.sdk.ep.Request;

public class UserInfoRequest extends Request<UserInfoResponse>{

	private String code;
	
	public UserInfoRequest() {
		super();
		this.responseType = UserInfoResponse.class;
	}
	
	@Override
	public String getUrl() {
		
		return "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token="+this.getAccessToken()+"&code=" + this.getCode();
	}
	
	@Override
	protected UserInfoResponse doResponse() {

		UserInfoResponse response = this.executeHttpGet();
		return response;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
