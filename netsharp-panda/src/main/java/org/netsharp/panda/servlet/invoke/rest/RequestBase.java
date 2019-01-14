package org.netsharp.panda.servlet.invoke.rest;

import org.netsharp.panda.entity.PPart;


public abstract class RequestBase {

	private Long vid;
	private String service;
	private PPart ppart;
	
	public Long getvid() {
		return vid;
	}

	public void setvid(Long vid) {
		this.vid = vid;
	}
	
	public PPart getPpart() {
		return ppart;
	}

	public void setPpart(PPart ppart) {
		this.ppart = ppart;
	}
	
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
}
