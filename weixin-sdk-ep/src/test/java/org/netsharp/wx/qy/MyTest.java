package org.netsharp.wx.qy;

import org.junit.Test;
import org.netsharp.util.JsonManage;
import org.netsharp.wx.sdk.ep.user.UserInfoResponse;

public class MyTest {

	@Test
	public void run(){
		
		String str = "{\"UserId\":\"13301503086\",\"DeviceId\":\"3C85A992-09D4-4B97-BD5A-69516C4C24C3\",\"errcode\":0,\"errmsg\":\"ok\"}";
		
		UserInfoResponse uir  = (UserInfoResponse) JsonManage.deSerialize2(UserInfoResponse.class, str);
		
		System.out.println(uir.getUserId());
	}
}
