package org.netsharp.wx.sdk.mp.pay;

public class FeedBackPicUrl
{
	private String picUrl;
	
	public FeedBackPicUrl()
	{
	}

	public FeedBackPicUrl(String url)
	{
		this.setPicUrl(url);
	}
	
	public final String getPicUrl()
	{
		return picUrl;
	}
	public final void setPicUrl(String value)
	{
		picUrl = value;
	}
}