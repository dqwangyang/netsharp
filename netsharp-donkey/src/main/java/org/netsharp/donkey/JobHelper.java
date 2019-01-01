package org.netsharp.donkey;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.BusinessException;
import org.netsharp.core.Oql;
import org.netsharp.job.base.IJobLogService;
import org.netsharp.job.base.IJobService;
import org.netsharp.job.core.JobAdapter;
import org.netsharp.job.entity.Job;
import org.netsharp.job.entity.JobLog;
import org.netsharp.job.entity.JobStatus;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger.TriggerState;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

public class JobHelper {

	private static Log log = LogFactory.getLog(JobHelper.class);
	private static IJobService jobService = ServiceFactory.create(IJobService.class);
	private static IJobLogService logService = ServiceFactory.create(IJobLogService.class);
	private static SchedulerFactory schedulerFactory;
	private static boolean isException = false;

	public static List<Job> getAllTask() {
		Oql oql = new Oql();
		{
			oql.setType(Job.class);
			oql.setFilter(" 1=1 ");
			oql.setSelects("*");
		}
		return jobService.queryList(oql);
	}

	/**
	 * 添加任务
	 * 
	 * @param job
	 * @throws SchedulerException
	 */

	public static void add(Job job) throws SchedulerException {

		if (job == null) {
			return;
		}

		Scheduler scheduler = getSchedulerFactory().getScheduler();
		log.info(scheduler + ".......................................................................................add");
		TriggerKey triggerKey = TriggerKey.triggerKey(job.getName(), job.getGroupName());

		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

		// 不存在，创建一个
		if (null == trigger) {

			JobDetail jobDetail = JobBuilder.newJob(JobAdapter.class).withIdentity(job.getName(), job.getGroupName()).build();

			jobDetail.getJobDataMap().put("scheduleJob", job);

			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCron());

			trigger = TriggerBuilder.newTrigger().withIdentity(job.getName(), job.getGroupName()).withSchedule(scheduleBuilder).build();

			scheduler.scheduleJob(jobDetail, trigger);
		} else {
			// Trigger已存在，那么更新相应的定时设置
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCron());

			// 按新的cronExpression表达式重新构建trigger
			trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

