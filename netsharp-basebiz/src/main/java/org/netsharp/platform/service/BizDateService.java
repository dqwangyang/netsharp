package org.netsharp.platform.service;

import org.netsharp.communication.Service;
import org.netsharp.platform.base.IBizDateService;
import org.netsharp.platform.entity.BizDate;
import org.netsharp.service.PersistableService;

@Service
public class BizDateService extends PersistableService<BizDate> implements IBizDateService {

	public BizDateService() {
		super();
		this.type = BizDate.class;
	}
}