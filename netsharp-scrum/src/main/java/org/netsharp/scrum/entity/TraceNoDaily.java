package org.netsharp.scrum.entity;

import java.util.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;
import org.netsharp.organization.entity.Employee;
import org.netsharp.organization.entity.Organization;

//scrum.organization.it.idnotins
@Table(name="scrum_story_trace_no_daily",header="每日未跟进")
public class TraceNoDaily extends Entity {

	private static final long serialVersionUID = 9152675511628172749L;
	
	@Column(header="跟进日期")
	private String day;
	
	@Column(header="跟进日期")
	private Date date;
	
	@Column(name="tracor_id")
	private Integer tracorId;
	@Reference(foreignKey="tracorId",header="未跟进人")
	private Employee tracor;
	
	@Column(name="organization_id")
	private Integer organizationId;
	
	@Reference(foreignKey="organizationId",header="所属部门")
	private Organization organization;
	
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getTracorId() {
		return tracorId;
	}
	public void setTracorId(Integer tracorId) {
		this.tracorId = tracorId;
	}
	public Employee getTracor() {
		return tracor;
	}
	public void setTracor(Employee tracor) {
		this.tracor = tracor;
	}
	public Integer getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
}
