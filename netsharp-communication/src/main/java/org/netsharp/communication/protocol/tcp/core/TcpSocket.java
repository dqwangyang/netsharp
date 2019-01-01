package org.netsharp.communication.protocol.tcp.core;

import java.net.Socket;
import java.net.SocketException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.communication.core.CommunicationException;
import org.netsharp.communication.protocol.tcp.container.serialize.ITcpSerialize;

public class TcpSocket {

	protected Socket socket;
	protected ITcpSerialize serialize;
	protected static Log logger = LogFactory.getLog(TcpSocket.class);

	public void send(Object obj) {
		logger.debug("send message");
		try {

			serialize.serialize(socket.getOutputStream(), obj);

		} catch (Exception ex) {
			throw new CommunicationException("发送请求send异常", ex);
		}
	}

	public Object read() {
		logger.debug("read message");

		try {
			Object obj = serialize.deSerialize(socket.getInputStream());

			return obj;
		} catch (Exception ex) {
			throw new CommunicationException("接收数据read异常", ex);
		}
	}

	public void close() {
		logger.debug("close");

		try {
			socket.close();
		} catch (Exception ex) {
			throw new CommunicationException("关闭socket异常", ex);
		}
	}

	// true为不黏包
	// 黏包：当在短时间内发送（Socket）很多数据量小的包时，底层（TCP/IP）会根据一定的算法（指Nagle）把一些包合作为一个包发送。
	// 拆包：当一次发送（Socket）的数据量过大，而底层（TCP/IP）不支持一次发送那么大的数据量，则会发生拆包现象。
	public void setTcpNoDelay(boolean on) {
		try {
			this.socket.setTcpNoDelay(on);
		} catch (SocketException ex) {
			throw new CommunicationException("设置黏包异常", ex);
		}
	}
}
