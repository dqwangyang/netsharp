package org.netsharp.organization.service;

import java.sql.Types;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.netsharp.action.ActionContext;
import org.netsharp.action.ActionManager;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.core.Oql;
import org.netsharp.core.QueryParameters;
import org.netsharp.organization.base.IEmployeeService;
import org.netsharp.organization.base.IOrganizationService;
import org.netsharp.organization.entity.Employee;
import org.netsharp.organization.entity.Organization;
import org.netsharp.organization.entity.OrganizationEmployee;
import org.netsharp.organization.service.action.login.PasswordHelper;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.service.PersistableService;
import org.netsharp.util.DateManage;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

@Service
public class EmployeeService extends PersistableService<Employee> implements IEmployeeService {

	public IEmployeeService employeeService = ServiceFactory.create(IEmployeeService.class);
	IOrganizationService orgService = ServiceFactory.create(IOrganizationService.class);

	public EmployeeService() {
		super();
		this.type = Employee.class;
	}

	@Override
	public Employee byId(Object id) {

		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("employee.*,employee.organizations.*,employee.organizations.organization.*,employee.roles.*,employee.roles.role.*");
			oql.setFilter(" id=? ");
			oql.getParameters().add("id", id, Types.INTEGER);
		}
		Employee employee = this.pm.queryFirst(oql);
		return employee;
	}
	
	@Override
	public Employee save(Employee entity) {

		ActionContext ctx = new ActionContext();
		{
			ctx.setPath("org/netsharp/basebiz/employee/save");
			ctx.setItem(entity);
			ctx.setState(entity.getEntityState());
		}

		ActionManager action = new ActionManager();
		action.execute(ctx);

		entity = (Employee) ctx.getItem();

		return entity;
	}

	// 登录
	public Employee login(String loginName, String pwd) {

		ActionContext ctx = new ActionContext();
		{
			ctx.setPath("org/netsharp/basebiz/employee/login");
			ctx.getStatus().put("loginName", loginName);
			ctx.getStatus().put("pwd", pwd);
		}

		ActionManager action = new ActionManager();
		action.execute(ctx);

		Employee employee = (Employee) ctx.getItem();

		return employee;

	}

	// 注册申请时判断是否存在手机号
	public int isMobilePhone(String mobilePhone) {

		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("id");
			oql.setFilter("mobile=?");
			oql.getParameters().add("mobile", mobilePhone, Types.VARCHAR);
		}
		IPersister<Employee> pst = PersisterFactory.create();
		List<Employee> list = pst.queryList(oql);
		if (list != null && list.size() > 0)
			return 1;
		else
			return 0;
	}

	// 注册申请时判断是否存在邮箱号
	public int isEamil(String email) {

		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("id");
			oql.setFilter("email=?");
			oql.getParameters().add("email", email, Types.VARCHAR);
		}
		IPersister<Employee> pst = PersisterFactory.create();
		List<Employee> list = pst.queryList(oql);
		if (list != null && list.size() > 0)
			return 1;
		else
			return 0;
	}

	// 注册申请时判断是否存在登录名
	public int isLoginName(String loginName) {

		Oql oql = new Oql();
		{
			oql.setType(Employee.class);
			oql.setSelects("id");
			oql.setFilter("loginName=?");
			oql.getParameters().add("loginName", loginName, Types.VARCHAR);
		}
		List<Employee> list = pm.queryList(oql);
		if (list != null && list.size() > 0)
			return 1;
		else
			return 0;
	}

	@Override
	public Employee loginByPhone(String phoneNo, String pwd) {
		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("*");
			oql.setFilter(" mobile=? and pwd=? ");
			oql.getParameters().add("mobile", phoneNo, Types.VARCHAR);
			oql.getParameters().add("pwd", pwd, Types.VARCHAR);
		}

		Employee employee = this.pm.queryFirst(oql);

		return employee;
	}

	@Override
	public Employee byPhone(String phoneNo) {
		
		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("*");
			oql.setFilter(" mobile=? ");
			oql.getParameters().add("mobile", phoneNo, Types.VARCHAR);
		}
		Employee employee = this.pm.queryFirst(oql);
		return employee;
	}

	/**
	 * @Definition: 根据组织机构ID，查询此ID下所有员工（包括此ID的组织机构）
	 * @author: TangWenWu
	 * @Created date: 2015-4-27
	 * @param organizationId
	 *            组织机构ID
	 * @return
	 */
	@Override
	public List<Employee> findByOrganizationId(Integer organizationId) {

		Organization org = orgService.byId(organizationId);
		if (null != org && StringUtils.isNotEmpty(org.getPathCode())) {
			
			String pathCode = org.getPathCode();
			Mtable mtOrgEmp = MtableManager.getMtable(OrganizationEmployee.class);
			Mtable mtOrg = MtableManager.getMtable(Organization.class);
			Oql oql = new Oql();
			{
				StringBuilder filter = new StringBuilder();
				filter.append(" Employee.id in ( select employeeId from ");
				filter.append(mtOrgEmp.getTableName());
				filter.append(" where organizationId in (");
				filter.append(" select id from ");
				filter.append(mtOrg.getTableName());
				filter.append(" where path_code LIKE ?");
				filter.append("))");
				oql.setType(this.type);
				oql.setSelects("id,name,disabled");
				oql.setFilter(filter.toString());
				oql.getParameters().add("@pathCode", pathCode + "%", Types.VARCHAR);
			}
			List<Employee> employees = this.pm.queryList(oql);

			return employees;
		}
		return null;
	}

	/**
	 * @Definition: 根据组织机构ID和职责类型，查询此ID下所有员工（包括此ID下所有的组织机构）
	 * @author: TangWenWu
	 * @Created date: 2015-4-27
	 * @param organizationId
	 *            组织机构ID
	 * @param position
	 *            职责类型ID 6店长 7机修工 8美容工 9司机
	 * @return
	 */
	@Override
	public List<Employee> findByOrgIdAndPosition(Integer organizationId, Integer position) {
		
		Organization org = orgService.byId(organizationId);
		if (null != org && StringUtils.isNotEmpty(org.getPathCode())) {
			String pathCode = org.getPathCode();
			Mtable mtOrgEmp = MtableManager.getMtable(OrganizationEmployee.class);
			Mtable mtOrg = MtableManager.getMtable(Organization.class);
			// select emp.* from sys_Permission_Employee emp where emp.id
			// in(select employeeId from sys_Permission_OrganizationEmployee
			// where
			// organizationId in( select id from sys_permission_organization
			// where positionId=6 and pathCode LIKE '106020%' ));
			Oql oql = new Oql();
			{
				StringBuilder filter = new StringBuilder();
				filter.append(" Employee.id in ( select employeeId from ");
				filter.append(mtOrgEmp.getTableName());
				filter.append(" where disabled !=1 and organizationId in (");
				filter.append(" select id from ");
				filter.append(mtOrg.getTableName());
				filter.append(" where positionId=? and path_code LIKE ?");
				filter.append("))");
				oql.setType(this.type);
				oql.setSelects("id,name,mobile,disabled");
				oql.setFilter(filter.toString());
				oql.getParameters().add("@positionId", position, Types.INTEGER);
				oql.getParameters().add("@pathCode", pathCode + "%", Types.VARCHAR);
			}
			List<Employee> employees = this.pm.queryList(oql);

			return employees;
		}
		return null;
	}

	/**
	 * 重置密码
	 */
	public boolean resetPassword(Integer id) {
		
		Employee employee = this.byId(id);
		if (employee != null) {
			
			String cmdText = "UPDATE sys_permission_employee SET pwd=?,update_time=NOW() WHERE id=?";
			String pwd = PasswordHelper.md5(null);
			QueryParameters qps = new QueryParameters();
			{
				qps.add("@pwd",pwd, Types.VARCHAR);
				qps.add("@id", id, Types.BIGINT);
			}
			this.pm.executeNonQuery(cmdText, qps);
		}
		return true;
	}

	/**
	 * <p>
	 * Title: byLoginNameAndMobile
	 * </p>
	 * <p>
	 * Description:根据登录名和手机号码查询员工（用于找回密码）
	 * </p>
	 * 
	 * @param loginName
	 * @param mobile
	 * @return
	 * @see org.netsharp.organization.base.IEmployeeService#byLoginNameAndMobile(java.lang.String,
	 *      java.lang.String)
	 */
	public Employee byLoginNameAndMobile(String loginName, String mobile) {
		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("*");
			oql.setFilter("loginName=? and mobile=?");
			oql.getParameters().add("loginName", loginName, Types.VARCHAR);
			oql.getParameters().add("mobile", mobile, Types.VARCHAR);
		}
		return super.queryFirst(oql);
	}

