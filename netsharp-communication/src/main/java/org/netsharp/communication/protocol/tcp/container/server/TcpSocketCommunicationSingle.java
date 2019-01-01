package org.netsharp.communication.protocol.tcp.container.server;

import java.net.Socket;

import org.netsharp.communication.core.InvokeRequest;
import org.netsharp.communication.core.InvokeResponse;
import org.netsharp.communication.protocol.tcp.container.serialize.TcpSerializeFactory;
import org.netsharp.communication.provider.ServiceInvoker;

public class TcpSocketCommunicationSingle extends TcpSocketCommunication {

	public TcpSocketCommunicationSingle(Socket socket) {
		
		logger.debug("begin TcpSocketCommunicationSingle");
		
		this.socket = socket;
		this.serialize = TcpSerializeFactory.create();
	}

	public void execute(Object firstRequestValue) {
		
		logger.debug("execute TcpSocketCommunicationSingle");

		InvokeRequest request = (InvokeRequest)firstRequestValue;
		
		ServiceInvoker invoker = new ServiceInvoker();
		
		InvokeResponse response = invoker.invoke(request);

		this.send( response );

		this.close();
	}

}
