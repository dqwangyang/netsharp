package org.netsharp.communication.protocol.http.consumer;

import org.netsharp.communication.consumer.ClientInvoker;
import org.netsharp.communication.core.CommunicationException;

public class HttpInvoker extends ClientInvoker {

	public HttpInvoker(String interfaceType) {

		this.interfaceType = interfaceType;
		
		throw new CommunicationException("not implements!!!");
	}

}