//	@Override
//	public String getWXEmployeeImage(Long employeeId, boolean isBigImage) {
//		String imageURL = null;
//		try {
//			IWxeaAppService paService = ServiceFactory.create(IWxeaAppService.class);
//			WxeaApp configuration = paService.byCode("WeChat");
//			AccessToken token = AccessTokenManage.get(configuration.getCorpid(), configuration.getCorpsecret());
//
//			UserGetRequest userGetRequest = new UserGetRequest();
//			userGetRequest.setUserid(employeeId);
//			userGetRequest.setToken(token);
//			UserGetResponse response = userGetRequest.getResponse();
//			if (response != null) {
//				imageURL = response.getAvatar();
//				if (imageURL != null && !isBigImage) {
//
//					imageURL = imageURL + "64";
//
//				}
//			}
//		} catch (Exception e) {
//
//		}
//		return imageURL;
//	}

	@Override
	public Employee changPassword(Employee employee) {

		UpdateBuilder updator = UpdateBuilder.getInstance();{
			
			updator.update(MtableManager.getMtable(this.type).getTableName());
			updator.set("pwd", employee.getPwd());
			updator.set("update_time", DateManage.toLongString(new Date()));
			updator.where("id = " + employee.getId());
		}
		
		String cmdText = updator.toSQL();
		pm.executeNonQuery(cmdText, null);
		return employee;
	}
}
