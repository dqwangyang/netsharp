package org.netsharp.wx.sdk.ep.message.send;

import java.util.HashMap;
import java.util.Map;

public class SendMessageFileRequest extends SendMessageRequest {
	
	private Integer media_id;
	
	@Override
	protected Object getInnerObject(){
		
		this.setMsgtype(MessageType.file);
		
		Map<String,Object> item  = new HashMap<String,Object>();
		{
			item.put("media_id", this.media_id);
		}
		
		return item;
	}

	public Integer getMedia_id() {
		return media_id;
	}

	public void setMedia_id(Integer media_id) {
		this.media_id = media_id;
	}
}
