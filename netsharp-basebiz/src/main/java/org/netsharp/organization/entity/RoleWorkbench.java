package org.netsharp.organization.entity;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

@Table(name = "sys_permission_role_workbench", header = "工作台配置")
public class RoleWorkbench  extends Entity{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 7623373393187290734L;

	@Column(name = "name", size = 50, header = "名称")
	private String name;
	
	@Column(name = "path", size = 256, header = "路径")
	private String path;
	
	@Column(name = "memoto", size = 500, header = "备注")
	private String memoto;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getMemoto() {
		return memoto;
	}

	public void setMemoto(String memoto) {
		this.memoto = memoto;
	}
}