			// 按新的trigger重新设置job执行
			scheduler.rescheduleJob(triggerKey, trigger);
		}

	}

	public static void init() throws Exception {

		// 这里获取任务信息数据
		List<Job> jobList = getAllTask();

		for (Job job : jobList) {
			add(job);
		}
	}

	/**
	 * 暂停一个job
	 * 
	 * @param scheduleJobid
	 * @throws SchedulerException
	 */
	public static void pause(Integer scheduleJobid) throws SchedulerException {
		JobLog entity = new JobLog();
		entity.toNew();
		Job scheduleJob = jobService.byId(scheduleJobid);
		Scheduler scheduler = getSchedulerFactory().getScheduler();
		JobKey jobKey = JobKey.jobKey(scheduleJob.getName(), scheduleJob.getGroupName());
		{
			entity.setOperation("暂停");
			entity.setName(scheduleJob.getName());
			entity.setGroupName(scheduleJob.getGroupName());
			entity.setJavaType(scheduleJob.getJavaType());
		}
		try {
			scheduler.pauseJob(jobKey);
			updateStatus(scheduler, scheduleJob);
			entity.setMemoto("成功!");
			isException = false;
		} catch (Exception e) {
			entity.setMemoto("失败!" + e.getMessage());
			isException = true;
		}
		logService.save(entity);
		if (isException) {
			throw new BusinessException(entity.getMemoto());
		}
	}

	/**
	 * 恢复一个job
	 * 
	 * @param scheduleJobid
	 * @throws SchedulerException
	 */
	public static void resume(Integer scheduleJobid) throws SchedulerException {
		JobLog entity = new JobLog();

		Scheduler scheduler = getSchedulerFactory().getScheduler();
		Job scheduleJob = jobService.byId(scheduleJobid);
		JobKey jobKey = JobKey.jobKey(scheduleJob.getName(), scheduleJob.getGroupName());
		{
			entity.toNew();
			entity.setOperation("恢复");
			entity.setName(scheduleJob.getName());
			entity.setGroupName(scheduleJob.getGroupName());
			entity.setJavaType(scheduleJob.getJavaType());
		}
		try {
			scheduler.resumeJob(jobKey);
			updateStatus(scheduler, scheduleJob);
			entity.setMemoto("成功!");
			isException = false;
		} catch (Exception e) {
			entity.setMemoto("失败！" + e.getMessage());
			isException = true;
		}
		logService.save(entity);
		if (isException) {
			throw new BusinessException(entity.getMemoto());
		}
	}

	/**
	 * 删除一个job
	 * 
	 * @param scheduleJobid
	 * @throws SchedulerException
	 */
	public static void delete(Integer scheduleJobid) throws SchedulerException {
		Scheduler scheduler = getSchedulerFactory().getScheduler();
		Job scheduleJob = jobService.byId(scheduleJobid);
		JobKey jobKey = JobKey.jobKey(scheduleJob.getName(), scheduleJob.getGroupName());
		scheduler.deleteJob(jobKey);
		updateStatus(scheduler, scheduleJob);
	}

	/**
	 * 删除一个job 数据库删除
	 * 
	 * @param scheduleJobid
	 * @throws SchedulerException
	 */
	public static void deleteDB(Integer scheduleJobid) throws SchedulerException {
		JobLog entity = new JobLog();
		Job scheduleJob = jobService.byId(scheduleJobid);
		{
			entity.toNew();
			entity.setOperation("删除");
			entity.setName(scheduleJob.getName());
			entity.setGroupName(scheduleJob.getGroupName());
			entity.setJavaType(scheduleJob.getJavaType());
			entity.setMemoto("成功!");
		}
		try {
			delete(scheduleJobid);
			scheduleJob.toDeleted();
			jobService.save(scheduleJob);
			isException = false;
		} catch (Exception e) {
			entity.setMemoto("失败!" + e.getMessage());
			isException = true;
		}
		logService.save(entity);
		if (isException) {
			throw new BusinessException(entity.getMemoto());
		}
	}

	/**
	 * 立即执行job
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	public static void runNow(Integer scheduleJobid) throws SchedulerException {
		JobLog entity = new JobLog();
		entity.toNew();
		Job scheduleJob = jobService.byId(scheduleJobid);
		Scheduler scheduler = getSchedulerFactory().getScheduler();
		JobKey jobKey = JobKey.jobKey(scheduleJob.getName(), scheduleJob.getGroupName());
		{
			entity.setOperation("执行");
			entity.setName(scheduleJob.getName());
			entity.setGroupName(scheduleJob.getGroupName());
			entity.setJavaType(scheduleJob.getJavaType());
		}
		try {
			scheduler.triggerJob(jobKey);
			updateStatus(scheduler, scheduleJob);
			entity.setMemoto("成功!");
			isException = false;
		} catch (Exception e) {
			entity.setMemoto("失败!" + e.getMessage());
			isException = true;
		}
		logService.save(entity);
		if (isException) {
			throw new BusinessException(entity.getMemoto());
		}
	}

	/**
	 * 启动任务
	 */
	
	public static void start(Integer jobId) {
		JobLog entity = new JobLog();

		Job job = jobService.byId(jobId);
		{
			entity.toNew();
			entity.setOperation("启动");
			entity.setName(job.getName());
			entity.setGroupName(job.getGroupName());
			entity.setJavaType(job.getJavaType());
		}
		try {
			add(job);
			Scheduler sched = getSchedulerFactory().getScheduler();
			sched.start();
			updateStatus(sched, job);
			entity.setMemoto("启动成功!");
			isException = false;
		} catch (Exception e) {
			entity.setMemoto("启动失败" + e.getMessage());
			isException = true;
		}
		logService.save(entity);
		if (isException) {
			throw new BusinessException(entity.getMemoto());
		}
	}

	/**
	 * 启动所有
	 */
	public static void starts() {
		JobLog entity = new JobLog();
		{
			entity.toNew();
			entity.setOperation("tomcat启动所有任务");
		}
		try {
			init();
			Scheduler sched = getSchedulerFactory().getScheduler();
			sched.start();
			List<Job> jobs = getAllTask();
			for (Job job : jobs) {
				updateStatus(sched, job);
			}
			isException = false;
			entity.setMemoto("启动成功！");
		} catch (Exception e) {
			isException = true;
			entity.setMemoto("启动失败！" + e.getMessage());
		}
		logService.save(entity);
		if (isException) {
			throw new BusinessException(entity.getMemoto());
		}
	}

	/**
	 * 停止所有
	 */
	public static void shutdowns() {
		JobLog entity = new JobLog();
		{
			entity.toNew();
			entity.setOperation("停止所有任务");
		}
		try {
			Scheduler sched = getSchedulerFactory().getScheduler();
			if (!sched.isShutdown()) {
				sched.shutdown();
				List<Job> jobs = getAllTask();
				for (Job job : jobs) {
					job.setStatus(JobStatus.Stop);
					job.toPersist();
					jobService.save(job);
				}
			}
			entity.setMemoto("成功！");
			isException = false;
		} catch (Exception e) {
			entity.setMemoto("停止失败" + e.getMessage());

			isException = true;
		}
		logService.save(entity);
		if (isException) {
			throw new BusinessException(entity.getMemoto());
		}
	}

	private static JobStatus getStatus(String name) {

		if (name.equals(JobStatus.BLOCKED.name())) {
			return JobStatus.BLOCKED;
		}
		if (name.equals(JobStatus.COMPLETE.name())) {
			return JobStatus.COMPLETE;
		}
		if (name.equals(JobStatus.ERROR.name())) {
			return JobStatus.ERROR;
		}
		if (name.equals(JobStatus.NONE.name())) {
			return JobStatus.NONE;
		}
		if (name.equals(JobStatus.NORMAL.name())) {
			return JobStatus.NORMAL;
		}
		if (name.equals(JobStatus.PAUSED.name())) {
			return JobStatus.PAUSED;
		}
		return JobStatus.Stop;
	}

	private static void updateStatus(Scheduler scheduler, Job scheduleJob) throws SchedulerException {
		TriggerState triggerState = scheduler.getTriggerState(TriggerKey.triggerKey(scheduleJob.getName(), scheduleJob.getGroupName()));
		scheduleJob.setStatus(getStatus(triggerState.name()));
		scheduleJob.toPersist();
		jobService.save(scheduleJob);
	}

	public static SchedulerFactory getSchedulerFactory() {
		if(schedulerFactory==null){
			schedulerFactory = new StdSchedulerFactory();
		}
		return schedulerFactory;
	}
}
