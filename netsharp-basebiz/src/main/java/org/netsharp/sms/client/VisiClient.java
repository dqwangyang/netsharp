//package org.netsharp.sms.client;
//
//import java.io.IOException;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//
//public class VisiClient implements ISmsClient {
//	private static final Log logger = LogFactory.getLog(VisiClient.class);
//
//	private String apiId = SmsClientFactory.smsContext.getUid();// "visi";
//	private static String signature = SmsClientFactory.smsContext.getSignature();// "【易快修】";
//
//	public String getSupplierName() {
//		return "Visi";
//	}
//
//	public SmsSendingResult send(String mobile, String content) {
//		return sendAction(mobile,content,false);
//	}
//	
//	public SmsSendingResult sendForMarketing(String mobile, String content) {
//		return sendAction(mobile,content,true);
//	}
//
//	/**
//	 * 发送语音验证短信
//	 *
//	 * @param mobile
//	 *            手机号码
//	 * @param content
//	 *            短信内容
//	 */
//	public SmsSendingResult sendVoice(String mobile, String content) {
//		return null;
//	}
//
//	/**
//	 * 查询短信余额
//	 */
//	public String getBalance() {
//		return null;
//	}
//	
//	private SmsSendingResult sendAction(String mobile, String content,Boolean forMarketing){
//		SmsSendingResult sr = new SmsSendingResult();
//		sr.setContent(content);
//		sr.setPhone(mobile);
//		try {
//			if (!content.startsWith(signature)) {
//				content = signature + content;
//			}
//			String r = VisiAPI.sendSms(apiId,mobile, content, "", forMarketing);
//			sr.setResultFromVendor(r);
//			sr.setResult(r.contains("successful"));
//			return sr;
//		} catch (IOException e) {
//			logger.error("短信发送失败：", e);
//			sr.setResult(false);
//			return sr;
//		}
//	}
//}
