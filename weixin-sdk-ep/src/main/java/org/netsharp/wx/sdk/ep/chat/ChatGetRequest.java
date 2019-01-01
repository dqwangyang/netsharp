package org.netsharp.wx.sdk.ep.chat;

import org.netsharp.wx.sdk.ep.Request;


public class ChatGetRequest extends Request<ChatGetResponse> {

	private Integer chatid;

	public ChatGetRequest() {
		super();
		this.responseType = ChatGetResponse.class;
	}

	@Override
	protected ChatGetResponse doResponse() {
		
		ChatGetResponse response =this.executeHttpGet();

		return response;
	}

	@Override
	public String getUrl() {
		return "https://qyapi.weixin.qq.com/cgi-bin/chat/get?access_token=" + this.getAccessToken()+"&userid="+this.chatid;
	}

	public Integer getChatid() {
		return chatid;
	}

	public void setChatid(Integer chatid) {
		this.chatid = chatid;
	}
}