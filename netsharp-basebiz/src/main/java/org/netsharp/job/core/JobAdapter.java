package org.netsharp.job.core;

import org.netsharp.job.entity.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/*quartz的job适配器*/
public class JobAdapter implements org.quartz.Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {

		Job scheduleJob = (Job) context.getMergedJobDataMap().get("scheduleJob");

		JobManager jm = new JobManager();

		jm.run(scheduleJob);

	}
}