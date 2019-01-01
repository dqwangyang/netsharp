package org.netsharp.wx.sdk.ep.user;

import java.util.HashMap;
import java.util.Map;

import org.netsharp.util.JsonManage;
import org.netsharp.wx.sdk.ep.Request;

public class UserDeletesRequest  extends Request<UserDeletesResponse> {
	
	private String[] useridlist;

	public UserDeletesRequest() {
		super();
		this.responseType = UserDeletesResponse.class;
	}

	@Override
	protected UserDeletesResponse doResponse() {
		
		Map<String,Object> item = new HashMap<String,Object>();
		{
			item.put("useridlist", this.useridlist);
		}
		
		String json = JsonManage.serialize2(item);
		
		UserDeletesResponse response =this.executeHttpPost(json);

		return response;
	}

	@Override
	public String getUrl() {
		return "https://qyapi.weixin.qq.com/cgi-bin/user/batchdelete?access_token=" + this.getAccessToken();
	}

}
