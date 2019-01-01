package org.netsharp.wx.sdk.mp.api.messagetemplate;

import org.netsharp.wx.sdk.mp.api.Request;

/* 获取模板列表
   获取已添加至帐号下所有模板列表，可在MP中查看模板列表信息，为方便第三方开发者，提供通过接口调用的方式来获取帐号下所有模板信息
 * */
public class GetAllPrivateTemplateRequest extends Request<GetAllPrivateTemplateResponse> {
	
    public GetAllPrivateTemplateRequest() {
        super();
        this.responseType = GetAllPrivateTemplateResponse.class;
    }

	@Override
	public String getUrl() {
		String url = "https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token=%s";
		return String.format(url, this.getAccessToken());
	}

	@Override
	protected GetAllPrivateTemplateResponse doResponse() {
		
		GetAllPrivateTemplateResponse response = this.executeHttpGet();
		return response;
	}

}
