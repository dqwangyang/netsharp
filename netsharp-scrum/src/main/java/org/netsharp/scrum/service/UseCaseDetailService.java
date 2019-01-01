package org.netsharp.scrum.service;

import org.netsharp.communication.Service;
import org.netsharp.scrum.base.IUseCaseDetailService;
import org.netsharp.scrum.entity.UseCaseDetail;
import org.netsharp.service.PersistableService;

@Service
public class UseCaseDetailService extends PersistableService<UseCaseDetail> implements IUseCaseDetailService {
	
	public UseCaseDetailService() {
		super();
		this.type = UseCaseDetail.class;
	}
	
}