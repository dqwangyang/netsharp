package org.netsharp.wx.sdk.ep.message.send;

import java.util.HashMap;
import java.util.Map;

public class SendMessageVideoRequest extends SendMessageRequest {
	
	private Integer media_id;
	private String title;
	private String description;
	
	@Override
	protected Object getInnerObject(){
		
		this.setMsgtype(MessageType.video);
		
		Map<String,Object> item  = new HashMap<String,Object>();
		{
			item.put("media_id", this.media_id);
			item.put("title", this.title);
			item.put("description", this.description);
		}
		
		return item;
	}
	
	public Integer getMedia_id() {
		return media_id;
	}
	public void setMedia_id(Integer media_id) {
		this.media_id = media_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
