package org.netsharp.wx.sdk.mp.pay;

/*支付预警信息*/
public class PayWarning
{
	private String appId;
	private String errorType;
	private String description;
	private String alarmContent;
	private String timeStamp;
	private String appSignature;
	private String signMothed;
	
	public final String getAppId()
	{
		return appId;
	}
	public final void setAppId(String value)
	{
		appId = value;
	}
	public final String getErrorType()
	{
		return errorType;
	}
	public final void setErrorType(String value)
	{
		errorType = value;
	}
	public final String getDescription()
	{
		return description;
	}
	public final void setDescription(String value)
	{
		description = value;
	}
	public final String getAlarmContent()
	{
		return alarmContent;
	}
	public final void setAlarmContent(String value)
	{
		alarmContent = value;
	}
	public final String getTimeStamp()
	{
		return timeStamp;
	}
	public final void setTimeStamp(String value)
	{
		timeStamp = value;
	}
	public final String getAppSignature()
	{
		return appSignature;
	}
	public final void setAppSignature(String value)
	{
		appSignature = value;
	}
	public final String getSignMothed()
	{
		return signMothed;
	}
	public final void setSignMothed(String value)
	{
		signMothed = value;
	}
	
	/*发货超时时  返回交易Id*/
	public final String getTransId()
	{
		if (this.getErrorType().equals("1001"))
		{
			return this.getAlarmContent().replace("transaation_id=", "");
		}

		return "";
	}
}