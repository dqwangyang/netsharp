package org.netsharp.web.dto;

public class OssConfig {

	private String host;
	
	private String policyBase64;
	
	private String accessid;
	
	private String signature;
	
	private int expire;
	
	private String callback;
	
	private String dir;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPolicyBase64() {
		return policyBase64;
	}

	public void setPolicyBase64(String policyBase64) {
		this.policyBase64 = policyBase64;
	}

	public String getAccessid() {
		return accessid;
	}

	public void setAccessid(String accessid) {
		this.accessid = accessid;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public int getExpire() {
		return expire;
	}

	public void setExpire(int expire) {
		this.expire = expire;
	}

	public String getCallback() {
		return callback;
	}

	public void setCallback(String callback) {
		this.callback = callback;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}
}
