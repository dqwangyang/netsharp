package org.netsharp.wx.sdk.mp.api.menu;

import org.netsharp.wx.sdk.mp.api.Request;
import org.netsharp.wx.sdk.mp.api.Response;

//[Api("删除自定义菜单")]
public class MenuDeleteRequest extends Request<Response>
{
	@Override
	public String getUrl()
	{
		return String.format("https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=%1$s", this.getAccessToken());
	}


	@Override
	protected Response doResponse() 
	{
		return this.executeHttpGet();
	}
}