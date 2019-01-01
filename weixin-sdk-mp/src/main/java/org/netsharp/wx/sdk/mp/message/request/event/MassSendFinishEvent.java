package org.netsharp.wx.sdk.mp.message.request.event;

public class MassSendFinishEvent extends EventRequest
{
	private String status;       //返回状态
	private int totalCount;      //group_id下粉丝数；或者openid_list中的粉丝数
	private int filterCount;     //过滤（过滤是指，有些用户在微信设置不接收该公众号的消息）后，准备发送的粉丝数，原则上，FilterCount = SentCount + ErrorCount
	private int sendCount;       //发送成功的粉丝数
	private int errorCount;      //发送失败的粉丝数
	
	public final String getStatus()
	{
		return status;
	}
	public final void setStatus(String value)
	{
		status = value;
	}

	public final int getTotalCount()
	{
		return totalCount;
	}
	public final void setTotalCount(int value)
	{
		totalCount = value;
	}
	
	public final int getFilterCount()
	{
		return filterCount;
	}
	public final void setFilterCount(int value)
	{
		filterCount = value;
	}
	
	public final int getSendCount()
	{
		return sendCount;
	}
	public final void setSendCount(int value)
	{
		sendCount = value;
	}
	
	public final int getErrorCount()
	{
		return errorCount;
	}
	public final void setErrorCount(int value)
	{
		errorCount = value;
	}
}