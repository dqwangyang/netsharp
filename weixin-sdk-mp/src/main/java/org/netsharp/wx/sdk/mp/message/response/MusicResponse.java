package org.netsharp.wx.sdk.mp.message.response;

import org.netsharp.wx.sdk.mp.message.RequestMessage;
import org.netsharp.wx.sdk.mp.message.ResponseMessage;

public class MusicResponse extends ResponseMessage
{
	private String title;
	private String description;
	private String musicUrl;
	private String hQMusicUrl;
	private String thumbMediaId;
	
	public MusicResponse()
	{
		super("music");

	}

	public MusicResponse(RequestMessage message)
	{
		super("music", message);

	}


	
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
	
	public final String getMusicUrl()
	{
		return musicUrl;
	}
	public final void setMusicUrl(String value)
	{
		musicUrl = value;
	}
	
	public final String getHQMusicUrl()
	{
		return hQMusicUrl;
	}
	public final void setHQMusicUrl(String value)
	{
		hQMusicUrl = value;
	}
	
	public final String getThumbMediaId()
	{
		return thumbMediaId;
	}
	public final void setThumbMediaId(String value)
	{
		thumbMediaId = value;
	}

	@Override
	protected String InnerToxml()
	{
		String baseXml = super.InnerToxml();


		return baseXml + "<Music><Title>" + this.getTitle() + "</Title><Description>" + this.getDescription() + "</Description><MusicUrl>" + this.getMusicUrl() + "</MusicUrl><HQMusicUrl>" + this.getHQMusicUrl() + "</HQMusicUrl><ThumbMediaId>" + this.getThumbMediaId() + "</ThumbMediaId></Music>";

	}
}