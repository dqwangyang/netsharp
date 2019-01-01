package org.netsharp.wx.sdk.mp.api.user;

import java.util.HashMap;

import org.netsharp.wx.sdk.mp.api.Request;
import org.netsharp.wx.sdk.mp.api.Response;
import org.netsharp.wx.sdk.mp.message.WeixinValidation;
import org.netsharp.wx.sdk.mp.util.StringHelper;

public class MarkUserRequest extends Request<Response>
{
	private String openId;
	private String remark;

	@Override
	public String getUrl()
	{
		return String.format("https://api.weixin.qq.com/cgi-bin/user/info/updateremark?access_token=%1$s", this.getAccessToken());
	}

	@Override
	protected WeixinValidation doValidate()
	{
		if (StringHelper.isNullOrEmpty(getOpenId()))
		{
			return WeixinValidation.create(false, "Openid is null");
		}

		if (StringHelper.isNullOrEmpty(this.getRemark()))
		{
			return WeixinValidation.create(false, "Remark is null");
		}

		if (this.getRemark().length() >= 30)
		{
			if (StringHelper.isNullOrEmpty(this.getRemark()))
			{
				return WeixinValidation.create(false, "Remark must be less than 30 characters");
			}
		}

		return super.doValidate();
	}

	@Override
	protected Response doResponse() 
	{
		HashMap<String,Object> map=new HashMap<String,Object>();
		map.put("openid", this.getOpenId());
		map.put("remark", this.getRemark());
		
		String json = this.serialize(map);

		return this.executeHttpPost(json);
	}
	
	public final String getOpenId()
	{
		return openId;
	}
	public final void setOpenId(String value)
	{
		openId = value;
	}

	
	public final String getRemark()
	{
		return remark;
	}
	public final void setRemark(String value)
	{
		remark = value;
	}
}