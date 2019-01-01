package org.netsharp.dbm.entity;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

@Table(name="sys_dbm_log",header="数据库操作日志")
public class DbmLog extends Entity {

	private static final long serialVersionUID = -7243236639938102477L;
	
	@Column(size=4000)
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	

}
