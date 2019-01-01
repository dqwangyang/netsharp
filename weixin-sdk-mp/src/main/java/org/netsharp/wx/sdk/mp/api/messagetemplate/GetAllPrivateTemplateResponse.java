package org.netsharp.wx.sdk.mp.api.messagetemplate;

import java.util.List;

import org.netsharp.wx.sdk.mp.api.Response;

/*
{	
	 "template_list": [{
	      "template_id": "iPk5sOIt5X_flOVKn5GrTFpncEYTojx6ddbt8WYoV5s",
	      "title": "领取奖金提醒",
	      "primary_industry": "IT科技",
	      "deputy_industry": "互联网|电子商务",
	      "content": "{ {result.DATA} }\n\n领奖金额:{ {withdrawMoney.DATA} }\n领奖  时间:{ {withdrawTime.DATA} }\n银行信息:{ {cardInfo.DATA} }\n到账时间:  { {arrivedTime.DATA} }\n{ {remark.DATA} }",
	      "example": "您已提交领奖申请\n\n领奖金额：xxxx元\n领奖时间：2013-10-10 12:22:22\n银行信息：xx银行(尾号xxxx)\n到账时间：预计xxxxxxx\n\n预计将于xxxx到达您的银行卡"
	   }]
	}
*/	
public class GetAllPrivateTemplateResponse extends Response {
	
	private List<Template> template_list;

	public List<Template> getTemplate_list() {
		return template_list;
	}

	public void setTemplate_list(List<Template> template_list) {
		this.template_list = template_list;
	}
}

