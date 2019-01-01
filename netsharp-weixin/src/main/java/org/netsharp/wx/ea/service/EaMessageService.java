package org.netsharp.wx.ea.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.util.StringManager;
import org.netsharp.wx.ea.base.IEaMessageService;
import org.netsharp.wx.ea.base.IWxeaAppService;
import org.netsharp.wx.ea.base.IWxeaMessageService;
import org.netsharp.wx.ea.entity.WxeaApp;
import org.netsharp.wx.ea.entity.WxeaMessage;
import org.netsharp.wx.sdk.ep.accesstoken.AccessToken;
import org.netsharp.wx.sdk.ep.accesstoken.AccessTokenManage;
import org.netsharp.wx.sdk.ep.message.send.SendMessageTextRequest;

@Service
public class EaMessageService implements IEaMessageService {

	protected static Log logger = LogFactory.getLog(EaMessageService.class);

	IWxeaMessageService wxuService = ServiceFactory.create(IWxeaMessageService.class);
	IWxeaAppService wxpService = ServiceFactory.create(IWxeaAppService.class);

	@Override
	public void send(String appCode, String content, String userIds) {
		WxeaMessage wxuc = wxuService.byCode(appCode);
		if (wxuc != null && !StringManager.isNullOrEmpty(content)) {
			WxeaApp wxpa = wxuc.getWxpaConfiguration();
			send(wxpa.getCorpid(), wxpa.getCorpsecret(), wxpa.getAgentId(), content, wxuc.getToUser() + "|" + userIds);
		}
	}

	@Override
	public void send2(String appCode, String content, String userIds) {
		WxeaApp wxpa = wxpService.byCode(appCode);
		if (wxpa != null && !StringManager.isNullOrEmpty(content)) {
			send(wxpa.getCorpid(), wxpa.getCorpsecret(), wxpa.getAgentId(), content, userIds);
		}
	}

	@Override
	public void sendAll(String wxpaConfigurationCode, String content) {
		WxeaApp wxpa = wxpService.byCode(wxpaConfigurationCode);
		if (wxpa != null && !StringManager.isNullOrEmpty(content)) {
			send(wxpa.getCorpid(), wxpa.getCorpsecret(), wxpa.getAgentId(), content, "@all");
		}

	}

	private void send(String corpid, String corpsecret, Integer agentId, String content, String userIds) {

		if (StringManager.isNullOrEmpty(content)) {
			return;
		}
		try {
			AccessToken token = AccessTokenManage.get(corpid, corpsecret);
			List<String> contentList = this.getContent(content);
			SendMessageTextRequest txt = new SendMessageTextRequest();
			{
				txt.setToken(token);
				txt.setTouser(userIds);
				txt.setAgentid(agentId);
			}
			for (String msg : contentList) {
				txt.setContent(msg);
				txt.getResponse();
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}

	private List<String> getContent(String content) {

		List<String> list = new ArrayList<String>();
		return this.subContent(list, content);

	}

	private List<String> subContent(List<String> messages, String content) {
		int length = content.getBytes().length;
		String message = "";
		if (length > 2048) {
			message = content.substring(0, 500);
			messages.add(message);
			this.subContent(messages, content.substring(500));
		} else {
			messages.add(content);
		}
		return messages;
	}

}
