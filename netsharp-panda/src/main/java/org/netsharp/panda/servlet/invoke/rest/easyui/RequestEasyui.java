package org.netsharp.panda.servlet.invoke.rest.easyui;

import org.netsharp.panda.servlet.invoke.rest.RequestBase;

public class RequestEasyui extends RequestBase {

	private String method;

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
}