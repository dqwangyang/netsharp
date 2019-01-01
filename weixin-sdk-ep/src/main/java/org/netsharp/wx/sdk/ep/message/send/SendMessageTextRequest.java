package org.netsharp.wx.sdk.ep.message.send;

import java.util.HashMap;
import java.util.Map;

public class SendMessageTextRequest extends SendMessageRequest {
	
	private String content;
	
	@Override
	protected Object getInnerObject(){
		
		this.setMsgtype(MessageType.text);
		
		Map<String,Object> item  = new HashMap<String,Object>();
		{
			item.put("content", this.content);
		}
		
		return item;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
