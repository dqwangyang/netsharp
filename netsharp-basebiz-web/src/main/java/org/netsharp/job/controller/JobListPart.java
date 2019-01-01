package org.netsharp.job.controller;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.BusinessException;
import org.netsharp.job.base.IJobService;
import org.netsharp.job.core.JobManager;
import org.netsharp.job.entity.Job;
import org.netsharp.panda.commerce.ListPart;
import org.quartz.SchedulerException;

public class JobListPart extends ListPart {

	public void init() throws Exception {
		throw new BusinessException("请启动独立的作业服务器，tomcat容器不支持运行作业");
//		// 这里获取任务信息数据
//		List<Job> jobList = JobHelper.getAllTask();
//		for (Job job : jobList) {
//			JobHelper.add(job);
//		}
	}

	/**
	 * 暂停一个job
	 * 
	 * @param scheduleJobid
	 * @throws SchedulerException
	 */
	public boolean pause(Integer scheduleJobid) throws SchedulerException {
		throw new BusinessException("请启动独立的作业服务器，tomcat容器不支持运行作业");
//		try {
//			JobHelper.pause(scheduleJobid);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
//		return true;
	}

	/**
	 * 恢复一个job
	 * 
	 * @param scheduleJobid
	 * @throws SchedulerException
	 */
	public boolean resume(Integer scheduleJobid) throws SchedulerException {
		throw new BusinessException("请启动独立的作业服务器，tomcat容器不支持运行作业");
//		try {
//			JobHelper.resume(scheduleJobid);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
//
//		return true;
	}

	/**
	 * 删除一个job
	 * 
	 * @param scheduleJobid
	 * @throws SchedulerException
	 */
	public boolean delete(Integer scheduleJobid) throws SchedulerException {
		
		throw new BusinessException("请启动独立的作业服务器，tomcat容器不支持运行作业");

//		try {
//			JobHelper.delete(scheduleJobid);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
//
//		return true;
	}

	/**
	 * 删除一个job 数据库删除
	 * 
	 * @param scheduleJobid
	 * @throws SchedulerException
	 */
	public boolean deleteDB(Integer scheduleJobid) throws SchedulerException {
		throw new BusinessException("请启动独立的作业服务器，tomcat容器不支持运行作业");
//		try {
//			JobHelper.deleteDB(scheduleJobid);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
//
//		return true;
	}

	/**
	 * 立即执行job
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	public boolean runNow(Integer jobId) throws SchedulerException {
		
		IJobService jobService = ServiceFactory.create(IJobService.class);
		Job job = jobService.byId(jobId);
		
		JobManager jm = new JobManager();
		jm.run(job);

		return true;
	}

	/**
	 * 启动任务
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	public boolean start(Integer jobId) throws SchedulerException {
		throw new BusinessException("请启动独立的作业服务器，tomcat容器不支持运行作业");
//		try {
//			JobHelper.start(jobId);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
//
//		return true;
	}

	public void starts() {

//		JobHelper.starts();
		throw new BusinessException("请启动独立的作业服务器，tomcat容器不支持运行作业");
	}

	public void shutdowns() {
//		JobHelper.shutdowns();
		throw new BusinessException("请启动独立的作业服务器，tomcat容器不支持运行作业");
	}

}
