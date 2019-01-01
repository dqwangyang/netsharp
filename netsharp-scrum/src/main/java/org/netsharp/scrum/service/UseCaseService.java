package org.netsharp.scrum.service;

import org.netsharp.communication.Service;
import org.netsharp.scrum.base.IUseCaseService;
import org.netsharp.scrum.entity.UseCase;
import org.netsharp.service.BizService;

@Service
public class UseCaseService extends BizService<UseCase> implements IUseCaseService {
	public UseCaseService() {
		super();
		this.type = UseCase.class;
	}
}
