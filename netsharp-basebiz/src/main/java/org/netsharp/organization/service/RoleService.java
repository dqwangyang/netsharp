package org.netsharp.organization.service;

import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.EntityState;
import org.netsharp.organization.base.IAuthorizationPrincipalService;
import org.netsharp.organization.base.IRoleService;
import org.netsharp.organization.dic.PrincipalType;
import org.netsharp.organization.entity.AuthorizationPrincipal;
import org.netsharp.organization.entity.Role;
import org.netsharp.service.PersistableService;
import org.netsharp.util.sqlbuilder.DeleteBuilder;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

@Service
public class RoleService extends PersistableService<Role> implements IRoleService {

	public RoleService() {
		super();
		this.type = Role.class;
	}

	public Role save(Role entity) {

		EntityState state = entity.getEntityState();
		entity = super.save(entity);
		if (state == EntityState.New) {

			addAuthorizationPrincipal(entity);
		} else if (state == EntityState.Persist) {

			updateAuthorizationPrincipal(entity);
		} else if (state == EntityState.Deleted) {

			// 校验是否已经有员工配置的角色
			
			deleteAuthorizationPrincipal(entity);
		}
		return entity;
	}

	/**
	 * @Title: addAuthorizationPrincipal
	 * @Description: TODO(创建授权主体)
	 * @param: @param role
	 * @return: void
	 * @throws
	 */
	private void addAuthorizationPrincipal(Role role) {

		IAuthorizationPrincipalService principalService = ServiceFactory.create(IAuthorizationPrincipalService.class);
		AuthorizationPrincipal principal = new AuthorizationPrincipal();
		{
			principal.toNew();
			principal.setPrincipalId(role.getId());
			principal.setPrincipalType(PrincipalType.Role);
			principal.setPrincipalName(role.getName());
		}

		principalService.save(principal);
	}

	/**
	 * @Title: updateAuthorizationPrincipal
	 * @Description: TODO(更新授权主体名称)
	 * @param: @param role
	 * @return: void
	 * @throws
	 */
	private void updateAuthorizationPrincipal(Role role) {

		UpdateBuilder updateSql = UpdateBuilder.getInstance();
		{
			updateSql.update("sys_permission_authorization_principal");
			updateSql.set("principal_name", role.getName());
			updateSql.where("principal_type=" + PrincipalType.Role.getValue(), "principal_id=" + role.getId());
		}
		String cmdText = updateSql.toSQL();
		this.pm.executeNonQuery(cmdText, null);
	}

	/**
	 * @Title: updateAuthorizationPrincipal
	 * @Description: TODO(更新授权主体名称)
	 * @param: @param role
	 * @return: void
	 * @throws
	 */
	private void deleteAuthorizationPrincipal(Role role) {

		DeleteBuilder deleteSql = DeleteBuilder.getInstance();
		{
			deleteSql.deleteFrom("sys_permission_authorization_principal");
			deleteSql.where("principal_type=" + PrincipalType.Role.getValue(), "principal_id=" + role.getId());
		}
		String cmdText = deleteSql.toSQL();
		this.pm.executeNonQuery(cmdText, null);
	}
}
