package org.netsharp.wx.sdk.ep.user;

import org.codehaus.jackson.annotate.JsonProperty;
import org.netsharp.wx.sdk.ep.Response;

public class UserInfoResponse  extends Response{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7133521197630718747L;

	@JsonProperty("UserId")
	private String userId;
	
	@JsonProperty("DeviceId")
	private String deviceId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
}
