package org.netsharp.wx.sdk.mp.message;

import org.netsharp.util.DateManage;

//[XmlRoot("xml")]
public class RequestMessage
{
	private String createTimeString;
	private java.util.Date createTime = new java.util.Date(0);
	private String msgType;
	private String msgId;
	private String toUserName;
	private String fromUserName;
	
	public final String getToUserName()
	{
		return toUserName;
	}
	public final void setToUserName(String value)
	{
		toUserName = value;
	}
	
	public final String getFromUserName()
	{
		return fromUserName;
	}
	public final void setFromUserName(String value)
	{
		fromUserName = value;
	}

	public final String getCreateTimeString()
	{
		return createTimeString;
	}
	public final void setCreateTimeString(String value)
	{
		createTimeString = value;
	}

	
	public final java.util.Date getCreateTime()
	{
		return createTime;
	}
	public final void setCreateTime(java.util.Date value)
	{
		createTime = value;
	}
	
	public final String getMsgType()
	{
		return msgType;
	}
	public final void setMsgType(String value)
	{
		msgType = value;
	}
	
	public final String getMsgId()
	{
		return msgId;
	}
	public final void setMsgId(String value)
	{
		msgId = value;
	}

	@Override
	public String toString()
	{
		return "ToUsername:" + this.getToUserName() + ";FromUserName:" + this.getFromUserName() + ";createTime:" + DateManage.toString(this.getCreateTime()) + ";MsgType:" + getMsgType() + ";msgid:" + getMsgId();
	}
}