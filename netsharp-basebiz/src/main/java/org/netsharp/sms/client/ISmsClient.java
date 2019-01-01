package org.netsharp.sms.client;

public interface ISmsClient {

	String getSupplierName();
	/**
	 * 发送短信
	 * @param mobile 手机号码
	 * @param content 短信内容
	 */
	SmsSendingResult send(String mobile, String content);

	/**
	 * 发送营销短信
	 * @param mobile 手机号码
	 * @param content 短信内容
	 */
	SmsSendingResult sendForMarketing(String mobile, String content);

	SmsSendingResult sendVoice(String mobile, String content);
	/**
	 * 查询短信余额
	 */
	String getBalance();
}
