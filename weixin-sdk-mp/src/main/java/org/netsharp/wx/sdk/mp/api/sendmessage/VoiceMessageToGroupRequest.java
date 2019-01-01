package org.netsharp.wx.sdk.mp.api.sendmessage;

import org.netsharp.wx.sdk.mp.message.WeixinValidation;
import org.netsharp.wx.sdk.mp.util.StringHelper;

//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//[Api("发送消息到用户组.语音")]
public class VoiceMessageToGroupRequest extends SendMessageToGroupRequest
{
	private String privateMediaId;
	public final String getMediaId()
	{
		return privateMediaId;
	}
	public final void setMediaId(String value)
	{
		privateMediaId = value;
	}

	@Override
	protected WeixinValidation doValidate()
	{
		if (StringHelper.isNullOrEmpty(this.getMediaId()))
		{
			return WeixinValidation.create(false, "MediaId is null");
		}

		return super.doValidate();
	}

	@Override
	protected Object GetMessageObject()
	{
		return null;
//		
//		var jsonData = new { filter = new { group_id = this.getGroupId() }, voice = new { media_id = this.getMediaId() }, msgtype = "voice" };
//
//		return jsonData;
	}
}