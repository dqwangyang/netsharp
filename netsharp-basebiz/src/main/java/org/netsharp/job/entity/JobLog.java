package org.netsharp.job.entity;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.BizEntity;

/*作业日志*/
@Table(name = "sys_job_log", header = "作业日志")
public class JobLog extends BizEntity {

	private static final long serialVersionUID = -5551326969171301893L;

	@Column(name="group_name",header="分组")
	private String groupName;
	
	@Column(size = 500, name = "java_type")
	private String javaType;

	@Column(name = "operation", header = "操作")
	private String operation;// 操作

	@Column(name = "timed", header = "执行耗时(分钟)")
	private long timed = 0;

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getJavaType() {
		return javaType;
	}

	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public long getTimed() {
		return timed;
	}

	public void setTimed(long timed) {
		this.timed = timed;
	}
}
