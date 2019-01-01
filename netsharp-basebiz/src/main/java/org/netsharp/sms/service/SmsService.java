package org.netsharp.sms.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.communication.Service;
import org.netsharp.sms.base.ISmsService;
import org.netsharp.sms.client.ISmsClient;
import org.netsharp.sms.client.ManDaoClient;
import org.netsharp.sms.client.SmsClientFactory;
import org.netsharp.sms.client.SmsSendingResult;
import org.netsharp.util.JsonManage;

@Service
public class SmsService implements ISmsService {
	private static final Log logger = LogFactory.getLog(SmsService.class);

	private static ISmsClient client;
	@SuppressWarnings("unused")
	private static ISmsClient alternativeClient;

	public SmsService() {
		client = SmsClientFactory.createDefaultClient();
		alternativeClient = new ManDaoClient();

	}

	// 不发送短信的号码：拖车、抢修、无姓名
	private static String[] exclusives = new String[] { "13263289633", "15801456152", "13241494243", "13811111111" };

	public void send(String mobile, String content, String type) {
		this.doSend(mobile, content,false);

	    //SaveSmsLog(mobile, content, type, client.getSupplierName()); VISI短信发送平台会自己记录
	}
	
	public void sendForMarketing(String mobile, String content, String type) {
		this.doSend(mobile, content,true);		
	}

	public void send(String[] mobiles, String content, String type) {
		if (mobiles == null || mobiles.length == 0) {
			return;
		}

		for (String mobile : mobiles) {
			try {
				this.doSend(mobile, content,false);
			} catch (Exception ex) {
				logger.error(ex);
			}
		}

	}

	public void sendForMarketing(String[] mobiles, String content, String type) {
		if (mobiles == null || mobiles.length == 0) {
			return;
		}

		for (String mobile : mobiles) {
			try {
				this.doSend(mobile, content,true);
			} catch (Exception ex) {
				logger.error(ex);
			}
		}
		
	}
	
	private void doSend(String mobile, String content,Boolean forMarketing) {

		for (String exclusive : exclusives) {
			if (exclusive.equals(mobile)) {
				return;
			}
		}
		SmsSendingResult ret = forMarketing ? client.sendForMarketing(mobile, content) : client.send(mobile, content);
		if (!ret.getResult()) {
			logger.info("短信发送失败：" + JsonManage.serialize2(ret));
		} else {
			logger.info("sending sms, phoneNumber: '" + mobile + "',content:" + content);
		}
	}

	public void sendVoice(String mobile, String content, String type) {
		
		for (String exclusive : exclusives) {
			if (exclusive.equals(mobile)) {
				return;
			}
		}
		SmsSendingResult ret = client.sendVoice(mobile, content);
		if (!ret.getResult()) {
			logger.info("短信发送失败：" + JsonManage.serialize2(ret));
		} else {
			logger.info("sending sms, phoneNumber: '" + mobile + "',content:" + content);
		}
	}

	/**
	 * @return 得到短信余额
	 */
	public int getBalance() {
		try {
			String balance = client.getBalance();
			return Integer.valueOf(balance);
		} catch (Exception ex) {
			logger.error("can not check balance");
			return 0;
		}
	}

}
