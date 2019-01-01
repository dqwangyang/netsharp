package org.netsharp.communication.protocol.tcp.core;

import java.io.Serializable;

import org.netsharp.communication.protocol.tcp.container.server.TcpSocketCommunicationSingle;

public class TcpRequest implements Serializable {

	private static final long serialVersionUID = -1051136198681209026L;
	
	private String tcpServerType = TcpSocketCommunicationSingle.class.getName();
	private Object requestValue;
	
	public String getTcpServerType() {
		return tcpServerType;
	}
	public void setTcpServerType(String tcpServerType) {
		this.tcpServerType = tcpServerType;
	}
	public Object getRequestValue() {
		return requestValue;
	}
	public void setRequestValue(Object requestValue) {
		this.requestValue = requestValue;
	}
}
