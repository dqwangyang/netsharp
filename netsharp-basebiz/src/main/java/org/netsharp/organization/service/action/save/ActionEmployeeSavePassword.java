package org.netsharp.organization.service.action.save;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.core.EntityState;
import org.netsharp.organization.entity.AuthorizationConfiguration;
import org.netsharp.organization.entity.Employee;
import org.netsharp.util.EncrypUtil;
import org.netsharp.util.StringManager;

/*员工构持久化
 *密码处理*/
public class ActionEmployeeSavePassword implements IAction {

	@Override
	public void execute(ActionContext ctx) {

		Employee entity = (Employee) ctx.getItem();
		if (ctx.getState() == EntityState.New) {

			if (StringManager.isNullOrEmpty(entity.getPwd())) {

				String pwd = EncrypUtil.md5(AuthorizationConfiguration.getInstance().getDefaultPassword() + "user!@#123").substring(8,24);
				entity.setPwd(pwd);
			} else if (entity.getPwd().length() < 8) {

				entity.setPwd(EncrypUtil.md5(entity.getPwd()));
			}
		}
	}
}
