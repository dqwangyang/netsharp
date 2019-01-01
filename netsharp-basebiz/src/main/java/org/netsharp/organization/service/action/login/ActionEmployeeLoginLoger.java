package org.netsharp.organization.service.action.login;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.log.base.INLogService;
import org.netsharp.log.entity.NLog;
import org.netsharp.log.entity.NLogType;
import org.netsharp.organization.entity.Employee;
import org.netsharp.organization.entity.OrganizationEmployee;
import org.netsharp.util.StringManager;

public class ActionEmployeeLoginLoger implements IAction {

	@Override
	public void execute(ActionContext ctx) {

		String loginName = (String) ctx.getStatus().get("loginName");
		Employee employee = (Employee) ctx.getItem();

		INLogService logService = ServiceFactory.create(INLogService.class);
		NLog log = new NLog();
		{
			log.toNew();
			log.setOperaitonName("登录");
			log.setMessage("loginName:" + loginName);
			log.setEntityId(Employee.class.getName());
		}

		// 登录失败
		if (employee == null) {
			log.setCode("失败");
			log.setLogType(NLogType.warn);

			logService.save(log);

			return;
		}

		// 登录成功
		log.setCode("成功");
		log.setAssociateId(employee.getId().toString());
		log.setName(employee.getName());
		log.setCode(employee.getCode());
		log.setLogType(NLogType.info);
		log.setCreatorId(employee.getId());
		log.setCreator(employee.getName());
		log.setAssociateId(employee.getId().toString());
		log.setEntityId(Employee.class.getName());
		log.setMemoto(this.getMemoto(employee));
		logService.save(log);
	}

	private String getMemoto(Employee employee) {

		if (employee.getOrganizations() == null) {
			return "无岗位";
		}

		List<String> ss = new ArrayList<String>();
		for (OrganizationEmployee oe : employee.getOrganizations()) {
			if (oe != null && oe.getOrganization() != null) {
				ss.add(oe.getOrganization().getPathName());
			}
		}

		return StringManager.join(",", ss);
	}

//	private void logback(Employee employee) {
//		// 更新最后登录时间和登录次数
//		String ticket = EncrypUtil.md5(employee.getId() + employee.getLoginName() + employee.getPwd());
//		String cmdText = "UPDATE sys_permission_employee SET login_num=login_num+1,last_login_time=NOW(),ticket=? WHERE id=?";
//		QueryParameters qps = new QueryParameters();
//		{
//			qps.add("@ticket", ticket, Types.VARCHAR);
//			qps.add("@id", employee.getId(), Types.INTEGER);
//		}
//		IPersister<Employee> pm = PersisterFactory.create();
//		pm.executeNonQuery(cmdText, qps);
//
//		int loginNum = employee.getLoginNum() + 1;
//		employee.setLoginNum(loginNum);
//		employee.setTicket(ticket);
//	}

}
