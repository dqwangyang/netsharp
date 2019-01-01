//package org.netsharp.sms.client;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.message.BasicNameValuePair;
//
//public class ChangTianYouClient implements ISmsClient {
//
//	private static final Log logger = LogFactory.getLog(ChangTianYouClient.class);
//
//	private static String un = SmsClientFactory.smsContext.getUid(); // "ctyswse-32";
//	private static String pwd = SmsClientFactory.smsContext.getPwd();// "a953e5";
//	private static String signature = SmsClientFactory.smsContext.getSignature();// "【易快修】";
//
//	public String getSupplierName() {
//		return "ChangTianYou";
//	}
//
//	public SmsSendingResult sendVoice(String mobile, String content) {
//		return null;
//	}
//
//	/**
//	 * 发送短信
//	 * 
//	 * @param mobile
//	 *            手机号码
//	 * @param content
//	 *            短信内容
//	 */
//	@Override
//	public SmsSendingResult send(String mobile, String content) {
//		try {
//			List<BasicNameValuePair> Listnvps = new ArrayList<BasicNameValuePair>();
//			Listnvps.add(new BasicNameValuePair("un", un));
//			Listnvps.add(new BasicNameValuePair("pwd", pwd));
//			Listnvps.add(new BasicNameValuePair("mobile", mobile));
//			Listnvps.add(new BasicNameValuePair("msg", content + signature));
//
//			CloseableHttpClient httpClient = HttpClients.createDefault();
//			HttpPost httpPost = new HttpPost("http://si.800617.com:4400/SendSms.aspx");
//
//			httpPost.setEntity(new UrlEncodedFormEntity(Listnvps, "UTF-8"));
//			CloseableHttpResponse httppHttpResponse2 = httpClient.execute(httpPost);
//
//			try {
//				// 请求状态
//				// System.out.println(httppHttpResponse2.getStatusLine());
//				// String result =
//				// EntityUtils.toString(httppHttpResponse2.getEntity());
//
//				// return result;
//				// 1 = 发送成功
//				// -1 = 用户名和密码参数为空或者参数含有非法字符
//				// -2 = 手机号参数不正确
//				// -3 = msg参数为空或长度小于0个字符
//				// -4 = msg参数长度超过64个字符
//				// -6 = 发送号码为黑名单用户
//				// -8 = 下发内容中含有屏蔽词
//				// -9 = 下发账户不存在
//				// -10 = 下发账户已经停用
//				// -11 = 下发账户无余额
//				// -15 = MD5校验错误
//				// -16 = IP服务器鉴权错误
//				// -17 = 接口类型错误
//				// -18 = 服务类型错误
//				// -22 = 手机号达到当天发送限制
//				// -23 = 同一手机号，相同内容达到当天发送限制
//				// -24 =  模板不存在
//				// -25 = 模板变量超长
//				// -26 = 下发限制，该号码没有上行记录
//				// -27 = 手机号不是白名单用户
//				// -99 = 系统异常
//			} finally {
//				httppHttpResponse2.close();
//			}
//		} catch (Exception e) {
//			logger.error(e.getMessage());
//
//		}
//
//		SmsSendingResult sr = new SmsSendingResult();
//		sr.setContent(content);
//		sr.setPhone(mobile);
//		return sr;
//	}
//
//	@Override
//	public String getBalance() {
//		return null;
//	}
//
//	@Override
//	public SmsSendingResult sendForMarketing(String mobile, String content) {
//		return null;
//	}
//}
