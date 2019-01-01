package org.netsharp.wx.pa.response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.util.ReflectManager;
import org.netsharp.util.StringManager;
import org.netsharp.wx.pa.PublicAccountContext;
import org.netsharp.wx.pa.base.INGraphicReplyService;
import org.netsharp.wx.pa.base.INTextReplyService;
import org.netsharp.wx.pa.base.IPublicAccountService;
import org.netsharp.wx.pa.entity.NGraphicReply;
import org.netsharp.wx.pa.entity.NReply;
import org.netsharp.wx.pa.entity.NTextReply;
import org.netsharp.wx.pa.entity.NWeixinSubscriber;
import org.netsharp.wx.pa.entity.PublicAccount;
import org.netsharp.wx.pa.response.subscribe.IWeixinSubscriber;
import org.netsharp.wx.sdk.mp.WeixinException;
import org.netsharp.wx.sdk.mp.api.accesstoken.PAccount;

public class PublicAccountManager {

	private static Log logger = LogFactory.getLog(PublicAccountManager.class.getSimpleName());
	private static IPublicAccountService accountService = ServiceFactory.create(IPublicAccountService.class);
	private static Map<String, PublicAccountContext> pacMap = new HashMap<String, PublicAccountContext>(); // 建立originalid和公众号context之间的关系
	private static Map<String, String> appRelations = new HashMap<String, String>();// 建立appid和originalid之间的关系
	private static PublicAccountManager instance;

	private Boolean isReloaded = false;
	private static ReentrantLock loadLock = new ReentrantLock();

	public static PublicAccountManager getInstance() {
		if(instance==null){
			instance = new PublicAccountManager();
		}
		return instance;
	}

	public PublicAccountContext get(String originalId) {

		this.doInitialize(originalId);

		PublicAccountContext pac = pacMap.get(originalId);
		if (pac == null) {
			throw new RuntimeException(String.format("============> 公众号[%s]加载不成功！", originalId));
		}

		return pac;
	}
	
	public PublicAccount getByAppId(String appId) {
		String originalId;
		if (appRelations.size() == 0 || appRelations.get(appId)==null ) {
			PublicAccount pa = accountService.byAppId(appId);
			if (pa == null) {
				throw new WeixinException(String.format("APP[%s]配置信息不存在", appId));
			}
			originalId = pa.getOriginalId();
		} else {
			originalId = appRelations.get(appId);
		}
		return get(originalId).getAccount();
	}


	public void reload(String originalId) {

		isReloaded = false;
		doInitialize(originalId);
	}
	
	public NReply getReply(String keywords, String originalId) {
		if (keywords == null) {
			return null;
		}

		Map<String, NReply> replies = get(originalId).getReplies();
		if (replies == null || replies.size() == 0) {
			logger.warn("回复为空，或者个数为0");
			return null;
		}
		
		for(String key : replies.keySet()){
			if(StringManager.equals(key, keywords,true)){
				return replies.get(key);
			}
		}

		return null;
	}

	/**
	 * 公众号初始化 get方法和reload方法使用
	 */
	private void doInitialize(String originalId) {
		if (isReloaded) {
			return;
		}
		try {
			loadLock.lock();
			if (isReloaded) {
				return;
			}
			logger.info(String.format("===============公众号[%s]初始化中====================", originalId));
			
			
			PublicAccount pa = accountService.byOriginalId(originalId);
			PublicAccountContext pac = new PublicAccountContext();
			{
				pac.setOriginalId(originalId);
				if (pa == null) {
					throw new WeixinException(String.format("公众号[%s]配置信息不存在", originalId));
				}
				
				pac.setAccount(pa);
			}
			
			//
			// 加载公众号预定义消息
			//
			Map<String, NReply> replies = new HashMap<String, NReply>();
			INTextReplyService textReplyService = ServiceFactory.create(INTextReplyService.class);
			List<NTextReply> trs = textReplyService.getTextReplies(pa.getId());
			for (NTextReply reply : trs) {
				logger.warn("初始化文本回复："+reply.getKeyword());
				if(StringManager.isNullOrEmpty(reply.getKeyword())){
					continue;
				}
				replies.put(reply.getKeyword(), reply);
			}
			INGraphicReplyService graphicService = ServiceFactory.create(INGraphicReplyService.class);
			List<NGraphicReply> grs = graphicService.getReplies(pa.getId());
			for (NGraphicReply reply : grs) {
				logger.warn("初始化图文回复："+reply.getKeyword());
				if(StringManager.isNullOrEmpty(reply.getKeyword())){
					continue;
				}
				replies.put(reply.getKeyword(), reply);
			}
			pac.setReplies(replies);

			//
			// 加载关注处理器
			//
			pac.setSubscriberList(new ArrayList<IWeixinSubscriber>());
			List<NWeixinSubscriber> ns = accountService.querySubscribers(pa.getId());
			for (NWeixinSubscriber nsubscriber : ns) {
				IWeixinSubscriber subscriber = (IWeixinSubscriber) ReflectManager.newInstance(nsubscriber.getJavaType());
				pac.getSubscriberList().add(subscriber);
			}

			//
			// 建立appid和originalid之间的关系
			//
			appRelations.put(pa.getAppId(), pa.getOriginalId());

			pacMap.put(originalId, pac);
			isReloaded = true;
			logger.info(String.format("===============公众号[%s]加载完成====================", originalId));
		} catch (Exception ex) {
			throw new WeixinException(String.format("公众号[%s]初始化失败：", originalId), ex);
		} finally {
			loadLock.unlock();
		}
	}

	/*
	 * PublicAccount生成PAccountPAccount在sdk中使用，PublicAccount在netsharp.weixin中使用
	 */
	public PAccount toPaConfiguration(PublicAccount publicAccount) {

		PAccount pa = new PAccount();
		{
			pa.setName(publicAccount.getName());

			pa.setOriginalId(publicAccount.getOriginalId());
			pa.setAppId(publicAccount.getAppId());
			pa.setAppSecret(publicAccount.getAppSecret());

			pa.setMch_id(publicAccount.getMch_id());
			pa.setMch_notify_url(publicAccount.getMchNotifyUrl());
			pa.setMch_parner_key(publicAccount.getMchParnerKey());

			pa.setApp_id(publicAccount.getAppId());
			pa.setApp_mch_id(publicAccount.getAppMchId());
			pa.setApp_partner_key(publicAccount.getAppPartnerKey());
		}

		return pa;
	}
}