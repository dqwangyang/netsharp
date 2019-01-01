package org.netsharp.wx.sdk.ep.chat;

import java.util.HashMap;
import java.util.Map;

import org.netsharp.util.JsonManage;
import org.netsharp.wx.sdk.ep.Request;

public class ChatUpdateRequest extends Request<ChatUpdateResponse> {
	
	private Integer chatid;      // 会话id。字符串类型，最长32个字符。只允许字符0-9及字母a-zA-Z, 如果值内容为64bit无符号整型：要求值范围在[1, 2^63)之间，[2^63, 2^64)为系统分配会话id区间
	private String name;         // 会话标题
	private String op_user;      // 操作人userid (修改时使用)
	private String owner;        // 管理员userid，必须是该会话userlist的成员之一
	private String[] add_user_list;   // 会话新增成员列表，成员用userid来标识
	private String[] del_user_list;   // 会话退出成员列表，成员用userid来标识
	

	public ChatUpdateRequest() {
		super();
		this.responseType = ChatUpdateResponse.class;
	}
	
	@Override
	protected ChatUpdateResponse doResponse() {
		
		Map<String,Object> item = new HashMap<String,Object>();
		{
			item.put("chatid", this.chatid);
			item.put("name", this.name);
			item.put("op_user", this.op_user);
			item.put("owner", this.owner);
			item.put("add_user_list", this.add_user_list);
			item.put("del_user_list", this.del_user_list);
		}
		
		String json = JsonManage.serialize2(item);
		
		ChatUpdateResponse response =this.executeHttpPost(json);

		return response;
	}


	@Override
	public String getUrl() {
		return "https://qyapi.weixin.qq.com/cgi-bin/chat/update?access_token="+this.getToken().getAccess_token();
	}

	public Integer getChatid() {
		return chatid;
	}

	public void setChatid(Integer chatid) {
		this.chatid = chatid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOp_user() {
		return op_user;
	}

	public void setOp_user(String op_user) {
		this.op_user = op_user;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String[] getAdd_user_list() {
		return add_user_list;
	}

	public void setAdd_user_list(String[] add_user_list) {
		this.add_user_list = add_user_list;
	}

	public String[] getDel_user_list() {
		return del_user_list;
	}

	public void setDel_user_list(String[] del_user_list) {
		this.del_user_list = del_user_list;
	}
}
