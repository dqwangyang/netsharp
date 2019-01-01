package org.netsharp.wx.sdk.ep.chat;

import org.netsharp.wx.sdk.ep.Response;

public class ChatGetResponse extends Response {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8439244102653333828L;
	private Chat chat_info;

	public Chat getChat_info() {
		return chat_info;
	}

	public void setChat_info(Chat chat_info) {
		this.chat_info = chat_info;
	}
}
