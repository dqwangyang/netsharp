package org.netsharp.organization.controller;

import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.organization.base.IEmployeeService;
import org.netsharp.organization.base.IOrganizationService;
import org.netsharp.organization.base.IRoleEmployeeService;
import org.netsharp.organization.controller.dto.LoginResult;
import org.netsharp.organization.controller.dto.Workbench;
import org.netsharp.organization.entity.AuthorizationConfiguration;
import org.netsharp.organization.entity.Employee;
import org.netsharp.organization.entity.RoleEmployee;
import org.netsharp.organization.entity.RoleWorkbench;
import org.netsharp.panda.annotation.Authorization;
import org.netsharp.panda.core.HttpContext;
import org.netsharp.panda.core.comunication.IResponse;
import org.netsharp.panda.core.comunication.session.RedisSession;
import org.netsharp.panda.session.PandaSessionPersister;
import org.netsharp.util.EncrypUtil;
import org.netsharp.util.StringManager;
import org.netsharp.wx.ea.base.IWxeaAppService;
import org.netsharp.wx.ea.entity.WxeaApp;
import org.netsharp.wx.sdk.ep.accesstoken.AccessToken;
import org.netsharp.wx.sdk.ep.accesstoken.AccessTokenManage;
import org.netsharp.wx.sdk.ep.user.UserLoginRequest;
import org.netsharp.wx.sdk.ep.user.UserLoginResponse;

public class LoginController {

	IOrganizationService organizationService = ServiceFactory.create(IOrganizationService.class);
	IEmployeeService employeeService = ServiceFactory.create(IEmployeeService.class);

	// 登录
	@Authorization(is = false)
	public LoginResult login(String userName, String password) {

		LoginResult result = new LoginResult();

		if (StringManager.isNullOrEmpty(userName) || StringManager.isNullOrEmpty(password)) {
			return result;
		}

		Employee employee = employeeService.login(userName, password);
		if (employee == null) {
			return result;
		}
		
		if (employee.getDisabled()) {
			result.setResult(LoginResult.Types.disabled);
			return result;
		}
		
		IResponse response = HttpContext.getCurrent().getResponse();
		String cookie = UUID.randomUUID().toString();
		response.addCookie(RedisSession.SESSION_TICKET_KEY, cookie);//服务端无效，不知道为什么
		employee.setTicket(cookie);
		
		String defpwd = EncrypUtil.md5(AuthorizationConfiguration.getInstance() .getDefaultPassword());
		if (employee.getPwd().equals(defpwd)) {
			
			PandaSessionPersister.add(employee);
			result.setResult(LoginResult.Types.ok);
			result.setData(cookie);
		} else {
			PandaSessionPersister.add(employee);
			result.setResult(LoginResult.Types.ok);
			result.setData(cookie);
		}

		List<Workbench> workbenches = this.getWorkbenches(employee.getId());
		
		result.setWorkbenchList(workbenches);
		
		return result;
	}
	
	// 微信扫码登录
	@Authorization(is = false)
	public int wxLogin(String authCode) {

		if (StringManager.isNullOrEmpty(authCode)) {
			return 0;
		}
		String ps = "{\"auth_code\":\"" + authCode + "\"}";
		IWxeaAppService service = ServiceFactory.create(IWxeaAppService.class);
		WxeaApp wxpa = service.byCode("WeChat");

		UserLoginRequest loginRequest = new UserLoginRequest();
		{
			AccessToken token = AccessTokenManage.get(wxpa.getCorpid(), wxpa.getCorpsecret());
			loginRequest.setToken(token);
			loginRequest.setJson(ps);
		}
		try {
			UserLoginResponse response = loginRequest.getResponse();

			if (response.getUser_info() != null && response.getUser_info().getUserid() != null) {

				IEmployeeService employeeService = ServiceFactory.create(IEmployeeService.class);
				Employee emp = employeeService.byId(response.getUser_info().getUserid());
				if (emp != null && !emp.getDisabled()) {
					PandaSessionPersister.add(emp);
					return 1;
				}
				return 0;
			}
			return 0;
		} catch (Exception e) {
			return 0;
		}
	}

	@Authorization(is = false)
	public int logout() {
		PandaSessionPersister.remove();
		return 1;
	}

	/**
	 * @Title: getWorkbenchPath
	 * @Description: TODO(获取用户工作台路径,取第一个角色上的)
	 * @param: @param employeeId
	 * @param: @return
	 * @return: String
	 * @throws
	 */
	public List<Workbench> getWorkbenches(Integer employeeId) {

		IRoleEmployeeService srvice = ServiceFactory.create(IRoleEmployeeService.class);
		Oql oql = new Oql();
		{
			oql.setType(RoleEmployee.class);
			oql.setSelects("RoleEmployee.*,RoleEmployee.role.*,RoleEmployee.role.workbench.{id,name,path}");
			oql.setFilter(" employeeId=?");
			oql.getParameters().add("employeeId", employeeId, Types.INTEGER);
		}
		List<RoleEmployee> reList = srvice.queryList(oql);

		List<Workbench> workbenchDTOList = new ArrayList<Workbench>();
		Map<String, String> maps = new HashMap<String, String>();
		for (RoleEmployee re : reList) {

			if (re.getRole() != null && re.getRole().getWorkbench() != null) {

				RoleWorkbench rw = re.getRole().getWorkbench();
				String name = maps.get(rw.getPath());

				if (StringManager.isNullOrEmpty(name)) {

					maps.put(rw.getPath(), rw.getName());
				}
			}
		}

		if (maps.size() == 0) {

			Workbench dto = new Workbench();
			dto.setPath("/panda/workbench");
			workbenchDTOList.add(dto);
		} else {

			for (String key : maps.keySet()) {

				Workbench dto = new Workbench();
				dto.setPath(key);
				dto.setName(maps.get(key));
				workbenchDTOList.add(dto);
			}
		}
		return workbenchDTOList;
	}
}
