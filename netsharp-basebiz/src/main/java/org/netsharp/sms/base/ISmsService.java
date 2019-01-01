package org.netsharp.sms.base;



/*短信发送接口*/
public interface ISmsService {

	/**
	 * 发送短信
	 *
	 * @param mobile
	 *            单个手机号码
	 * @param content
	 *            发送内容
	 * @return 短信发送批次 @
	 */
	void send(String mobile, String content, String type);

	/**
	 * 发送营销短信
	 *
	 * @param mobile
	 *            单个手机号码
	 * @param content
	 *            发送内容
	 * @return 短信发送批次 @
	 */
	void sendForMarketing(String mobile, String content, String type);

	/**
	 * 发送短信
	 *
	 * @param mobile
	 *            单个手机号码
	 * @param content
	 *            发送内容
	 * @return 短信发送批次 @
	 */
	void sendVoice(String mobile, String content, String type);

	/**
	 * 发送短信
	 * 
	 * @param mobiles
	 *            多个手机号码
	 * @param content
	 *            发送内容
	 * @return 短信发送批次 @
	 */
	void send(String[] mobiles, String content, String type);
	
	/**
	 * 发送营销短信
	 * 
	 * @param mobiles
	 *            多个手机号码
	 * @param content
	 *            发送内容
	 * @return 短信发送批次 @
	 */
	void sendForMarketing(String[] mobiles, String content, String type);
	 

	/**
	 * @return 得到短信余额(短信条数)
	 */
	int getBalance();
}
