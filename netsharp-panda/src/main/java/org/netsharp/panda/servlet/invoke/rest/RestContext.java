package org.netsharp.panda.servlet.invoke.rest;

import java.lang.reflect.Method;

import org.netsharp.panda.servlet.invoke.PandaContext;

public class RestContext extends PandaContext {

	private String name;
	private Object serviceObject;
	private Method method;
	private RequestBase request;
	private Result<Object> response;
	private Boolean isTruncation = false;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Object getServiceObject() {
		return serviceObject;
	}
	public void setServiceObject(Object serviceObject) {
		this.serviceObject = serviceObject;
	}
	public Method getMethod() {
		return method;
	}
	public void setMethod(Method method) {
		this.method = method;
	}

	public RequestBase getRequest() {
		return request;
	}
	public void setRequest(RequestBase request) {
		this.request = request;
	}
	public Result<Object> getResponse() {
		return response;
	}
	public void setResponse(Result<Object> response) {
		this.response = response;
	}
	public Boolean getIsTruncation() {
		return isTruncation;
	}
	public void setIsTruncation(Boolean isTruncation) {
		this.isTruncation = isTruncation;
	}
}
