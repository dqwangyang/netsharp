package org.netsharp.communication.protocol.tcp.consumer;

import org.netsharp.communication.consumer.CiContext;
import org.netsharp.communication.consumer.IClientFilter;
import org.netsharp.communication.core.CommunicationConfiguration;
import org.netsharp.communication.core.InvokeRequest;
import org.netsharp.communication.core.InvokeResponse;
import org.netsharp.communication.protocol.tcp.core.TcpRequest;

public class TcpInvokeFilter implements IClientFilter {

	public void invoking(CiContext ctx) {
		
		if(ctx.getIsCacheHint()) {
			return;
		}
		
		InvokeRequest request = ctx.getRequest();
		
		TcpRequest tcpRequest = new TcpRequest();
		{
			tcpRequest.setRequestValue(request);
		}
		
		// 如果命中缓存就不用再进行远程调用了
		TcpSocketClient socket = new TcpSocketClient();
		socket.connect(CommunicationConfiguration.get());
		socket.send(tcpRequest);
		Object obj = socket.read();
		socket.close();
		
		InvokeResponse response = (InvokeResponse)obj;
		ctx.setResponse(response);
	}

	public void invoked(CiContext ctx) {
	}

}