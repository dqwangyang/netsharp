package org.netsharp.wx.sdk.mp.message.response;

import org.netsharp.wx.sdk.mp.message.RequestMessage;
import org.netsharp.wx.sdk.mp.message.ResponseMessage;

public class ImageResponse extends ResponseMessage
{
	private String mediaId;
	
	public ImageResponse()
	{
		super("image");

	}
	
	public final String getMediaId()
	{
		return mediaId;
	}
	public final void setMediaId(String value)
	{
		mediaId = value;
	}

	public ImageResponse(RequestMessage message)
	{
		super("image", message);

	}

	@Override
	protected String InnerToxml()
	{
		String baseXml = super.InnerToxml();


		return baseXml + "<Image><MediaId>" + getMediaId() + "</MediaId></Image>";
	}
}