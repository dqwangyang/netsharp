package org.netsharp.wx.sdk.mp.message.response;

import org.netsharp.wx.sdk.mp.message.RequestMessage;
import org.netsharp.wx.sdk.mp.message.ResponseMessage;

public class TextResponse extends ResponseMessage
{
	private String content;
	
	public TextResponse()
	{
		super("text");
	}

	public TextResponse(RequestMessage message)
	{
		super("text", message);
	}
	
	public final String getContent()
	{
		return content;
	}
	public final void setContent(String value)
	{
		content = value;
	}

	@Override
	protected String InnerToxml()
	{
		String basexml = super.InnerToxml();
		return basexml + "<Content><![CDATA[" + this.getContent() + "]]></Content>";
	}
}