package org.netsharp.communication.core;

import java.io.Serializable;

public class InvokeResponse implements Serializable {
	
	private static final long serialVersionUID = 2794661303707527230L;
	
	private Boolean succeed;
	private Object returnObject;
	private Throwable exception;
	
	public Boolean getSucceed() {
		return succeed;
	}
	public void setSucceed(Boolean succeed) {
		this.succeed = succeed;
	}
	public Object getReturnObject() {
		return returnObject;
	}
	public void setReturnObject(Object returnObject) {
		this.returnObject = returnObject;
	}
	public Throwable getException() {
		return exception;
	}
	public void setException(Throwable exception) {
		this.exception = exception;
	}
}
