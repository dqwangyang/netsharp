package org.netsharp.wx.sdk.mp.api.chatrecord;

public class ChatMessage
{
	private String privateWorker;
	public final String getWorker()
	{
		return privateWorker;
	}
	public final void setWorker(String value)
	{
		privateWorker = value;
	}

	private String privateOpercode;
	public final String getOpercode()
	{
		return privateOpercode;
	}
	public final void setOpercode(String value)
	{
		privateOpercode = value;
	}

	private String privateTime;
	public final String getTime()
	{
		return privateTime;
	}
	public final void setTime(String value)
	{
		privateTime = value;
	}

	private String privateOpenId;
	public final String getOpenId()
	{
		return privateOpenId;
	}
	public final void setOpenId(String value)
	{
		privateOpenId = value;
	}

	private String privateText;
	public final String getText()
	{
		return privateText;
	}
	public final void setText(String value)
	{
		privateText = value;
	}
}