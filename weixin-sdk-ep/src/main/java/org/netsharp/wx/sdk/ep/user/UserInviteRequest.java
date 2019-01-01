package org.netsharp.wx.sdk.ep.user;

import java.util.HashMap;
import java.util.Map;

import org.netsharp.util.JsonManage;
import org.netsharp.wx.sdk.ep.Request;

public class UserInviteRequest  extends Request<UserInviteResponse> {

	private Integer userid;

	public UserInviteRequest() {
		super();
		this.responseType = UserInviteResponse.class;
	}

	@Override
	protected UserInviteResponse doResponse() {
		
		Map<String,Object> item = new HashMap<String,Object>();
		{
			item.put("userid", this.getUserid());
		}
		
		String json = JsonManage.serialize2(item);
		
		UserInviteResponse response =this.executeHttpPost(json);

		return response;
	}

	@Override
	public String getUrl() {
		return "https://qyapi.weixin.qq.com/cgi-bin/invite/send?access_token=" + this.getAccessToken();
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

}
