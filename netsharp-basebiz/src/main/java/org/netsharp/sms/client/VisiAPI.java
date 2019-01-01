//package org.netsharp.sms.client;
//
//import java.io.IOException;
//import java.net.URISyntaxException;
//import java.util.HashMap;
//import java.util.Map;
//
//import org.apache.http.HttpEntity;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.protocol.HTTP;
//import org.apache.http.util.EntityUtils;
//import org.netsharp.util.JsonManage;
//
///**
// * visi系统自己的发送短信平台。
// * 
// */
//public class VisiAPI {
//
//	// 发送短信验证码http地址
//	private static String URI_VERIFICATION_CODE = "http://10.165.125.137/webapi/sms/verification_code";
//
//	// 通用发送接口的http地址
//	private static String URI_VERIFICATION_CODE_CHECKING = "http://10.165.125.137/webapi/sms/verification_code_checking";
//
//	// 发送接口的http地址
//	private static String URI_SEND = "http://10.165.125.137/webapi/sms";
//
//	// 编码格式。发送编码格式统一用UTF-8
//	private static String ENCODING = "UTF-8";
//
//	private static final String APPLICATION_JSON = "application/json";
//
//	public static void main(String[] args) throws IOException, URISyntaxException {
//
//		// 修改为您要发送的手机号
//		String mobile = "18500197198";
//
//		/**************** 使用通用接口发短信(推荐) *****************/
//		String text = "欢迎您使用VISI";
//		// 发短信调用示例
//		System.out.println(sendSms("visi", mobile, text, "", true));
//
//	}
//
//	/**
//	 * 通用接口发短信
//	 *
//	 * @param content
//	 *            短信内容
//	 * @param mobile
//	 *            　接受的手机号
//	 * @param business_type
//	 *            业务类型，统计使用
//	 * @param for_marketing
//	 *            是否为营销短信
//	 * @return json格式字符串
//	 * @throws IOException
//	 */
//	public static String sendSms(String appId, String mobile, String content, String businessType, boolean forMarketing) throws IOException {
//		Map<String, String> params = new HashMap<String, String>();
//
//		params.put("mobiles", mobile);
//		params.put("content", content);
//		params.put("app_id", appId);
//		// params.put("business_type",businessType);
//		params.put("for_marketing", forMarketing ? "true" : "false");
//
//		return post(URI_SEND, params);
//	}
//
//	/**
//	 * 发短信验证码
//	 *
//	 * @param mobile
//	 *            　接受的手机号
//	 * @param code
//	 *            可选，若为空，系统会自动生成
//	 * @param sms_type
//	 *            取值范围：text，voice；
//	 * @return json格式字符串
//	 * @throws IOException
//	 */
//	public static String sendVerificationCode(String appId, String mobile, String code, String smsType) {
//		Map<String, String> params = new HashMap<String, String>();
//
//		params.put("mobile", mobile);
//		params.put("code", code);
//		params.put("app_id", appId);
//		params.put("sms_type", smsType);
//
//		return post(URI_VERIFICATION_CODE, params);
//
//	}
//
//	/**
//	 * 验证短信验证码
//	 *
//	 * @param mobile
//	 *            　接受的手机号
//	 * @param code
//	 *            可选，若为空，系统会自动生成
//	 * @return json格式字符串
//	 * @throws IOException
//	 */
//	public static String sendVerificationCodeChecking(String appId, String mobile, String code) {
//		Map<String, String> params = new HashMap<String, String>();
//
//		params.put("mobile", mobile);
//		params.put("code", code);
//		params.put("app_id", appId);
//
//		return post(URI_VERIFICATION_CODE_CHECKING, params);
//
//	}
//
//	/**
//	 * 基于HttpClient 4.3的通用POST方法
//	 *
//	 * @param url
//	 *            提交的URL
//	 * @param paramsMap
//	 *            提交<参数，值>Map
//	 * @return 提交响应
//	 */
//	public static String post(String url, Map<String, String> paramsMap) {
//		CloseableHttpClient client = HttpClients.createDefault();
//		String responseText = "";
//		CloseableHttpResponse response = null;
//		try {
//			HttpPost post = new HttpPost(url);
//			post.addHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);
//
//			StringEntity s = new StringEntity(JsonManage.serialize(paramsMap).toString(), ENCODING);
//			s.setContentType(APPLICATION_JSON);
//			post.setEntity(s);
//
//			response = client.execute(post);
//			HttpEntity entity = response.getEntity();
//			if (entity != null) {
//				responseText = EntityUtils.toString(entity);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				response.close();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		return responseText;
//	}
//}