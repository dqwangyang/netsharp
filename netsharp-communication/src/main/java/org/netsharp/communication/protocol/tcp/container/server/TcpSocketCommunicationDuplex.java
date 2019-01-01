package org.netsharp.communication.protocol.tcp.container.server;

import java.net.Socket;
import java.util.Date;

import org.netsharp.communication.protocol.tcp.container.serialize.TcpSerializeFactory;
import org.netsharp.util.DateManage;
import org.netsharp.util.StringManager;

public class TcpSocketCommunicationDuplex extends TcpSocketCommunication {

	public TcpSocketCommunicationDuplex(Socket socket) {
		logger.debug("begin TcpSocketCommunicationDuplex");
		
		this.socket = socket;
		this.serialize = TcpSerializeFactory.create();
	}

    public  void  execute(Object firstRequestValue) {
    	
    	logger.debug("execute TcpSocketCommunicationDuplex");
    	
    	System.out.println("from client : " + firstRequestValue);
    	
    	while (true) {
			
			Object message = Thread.currentThread().getId() + "  duplex client saysing.... " + DateManage.toString(new Date(), "yyyy-MM-dd mm:hh:ss sss");
			this.send(message);
			
			Object obj = this.read();
			System.out.println("from client : " + obj);
			if (StringManager.equals(obj.toString(), "EOF")) {
				break;
			}
		}

		this.close();
    }
}
