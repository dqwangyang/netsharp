package org.netsharp.wx.sdk.mp.api.menu;

import org.netsharp.wx.sdk.mp.api.Request;

//[Api("查询自定义菜单")]
public class MenuQueryRequest extends Request<MenuQueryResponse>
{
	@Override
	public String getUrl()
	{
		return String.format("https://api.weixin.qq.com/cgi-bin/menu/get?access_token=%1$s", this.getAccessToken());
	}

	@Override
	protected MenuQueryResponse doResponse() 
	{
		return this.executeHttpGet();
	}
}