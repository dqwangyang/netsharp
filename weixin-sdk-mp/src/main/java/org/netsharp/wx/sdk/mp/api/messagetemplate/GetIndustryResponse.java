package org.netsharp.wx.sdk.mp.api.messagetemplate;

import org.netsharp.util.JsonManage;
import org.netsharp.wx.sdk.mp.api.Response;

/*同SetIndustryResponse*/
public class GetIndustryResponse extends Response {
	
	private IndustryCode primary_industry;
	private IndustryCode secondary_industry;
	
    public GetIndustryResponse() {
    	
    }

	public IndustryCode getPrimary_industry() {
		return primary_industry;
	}

	public void setPrimary_industry(IndustryCode primary_industry) {
		this.primary_industry = primary_industry;
	}

	public IndustryCode getSecondary_industry() {
		return secondary_industry;
	}

	public void setSecondary_industry(IndustryCode secondary_industry) {
		this.secondary_industry = secondary_industry;
	}

	@Override
	public String toString(){
		String json = JsonManage.serialize2(this);
		
		return json;
	}
}