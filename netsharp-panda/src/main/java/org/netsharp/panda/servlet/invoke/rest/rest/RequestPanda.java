package org.netsharp.panda.servlet.invoke.rest.rest;

import java.util.List;

import org.netsharp.panda.servlet.invoke.rest.RequestBase;

public class RequestPanda extends RequestBase {

	private String methodName;
	private List<RequestParameter> parameters;

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public List<RequestParameter> getParameters() {
		return parameters;
	}

	public void setParameters(List<RequestParameter> parameters) {
		this.parameters = parameters;
	}
}