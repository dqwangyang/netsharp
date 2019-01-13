package org.netsharp.communication.provider.filter;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.communication.consumer.filters.ExceptionFilter.Exceptions;
import org.netsharp.communication.core.CommunicationConfiguration;
import org.netsharp.communication.core.InvokeRequest;
import org.netsharp.communication.provider.IServiceFilter;
import org.netsharp.communication.provider.SiContext;
import org.netsharp.core.BusinessException;
import org.netsharp.session.NetsharpSession;
import org.netsharp.util.DateManage;
import org.netsharp.util.StringManager;

public class LogFilter implements IServiceFilter {

	protected final Log logger = LogFactory.getLog(this.getClass());

	private Date startTime;
	private Long userId=null;
	private String userName = "";
	private String sessionId = "";

	@Override
	public void invoking(SiContext ctx) {

		this.startTime = new Date();
		
		InvokeRequest request = ctx.Request;
		NetsharpSession session = request.getSession();
		if(session!=null) {
			userId = session.getUserId();
			sessionId = session.getSessionId();
			userName = session.getUserName();
		}
		
		String message = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s", 
				"<",
				"provider invoke",
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
	public void invoked(SiContext ctx) {

		Date endTime = new Date();
		Long eclipse = endTime.getTime() - this.startTime.getTime();
		
		InvokeRequest request = ctx.Request;
		//protocol,userId,userName,sessionId,requestId,interfaceType,method,eclipse,startTime,endTime
		String message = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s", 
				">",
				"provider invoke",
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

		if (ctx.Response.getSucceed()) {
			return;
		}

		Throwable cause = ctx.Response.getException().getCause();
		BusinessException be = Exceptions.causeby(cause, BusinessException.class);

		if (be != null) {
			logger.debug("执行失败！" + be.getMessage());
		} else {
			logger.error("执行失败！" + StringManager.NewLine, cause);
		}
	}
}
