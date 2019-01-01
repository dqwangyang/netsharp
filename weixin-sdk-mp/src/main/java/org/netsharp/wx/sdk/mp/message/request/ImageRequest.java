package org.netsharp.wx.sdk.mp.message.request;

import org.netsharp.wx.sdk.mp.message.RequestMessage;

public class ImageRequest extends RequestMessage
{
	private String mediaId;
	private String picUrl;
	
	public final String getMediaId()
	{
		return mediaId;
	}
	public final void setMediaId(String value)
	{
		mediaId = value;
	}
	
	
	public final String getPicUrl()
	{
		return picUrl;
	}
	public final void setPicUrl(String value)
	{
		picUrl = value;
	}


	@Override
	public String toString()
	{
		String baseString = super.toString();

		return baseString + ";MediaId" + this.getMediaId() + ";PicUrl" + getPicUrl();
	}
}