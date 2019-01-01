package org.netsharp.wx.sdk.mp;

/** 
 微信客户端信息
 
*/
public class WxClientVersion
{
	/** 
	 是否微信浏览器
	 
	*/
	private boolean privateIsWxBrowser;
	public final boolean getIsWxBrowser()
	{
		return privateIsWxBrowser;
	}
	public final void setIsWxBrowser(boolean value)
	{
		privateIsWxBrowser = value;
	}

	/** 
	 手机客户端类型目前只有：Android,Iphone
	 
	*/
	private String privatePhoneOs;
	public final String getPhoneOs()
	{
		return privatePhoneOs;
	}
	public final void setPhoneOs(String value)
	{
		privatePhoneOs = value;
	}

	/** 
	 是否支持微信支付
	 
	*/
	private boolean privateCanPay;
	public final boolean getCanPay()
	{
		return privateCanPay;
	}
	public final void setCanPay(boolean value)
	{
		privateCanPay = value;
	}
	/** 
	 手机客户端版本
	 
	*/
	private String privateWxVersion;
	public final String getWxVersion()
	{
		return privateWxVersion;
	}
	public final void setWxVersion(String value)
	{
		privateWxVersion = value;
	}
}