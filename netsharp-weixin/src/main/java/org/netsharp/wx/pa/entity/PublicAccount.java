package org.netsharp.wx.pa.entity;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.BizEntity;
import org.netsharp.wx.pa.dic.PublicAccountType;

/*微信公众号*/
@Table(name = "wx_pa_account", header = "公众号")
public class PublicAccount extends BizEntity {

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 3231269021555084182L;

	@Column(name = "turnout", header = "是否转出")
	private Boolean turnout = false;

	@Column(name = "subscribe_code", header = "关注回复关键字")
	private String subscribeCode;

	@Column(name = "unhint_code", header = "无关键字匹配时回复")
	private String unhintCode;

	@Column(name = "weixin_code", header = "微信号")
	private String weixinCode;

	@Column(name = "qr_code", size = 500, header = "二维码地址")
	private String qrCode;

	@Column(name = "public_account_type", header = "公众号类型")
	private PublicAccountType publicAccountType;

	@Column(name = "tel", header = "热线电话")
	private String tel;

	@Column(name = "authenticated", header = "公众号已经认证")
	private Boolean authenticated = false;

	// 需要配置的基本信息
	@Column(name = "app_id", header = "appId")
	private String appId;

	@Column(name = "app_secret", header = "appSecret")
	private String appSecret;

	@Column(name = "token", header = "token")
	private String token;

	@Column(name = "encryption_key", size = 200)
	private String encryptionKey;

	@Column(name = "original_id", header = "原始Id")
	private String originalId;

	// 微信支付
	@Column(name = "mch_id", header = "支付的mchid")
	private String mch_id; // 支付的mchid = "10037118";

	@Column(name = "mch_parner_key", header = "标题")
	private String mchParnerKey; // 支付商户key =
									// "e9ceec8b8ac546fc965a809be4755c98";

	@Column(name = "mch_notify_url", header = "标题")
	private String mchNotifyUrl; // 支付回调url =
									// "http://www.yikuaixiu.com/weixin/pay/notify";

	// app支付
//	@Column(name = "header", header = "标题")
//	private String app_id; // "wx0afa0649f741bc3f";

	@Column(name = "app_mch_id", header = "标题")
	private String appMchId; // APP_MCHID = "1230701601";

	@Column(name = "app_partner_key", header = "标题")
	private String appPartnerKey; // APP_PARTNERKEY =
									// "c14dbbfce8c711e48aa200163e0033de";

	// // 微信红包接口
	// private String redpacket_api_url=
	// "https://api.mch.weixin.qq.com/mmpaymkttransfers/sendredpack";
	// private String redpacket_apiclient_cert="apiclient_cert.p12";//证书

	@Column(name = "host", size = 300, header = "微信网站的域名")
	private String host;


	public String getSubscribeCode() {
		return subscribeCode;
	}

	public void setSubscribeCode(String subscribeCode) {
		this.subscribeCode = subscribeCode;
	}

	public String getUnhintCode() {
		return unhintCode;
	}

	public void setUnhintCode(String unhintCode) {
		this.unhintCode = unhintCode;
	}

	public String getWeixinCode() {
		return weixinCode;
	}

	public void setWeixinCode(String weixinCode) {
		this.weixinCode = weixinCode;
	}

	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}


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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getEncryptionKey() {
		return encryptionKey;
	}

	public void setEncryptionKey(String encryptionKey) {
		this.encryptionKey = encryptionKey;
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

	public PublicAccountType getPublicAccountType() {
		return publicAccountType;
	}

	public void setPublicAccountType(PublicAccountType publicAccountType) {
		this.publicAccountType = publicAccountType;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Boolean getTurnout() {
		return turnout;
	}

	public void setTurnout(Boolean turnout) {
		this.turnout = turnout;
	}

	public Boolean getAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(Boolean authenticated) {
		this.authenticated = authenticated;
	}

	public String getMchParnerKey() {
		return mchParnerKey;
	}

	public void setMchParnerKey(String mchParnerKey) {
		this.mchParnerKey = mchParnerKey;
	}

	public String getMchNotifyUrl() {
		return mchNotifyUrl;
	}

	public void setMchNotifyUrl(String mchNotifyUrl) {
		this.mchNotifyUrl = mchNotifyUrl;
	}

	public String getAppMchId() {
		return appMchId;
	}

	public void setAppMchId(String appMchId) {
		this.appMchId = appMchId;
	}

	public String getAppPartnerKey() {
		return appPartnerKey;
	}

	public void setAppPartnerKey(String appPartnerKey) {
		this.appPartnerKey = appPartnerKey;
	}
}
