package org.netsharp.organization.service.action.save;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;

public class ActionEmployeeSaveWeixin implements IAction {

	@Override
	public void execute(ActionContext ctx) {

//		Employee employee = (Employee) ctx.getItem();
//		Log logger = LogFactory.getLog(EmployeeService.class);
//		logger.info("同步员工到微信企业号!" + employee.getName());
//		if (!Environment.Product.equals(Application.getContext().getEnvironment())) {
//			return;
//		}
//
//		try {
//			if (employee.getOrganizations() != null) {
//				List<String> ids = new ArrayList<String>();
//				for (OrganizationEmployee oe : employee.getOrganizations()) {
//					if (oe != null && oe.getOrganization() != null) {
//						Integer id = oe.getOrganization().getQyWeiXinId() * 10;
//						ids.add(id.toString());
//					}
//				}
//
//				User user = new User();
//				{
//					String[] strings = new String[ids.size()];
//					ids.toArray(strings);
//					user.setDepartment(strings);
//					user.setEmail(employee.getEmail());
//					// user.setGender(emp.getGender().getText());
//					user.setName(employee.getName());
//					user.setMobile(employee.getMobile());
//					user.setPosition(employee.getPost().getPathName());
//					user.setUserid(employee.getId().toString());
//				}
//
//				UserCreateRequest userCreateRequest = new UserCreateRequest();
//				{
//					IWxeaAppService service = ServiceFactory.create(IWxeaAppService.class);
//					WxeaApp wxpa = service.byCode("WeChat");
//					AccessToken token = AccessTokenManage.get(wxpa.getCorpid(), wxpa.getCorpsecret());
//					userCreateRequest.setToken(token);
//					userCreateRequest.setUser(user);
//
//				}
//				userCreateRequest.getResponse();
//			}
//			logger.info("同步员工到微信企业号成功!" + employee.getName());
//		} catch (Exception e) {
//
//			logger.info("同步员工到微信企业号失败!" + e.getMessage());
//		}
	}

}
