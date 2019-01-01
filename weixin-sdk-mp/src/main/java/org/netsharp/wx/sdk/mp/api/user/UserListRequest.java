package org.netsharp.wx.sdk.mp.api.user;

import org.netsharp.wx.sdk.mp.WebClient;
import org.netsharp.wx.sdk.mp.api.Request;

//[Api("获取关注者列表")]
public class UserListRequest extends Request<UserListResponse>
{

	@Override
	public String getUrl()
	{
		return String.format("https://api.weixin.qq.com/cgi-bin/user/get?access_token=%1$s", this.getAccessToken());
	}

	private String PagedUrl(String nextId)
	{
		return String.format("https://api.weixin.qq.com/cgi-bin/user/get?access_token=%1$s&next_openid=%2$s", this.getAccessToken(), nextId);
	}


	@Override
	protected UserListResponse doResponse()
	{
		WebClient client = new WebClient();
		String json = client.downloadString(this.getUrl());

		logger.info(json);

		UserListResponse ulr = this.deserialize(json);

		if (ulr.getCount() < ulr.getTotal())
		{
			do
			{
				String pageJson = client.downloadString(this.PagedUrl(ulr.getNext_openid()));

				UserListResponse newUlr = this.deserialize(pageJson);

				ulr.setCount(ulr.getCount() + newUlr.getCount());

				ulr.getData().getOpenId().addAll(newUlr.getData().getOpenId());
				ulr.setNext_openid(newUlr.getNext_openid());
			}
			while (ulr.getCount() >= ulr.getTotal());
		}

		return ulr;
	}
}