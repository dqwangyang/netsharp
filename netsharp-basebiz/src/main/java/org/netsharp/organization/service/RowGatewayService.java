package org.netsharp.organization.service;

import org.netsharp.communication.Service;
import org.netsharp.organization.base.IRowGatewayService;
import org.netsharp.organization.entity.RowGateway;
import org.netsharp.service.PersistableService;

@Service
public class RowGatewayService extends PersistableService<RowGateway> implements IRowGatewayService {
	public RowGatewayService(){
		super();
		this.type=RowGateway.class;
	}
}
