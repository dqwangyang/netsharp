package org.netsharp.wx.ea.organization;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.DataTable;
import org.netsharp.core.Oql;
import org.netsharp.core.Row;
import org.netsharp.organization.base.IEmployeeService;
import org.netsharp.organization.base.IOrganizationService;
import org.netsharp.organization.entity.Employee;
import org.netsharp.wx.ea.base.IWxeaAppService;
import org.netsharp.wx.ea.entity.WxeaApp;
import org.netsharp.wx.sdk.ep.accesstoken.AccessToken;
import org.netsharp.wx.sdk.ep.accesstoken.AccessTokenManage;
import org.netsharp.wx.sdk.ep.user.User;
import org.netsharp.wx.sdk.ep.user.UserCreateRequest;
import org.netsharp.wx.sdk.ep.user.UserGetRequest;
import org.netsharp.wx.sdk.ep.user.UserGetResponse;
import org.netsharp.wx.sdk.ep.user.UserUpdateRequest;

/**
 * 同步员工到微信企业号
 */
public class EmployeeHelper {

	private static IOrganizationService service = ServiceFactory.create(IOrganizationService.class);
	private static WxeaApp wxpa = null;
	private static Log logger = LogFactory.getLog(EmployeeHelper.class);
//	private static boolean is_product = Environment.Product.equals(Application.getContext().getEnvironment());

	/**
	 * 同步所有人员
	 */
	public static void run() {

		initWxpaConfiguration();
//		List<Integer> qiyeIds = getOrg();
//		for (Integer id : qiyeIds) {
//			create(id);
//		}
		create(1);
	}

	/**
	 * 更新指定人员的部门,先取出旧的部门ID，然后和新的部门ID合并，新的部门ID必须在微信企业号中存在,
	 * 如果部门ID值为VISI中的organization.getId(),则需要*10
	 * 
	 * @param depIds
	 * @param empId
	 */
	public static void updateUserDepartments(String[] depIds, String empId) {
//		if (!is_product) {
//			return;
//		}
		initWxpaConfiguration();
		try {
			AccessToken token = AccessTokenManage.get(wxpa.getCorpid(), wxpa.getCorpsecret());
			UserGetRequest userGetRequest = new UserGetRequest();
			userGetRequest.setUserid(empId);
			userGetRequest.setToken(token);
			UserGetResponse response = userGetRequest.getResponse();
			if (response != null) {
				String[] oldDeps = response.getDepartment();
				String[] newDeps = (String[]) ArrayUtils.addAll(oldDeps, depIds);

				UserUpdateRequest userUpdateRequest = new UserUpdateRequest();
				{
					User user = new User();
					{
						user.setUserid(response.getUserid().toString());
						user.setName(response.getName());
						user.setEmail(response.getEmail());
						user.setExtattr(response.getExtattr());
						user.setGender(response.getGender());
						user.setMobile(response.getMobile());
						user.setPosition(response.getPosition());
						user.setWeixinid(response.getWeixinid());
						user.setDepartment(newDeps);
					}

					userUpdateRequest.setUser(user);
					userUpdateRequest.setToken(token);
					userUpdateRequest.getResponse();
				}

			}
		} catch (Exception e) {
			logger.warn(e.getMessage());
		}
	}

	/**
	 * 删除所有人员
	 */
	public static void runDelete() {

		initWxpaConfiguration();
		List<Integer> orgids = getOrg();
		for (Integer id : orgids) {
			delelte(id);
		}

	}

	/**
	 * 判断员工是否有某个应用的权限
	 * 
	 * @param empId
	 * @param corpId
	 * @param corpsecret
	 * @return
	 */
	public static boolean hasEmployee(String empId, String corpId, String corpsecret) {
		initWxpaConfiguration();
		try {
			AccessToken token = AccessTokenManage.get(corpId, corpsecret);
			UserGetRequest userGetRequest = new UserGetRequest();
			userGetRequest.setUserid(empId);
			userGetRequest.setToken(token);
			UserGetResponse response = userGetRequest.getResponse();
			if (response != null) {

				return true;

			}
		} catch (Exception e) {
			logger.warn(e.getMessage());
			return false;

		}
		return false;

	}

