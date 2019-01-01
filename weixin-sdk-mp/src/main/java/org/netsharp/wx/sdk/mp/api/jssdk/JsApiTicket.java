package org.netsharp.wx.sdk.mp.api.jssdk;

import java.io.Serializable;
import java.util.Date;

public class JsApiTicket implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
    private String  originalId;
    private String  appId;
    private Date    createTime;
    private String  ticket; // for jssdk invoke
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOriginalId() {
		return originalId;
	}
	public void setOriginalId(String originalId) {
		this.originalId = originalId;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
