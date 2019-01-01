package org.netsharp.organization.service;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.netsharp.action.ActionContext;
import org.netsharp.action.ActionManager;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.core.QueryParameters;
import org.netsharp.entity.CatEntity;
import org.netsharp.organization.base.IEmployeeService;
import org.netsharp.organization.base.IOrganizationEmployeeService;
import org.netsharp.organization.base.IOrganizationService;
import org.netsharp.organization.dic.OrganizationType;
import org.netsharp.organization.dic.PostType;
import org.netsharp.organization.entity.Employee;
import org.netsharp.organization.entity.Organization;
import org.netsharp.organization.entity.OrganizationEmployee;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.service.PersistableService;
import org.netsharp.util.OrdinalMap;
import org.netsharp.util.PropertyConfigurer;
import org.netsharp.util.StrUtil;
import org.netsharp.util.StringManager;

@Service
public class OrganizationService extends PersistableService<Organization> implements IOrganizationService {

	IOrganizationEmployeeService oeS = ServiceFactory.create(IOrganizationEmployeeService.class);
	IEmployeeService eS = ServiceFactory.create(IEmployeeService.class);

	public OrganizationService() {
		super();
		this.type = Organization.class;
	}

	@Override
	public List<Organization> queryList(Oql oql) {

		String filter = oql.getFilter();
		if (filter != null && filter.indexOf("resource.id") > -1) {
			String[] strs = filter.toLowerCase().split("and");
			for (int i = 0, len = strs.length; i < len; i++) {
				if (strs[i].indexOf("resource.id") > -1) {
					String[] ss = strs[i].split("=");
					strs[i] = "(EXISTS (SELECT 1 FROM sys_permission_principal_operation po LEFT JOIN sys_permission_operation op ON po.operation_id=op.id WHERE Organization.id=po.principal_id AND op.resource_node_id="
							+ ss[1] + "))";
					break;
				}
			}
			oql.setFilter(StringManager.join(" and ", strs));
		}
		return super.queryList(oql);
	}

	@Override
	public Organization save(Organization entity) {

		ActionContext ctx = new ActionContext();
		{
			ctx.setPath("org/netsharp/basebiz/organization/save");
			ctx.setItem(entity);
			ctx.setState(entity.getEntityState());
		}

		ActionManager action = new ActionManager();
		action.execute(ctx);

		entity = (Organization) ctx.getItem();

		return entity;
	}

	public Map<Integer, String> getPosts() {

		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("id,pathName");
			oql.setFilter("organizationType = " + OrganizationType.POST.getValue());
			oql.setOrderby("pathName");
		}

		List<Organization> list = this.pm.queryList(oql);
		Map<Integer, String> map = new OrdinalMap<Integer, String>();
		for (Organization org : list) {
			map.put(org.getId(), org.getPathName());
		}

