package org.netsharp.wx.sdk.ep.user;

import org.netsharp.util.JsonManage;
import org.netsharp.wx.sdk.ep.Request;

public class UserUpdateRequest  extends Request<UserUpdateResponse> {

	private User user ;

	public UserUpdateRequest() {
		super();
		this.responseType = UserUpdateResponse.class;
	}

	@Override
	protected UserUpdateResponse doResponse() {
		
		String json = JsonManage.serialize2( this.user );
		
		UserUpdateResponse response =this.executeHttpPost(json);

		return response;
	}

	@Override
	public String getUrl() {
		return "https://qyapi.weixin.qq.com/cgi-bin/user/update?access_token=" + this.getAccessToken();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
