package org.netsharp.organization.entity;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;


/*部门业务类型*/
@Table(name = "sys_permission_organization_function", header = "部门业务类型")
public class OrganizationFunction extends Entity {

	private static final long serialVersionUID = -985527693271595765L;
	
	@Column(name="code",header="编码")
	private String code;
	
	@Column(name="name",header="名称")
	private String name;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
