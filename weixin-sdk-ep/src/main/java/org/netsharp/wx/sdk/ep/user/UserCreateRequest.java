package org.netsharp.wx.sdk.ep.user;

import org.netsharp.util.JsonManage;
import org.netsharp.wx.sdk.ep.Request;

public class UserCreateRequest  extends Request<UserCreateResponse> {
	
	private User user ;

	public UserCreateRequest() {
		super();
		this.responseType = UserCreateResponse.class;
	}

	@Override
	protected UserCreateResponse doResponse() {
		
		String json = JsonManage.serialize2(this.user);
		
		UserCreateResponse response =this.executeHttpPost(json);

		return response;
	}

	@Override
	public String getUrl() {
		return "https://qyapi.weixin.qq.com/cgi-bin/user/create?access_token=" + this.getAccessToken();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
