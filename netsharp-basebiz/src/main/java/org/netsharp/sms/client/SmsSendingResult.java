package org.netsharp.sms.client;

public class SmsSendingResult {	 
	private Boolean result;
	private String phone;
	private String content;
	private String resultFromVendor;
	public Boolean getResult() {
		return result;
	}
	public void setResult(Boolean result) {
		this.result = result;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getResultFromVendor() {
		return resultFromVendor;
	}
	public void setResultFromVendor(String resultFromVendor) {
		this.resultFromVendor = resultFromVendor;
	}

}
