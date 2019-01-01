package org.netsharp.weixin.pa;

import org.junit.Test;
import org.netsharp.wx.sdk.mp.api.accesstoken.AccessTokenManage;

public class AccessTokenTest {
	
	@Test
	public void refresh(){
		AccessTokenManage.refreshToken(WeixinTestConfiguration.appId);
	}
}
