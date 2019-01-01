package org.netsharp.wx.sdk.mp.message;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.wx.sdk.mp.WeixinException;
import org.netsharp.wx.sdk.mp.util.StringHelper;
import org.netsharp.wx.sdk.mp.util.WeixinUtil;

public abstract class ResponseMessage
{
	protected Log logger;

	private ResponseMessage()
	{
		this.setCreateTime(new java.util.Date());
		logger = LogFactory.getLog(this.getClass());
	}

	protected ResponseMessage(String messsageType)
	{
		this();
		this._messageType = messsageType;
	}

	protected ResponseMessage(String messageType, RequestMessage rm)
	{
		this(messageType);
		this.setToUserName(rm.getFromUserName());
		this.setFromUserName(rm.getToUserName());
	}

	private String toUserName;
	public final String getToUserName()
	{
		return toUserName;
	}
	public final void setToUserName(String value)
	{
		toUserName = value;
	}
	private String fromUserName;
	public final String getFromUserName()
	{
		return fromUserName;
	}
	public final void setFromUserName(String value)
	{
		fromUserName = value;
	}
	private java.util.Date createTime = new java.util.Date(0);
	public final java.util.Date getCreateTime()
	{
		return createTime;
	}
	public final void setCreateTime(java.util.Date value)
	{
		createTime = value;
	}

	private String _messageType;
	public final String getMessageType()
	{
		return _messageType;
	}

	public WeixinValidation Validate()
	{
		if (StringHelper.isNullOrEmpty(this.getMessageType()))
		{
			return WeixinValidation.create(false, "MessageType is null");
		}

		if (StringHelper.isNullOrEmpty(this.getFromUserName()))
		{
			return WeixinValidation.create(false, "FromUserName is null");
		}

		if (StringHelper.isNullOrEmpty(this.getToUserName()))
		{
			return WeixinValidation.create(false, "ToUserName is null");
		}

		return this.DoValidate();
	}

	protected final WeixinValidation DoValidate()
	{
		return WeixinValidation.create(true, "");
	}

	public String ToXml() throws WeixinException
	{
		WeixinValidation validateResult = this.Validate();

		if (!validateResult.Succeed)
		{
			throw new WeixinException(validateResult.Message);
		}

		String template = "<xml>" + this.InnerToxml() + "</xml>";

		return template;
	}

	protected String InnerToxml()
	{
		String tmp = 
				"<ToUserName>"+this.getToUserName()+"</ToUserName>" + "\r\n" 
				+ "<FromUserName>"+this.getFromUserName()+"</FromUserName>" + "\r\n" 
				+ "<CreateTime>"+WeixinUtil.ToUnixTime(this.getCreateTime())+"</CreateTime>" + "\r\n" 
				+ "<MsgType>"+this.getMessageType()+"</MsgType>";

		return tmp;
	}
	
	
}