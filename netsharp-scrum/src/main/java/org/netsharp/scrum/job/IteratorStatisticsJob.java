package org.netsharp.scrum.job;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.job.core.IJob;
import org.netsharp.scrum.base.IIteratorStatisticsService;

/**
 * 迭代统计生成报表任务
 * @author MengLingqin
 * 2016-06-07 09:20
 */
public class IteratorStatisticsJob implements IJob {
	
	IIteratorStatisticsService iteratorStatisticsService = ServiceFactory.create(IIteratorStatisticsService.class);

	public void execute(String par) {
		// TODO Auto-generated method stub
		Log logger = LogFactory.getLog(IteratorStatisticsJob.class);
		logger.info("IteratorStatisticsJob" + "------------------------start");
		iteratorStatisticsService.run();
		logger.info("IteratorStatisticsJob" + "------------------------end");
	}

}
