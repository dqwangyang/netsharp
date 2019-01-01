package org.netsharp.wx.sdk.mp.api.sendmessage;

import org.netsharp.wx.sdk.mp.api.Request;
import org.netsharp.wx.sdk.mp.message.WeixinValidation;
import org.netsharp.wx.sdk.mp.util.StringHelper;

public abstract class SendMessageToGroupRequest extends Request<SendMessageResponse>
{
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
		return String.format("https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=%1$s", this.getAccessToken());
	}

	private String privateGroupId;
	public final String getGroupId()
	{
		return privateGroupId;
	}
	public final void setGroupId(String value)
	{
		privateGroupId = value;
	}

	@Override
	protected WeixinValidation doValidate()
	{
		if (StringHelper.isNullOrEmpty(this.getGroupId()))
		{
			return WeixinValidation.create(false, "GroupId is null");
		}
		return super.doValidate();
	}

	@Override
	protected SendMessageResponse doResponse() 
	{
		Object o = GetMessageObject();

		String json = this.serialize(o);

		return this.executeHttpPost(json);
	}

	protected abstract Object GetMessageObject();
}