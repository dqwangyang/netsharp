package org.netsharp.wx.sdk.mp.api.accesstoken;

import org.netsharp.wx.sdk.mp.api.Response;

public class AccessTokenResponse extends Response
{
	private String access_token;        //获取到的凭证
	private int expires_in;             //凭证有效时间，单位：秒
	
	public final String getAccess_token()
	{
		return access_token;
	}
	public final void setAccess_token(String value)
	{
		access_token = value;
	}
	public final int getExpires_in()
	{
		return expires_in;
	}
	public final void setExpires_in(int value)
	{
		expires_in = value;
	}

}