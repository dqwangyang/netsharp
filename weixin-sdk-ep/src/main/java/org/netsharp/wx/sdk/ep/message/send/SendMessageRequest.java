package org.netsharp.wx.sdk.ep.message.send;

import java.util.HashMap;
import java.util.Map;

import org.netsharp.util.JsonManage;
import org.netsharp.wx.sdk.ep.Request;

public abstract class SendMessageRequest  extends Request<SendMessageResponse> {
	
	private String touser;     //成员ID列表（消息接收者，多个接收者用‘|’分隔，最多支持1000个）。特殊情况：指定为@all，则向关注该企业应用的全部成员发送
	private String toparty;    //部门ID列表，多个接收者用‘|’分隔，最多支持100个。当touser为@all时忽略本参数
	private String totag;      //标签ID列表，多个接收者用‘|’分隔。当touser为@all时忽略本参数
	private MessageType msgtype;
	private Integer agentid;    //企业应用的id，整型。可在应用的设置页面查看
	private String safe="0";    //表示是否是保密消息，0表示否，1表示是，默认0

	public SendMessageRequest() {
		super();
		this.responseType = SendMessageResponse.class;
	}

	@Override
	protected SendMessageResponse doResponse() {
		
		Map<String,Object> item = new HashMap<String,Object>();
		{
			item.put("touser", this.touser);
			item.put("toparty", this.toparty);
			item.put("totag", this.totag);
			item.put("agentid", this.agentid);
			
			Object obj = this.getInnerObject();
			
			item.put(this.msgtype.name(), obj);
			item.put("msgtype", this.msgtype.name());
			
			item.put("safe", this.safe);
		}
		
		String json = JsonManage.serialize2(item);
		
		SendMessageResponse response =this.executeHttpPost(json);

		return response;
	}
	
	protected abstract Object getInnerObject();

	@Override
	public String getUrl() {
		return "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=" + this.getAccessToken();
	}

	public String getTouser() {
		return touser;
	}
	public void setTouser(String touser) {
		this.touser = touser;
	}
	public String getToparty() {
		return toparty;
	}
	public void setToparty(String toparty) {
		this.toparty = toparty;
	}
	public String getTotag() {
		return totag;
	}
	public void setTotag(String totag) {
		this.totag = totag;
	}
	public MessageType getMsgtype() {
		return msgtype;
	}
	public void setMsgtype(MessageType msgtype) {
		this.msgtype = msgtype;
	}
	public Integer getAgentid() {
		return agentid;
	}
	public void setAgentid(Integer agentid) {
		this.agentid = agentid;
	}
	public String getSafe() {
		return safe;
	}
	public void setSafe(String safe) {
		this.safe = safe;
	}
}
