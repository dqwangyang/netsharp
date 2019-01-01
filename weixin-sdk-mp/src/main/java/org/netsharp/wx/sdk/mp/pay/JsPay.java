package org.netsharp.wx.sdk.mp.pay;

import java.util.HashMap;

/*微信支付jsapi模式下，WeixinJSBridge.getBrandWCPayRequest调用的json参数*/
public class JsPay extends HashMap<String,String> {
	
	private static final long serialVersionUID = 1L;
	
	public String getAppId() {
		return this.get("appId");
	}
	public void setAppId(String appId) {
		this.put("appId", appId);
	}
	public String getTimeStamp() {
		return this.get("timeStamp");
	}
	public void setTimeStamp(String timeStamp) {
		this.put("timeStamp", timeStamp);
	}
	public String getNonceStr() {
		return this.get("nonceStr");
	}
	public void setNonceStr(String nonceStr) {
		this.put("nonceStr", nonceStr);
	}
	public String getSignType() {
		return this.get("signType");
	}
	public void setSignType(String signType) {
		this.put("signType", signType);
	}
	public String getPaySign() {
		return this.get("paySign");
	}
	public void setPaySign(String paySign) {
		this.put("paySign", paySign);
	}
	public String getPackage() {
		return this.get("package");
	}
	public void setPackage(String p) {
		this.put("package", p);
	}
	
}
