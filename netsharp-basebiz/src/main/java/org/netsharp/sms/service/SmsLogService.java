package org.netsharp.sms.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;
import org.netsharp.sms.base.ISmsLogService;
import org.netsharp.sms.mandao.entity.SmsLog;

@Service
public class SmsLogService extends PersistableService<SmsLog> implements ISmsLogService {
	public SmsLogService(){
		super();
		this.type=SmsLog.class;
	}
}
