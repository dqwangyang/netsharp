package org.netsharp.communication.consumer.filters;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.communication.consumer.CiContext;
import org.netsharp.communication.consumer.IClientFilter;
import org.netsharp.communication.core.CommunicationConfiguration;
import org.netsharp.communication.core.InvokeRequest;
import org.netsharp.session.NetsharpSession;
import org.netsharp.util.DateManage;

public class LogFilter implements IClientFilter {

	protected final static Log logger = LogFactory.getLog(LogFilter.class);

	private Date startTime;
	private Long userId=null;
	private String userName = "";
	private String sessionId = "";

	@Override
	public void invoking(CiContext ctx) {

		this.startTime = new Date();
		
		InvokeRequest request = ctx.getRequest();
		NetsharpSession session = request.getSession();
		if(session!=null) {
			userId = session.getUserId();
			sessionId = session.getSessionId();
			userName = session.getUserName();
		}

		String message = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s", 
				"<",
				"consumer invoke",
				CommunicationConfiguration.get().getProtocol().name(),
				userId,
				userName,
				sessionId,
				request.getRequestId(),
				request.getType(), 
				request.getMethod(),
				DateManage.toMillisecondsString(this.startTime));
		logger.debug(message);
	}

	@Override
	public void invoked(CiContext ctx) {

		Date endTime = new Date();
		Long eclipse = endTime.getTime() - this.startTime.getTime();
		
		InvokeRequest request = ctx.getRequest();

		String message = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s",
				">",
				"consumer invoke",
				CommunicationConfiguration.get().getProtocol().name(),
				userId,
				userName,
				sessionId,
				request.getRequestId(),
				request.getType(), 
				request.getMethod(),
				eclipse, 
				DateManage.toMillisecondsString(this.startTime),
				DateManage.toMillisecondsString(endTime));
		logger.debug(message);
	}

}