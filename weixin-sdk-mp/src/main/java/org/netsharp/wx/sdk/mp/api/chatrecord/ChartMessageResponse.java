package org.netsharp.wx.sdk.mp.api.chatrecord;

import org.netsharp.wx.sdk.mp.api.Response;

public class ChartMessageResponse extends Response
{
	public ChartMessageResponse()
	{
		setRecordlist(new java.util.ArrayList<ChatMessage>());
	}

	private java.util.ArrayList<ChatMessage> privateRecordlist;
	public final java.util.ArrayList<ChatMessage> getRecordlist()
	{
		return privateRecordlist;
	}
	public final void setRecordlist(java.util.ArrayList<ChatMessage> value)
	{
		privateRecordlist = value;
	}
}