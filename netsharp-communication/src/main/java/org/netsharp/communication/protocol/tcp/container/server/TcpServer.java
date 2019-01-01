package org.netsharp.communication.protocol.tcp.container.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.communication.core.CommunicationException;
import org.netsharp.communication.protocol.tcp.container.server.filter.SessionFilter;

public class TcpServer {

	private ServerSocket server = null;
	private ThreadPoolExecutor threadPool;
	protected static Log logger = LogFactory.getLog(TcpServer.class);

	public void start(TcpServerConfiguration conf) {
		
		new SessionFilter().startup();
		
		logger.debug("tcp server start");

		try {
			server = new ServerSocket(conf.getPort());
		} catch (IOException e) {
			throw new CommunicationException("tcp server 启动异常", e);
		}
		
		this.threadPool = new ThreadPoolExecutor(conf.getCorePoolSize(),conf.getMaximumPoolSize(),conf.getKeepAliveTime(),TimeUnit.SECONDS,new SynchronousQueue<Runnable>());

	}

	public void accept() {
		
		logger.debug("tcp server accept");

		Socket socket = null;
		try {
			socket = server.accept();
		} catch (IOException e) {
			throw new CommunicationException("tcp server accept异常", e);
		}
		
		TcpSocketServerProcessor tcpsocket = new TcpSocketServerProcessor(socket);
		this.threadPool.execute(tcpsocket);
	}

	public void close() {
		
		logger.debug("tcp server close");

		try {
			server.close();
		} catch (IOException e) {
			throw new CommunicationException("tcp server accept异常", e);
		}
	}

}
