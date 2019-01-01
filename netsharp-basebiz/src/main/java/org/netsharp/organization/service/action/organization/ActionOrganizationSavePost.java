package org.netsharp.organization.service.action.organization;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.EntityState;
import org.netsharp.organization.base.IAuthorizationPrincipalService;
import org.netsharp.organization.base.IOrganizationEmployeeService;
import org.netsharp.organization.entity.Organization;

/*组织机构保存后置条件：同步岗位信息*/
public class ActionOrganizationSavePost implements IAction {

	@Override
	public void execute(ActionContext ctx) {

		Organization entity = (Organization) ctx.getItem();
		EntityState state = ctx.getState();

		IAuthorizationPrincipalService principalService = ServiceFactory.create(IAuthorizationPrincipalService.class);
		if (state == EntityState.New) {
			principalService.addByPost(entity);
		} else if (state == EntityState.Persist) {

		} else if (state == EntityState.Deleted) {
			entity.toDeleted();
			
			//删除员工关联关系
			IOrganizationEmployeeService  oeService = ServiceFactory.create(IOrganizationEmployeeService.class);
			oeService.deleteByOrganizationId(entity.getId());
			
			//删除授权主体
			principalService.deleteByPostId(entity.getId());
		} else {

		}
	}
}
