package org.netsharp.communication.core;

import java.io.Serializable;
import java.util.UUID;

import org.netsharp.session.NetsharpSession;

public class InvokeRequest implements Serializable {
	
	private static final long serialVersionUID = 853466480206176743L;
	
	private UUID requestId;
	private String type;
	private String method;
	private Object[] pars;
	private String[] parType;
	private NetsharpSession session;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public Object[] getPars() {
		return pars;
	}
	public void setPars(Object[] pars) {
		this.pars = pars;
	}
	public String[] getParType() {
		return parType;
	}
	public void setParType(String[] parType) {
		this.parType = parType;
	}
	public NetsharpSession getSession() {
		return session;
	}
	public void setSession(NetsharpSession session) {
		this.session = session;
	}
	public UUID getRequestId() {
		return requestId;
	}
	public void setRequestId(UUID requestId) {
		this.requestId = requestId;
	}
	
	
}
