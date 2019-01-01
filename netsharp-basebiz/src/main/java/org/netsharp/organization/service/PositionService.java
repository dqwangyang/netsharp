package org.netsharp.organization.service;

import java.sql.Types;
import java.util.List;

import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.BusinessException;
import org.netsharp.core.EntityState;
import org.netsharp.core.Oql;
import org.netsharp.core.QueryParameters;
import org.netsharp.organization.base.IAuthorizationPrincipalService;
import org.netsharp.organization.base.IPositionService;
import org.netsharp.organization.entity.Position;
import org.netsharp.service.PersistableService;

@Service
public class PositionService extends PersistableService<Position> implements IPositionService {

	public PositionService() {
		super();
		this.type = Position.class;
	}

	@Override
	public Position save(Position entity) {
		EntityState state = entity.getEntityState();
		super.save(entity);
		if (state == EntityState.New) {
			
			IAuthorizationPrincipalService principalService = ServiceFactory.create(IAuthorizationPrincipalService.class);
			principalService.addByPosition(entity);
		} else if (state == EntityState.Deleted) {
			
			IAuthorizationPrincipalService principalService = ServiceFactory.create(IAuthorizationPrincipalService.class);
			principalService.deleteByPosition(entity);
		}
		return entity;
	}

	@Override
	public Position byCode(String code) {
		
		Oql oql = new Oql();
		{
			oql.setSelects("*");
			oql.setType(this.type);
			oql.setFilter("code=?");
			oql.setParameters(new QueryParameters());
			oql.getParameters().add("@code", code, Types.VARCHAR);
		}
		List<Position> list = this.pm.queryList(oql);
		if (list.size() > 1) {
			throw new BusinessException("编码重复!" + code);
		}
		if (list.size() == 1) {
			return list.get(0);
		}
		return null;
	}
}
