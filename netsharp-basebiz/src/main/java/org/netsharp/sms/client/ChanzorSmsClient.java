package org.netsharp.sms.client;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ChanzorSmsClient implements ISmsClient {

	protected static Log logger = LogFactory.getLog(ChanzorSmsClient.class);

	private static String account = SmsClientFactory.smsContext.getUid();// "zcs8352";
	private static String pwd = SmsClientFactory.smsContext.getPwd();// "133140";
	private static String signature = SmsClientFactory.smsContext.getSignature();// "【易快修】";

	public String getSupplierName() {
		return "Chanzor";
	}

	public SmsSendingResult sendVoice(String mobile, String content) {
		return null;
	}

	/**
	 * 发送短信
	 * 
	 * @param mobile
	 *            手机号码
	 * @param content
	 *            短信内容
	 */
	@Override
	public SmsSendingResult send(String mobile, String content) {
		SmsSendingResult sr = new SmsSendingResult();
		sr.setContent(content);
		sr.setPhone(mobile);

		StringBuilder postData = new StringBuilder();
		// postData.append("?action=send");
		postData.append("account=");
		postData.append(account);
		postData.append("&password=");
		postData.append(pwd);

		postData.append("&mobile=");
		postData.append(mobile);
		postData.append("&sendTime=");
		postData.append((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()));
		postData.append("&content=");
		try {
			postData.append(URLEncoder.encode(content + signature, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		// return sendSMS(postData.toString(), smsUrl);
		return sr;
	}

	//
	// private static String sendSMS(String postData, String postUrl) {
	// try {
	// // 发送POST请求
	// URL url = new URL(postUrl);
	// HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	// conn.setRequestMethod("POST");
	// conn.setRequestProperty("Content-Type",
	// "application/x-www-form-urlencoded");
	// conn.setRequestProperty("Connection", "Keep-Alive");
	// conn.setUseCaches(false);
	// conn.setDoOutput(true);
	//
	// conn.setRequestProperty("Content-Length", "" + postData.length());
	// OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(),
	// "UTF-8");
	// out.write(postData);
	// out.flush();
	// out.close();
	//
	// // 获取响应状态
	// if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
	// System.out.println("connect failed!");
	// return "";
	// }
	// // 获取响应内容体
	// String line, result = "";
	// BufferedReader in = new BufferedReader(new
	// InputStreamReader(conn.getInputStream(), "utf-8"));
	// while ((line = in.readLine()) != null) {
	// result += line + "\n";
	// }
	// in.close();
	// return result;
	// } catch (IOException e) {
	// e.printStackTrace(System.out);
	// }
	// return "";
	// }

	@Override
	public String getBalance() {
		return null;
	}

	@Override
	public SmsSendingResult sendForMarketing(String mobile, String content) {
		return null;
	}
}
