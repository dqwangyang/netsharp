package org.netsharp.wx.sdk.mp.message.request;

import org.netsharp.wx.sdk.mp.message.RequestMessage;

public class LinkRequest extends RequestMessage
{
	private String title;
	private String description;
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
	
	public final String getUrl()
	{
		return url;
	}
	public final void setUrl(String value)
	{
		url = value;
	}


	@Override
	public String toString()
	{
		String baseString = super.toString();

		return baseString + ";Title" + this.getTitle() + ";Description" + getDescription() + ";Url" + getUrl();
	}

}