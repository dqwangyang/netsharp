package org.netsharp.job.core;

import java.util.Date;

import org.apache.log4j.Logger;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.job.base.IJobLogService;
import org.netsharp.job.entity.Job;
import org.netsharp.job.entity.JobLog;
import org.netsharp.util.ReflectManager;

public class JobManager {

	private final Logger log = Logger.getLogger(this.getClass());
	private java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public void run(Job pjob) {
		
		IJobLogService joblogService = ServiceFactory.create(IJobLogService.class);
		String javaType = pjob.getJavaType();
		IJob job = (IJob) ReflectManager.newInstance(javaType);
		JobLog jobLog = new JobLog();
		{
			jobLog.toNew();
			jobLog.setOperation("自动执行");
			jobLog.setName(pjob.getName());
			jobLog.setGroupName(pjob.getGroupName());
			jobLog.setJavaType(javaType);
		}

		try {
			log.info("自动执行作业开始--------" + javaType);

			Date startDate = format.parse(format.format(new Date()));
			job.execute(pjob.getPar());
			Date endDate = format.parse(format.format(new Date()));
			long diff = endDate.getTime() - startDate.getTime();// 耗时，毫秒
			long minDiff = diff / (60 * 1000) % 60;
			jobLog.setTimed(minDiff);// 分钟
			log.info("自动执行作业成功--------" + javaType);
			jobLog.setMemoto("执行成功！");
		} catch (Exception e) {
			log.info("自动执行作业" + javaType + " 失败!" + e.getMessage());
			jobLog.setMemoto(" 自动执行失败！" + e.getMessage());
		}

		joblogService.save(jobLog);
	}
}
