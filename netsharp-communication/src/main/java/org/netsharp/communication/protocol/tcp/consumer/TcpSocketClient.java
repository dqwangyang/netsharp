package org.netsharp.communication.protocol.tcp.consumer;

import java.net.Socket;
import java.net.SocketException;

import org.netsharp.communication.core.CommunicationConfiguration;
import org.netsharp.communication.core.CommunicationException;
import org.netsharp.communication.protocol.tcp.container.serialize.TcpSerializeFactory;
import org.netsharp.communication.protocol.tcp.core.TcpSocket;

public class TcpSocketClient extends TcpSocket {

	public void connect(CommunicationConfiguration conf)
	{
		logger.debug("connect");
		try {
			this.socket = new Socket(conf.getHost(), conf.getPort());	
		} catch (Exception ex) {
			throw new CommunicationException("创建socket异常", ex);
		}
		this.serialize = TcpSerializeFactory.create();
	}
	
	//设置timeout，毫秒
	public void setTimeoust(int timeout) {
		logger.debug("set timeout "+timeout);
		try {
			this.socket.setSoTimeout(timeout);
		} catch (SocketException e) {
			throw new CommunicationException("设置客户端sockettimeout异常", e);
		}
	}
}
