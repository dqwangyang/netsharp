package com.netsharp.communication.tcp.entity;

import java.io.Serializable;
import java.util.Date;

public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 3142125386935984709L;
	
	private Integer id;
	
	private Date createTime = new Date();
	private String creator = "netsharp";
	private Date updateTime = new Date();
	private String updator = "netsharp";
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getUpdator() {
		return updator;
	}
	public void setUpdator(String updator) {
		this.updator = updator;
	}

	
}
