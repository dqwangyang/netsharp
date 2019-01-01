package org.netsharp.wx.sdk.mp.api.messagetemplate;

import java.util.HashMap;

import org.netsharp.util.JsonManage;
import org.netsharp.wx.sdk.mp.api.Request;

/*删除模板
删除模板可在MP中完成，为方便第三方开发者，提供通过接口调用的方式来删除某帐号下的模板
 * */
public class DeletePrivateTemplateRequest extends Request<DeletePrivateTemplateResponse> {
	
	private String template_id;

    public DeletePrivateTemplateRequest() {
        super();
        this.responseType = DeletePrivateTemplateResponse.class;
    }

    @Override
    public String getUrl() {
        String url = "https://api.weixin.qq.com/cgi-bin/template/del_private_template?access_token=%s";
        return String.format(url, this.getAccessToken());
    }

    @Override
    protected DeletePrivateTemplateResponse doResponse(){
    	
    	HashMap<String,String> map = new HashMap<String,String>();
    	{
			map.put("template_id", this.getTemplate_id());
		}
    	
    	String json = JsonManage.serialize2(map);

        DeletePrivateTemplateResponse response = this.executeHttpPost(json);

        return response;
    }

	public String getTemplate_id() {
		return template_id;
	}

	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}
}