package org.netsharp.job.service;

import org.netsharp.communication.Service;
import org.netsharp.job.base.IJobLogService;
import org.netsharp.job.entity.JobLog;
import org.netsharp.service.PersistableService;

@Service
public class JobLogService extends PersistableService<JobLog> implements IJobLogService {

	public JobLogService() {
		super();
		this.type = JobLog.class;
	}

}
