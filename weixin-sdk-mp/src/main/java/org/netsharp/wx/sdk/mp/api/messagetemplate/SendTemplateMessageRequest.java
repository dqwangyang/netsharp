package org.netsharp.wx.sdk.mp.api.messagetemplate;

import java.util.HashMap;

import org.netsharp.util.JsonManage;
import org.netsharp.wx.sdk.mp.api.Request;

public class SendTemplateMessageRequest extends Request<SendTemplateMessageResponse> {
	
	private String touser;
	private String template_id;
	private String pageUrl;//对应微信的url参数,因为和Request方法的getUrl冲突，所以改名为pageUrl
	private SendTemplateData data;
	
    public SendTemplateMessageRequest() {
        super();
        this.responseType = SendTemplateMessageResponse.class;
    }

    @Override
    public String getUrl() {
        String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=%s";
        return String.format(url, this.getAccessToken());
    }

    @Override
    protected SendTemplateMessageResponse doResponse(){
    	
    	HashMap<String,Object> jsonData=new HashMap<String,Object>();
		jsonData.put("touser", this.touser);
		jsonData.put("template_id", this.template_id);
		jsonData.put("url", this.pageUrl);
		jsonData.put("data", this.data.getMap());
		
		String json = JsonManage.serialize2(jsonData);
		
		System.out.println(json);
    	
        SendTemplateMessageResponse response =  this.executeHttpPost(json);

        return response;
    }

	public String getTouser() {
		return touser;
	}

	public void setTouser(String touser) {
		this.touser = touser;
	}

	public String getTemplate_id() {
		return template_id;
	}

	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}

	public SendTemplateData getData() {
		return data;
	}

	public void setData(SendTemplateData data) {
		this.data = data;
	}

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}
}
