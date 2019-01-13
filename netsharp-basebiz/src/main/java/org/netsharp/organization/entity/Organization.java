package org.netsharp.organization.entity;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Subs;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.CatEntity;
import org.netsharp.organization.dic.OrganizationType;

@Table(name = "sys_permission_organization", header = "组织机构")
public class Organization extends CatEntity {

	private static final long serialVersionUID = 3242707857062948131L;

	@Column(name = "organization_type", header = "组织类型")
	private OrganizationType organizationType;

	@Column(name = "organization_function_id", header = "部门职能，业务属性")
	private Long organizationFunctionId;

	@Reference(foreignKey = "organizationFunctionId")
	private OrganizationFunction organizationFunction;

	@JsonIgnore
	@Subs(subType = OrganizationEmployee.class, foreignKey = "organizationId")
	private List<OrganizationEmployee> employees;

	@Reference(foreignKey = "positionId")
	private Position position;

	@Column(name = "position_id", header = "职务")
	private Long positionId;

//	@Auto
	@Column(name = "qy_weixin_id", header = "企业号部门ID")
	private Long qyWeiXinId;

	public Long getQyWeiXinId() {
		return qyWeiXinId;
	}

	public void setQyWeiXinId(Long qyWeiXinId) {
		this.qyWeiXinId = qyWeiXinId;
	}

	public List<OrganizationEmployee> getEmployees() {
		if (this.employees == null) {
			this.employees = new ArrayList<OrganizationEmployee>();
		}
		return this.employees;
	}

	public Long getPositionId() {
		return this.positionId;
	}

	public Organization setPositionId(Long positionId) {
		this.positionId = positionId;
		return this;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public void setEmployees(List<OrganizationEmployee> employees) {
		this.employees = employees;
	}

	public OrganizationType getOrganizationType() {
		return organizationType;
	}

	public void setOrganizationType(OrganizationType organizationType) {
		this.organizationType = organizationType;
	}

	public OrganizationFunction getOrganizationFunction() {
		return organizationFunction;
	}

	public void setOrganizationFunction(OrganizationFunction organizationFunction) {
		this.organizationFunction = organizationFunction;
	}

	public Long getOrganizationFunctionId() {
		return organizationFunctionId;
	}

	public void setOrganizationFunctionId(Long organizationFunctionId) {
		this.organizationFunctionId = organizationFunctionId;
	}
}