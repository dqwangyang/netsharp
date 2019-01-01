package org.netsharp.weixin.pa;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.wx.pa.WeixinMessageListener;
import org.netsharp.wx.pa.startup.StartupWeixin;
import org.netsharp.wx.sdk.mp.api.accesstoken.AccessToken;
import org.netsharp.wx.sdk.mp.api.accesstoken.AccessTokenManage;
import org.netsharp.wx.sdk.mp.api.messagetemplate.AddTemplateRequest;
import org.netsharp.wx.sdk.mp.api.messagetemplate.AddTemplateResponse;
import org.netsharp.wx.sdk.mp.api.messagetemplate.GetAllPrivateTemplateRequest;
import org.netsharp.wx.sdk.mp.api.messagetemplate.GetIndustryRequest;
import org.netsharp.wx.sdk.mp.api.messagetemplate.GetIndustryResponse;
import org.netsharp.wx.sdk.mp.api.messagetemplate.KeyNote;
import org.netsharp.wx.sdk.mp.api.messagetemplate.SendTemplateData;
import org.netsharp.wx.sdk.mp.api.messagetemplate.SendTemplateMessageRequest;

public class TemplateMessage {
	
	private AccessToken token;

	@Before
	public void initialize() {
		new StartupWeixin().startup();
		WeixinMessageListener listner = new WeixinMessageListener();
		AccessTokenManage.getTokenByAppId(WeixinTestConfiguration.appId);
		token = listner.getAccessToken(WeixinTestConfiguration.originalId);

	}

//	@Test
//	public void getIndustry() {
//		
//		
//		GetIndustryRequest request = new GetIndustryRequest();
//		{
//			request.setTokenInfo(token);
//		}
//		
//		GetIndustryResponse response = request.getResponse();
//	}
	
	@Test
	public void getPrivateTemplates(){
		
		GetAllPrivateTemplateRequest request = new GetAllPrivateTemplateRequest();
		{
			request.setTokenInfo(token);
		}
		
		 request.getResponse();
	}
	
	/*每个月设置一次*/
	@Test
	public void setIndustry(){
		
//		SetIndustryRequest request = new SetIndustryRequest();
//		{
//			request.setIndustry_id1("16");
//			request.setIndustry_id2("17");
//			
//			request.setTokenInfo(token);
//		}
//		
//		SetIndustryResponse response = request.getResponse();
		
	}
	
	@Test
	public void getIndustry(){
		
		GetIndustryRequest request = new GetIndustryRequest();
		{
			request.setTokenInfo(token);
		}
		
		GetIndustryResponse response = request.getResponse();
		
		System.out.println(response.toString());
	}
	
	@Test
	public void addTemplate(){
		
		this.getTemplateId();
	}
	
	@Test
	public void sendMessage(){
		
		String template_id = this.getTemplateId();
		
		SendTemplateData data = new SendTemplateData();
		{
			data.getFirst().setValue("上课通知");//这个模板下没有使用这个关键字
			
			
			data.getKeynotes().put("userName",new KeyNote("张三"));
			data.getKeynotes().put("courseName",new KeyNote("书法欣赏"));
			data.getKeynotes().put("date",new KeyNote("下周二下午3:00"));
			
			data.getRemark().setValue("请准时参加，谢谢！");
		}
		
		SendTemplateMessageRequest request = new SendTemplateMessageRequest();
		{
			request.setTokenInfo(token);
			
//			request.setTouser("og-Yqw10pa_lcnZk_VFkEmpdy_Go");
			request.setTouser("og-Yqw7UcnamkrZmb2gFEoZhCVyU");
			request.setTemplate_id(template_id);
			request.setPageUrl("http://www.baidu.com");
			request.setData(data);
		}
		
		request.getResponse();
	}
	
	private String getTemplateId(){
		AddTemplateRequest request = new AddTemplateRequest();
		{
			request.setTokenInfo(token);
			request.setTemplate_id_short("TM00080");
		}
		
		AddTemplateResponse response = request.getResponse();
		
		System.out.println(response.getTemplate_id());
		
		return response.getTemplate_id();
	}
}
