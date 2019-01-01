package org.netsharp.wx.sdk.ep.chat;

import org.netsharp.util.JsonManage;
import org.netsharp.wx.sdk.ep.Request;

public class ChatCreateRequest extends Request<ChatCreateResponse> {
	
	private Chat chat;

	public ChatCreateRequest() {
		super();
		this.responseType = ChatCreateResponse.class;
	}

	@Override
	protected ChatCreateResponse doResponse() {
		
		String json = JsonManage.serialize2( this.chat );
		
		ChatCreateResponse response =this.executeHttpPost(json);

		return response;
	}

	@Override
	public String getUrl() {
		return "https://qyapi.weixin.qq.com/cgi-bin/chat/create?access_token=" + this.getAccessToken();
	}

	public Chat getChat() {
		return chat;
	}

	public void setChat(Chat chat) {
		this.chat = chat;
	}
}
