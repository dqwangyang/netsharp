package org.netsharp.wx.sdk.mp.api.messagetemplate;

import java.util.HashMap;

import org.netsharp.util.JsonManage;
import org.netsharp.wx.sdk.mp.api.Request;

/*获得模板ID
 从行业模板库选择模板到帐号后台，获得模板ID的过程可在MP中完成。为方便第三方开发者，提供通过接口调用的方式来获取模板ID，具体如下：
 * */
public class AddTemplateRequest extends Request<AddTemplateResponse> {
	
	private String template_id_short;

    public AddTemplateRequest() {
        super();
        this.responseType = AddTemplateResponse.class;
    }

    @Override
    public String getUrl() {
        String url = "https://api.weixin.qq.com/cgi-bin/template/api_add_template?access_token=%s";
        return String.format(url, this.getAccessToken());
    }

    @Override
    protected AddTemplateResponse doResponse(){
    	
    	HashMap<String,String> map = new HashMap<String,String>();
    	{
			map.put("template_id_short", this.template_id_short);
		}
    	
    	String json = JsonManage.serialize2(map);

        AddTemplateResponse response = this.executeHttpPost(json);

        return response;
    }

	public String getTemplate_id_short() {
		return template_id_short;
	}

	public void setTemplate_id_short(String template_id_short) {
		this.template_id_short = template_id_short;
	}
}