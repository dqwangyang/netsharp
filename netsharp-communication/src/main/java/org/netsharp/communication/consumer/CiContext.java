package org.netsharp.communication.consumer;

import java.lang.reflect.Method;

import org.netsharp.communication.core.InvokeRequest;
import org.netsharp.communication.core.InvokeResponse;

public class CiContext {
	
	private String name;
	private Method method;
	private Boolean isCacheHint=false;
	private InvokeRequest request;
	private InvokeResponse response;
	
	public InvokeRequest getRequest() {
		return request;
	}
	public void setRequest(InvokeRequest request) {
		this.request = request;
	}
	public InvokeResponse getResponse() {
		return response;
	}
	public void setResponse(InvokeResponse response) {
		this.response = response;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Method getMethod() {
		return method;
	}
	public void setMethod(Method method) {
		this.method = method;
	}
	public Boolean getIsCacheHint() {
		return isCacheHint;
	}
	public void setIsCacheHint(Boolean isCacheHint) {
		this.isCacheHint = isCacheHint;
	}
}
