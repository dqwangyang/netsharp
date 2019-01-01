package org.netsharp.panda.servlet.invoke.rest.rest;

public class RequestParameter {
	
    private String value;
    private Object parameter;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Object getParameter() {
		return parameter;
	}

	public void setParameter(Object parameter) {
		this.parameter = parameter;
	}
}
