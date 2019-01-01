package org.netsharp.wx.sdk.mp.api.accesstoken;

public class PAccount {
	
	private String name;
	
	//基本信息
	private String appId;
	private String appSecret;
	private String originalId;
	
    // 微信支付
	private String            mch_id;          // 支付的mchid  = "10037118";
	private String            mch_parner_key;  // 支付商户key  = "e9ceec8b8ac546fc965a809be4755c98";
	private String            mch_notify_url;  // 支付回调url  = "http://www.yikuaixiu.com/weixin/pay/notify";

	// app支付
	private String            app_id;          // "wx0afa0649f741bc3f";
	private String            app_mch_id;      // APP_MCHID = "1230701601";
	private String            app_partner_key; // APP_PARTNERKEY = "c14dbbfce8c711e48aa200163e0033de";
	
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getAppSecret() {
		return appSecret;
	}
	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}
	public String getOriginalId() {
		return originalId;
	}
	public void setOriginalId(String originalId) {
		this.originalId = originalId;
	}
	public String getMch_id() {
		return mch_id;
	}
	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}
	public String getMch_parner_key() {
		return mch_parner_key;
	}
	public void setMch_parner_key(String mch_parner_key) {
		this.mch_parner_key = mch_parner_key;
	}
	public String getMch_notify_url() {
		return mch_notify_url;
	}
	public void setMch_notify_url(String mch_notify_url) {
		this.mch_notify_url = mch_notify_url;
	}
	public String getApp_id() {
		return app_id;
	}
	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}
	public String getApp_mch_id() {
		return app_mch_id;
	}
	public void setApp_mch_id(String app_mch_id) {
		this.app_mch_id = app_mch_id;
	}
	public String getApp_partner_key() {
		return app_partner_key;
	}
	public void setApp_partner_key(String app_partner_key) {
		this.app_partner_key = app_partner_key;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
