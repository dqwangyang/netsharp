package org.netsharp.wx.sdk.mp.message.request;

import org.netsharp.wx.sdk.mp.message.RequestMessage;

public class VideoRequest extends RequestMessage
{
	private String mediaId;
	private String thumbMediaId;
	
	public final String getMediaId()
	{
		return mediaId;
	}
	public final void setMediaId(String value)
	{
		mediaId = value;
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
	public String toString()
	{
		String baseString = super.toString();

		return baseString + ";MediaId" + this.getMediaId() + ";ThumbMediaId" + getThumbMediaId();
	}
}