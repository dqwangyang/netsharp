package org.netsharp.panda.session;

import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.BusinessException;
import org.netsharp.core.Column;
import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.core.Oql;
import org.netsharp.entity.CatEntity;
import org.netsharp.organization.base.IAuthorizationPrincipalService;
import org.netsharp.organization.base.IOrganizationEmployeeService;
import org.netsharp.organization.base.IOrganizationService;
import org.netsharp.organization.base.IRoleEmployeeService;
import org.netsharp.organization.dic.OrganizationType;
import org.netsharp.organization.dic.PostType;
import org.netsharp.organization.dic.PrincipalType;
import org.netsharp.organization.entity.AuthorizationPrincipal;
import org.netsharp.organization.entity.FieldGeteway;
import org.netsharp.organization.entity.Operation;
import org.netsharp.organization.entity.Organization;
import org.netsharp.organization.entity.OrganizationEmployee;
import org.netsharp.organization.entity.PrincipalOperation;
import org.netsharp.organization.entity.RoleEmployee;
import org.netsharp.session.NetsharpSession;
import org.netsharp.util.StringManager;

public class PermissionQuery {	

	public UserPermission query(PandaSession ps) {
		
		NetsharpSession session = ps.getSession();
		UserPermission up = new UserPermission();
		ps.setPermission(up);

		// 得到当前用户的所有岗位
		Mtable meta = MtableManager.getMtable(OrganizationEmployee.class);
		Column column = meta.getPropertyOrColumn("employeeId");

		Oql oql = new Oql();
		{
			oql.setType(OrganizationEmployee.class);
			oql.setSelects("organization.{id,code,name,pathCode,parentId}");
			oql.setFilter("employeeId=?");
			oql.getParameters().add("@employeeId", session.getUserId(), column.getDataType().getJdbcType());
		}

		IOrganizationEmployeeService organizationEmployeeService = ServiceFactory.create(IOrganizationEmployeeService.class);
		List<OrganizationEmployee> oes = organizationEmployeeService.queryList(oql);

		// 根据岗位得到所有上级部门的pathCode
		List<String> pathCodes = this.getPathCodes(oes, session);

		// 根据pathCode得到id
		oql = new Oql();
		{
			oql.setType(Organization.class);
			oql.setSelects("id,positionId");
			oql.setFilter("pathcode in (" + StringManager.join(",", pathCodes) + ") and organizationType=" + OrganizationType.POST.getValue());
		}

		IOrganizationService organizationService = ServiceFactory.create(IOrganizationService.class);
		List<Organization> orgs = organizationService.queryList(oql);

		List<Long> orgIds = new ArrayList<Long>();
		List<Long> posIds = new ArrayList<Long>();
		for (Organization org : orgs) {
			orgIds.add(org.getId());
			if (org.getPositionId() != null) {
				posIds.add(org.getPositionId());
			}
		}

		// 根据组织结构查询授权主体和授权主体的操作
		oql = new Oql();
		{
			oql.setType(AuthorizationPrincipal.class);
			oql.setSelects("id,principalId,principalOperations.{id,principalId,operationId},principalOperations.operation.operationtype.{name},principalOperations.operation.resourceNode.{pathName,name}, principalOperations.operation.{id,name,operationTypeId,resourceNodeId},principalOperations.operation.fieldGeteways.{id,operationId,entityId,propertyName}");
			oql.setFilter("principalType="+OrganizationType.POST.getValue()+" AND principalId in (" + StringManager.join(",", orgIds) + ")");
		}

		IAuthorizationPrincipalService apService = ServiceFactory.create(IAuthorizationPrincipalService.class);
		List<AuthorizationPrincipal> aps = apService.queryList(oql);

		// 根据职务查询授权主体和授权主体的操作
		oql = new Oql();
		{
			oql.setType(AuthorizationPrincipal.class);
			oql.setSelects("id,principalId,principalOperations.{id,principalId,operationId},principalOperations.operation.operationtype.{name},principalOperations.operation.resourceNode.{pathName,name}, principalOperations.operation.{id,name,operationTypeId,resourceNodeId},principalOperations.operation.fieldGeteways.{id,operationId,entityId,propertyName}");
			oql.setFilter("principalType="+PrincipalType.STATION.getValue()+" AND principalId in (" + StringManager.join(",", posIds) + ")");
		}

		List<AuthorizationPrincipal> apposs = apService.queryList(oql);
		if (apposs != null && apposs.size() > 0) {
			aps.addAll(apposs);
		}

		// 根据角色查询授权主体和授权主体的操作
		oql = new Oql();
		{
			oql.setType(RoleEmployee.class);
			oql.setSelects("role.{id,name}");
			oql.setFilter("employeeId=? and role.disabled=0");
			oql.getParameters().add("@employeeId", session.getUserId(), column.getDataType().getJdbcType());
		}
		IRoleEmployeeService roleEmployeeService = ServiceFactory.create(IRoleEmployeeService.class);
		List<RoleEmployee> roles = roleEmployeeService.queryList(oql);
		List<Long> roleIds = new ArrayList<Long>();
		for (RoleEmployee re : roles) {
			roleIds.add(re.getRoleId());
		}

		oql = new Oql();
		{
			oql.setType(AuthorizationPrincipal.class);
			oql.setSelects("id,principalId,principalOperations.{id,principalId,operationId},principalOperations.operation.operationtype.{name},principalOperations.operation.resourceNode.{pathName,name}, principalOperations.operation.{id,name,operationTypeId,resourceNodeId},principalOperations.operation.fieldGeteways.{id,operationId,entityId,propertyName}");
			oql.setFilter("principalType="+PrincipalType.Role.getValue()+" AND principalId in (" + StringManager.join(",", roleIds) + ")");
		}

		List<AuthorizationPrincipal> roleAps = apService.queryList(oql);
		if (roleAps != null && roleAps.size() > 0) {
			aps.addAll(roleAps);
		}

		// 得到最终的操作权限集合
		List<Operation> operations = new ArrayList<Operation>();
		Map<String, List<String>> fieldGeteways = new HashMap<String, List<String>>();

		Set<Long> operationId = new HashSet<Long>();

		for (AuthorizationPrincipal ap : aps) {
			for (PrincipalOperation po : ap.getPrincipalOperations()) {

				Operation operation = po.getOperation();

				if (operation == null || operationId.contains(operation.getId())) {
					continue;
				}
				operations.add(operation);
				operationId.add(operation.getId());

				for (FieldGeteway fieldGetway : operation.getFieldGeteways()) {
					String entityId = fieldGetway.getEntityId();
					List<String> fields = fieldGeteways.get(entityId);
					if (fields == null) {
						fields = new ArrayList<String>();
						fieldGeteways.put(entityId, fields);
					}

					fields.add(fieldGetway.getPropertyName());
				}
			}
		}

		up.setOperations(operations);
		up.setFieldGeteways(fieldGeteways);
		up.setPermission(true);
		return up;
	}