		return map;
	}

	public List<Organization> getByFunction(String organizationFunctionType) {
		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("Organization.*");
			oql.setFilter("organizationFunction.code = '" + organizationFunctionType + "' ");
		}

		List<Organization> list = this.pm.queryList(oql);
		return list;
	}

	@Override
	public List<Organization> getDirectDepartmentByEmployeeId(Integer employeeId) {

		Employee e = eS.byId(employeeId);
		List<OrganizationEmployee> oes = e.getOrganizations();
		OrganizationEmployee oe = oes.get(0);
		Organization o = oe.getOrganization();
		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("Organization.*");
			List<String> pathCodes = getPathCodes(o.getPathCode());
			oql.setOrderby("Id desc");
			oql.setFilter("organizationType = " + OrganizationType.DEPARTMENT.getValue() + " and pathCode in ( " + StrUtil.join(pathCodes, ",") + ")");
		}

		List<Organization> list = this.pm.queryList(oql);
		return list;
	}

	private static List<String> getPathCodes(String pathCode) {
		if (pathCode == null || pathCode == "")
			return null;
		// 根据pathCode得到上级所有几次的pathCode
		// 输入参数为：010103
		// 返回结果为：01,0101,010103
		List<String> codes = new ArrayList<String>();
		// 分类码的长度从：CatEntity.PATH_CODE_SIZE;
		for (int i = 0; i < pathCode.length(); i = i + CatEntity.PATH_CODE_SIZE) {
			codes.add(pathCode.substring(0, i + CatEntity.PATH_CODE_SIZE));
		}
		return codes;
	}

	protected Employee getEmployeeByPathCodeAndPositionId(String pathCode, String positionId) {
		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("Organization.*");

			oql.setOrderby("Id desc");
			oql.setFilter("  organizationType = " + OrganizationType.POST.getValue() + "  and   path_code   like  '" + pathCode + "%'   and  positionId='" + positionId + "'");
		}
		Organization positonLeader = this.pm.queryFirst(oql);
		Employee emp = oeS.getEmpByPostOrgId(positonLeader.getId());
		// List<OrganizationEmployee> oes=positonLeader.getEmployees();
		return emp;
	}

	@Override
	public Employee getFitableEmployeeByPositonAndOrgNodeName(String postionStr, String deptName) {
		// 先按照名字找到部门
		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("Organization.*");
			oql.setFilter(" name = '" + deptName + "'");
		}
		Organization dept = this.pm.queryFirst(oql);
		String pathCode = dept.getPathCode();
		PropertyConfigurer conf = PropertyConfigurer.newInstance("/conf/nodePosition_Id_map.properties");
		String pid = conf.get(postionStr).toString();
		// TODO Auto-generated method stub
		Employee emp = this.getEmployeeByPathCodeAndPositionId(pathCode, pid);
		return emp;
	}

	@Override
	public Organization getCorprationByDepartment(String deptPathCode) {
		
		// 查询组织类型为子公司的所有组织
		Oql oql = new Oql();
		{
			oql.setEntityId(Organization.class.getName());
			oql.setSelects("Organization.*");
			oql.setFilter(" OrganizationType = '" + OrganizationType.CORPORATION.getValue() + "'   or  OrganizationType ='" + OrganizationType.SYSTEM.getValue() + "'");
			oql.setOrderby("createTime");
		}
		List<Organization> lst = this.pm.queryList(oql);
		Organization rtn = null;
		for (Organization o : lst) {
			if(o.getPathCode()==null) {
				continue;
			}
			if (deptPathCode.contains(o.getPathCode())) {
				rtn = o;
				break;
			}
		}
		return rtn;
	}

	@Override
	public Employee getDirectLeader(Integer deptId) {

		// 获取员工的部门
		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("Organization.*");
			oql.setFilter("id=" + deptId);
		}
		Organization directDept = this.pm.queryFirst(oql);

		/**
		 * 查看业务类型 判断是否是门店 如果是门店
		 */
		String postionId = null;
		PropertyConfigurer conf = PropertyConfigurer.newInstance("/conf/nodePosition_Id_map.properties");
		if (directDept.getOrganizationFunction().getCode() == "Campus") {
			postionId = conf.get("shop").toString();
		} else {// 否则取对应的id职务的岗位
			postionId = conf.get("dept").toString();
		}
		// 获取部门的pathCode,查询出含有这个pathCode的组织,且类型是岗位的
		String pathCode = directDept.getPathCode();
		oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("Organization.*");

			oql.setOrderby("Id asc");
			oql.setFilter("  organizationType = " + OrganizationType.DEPARTMENT.getValue() + "  and  POSITION(path_code IN '" + pathCode + "')=1  ");

			// oql.setFilter("  organizationType = 'Department'  and   path_code   like  '%"
			// + pathCode + "%'   and  positionId='" + postionId+ "'");
		}
		Organization deptRoot = this.pm.queryFirst(oql);
		String deptRootPathCode = deptRoot.getPathCode();
		oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("Organization.*");

			oql.setOrderby("Id desc");

			oql.setFilter("  organizationType =" + OrganizationType.POST.getValue() + "  and   path_code   like  '" + deptRootPathCode + "%'   and  positionId='" + postionId + "'");
		}
		Organization deptRootLeader = this.pm.queryFirst(oql);

		Employee emp = oeS.getEmpByPostOrgId(deptRootLeader.getId());
		// List<OrganizationEmployee> oes=positonLeader.getEmployees();
		return emp;
	}

	/* 找到当前部门的所有岗位的所有员工，不递归查询，disabled表示是否查询离职员工 */
	public List<Employee> getEmployeesByCurrentDepartment(Integer departmentId, boolean disabled) {

		QueryParameters qps = new QueryParameters();
		{
			qps.add("@departmentId", departmentId, Types.INTEGER);

		}

		List<String> filters = new ArrayList<String>();
		filters.add("Employee.organizations.organization.parent_id=?");
		if (!disabled) {
			qps.add("@disabled", disabled, Types.BOOLEAN);
			filters.add("Employee.disabled=?");
		}

		Oql oql = new Oql();
		{
			oql.setType(Employee.class);
			oql.setSelects("Employee.{id,name,loginName}");
			oql.setFilter(StringManager.join(" and ", filters));
			oql.setParameters(qps);
		}

		IPersister<Employee> pm = PersisterFactory.create();
		List<Employee> employees = pm.queryList(oql);

		return employees;

	}

	@Override
	public Organization getMainDepartment(Integer employeeId) {

		Employee e = eS.byId(employeeId);
		List<OrganizationEmployee> oes = e.getOrganizations();
		if (oes == null || oes.size() == 0) {
			return null;
		}

		OrganizationEmployee oe = null;
		for (int i = 0, len = oes.size(); i < len; i++) {
			if (PostType.MAINTIME.equals(oes.get(i).getPostType())) {
				oe = oes.get(i);
			}
		}
		if (oe == null) {
			oe = oes.get(0);
		}

		Organization o = oe.getOrganization();

		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("Organization.*");

			List<String> pathCodes = getPathCodes(o.getPathCode());
			oql.setOrderby("pathCode desc");
			oql.setFilter("organizationType = " + OrganizationType.DEPARTMENT.getValue() + " and pathCode in ( " + StrUtil.join(pathCodes, ",") + ")");
		}
		return this.pm.queryFirst(oql);
	}

	@Override
	public Organization getParentDepartment(Organization department) {

		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("Organization.*");
			oql.setFilter("id=" + department.getParentId());
		}
		Organization org = this.pm.queryFirst(oql);
		if (org != null) {
			if (OrganizationType.DEPARTMENT.equals(org.getOrganizationType())) {
				return org;
			} else {
				return getParentDepartment(org);
			}
		} else {
			return null;
		}
	}

	@Override
	public void changeParent(Integer nodeId, Integer newParentId) {

		String cmdText = "UPDATE sys_permission_organization SET parent_id=? WHERE id=?";
		QueryParameters qps = new QueryParameters();
		{
			qps.add("@parent_id", newParentId, Types.INTEGER);
			qps.add("@id", nodeId, Types.INTEGER);
		}
		this.pm.executeNonQuery(cmdText, qps);
	}

	@Override
	public List<Integer> getChildDepartment(Integer id) {

		List<Integer> ids = new ArrayList<Integer>();
		getOrganizations(id, ids);

		return ids;
	}

	private void getOrganizations(Integer id, List<Integer> ids) {

		Oql oql = new Oql();
		{
			oql.setSelects("id,name");
			oql.setType(this.type);
			oql.setFilter("parentId=? and organizationType=" + OrganizationType.DEPARTMENT.getValue());
			oql.setParameters(new QueryParameters());
			{
				oql.getParameters().add("@parentId", id, Types.INTEGER);
			}
		}
		List<Organization> childs = this.queryList(oql);
		if (childs.size() > 0) {

			for (Organization child : childs) {
				ids.add(child.getId());
				this.getOrganizations(child.getId(), ids);
			}
		}
	}

	@Override
	public Boolean disabled(Integer id) {

		Organization entity = this.byId(id);
		if (entity == null) {
			return false;
		}
		String cmdText = "update sys_permission_organization set disabled=1 where path_code like '" + entity.getPathCode() + "%'";
		return this.pm.executeNonQuery(cmdText, null) > 0;
	}

	@Override
	public List<Integer> getChildPostIds(Integer id) {

		return null;
	}

	@Override
	public List<Integer> getEmployeeIds(Integer departmentId) {

		return getEmployeeIds(departmentId, false);
	}

	@Override
	public List<Integer> getEmployeeIds(Integer departmentId, Boolean isDirectlyPost) {

		return null;
	}

	@Override
	public Boolean hasChild(Integer id) {

		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setFilter("parentId = ?");
			oql.setParameters(new QueryParameters());
			{
				oql.getParameters().add("@parentId", id, Types.INTEGER);
			}
		}
		return this.queryCount(oql) > 0;
	}
}
