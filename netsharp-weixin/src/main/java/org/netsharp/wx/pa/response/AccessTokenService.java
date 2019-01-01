package org.netsharp.wx.pa.response;

import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.wx.pa.entity.PublicAccount;
import org.netsharp.wx.sdk.mp.api.accesstoken.AccessToken;
import org.netsharp.wx.sdk.mp.api.accesstoken.AccessTokenManage;
import org.netsharp.wx.sdk.mp.api.accesstoken.AccessTokenRequest;
import org.netsharp.wx.sdk.mp.api.accesstoken.IAccessTokenService;
import org.netsharp.wx.sdk.mp.api.accesstoken.PAccount;
import org.netsharp.wx.sdk.mp.api.accesstoken.WeixinCache;

//weixin-sdk token获取实现类
public class AccessTokenService implements IAccessTokenService {

	private ReentrantLock lock = new ReentrantLock();
	private Log logger = LogFactory.getLog(AccessTokenManage.class.getSimpleName());

	public AccessToken getTokenByAppId(String appId)  {
		AccessToken token = WeixinCache.getInstance().getToken(appId);
		if (token != null && !token.isExpired()) {
			logger.info("读取缓存的Access Token：" + token.getToken());
		} else {
			token = refreshToken(appId);
		}

		return token;
	}

	public void clearTokenCache(String appId) {
		try {
			lock.lock();
			WeixinCache.getInstance().clearToken(appId);
		} finally {
			lock.unlock();
		}
	}

	public AccessToken refreshToken(String appId) {

		WeixinCache wxCache = WeixinCache.getInstance();
		AccessToken token = wxCache.getToken(appId);
		if (token != null && !token.isExpired()) {
			token.setExpired(true);
			wxCache.setToken(appId, token);
		}
		try {
			lock.lock();
			token = wxCache.getToken(appId);
			if (token != null && !token.isExpired()) {
				return token;
			}

			PublicAccount pa = PublicAccountManager.getInstance().getByAppId(appId);

			token = new AccessToken();
			{
				token.setId(pa.getId());
				token.setAppId(pa.getAppId());
				token.setAppSecret(pa.getAppSecret());
				token.setEncryptionKey(pa.getEncryptionKey());
			}

			AccessTokenRequest tokenRequest = new AccessTokenRequest();
			tokenRequest.setTokenInfo(token);
			String tokenStr = tokenRequest.getTokenStr();

			token.setCreateTime(new java.util.Date());
			token.setToken(tokenStr);
			token.setExpired(false);
			logger.info("从微信取到的token: " + token.getToken());

			// 将token写入缓存
			wxCache.setToken(appId, token);

			return token;
		} finally {
			lock.unlock();
		}
	}
	
	public PAccount getPAccount(String originalId) {
		
		PublicAccount pa = PublicAccountManager.getInstance().get(originalId).getAccount();
				
		PAccount account = new PAccount();
		{
			account.setAppId(pa.getAppId());
			account.setAppSecret(pa.getAppSecret());
			account.setOriginalId(pa.getOriginalId());
		}
		
		return account;
	}

}
