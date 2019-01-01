package org.netsharp.wx.sdk.mp.message.response;

import org.netsharp.wx.sdk.mp.message.RequestMessage;
import org.netsharp.wx.sdk.mp.message.ResponseMessage;

public class VideoResponse extends ResponseMessage
{
	private String mediaId;
	private String title;
	private String description;
	
	public VideoResponse()
	{
		super("video");
	}

	public VideoResponse(RequestMessage message)
	{
		super("video", message);
	}

	
	public final String getMediaId()
	{
		return mediaId;
	}
	public final void setMediaId(String value)
	{
		mediaId = value;
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

	@Override
	protected String InnerToxml()
	{
		String baseXml = super.InnerToxml();

		return baseXml + "<Video><MediaId>" + this.getMediaId() + "</MediaId><Title>" + this.getTitle() + "</Title><Description>" + this.getDescription() + "</Description></Video> ";
	}
}