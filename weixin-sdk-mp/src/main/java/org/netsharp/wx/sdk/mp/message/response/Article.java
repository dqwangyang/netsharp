package org.netsharp.wx.sdk.mp.message.response;

public class Article
{
	private String title;
	private String description;
	private String picUrl;
	private String url;
	
	public final String getTitle()
	{
		return title;
	}
	public final void setTitle(String value)
	{
		title = value;
	}
	
	public final String getDescription()
	{
		return description;
	}
	public final void setDescription(String value)
	{
		description = value;
	}
	public final String getPicUrl()
	{
		return picUrl;
	}
	public final void setPicUrl(String value)
	{
		picUrl = value;
	}
	public final String getUrl()
	{
		return url;
	}
	public final void setUrl(String value)
	{
		url = value;
	}
}