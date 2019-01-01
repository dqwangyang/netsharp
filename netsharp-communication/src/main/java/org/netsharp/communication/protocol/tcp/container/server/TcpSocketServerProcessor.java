package org.netsharp.communication.protocol.tcp.container.server;

import java.net.Socket;

import org.netsharp.communication.protocol.tcp.container.serialize.TcpSerializeFactory;
import org.netsharp.communication.protocol.tcp.core.TcpRequest;
import org.netsharp.communication.protocol.tcp.core.TcpSocket;
import org.netsharp.util.ReflectManager;

public class TcpSocketServerProcessor extends TcpSocket implements Runnable {
	
	public TcpSocketServerProcessor(Socket socket) {
		
		logger.debug("new TcpSocketServerProcessor");
		
		this.socket = socket;
		this.serialize = TcpSerializeFactory.create();
	}
	
	public synchronized void run() {
		
		logger.debug("run TcpSocketServerProcessor");

		TcpRequest request = (TcpRequest)this.read();
		
		String tcpServerType = request.getTcpServerType();
		
		TcpSocketCommunication server = (TcpSocketCommunication)ReflectManager.newInstance(tcpServerType,this.socket);
		
		server.execute(request.getRequestValue());

	}
}