	private List<String> getPathCodes(List<OrganizationEmployee> oes, NetsharpSession session ) {
		
		IOrganizationService organizationService = ServiceFactory.create(IOrganizationService.class);

		List<String> pathCodes = new ArrayList<String>();
		List<String> postIds = new ArrayList<String>();
		List<String> departmentIds = new ArrayList<String>();
		List<String> departmentPathCodes = new ArrayList<String>();

		Organization firstPost = null;
		Organization mainPost = null;
		
		for (OrganizationEmployee oe : oes) {

			Organization post = oe.getOrganization();
			if (post == null) {
				continue;
			}
			
			if(firstPost!=null) {
				firstPost = post;
			}
			if(mainPost == null && oe.getPostType() == PostType.MAINTIME) {
				mainPost = post;
			}

			//
			postIds.add(oe.getOrganizationId().toString());
			departmentIds.add(oe.getOrganization().getParentId().toString());
			departmentPathCodes.add(this.getParentPathCode(oe.getOrganization().getPathCode()));

			List<String> ss = this.getPathCodes(post.getPathCode());

			if(ss==null) {
				continue;
			}

			for (String pathCode : ss) {
				String str = "'" + pathCode + "'";
				if (!pathCodes.contains(str)) {
					pathCodes.add(str);
				}
			}
		}
		
		if(mainPost == null) {
			mainPost = firstPost;
		}
		
		if(mainPost == null ) {
			throw new BusinessException("您在系统中没有岗位，请联系管理员设置组织机构后再登录");
		}
		
		session.setPostId(mainPost.getId());
		session.setPostName(mainPost.getName());
		session.setDepartmentId(mainPost.getParentId());
		
		Oql oql = new Oql();
		{
			oql.setSelects("id,name,pathCode");
			oql.setFilter("id=?");
			oql.getParameters().add("@id",session.getDepartmentId(),Types.INTEGER);
		}
		Organization department = organizationService.queryFirst(oql);
		if(department == null) {
			throw new BusinessException("您在系统中没有部门，请联系管理员设置组织机构后再登录");
		}
		session.setDepartmentName(department.getName());
		
		
		session.setPostIds(StringManager.join(",", postIds));
		session.setDepartmentIds(StringManager.join(",", departmentIds));
		session.setDepartmentPathCodes(departmentPathCodes.toArray(new String[departmentPathCodes.size()]));

		Organization comp = organizationService.getCorprationByDepartment(department.getPathCode());
		if (comp != null) {
			session.setTenancyId(comp.getId());
			session.setTenancyName(comp.getName());
		}
		
		return pathCodes;
	}

	private List<String> getPathCodes(String pathCode) {
		if (pathCode == null || pathCode == "")
			return null;
		// 根据pathCode得到上级所有几次的pathCode
		// 输入参数为：010103
		// 返回结果为：01,0101,010103
		List<String> codes = new ArrayList<String>();
		// codes.add(pathCode);
		// 分类码的长度从：CatEntity.PATH_CODE_SIZE;
		for (int i = 0; i < pathCode.length(); i = i + CatEntity.PATH_CODE_SIZE) {
			codes.add(pathCode.substring(0, i + CatEntity.PATH_CODE_SIZE));
		}
		return codes;
	}

	private String getParentPathCode(String pathCode) {
		if (pathCode == null) {
			return "";
		}

		if (pathCode.length() <= CatEntity.PATH_CODE_SIZE) {
			return "";
		}

		pathCode = pathCode.substring(0, pathCode.length() - CatEntity.PATH_CODE_SIZE);

		return pathCode;
	}
}
