package org.netsharp.panda.servlet.filters;

import java.util.Date;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.panda.core.HttpContext;
import org.netsharp.panda.core.comunication.IRequest;
import org.netsharp.panda.servlet.invoke.PandaContext;
import org.netsharp.panda.servlet.invoke.PandaInvokeType;
import org.netsharp.util.DateManage;

public class LogFilter implements IPandaFilter {

	protected static final Log logger = LogFactory.getLog(LogFilter.class.getName());
	private Date startTime;
	private UUID requestId;
	private PandaInvokeType type;
	private String requestURL;
	private String queryString;
	private String sessionId;
	private String clientIp;
	private String userAgent;

	public LogFilter(PandaInvokeType type) {
		this.type = type;
	}

	public void invoking(PandaContext ctx) {
		
		IRequest request = HttpContext.getCurrent().getRequest();

		this.startTime = new Date();
		this.requestId = ctx.getRequestId();
		this.requestURL = request.getRequestURL();
		this.queryString= request.getQueryString();
		this.sessionId = request.getSessionId();
		this.clientIp = request.getClientIp();
		this.userAgent= request.getUserAgent();

		String message = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s", 
				"<",
				"http request",
				this.type.name(), 
				this.sessionId,
				this.clientIp,
				this.requestId, 
				this.requestURL,
				this.queryString,
				this.userAgent,
				DateManage.toMillisecondsString(this.startTime));
		logger.debug(message);
	}

	@Override
	public void invoked(PandaContext ctx) {

		Date endTime = new Date();
		Long eclipse = endTime.getTime() - startTime.getTime();

		String message = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s", 
				">",
				"http request",
				this.type.name(), 
				this.sessionId,
				this.clientIp,
				this.requestId, 
				this.requestURL,
				this.queryString,
				this.userAgent,
				eclipse,
				DateManage.toMillisecondsString(this.startTime),
				DateManage.toMillisecondsString(endTime));
		logger.debug(message);

		PandaContext.clean();
	}

}
