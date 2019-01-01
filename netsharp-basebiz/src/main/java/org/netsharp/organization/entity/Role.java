package org.netsharp.organization.entity;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.BizEntity;

@Table(name = "sys_permission_role", header = "数据权限设置")
public class Role extends BizEntity {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = -2294418359530812775L;

	@Column(name = "disabled", header = "停用")
	private Boolean disabled = false;
	
    @Column(name="workbench_id",header="角色")
    private Integer workbenchId;

	@Reference(foreignKey = "workbenchId")
	private RoleWorkbench workbench;

	@Column(name = "role_group_id", header = "角色分组")
	private Integer roleGroupId;

	@Reference(foreignKey = "roleGroupId")
	private RoleGroup roleGroup;
	
	
	public Boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}

	public Integer getRoleGroupId() {
		return roleGroupId;
	}

	public void setRoleGroupId(Integer roleGroupId) {
		this.roleGroupId = roleGroupId;
	}

	public Integer getWorkbenchId() {
		return workbenchId;
	}

	public void setWorkbenchId(Integer workbenchId) {
		this.workbenchId = workbenchId;
	}

	public RoleWorkbench getWorkbench() {
		return workbench;
	}

	public void setWorkbench(RoleWorkbench workbench) {
		this.workbench = workbench;
	}

	public RoleGroup getRoleGroup() {
		return roleGroup;
	}

	public void setRoleGroup(RoleGroup roleGroup) {
		this.roleGroup = roleGroup;
	}
}
