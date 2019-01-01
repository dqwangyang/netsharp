package org.netsharp.job.entity;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.resourcenode.entity.ResourceBizEntity;

@Table(name = "sys_job", header = "作业配置")
public class Job extends ResourceBizEntity {


	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -6579138076831178945L;

	@Column(name="group_name",header="分组")
	private String groupName;
	
	@Column(name="cron",header="cron表达式")
	private String cron;
	
	@Column(name="cron_description",size = 500,header="表达式描述")
	private String cronDescription;
	
	@Column(name="description",size = 500,header="描述")
	private String description;
	
	@Column(name="java_type",size = 500,header="作业JAVA类")
	private String javaType;
	
	@Column(name="par",size = 200,header="参数")
	private String par;
	
	@Column(name="status",header="状态")
	private JobStatus status = JobStatus.Stop;

	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getCron() {
		return cron;
	}
	public void setCron(String cron) {
		this.cron = cron;
	}
	public String getCronDescription() {
		return cronDescription;
	}
	public void setCronDescription(String cronDescription) {
		this.cronDescription = cronDescription;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getJavaType() {
		return javaType;
	}
	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}

	public JobStatus getStatus() {
		return status;
	}
	public void setStatus(JobStatus status) {
		this.status = status;
	}
	public String getPar() {
		return par;
	}
	public void setPar(String par) {
		this.par = par;
	}
}
