package org.netsharp.wx.sdk.ep.chat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.netsharp.util.JsonManage;
import org.netsharp.wx.sdk.ep.Request;

public class ChatClearRequest extends Request<ChatClearResponse> {
	
	private String op_user;      // 操作人userid (修改时使用)
	private List<ChatClear> chat=new ArrayList<ChatClear>();//会话
	
	public ChatClearRequest() {
		super();
		this.responseType = ChatClearResponse.class;
	}
	
	@Override
	protected ChatClearResponse doResponse() {
		
		Map<String,Object> item = new HashMap<String,Object>();
		{
			item.put("op_user", this.op_user);
		}
		
		String json = JsonManage.serialize2(item);
		
		ChatClearResponse response =this.executeHttpPost(json);

		return response;
	}

	@Override
	public String getUrl() {
		return "https://qyapi.weixin.qq.com/cgi-bin/chat/clearnotify?access_token="+this.getToken().getAccess_token();
	}

	public String getOp_user() {
		return op_user;
	}

	public void setOp_user(String op_user) {
		this.op_user = op_user;
	}
	
	public List<ChatClear> getChat() {
		return chat;
	}

	public void setChat(List<ChatClear> chat) {
		this.chat = chat;
	}

	public class ChatClear{
		
		private ChatClearType type;//会话类型：single|group，分别表示：群聊|单聊
		private String id;//会话值，为userid|chatid，分别表示：成员id|会话id
		
		public ChatClearType getType() {
			return type;
		}
		public void setType(ChatClearType type) {
			this.type = type;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		
	}
	
	public enum ChatClearType{
		single,//单聊
		group,//群聊
	}
}