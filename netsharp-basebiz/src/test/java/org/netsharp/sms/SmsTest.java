package org.netsharp.sms;

import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.sms.base.ISmsService;

public class SmsTest {

	/**
	 * @param args
	 */
	@Test
	public void run()  {
		ISmsService logService = ServiceFactory.create(ISmsService.class);
		logService.send("13301503086", "您的验证码为:123456", "phoneCode");
		System.out.println("123");
	}
}
