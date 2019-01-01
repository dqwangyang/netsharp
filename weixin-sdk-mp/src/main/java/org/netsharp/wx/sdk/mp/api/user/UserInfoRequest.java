package org.netsharp.wx.sdk.mp.api.user;

import org.netsharp.wx.sdk.mp.api.Request;
import org.netsharp.wx.sdk.mp.message.WeixinValidation;
import org.netsharp.wx.sdk.mp.util.StringHelper;

//[Api("获取用户信息")]
public class UserInfoRequest extends Request<UserInfoResponse> {
	
	private String openId;
	
	public UserInfoRequest(){
		super();
		this.responseType=UserInfoResponse.class;
	}

	@Override
	public String getUrl() {
		
		String url= "http://api.weixin.qq.com/cgi-bin/user/info?access_token=%1$s&openid=%2$s&lang=%3$s";
		url=String.format(url,this.getAccessToken(), this.getOpenId(), this.getLang());
		
		return url;
	}

	@Override
	protected WeixinValidation doValidate() {
		if (StringHelper.isNullOrEmpty(this.getOpenId())) {
			return WeixinValidation.create(false, "OpenId is null ");
		}

		return super.doValidate();
	}

	@Override
	protected UserInfoResponse doResponse()  {
		return this.executeHttpGet();
	}

	public final String getOpenId() {
		return openId;
	}

	public final void setOpenId(String value) {
		openId = value;
	}

	public final String getLang() {
		return "zh_CN";
	}
}