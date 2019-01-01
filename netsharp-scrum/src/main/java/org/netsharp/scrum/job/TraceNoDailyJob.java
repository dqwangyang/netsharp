package org.netsharp.scrum.job;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.job.core.IJob;
import org.netsharp.scrum.base.ITraceNoDailyService;

/**
 * 每日未跟进统计
 */
public class TraceNoDailyJob implements IJob {
	
	public void execute(String par) {
		
		ITraceNoDailyService service = ServiceFactory.create(ITraceNoDailyService.class);
		
		service.generateYestoday();
		
	}

}
