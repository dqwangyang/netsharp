package org.netsharp.wx.sdk.ep.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.netsharp.util.JsonManage;
import org.netsharp.wx.sdk.ep.Request;

public class MenuCreateRequest extends Request<MenuCreateResponse> {
	
	private Integer agentid;
	private List<Button> buttons = new ArrayList<Button>();

	public MenuCreateRequest() {
		super();
		this.responseType = MenuCreateResponse.class;
	}
	
	@Override
	protected MenuCreateResponse doResponse() {
		
		Map<String,Object> item = new HashMap<String,Object>();
		{
			item.put("button", this.buttons);
		}
		
		String json = JsonManage.serialize2(item);
		
		MenuCreateResponse response =this.executeHttpPost(json);

		return response;
	}


	@Override
	public String getUrl() {
		return "https://qyapi.weixin.qq.com/cgi-bin/menu/create?access_token="+this.getAccessToken()+"&agentid="+this.getAgentid();
	}

	public Integer getAgentid() {
		return agentid;
	}

	public void setAgentid(Integer agentid) {
		this.agentid = agentid;
	}

	public List<Button> getButtons() {
		return buttons;
	}

	public void setButtons(List<Button> buttons) {
		this.buttons = buttons;
	}
}
