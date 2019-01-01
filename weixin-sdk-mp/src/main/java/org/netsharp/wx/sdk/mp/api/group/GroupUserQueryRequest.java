package org.netsharp.wx.sdk.mp.api.group;

import java.util.HashMap;

import org.netsharp.wx.sdk.mp.api.Request;
import org.netsharp.wx.sdk.mp.message.WeixinValidation;
import org.netsharp.wx.sdk.mp.util.StringHelper;

//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//[Api("查询用户所在分组")]
public class GroupUserQueryRequest extends Request<GroupUserQueryResponse>
{
	private String privateOpenId;
	public final String getOpenId()
	{
		return privateOpenId;
	}
	public final void setOpenId(String value)
	{
		privateOpenId = value;
	}

	@Override
	public String getUrl()
	{
		return String.format("https://api.weixin.qq.com/cgi-bin/groups/getid?access_token=%1$s", this.getAccessToken());
	}

	@Override
	protected WeixinValidation doValidate()
	{
		if (StringHelper.isNullOrEmpty(this.getOpenId()))
		{
			return WeixinValidation.create(false, "OpenId is null");
		}

		return super.doValidate();
	}

	@Override
	protected GroupUserQueryResponse doResponse() 
	{
		HashMap<String,Object> data=new HashMap<String,Object>();
		data.put("openid",  this.getOpenId());
		
		String json = this.serialize(data);

		return this.executeHttpPost(json);
	}
}