	private static void create(Integer qiyeIds) {
		List<Employee> list = getEmployees(qiyeIds);
//		qiyeIds = qiyeIds * 10;
		
		for (Employee emp : list) {
//			Employee emp = organizationEmployee.getEmployee();

			if (emp != null && !emp.getDisabled()) {
				String[] strs = new String[1];
				strs[0] = qiyeIds.toString();
				User user = new User();
				{
					user.setDepartment(strs);
					user.setEmail(emp.getEmail());
					// user.setGender(emp.getGender().getText());
					user.setName(emp.getName());
					user.setMobile(emp.getMobile());
					if (emp.getPost() != null) {
						user.setPosition(emp.getPost().getPathName());
					}
					user.setUserid(emp.getMobile());
				}

				UserCreateRequest userCreateRequest = new UserCreateRequest();
				{
					AccessToken token = AccessTokenManage.get(wxpa.getCorpid(), wxpa.getCorpsecret());
					userCreateRequest.setToken(token);
					userCreateRequest.setUser(user);
				}
				try {
					if (!hasEmployee(emp.getMobile(), wxpa.getCorpid(), wxpa.getCorpsecret())) {
						userCreateRequest.getResponse();
					}

				} catch (Exception e) {
					logger.warn(e.getMessage());
				}

			}
		}
	}

	private static void initWxpaConfiguration() {
		if (wxpa == null) {
			IWxeaAppService service = ServiceFactory.create(IWxeaAppService.class);
			wxpa = service.byCode("WeChat");
		}
	}

	private static void delelte(Integer orgId) {
//		List<OrganizationEmployee> list = getEmployees(orgId);
//		for (OrganizationEmployee organizationEmployee : list) {
//			Employee emp = organizationEmployee.getEmployee();
//
//			if (emp != null) {
//				UserDeleteRequest userDeleteRequest = new UserDeleteRequest();
//				{
//					AccessToken token = AccessTokenManage.get(wxpa.getCorpid(), wxpa.getCorpsecret());
//					userDeleteRequest.setToken(token);
//					userDeleteRequest.setUserid(emp.getMobile());
//				}
//				try {
//					userDeleteRequest.getResponse();
//				} catch (Exception e) {
//					logger.warn(e.getMessage());
//				}
//
//			}
//
//		}
	}
	
	private static List<Employee> getEmployees(Integer qyWeiXinId) {
		Oql oql = new Oql();
		{
			oql.setSelects("*");
			oql.setType(Employee.class);
			oql.setFilter("employee.disabled=0");
		}
		IEmployeeService service = ServiceFactory.create(IEmployeeService.class);
		List<Employee> list = service.queryList(oql);
		return list;
	}

//	private static List<OrganizationEmployee> getEmployees(Integer qyWeiXinId) {
//		Oql oql = new Oql();
//		{
//			oql.setSelects("ID,employee.*,organization.*");
//			oql.setType(OrganizationEmployee.class);
////			oql.setFilter("employee.disabled=0 and organization.qyWeiXinId=" + qyWeiXinId);
//			oql.setFilter("employee.disabled=0");
//		}
//		IOrganizationEmployeeService service = ServiceFactory.create(IOrganizationEmployeeService.class);
//		List<OrganizationEmployee> list = service.queryList(oql);
//		return list;
//	}

	private static List<Integer> getOrg() {
		String sql = "SELECT qy_weixin_id  FROM sys_permission_organization WHERE disabled=0 and id NOT IN ( SELECT  DISTINCT parent_Id FROM sys_permission_organization WHERE parent_Id IS NOT NULL)";
		List<Integer> list = new ArrayList<Integer>();
		DataTable table = service.executeTable(sql, null);
		for (Row row : table) {
			list.add(Integer.valueOf(row.get("qy_weixin_id").toString()));
		}
		return list;
	}

}
