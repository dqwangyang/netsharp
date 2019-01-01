package org.netsharp.wx.sdk.ep.chat;

import java.util.HashMap;
import java.util.Map;

import org.netsharp.util.JsonManage;
import org.netsharp.wx.sdk.ep.Request;

public class ChatQuitRequest extends Request<ChatQuitResponse> {
	
	private Integer chatid;      // 会话id。字符串类型，最长32个字符。只允许字符0-9及字母a-zA-Z, 如果值内容为64bit无符号整型：要求值范围在[1, 2^63)之间，[2^63, 2^64)为系统分配会话id区间
	private String op_user;      // 操作人userid (修改时使用)
	
	public ChatQuitRequest() {
		super();
		this.responseType = ChatQuitResponse.class;
	}
	
	@Override
	protected ChatQuitResponse doResponse() {
		
		Map<String,Object> item = new HashMap<String,Object>();
		{
			item.put("chatid", this.chatid);
			item.put("op_user", this.op_user);
		}
		
		String json = JsonManage.serialize2(item);
		
		ChatQuitResponse response =this.executeHttpPost(json);

		return response;
	}

	@Override
	public String getUrl() {
		return "https://qyapi.weixin.qq.com/cgi-bin/chat/quit?access_token="+this.getToken().getAccess_token();
	}

	public Integer getChatid() {
		return chatid;
	}

	public void setChatid(Integer chatid) {
		this.chatid = chatid;
	}

	public String getOp_user() {
		return op_user;
	}

	public void setOp_user(String op_user) {
		this.op_user = op_user;
	}
}