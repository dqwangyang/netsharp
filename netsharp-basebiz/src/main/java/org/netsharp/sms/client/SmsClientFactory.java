package org.netsharp.sms.client;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.core.NotImplementException;
import org.netsharp.sms.mandao.entity.SmsContext;
import org.netsharp.util.PropertyConfigurer;

/*
 * 短信发送工厂类
 * */
public class SmsClientFactory {

	public static SmsContext smsContext;
	private static final String confPropertyFileNames = "conf/configuration.properties";
	private static final Log logger = LogFactory.getLog(SmsClientFactory.class);

	static {
		if (smsContext == null) {
			smsContext = new SmsContext();
			PropertyConfigurer propertyConfigurer = PropertyConfigurer.newInstance(confPropertyFileNames, "UTF-8");
			try {
				smsContext.setSmsChannelType(SmsChannelType.valueOf(SmsChannelType.class, propertyConfigurer.get("sms.channel.type")));
				smsContext.setUid(propertyConfigurer.get("sms.uid"));
				smsContext.setPwd(propertyConfigurer.get("sms.pwd"));
				smsContext.setSignature(propertyConfigurer.get("sms.signature"));
			} catch (Exception ex) {
				logger.trace("没有配置：sms(/conf/configuration.properties)");

			}
		}
	}

	public static ISmsClient create(SmsChannelType smsChannelType) {
		
		throw new NotImplementException();

//		ISmsClient smsClient = null;
//		if (smsChannelType == SmsChannelType.visi) {
//			smsClient = new VisiClient();
//		} else if (smsChannelType == SmsChannelType.changtianhyou) {
//			smsClient = new ChangTianYouClient();
//		} else if (smsChannelType == SmsChannelType.yunpian) {
//			smsClient = new YunPianClient();
//		} else if (smsChannelType == SmsChannelType.mandao) {
//			smsClient = new ManDaoClient();
//		} else if (smsChannelType == SmsChannelType.chanzor) {
//			smsClient = new ChanzorSmsClient();
//		} 
//		return smsClient;
	}

	public static ISmsClient createDefaultClient() {
		return create(smsContext.getSmsChannelType());
	}
}