package org.netsharp.wx.sdk.ep.accesstoken;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AccessTokenManage {

	private static Map<String, AccessToken> tokens = new HashMap<String, AccessToken>();
	private static Log logger = LogFactory.getLog(AccessTokenManage.class);

	public static AccessToken get(String corpid, String corpsecret) {

		AccessToken token = tokens.get(corpid);
		if (token != null && !token.getIsExpired()) {
			logger.info("读取缓存的Access Token：" + token.getAccess_token());
		} else {
			token = refresh(corpid, corpsecret);
		}

		return token;
	}

	public static AccessToken refresh(String corpid, String corpsecret) {

		// EnterpriseAccount ea =
		// EnterpriseAccountManage.getInstance().getEnterpriseAccount(corpid,corpsecret);

		AccessToken token = new AccessToken();
		{
			token.setCorpid(corpid);
			token.setCorpsecret(corpsecret);
		}

		AccessTokenRequest request = new AccessTokenRequest();
		{
			request.setToken(token);
		}

		AccessTokenResponse response = request.getResponse();
		{
			token.setAccess_token(response.getAccess_token());
			token.setExpires_in(response.getExpires_in());
			token.setCreateTime(new Date());
		}

		String key = corpid + "_" + corpsecret;

		tokens.put(key, token);

		return token;
	}
}
