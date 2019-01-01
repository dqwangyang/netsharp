package org.netsharp.wx.sdk.mp.api.accesstoken;

import org.netsharp.util.ReflectManager;

/*微信access_token管理器*/
public class AccessTokenManage {

	private static IAccessTokenService service = null;

	public static AccessToken getTokenByAppId(String appId)  {
		return getService().getTokenByAppId(appId);
	}

	public static void clearTokenCache(String appId) {
		getService().clearTokenCache(appId);
	}

	public static AccessToken refreshToken(String appId) {
		return getService().refreshToken(appId);
	}

	public static PAccount getPAccount(String originalId) {
		return getService().getPAccount(originalId);
	}

	private static IAccessTokenService getService() {
		if (service == null) {
			String type = "org.netsharp.wx.pa.response.AccessTokenService";
			service = (IAccessTokenService) ReflectManager.newInstance(type);
		}

		return service;
	}
}