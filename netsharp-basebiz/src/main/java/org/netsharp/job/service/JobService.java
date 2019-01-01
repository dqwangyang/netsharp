package org.netsharp.job.service;

import org.netsharp.communication.Service;
import org.netsharp.core.BusinessException;
import org.netsharp.core.EntityState;
import org.netsharp.job.base.IJobService;
import org.netsharp.job.entity.Job;
import org.netsharp.service.PersistableService;
import org.quartz.CronScheduleBuilder;

@Service
public class JobService extends PersistableService<Job> implements IJobService {

	public JobService() {
		super();
		this.type = Job.class;
	}

	@Override
	public Job save(Job entity) {
		if (entity.getEntityState() != EntityState.Deleted) {

			try {
				@SuppressWarnings("unused")
				CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(entity.getCron());
			} catch (Exception e) {
				throw new BusinessException("时间表达式不正确！");
			}
		}

		super.save(entity);

		return entity;
	}
}
