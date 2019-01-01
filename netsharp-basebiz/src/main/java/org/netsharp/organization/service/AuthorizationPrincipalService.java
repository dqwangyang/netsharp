package org.netsharp.organization.service;

import java.sql.Types;

import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.organization.base.IAuthorizationPrincipalService;
import org.netsharp.organization.dic.OrganizationType;
import org.netsharp.organization.dic.PrincipalType;
import org.netsharp.organization.entity.AuthorizationPrincipal;
import org.netsharp.organization.entity.Organization;
import org.netsharp.organization.entity.Position;
import org.netsharp.service.PersistableService;
import org.netsharp.util.StringManager;

@Service
public class AuthorizationPrincipalService extends PersistableService<AuthorizationPrincipal> implements IAuthorizationPrincipalService {

	public AuthorizationPrincipalService() {
		super();
		this.type = AuthorizationPrincipal.class;
	}

	public void addByPost(Organization organization) {

		AuthorizationPrincipal principal = new AuthorizationPrincipal();
		{
			principal.toNew();

			principal.setPrincipalId(organization.getId());
			if(organization.getOrganizationType()==OrganizationType.POST){
				principal.setPrincipalType(PrincipalType.POST);
			}
			else{
				principal.setPrincipalType(PrincipalType.ORGANIZATION);
			}
			
			if( StringManager.isNullOrEmpty(organization.getPathName())){
				principal.setPrincipalName(organization.getName());
			}else{
				principal.setPrincipalName(organization.getPathName());
			}
		}

		super.save(principal);
	}

	public void deleteByPostId(Integer organizationId) {

		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("*");
			oql.setFilter("principalId = ? and principalType = ?");
			oql.getParameters().add("@principalId", organizationId, Types.INTEGER);
			oql.getParameters().add("@principalType", PrincipalType.POST.getValue(), Types.TINYINT);
		}

		AuthorizationPrincipal principal = this.pm.queryFirst(oql);
		if (principal != null) {
			principal.toDeleted();
			super.save(principal);
		}
	}
	
	@Override
	public void addByPosition(Position position) {
		AuthorizationPrincipal principal = new AuthorizationPrincipal();
		{
			principal.toNew();
			principal.setPrincipalId(position.getId());
			principal.setPrincipalType(PrincipalType.STATION);
			principal.setPrincipalName(position.getName());
		}

		super.save(principal);
	}

	@Override
	public void deleteByPosition(Position position) {
		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("*");
			oql.setFilter("principalId = ? and principalType = ?");

			oql.getParameters().add("@principalId", position.getId(), Types.INTEGER);
			oql.getParameters().add("@principalType", PrincipalType.STATION.getValue(), Types.TINYINT);
		}

		AuthorizationPrincipal principal = this.pm.queryFirst(oql);

		if (principal != null) {
			principal.toDeleted();
			super.save(principal);
		}
	}
	
}