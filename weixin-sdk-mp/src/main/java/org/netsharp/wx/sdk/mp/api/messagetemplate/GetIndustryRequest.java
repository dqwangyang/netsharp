package org.netsharp.wx.sdk.mp.api.messagetemplate;

import org.netsharp.wx.sdk.mp.api.Request;

/* 获取设置的行业信息
   获取帐号设置的行业信息。可在MP官网中查看行业信息，为方便第三方开发者，提供通过接口调用的方式来获取帐号所设置的行业信息
*/
public class GetIndustryRequest extends Request<GetIndustryResponse> {
	
    public GetIndustryRequest() {
        super();
        this.responseType = GetIndustryResponse.class;
    }

    @Override
    public String getUrl() {
        String url = "https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token=%s";
        return String.format(url, this.getAccessToken());
    }

    @Override
    protected GetIndustryResponse doResponse(){

        GetIndustryResponse response = this.executeHttpGet();

        return response;
    }
}