package org.netsharp.wx.sdk.mp.api.messagetemplate;

import java.util.HashMap;

import org.netsharp.util.JsonManage;
import org.netsharp.wx.sdk.mp.api.Request;

/* 设置所属行业
 * 设置行业可在MP中完成，每月可修改行业1次，账号仅可使用所属行业中相关的模板，为方便第三方开发者，提供通过接口调用的方式来修改账号所属行业
 * */
public class SetIndustryRequest extends Request<SetIndustryResponse> {

	private String industry_id1; // 公众号模板消息所属行业编号
	private String industry_id2; // 公众号模板消息所属行业编号

	public SetIndustryRequest() {
		super();
		this.responseType = SetIndustryResponse.class;
	}

	@Override
	public String getUrl() {
		String url = "https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=%s";
		return String.format(url, this.getAccessToken());
	}

	@Override
	protected SetIndustryResponse doResponse() {

		HashMap<String,String> map = new HashMap<String,String>();
		{
			map.put("industry_id1", this.industry_id1);
			map.put("industry_id2", this.industry_id2);
		}
		
		String json = JsonManage.serialize2(map);
		
		SetIndustryResponse response = this.executeHttpPost(json);

		return response;
	}
	
	public String getIndustry_id1() {
		return industry_id1;
	}

	public void setIndustry_id1(String industry_id1) {
		this.industry_id1 = industry_id1;
	}

	public String getIndustry_id2() {
		return industry_id2;
	}

	public void setIndustry_id2(String industry_id2) {
		this.industry_id2 = industry_id2;
	}

}