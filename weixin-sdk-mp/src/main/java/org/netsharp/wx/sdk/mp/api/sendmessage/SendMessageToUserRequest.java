package org.netsharp.wx.sdk.mp.api.sendmessage;

import org.netsharp.wx.sdk.mp.api.Request;
import org.netsharp.wx.sdk.mp.message.WeixinValidation;

public abstract class SendMessageToUserRequest extends Request<SendMessageResponse>
{
	public SendMessageToUserRequest()
	{
		this.setOpenIds(new java.util.ArrayList<String>());
	}

	private String privateMessageType;
	public final String getMessageType()
	{
		return privateMessageType;
	}
	public final void setMessageType(String value)
	{
		privateMessageType = value;
	}

	@Override
	public String getUrl()
	{
		return String.format("https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token=%1$s", this.getAccessToken());
	}

	private java.util.ArrayList<String> privateOpenIds;
	public final java.util.ArrayList<String> getOpenIds()
	{
		return privateOpenIds;
	}
	public final void setOpenIds(java.util.ArrayList<String> value)
	{
		privateOpenIds = value;
	}

	@Override
	protected WeixinValidation doValidate()
	{
		if (this.getOpenIds().isEmpty())
		{
			return WeixinValidation.create(false, "Openids.count=0");
		}
		return super.doValidate();
	}

	@Override
	protected SendMessageResponse doResponse() 
	{
		Object o = this.getMessageObject();

		String json = this.serialize(o);

		return this.executeHttpPost(json);
	}

	protected abstract Object getMessageObject();
}