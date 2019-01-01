package org.netsharp.scrum.service;

import org.netsharp.communication.Service;
import org.netsharp.scrum.base.IBugLogService;
import org.netsharp.scrum.entity.BugLog;
import org.netsharp.service.PersistableService;

@Service
public class BugLogService extends PersistableService<BugLog> implements IBugLogService {

	public BugLogService() {
		super();

		this.type = BugLog.class;
	}
}