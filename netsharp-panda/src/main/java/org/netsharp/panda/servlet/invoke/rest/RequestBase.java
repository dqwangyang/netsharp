package org.netsharp.panda.servlet.invoke.rest;

import org.netsharp.panda.entity.PPart;


public abstract class RequestBase {

	private Integer vid;
	private String service;
	private PPart ppart;
	
	public Integer getvid() {
		return vid;
	}

	public void setvid(Integer vid) {
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
