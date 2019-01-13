package org.netsharp.organization.entity;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

@Table(name="sys_permission_role_employee",header="员工角色")
public class RoleEmployee extends Entity{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 4339224195644861710L;
	
    @Reference(foreignKey="roleId")
    private Role role;
    
    @Column(name="role_id",header="角色")
    private Long roleId;

    @JsonIgnore
    @Reference(foreignKey="employeeId")
    private Employee employee;
    
    @Column(name="employee_id",header="员工Id")
    private Long employeeId;

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
}
