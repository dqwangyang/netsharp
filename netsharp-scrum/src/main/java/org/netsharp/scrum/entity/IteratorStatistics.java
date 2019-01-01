package org.netsharp.scrum.entity;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.CatEntity;
import org.netsharp.organization.entity.Employee;
import org.netsharp.organization.entity.Organization;


@Table(name="scrum_report_iterator_statistics", header="迭代统计")
public class IteratorStatistics extends CatEntity {

	private static final long serialVersionUID = 913591966175288302L;
	
	@Column(name="iteration_id",header="迭代ID")
	private Long iterationId;
	
	@Reference(foreignKey="iterationId")
	private Iteration iteration;
	
	@Column(name="organization_id",header="部门ID")
	private Long organizationId;
	
	@Reference(foreignKey="organizationId")
	private Organization organization;
	
	@Column(name="employee_id",header="人员ID")
	private Long employeeId;
	
	@Reference(foreignKey="employeeId")
	private Employee employee;
	
	@Column(name="item_total_count",header="项目总个数")
	private Integer itemTotalCount;
	
	@Column(name="item_estimation_time",header="项目总估时")
	private Integer itemEstimationTime;
	
	@Column(name="item_real_time",header="项目总耗时")
	private Integer itemRealTime;
	
	@Column(name="finished_item_total_count",header="已完成项目总数")
	private Integer finishedItemTotalCount;
	
	@Column(name="finished_item_estimation_time",header="已完成总估时")
	private Integer finishedItemEstimationTime;
	
	@Column(name="finished_item_real_time",header="已完成总耗时")
	private Integer finishedItemRealTime;
	
	@Column(name="unfinished_item_total_count",header="未完成项目总数")
	private Integer unFinishedItemTotalCount;
	
	@Column(name="unfinished_item_estimation_time",header="未完成总估时")
	private Integer unFinishedItemEstimationTime;
	
	@Column(name="support_total_count",header="支持总个数")
	private Integer supportTotalCount;
	
	@Column(name="support_estimation_time",header="支持总估时")
	private Integer supportEstimationTime;
	
	@Column(name="support_real_time",header="支持总耗时")
	private Integer supportRealTime;
	
	@Column(name="finished_support_total_count",header="已完成支持总个数")
	private Integer finishedSupportTotalCount;
	
	@Column(name="finished_support_estimation_time",header="已完成支持总估时")
	private Integer finishedSupportEstimationTime;
	
	@Column(name="finished_support_real_time",header="已完成支持总耗时")
	private Integer finishedSupportRealTime;
	
	@Column(name="unfinished_support_total_count",header="未完成支持总个数")
	private Integer unfinishedSupportTotalCount;
	
	@Column(name="unfinished_support_estimation_time",header="未完成支持总估时")
	private Integer unfinishedSupportEstimationTime;

	public Long getIterationId() {
		return iterationId;
	}

	public void setIterationId(Long iterationId) {
		this.iterationId = iterationId;
	}

	public Iteration getIteration() {
		return iteration;
	}

	public void setIteration(Iteration iteration) {
		this.iteration = iteration;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Integer getItemTotalCount() {
		return itemTotalCount;
	}

	public void setItemTotalCount(Integer itemTotalCount) {
		this.itemTotalCount = itemTotalCount;
	}

	public Integer getItemEstimationTime() {
		return itemEstimationTime;
	}

	public void setItemEstimationTime(Integer itemEstimationTime) {
		this.itemEstimationTime = itemEstimationTime;
	}

	public Integer getItemRealTime() {
		return itemRealTime;
	}

	public void setItemRealTime(Integer itemRealTime) {
		this.itemRealTime = itemRealTime;
	}

	public Integer getFinishedItemTotalCount() {
		return finishedItemTotalCount;
	}

	public void setFinishedItemTotalCount(Integer finishedItemTotalCount) {
		this.finishedItemTotalCount = finishedItemTotalCount;
	}

	public Integer getFinishedItemEstimationTime() {
		return finishedItemEstimationTime;
	}

	public void setFinishedItemEstimationTime(Integer finishedItemEstimationTime) {
		this.finishedItemEstimationTime = finishedItemEstimationTime;
	}

	public Integer getFinishedItemRealTime() {
		return finishedItemRealTime;
	}

	public void setFinishedItemRealTime(Integer finishedItemRealTime) {
		this.finishedItemRealTime = finishedItemRealTime;
	}

	public Integer getUnFinishedItemTotalCount() {
		return unFinishedItemTotalCount;
	}

	public void setUnFinishedItemTotalCount(Integer unFinishedItemTotalCount) {
		this.unFinishedItemTotalCount = unFinishedItemTotalCount;
	}

	public Integer getUnFinishedItemEstimationTime() {
		return unFinishedItemEstimationTime;
	}

	public void setUnFinishedItemEstimationTime(Integer unFinishedItemEstimationTime) {
		this.unFinishedItemEstimationTime = unFinishedItemEstimationTime;
	}

	public Integer getSupportTotalCount() {
		return supportTotalCount;
	}

	public void setSupportTotalCount(Integer supportTotalCount) {
		this.supportTotalCount = supportTotalCount;
	}

	public Integer getSupportEstimationTime() {
		return supportEstimationTime;
	}

	public void setSupportEstimationTime(Integer supportEstimationTime) {
		this.supportEstimationTime = supportEstimationTime;
	}

	public Integer getSupportRealTime() {
		return supportRealTime;
	}

	public void setSupportRealTime(Integer supportRealTime) {
		this.supportRealTime = supportRealTime;
	}

	public Integer getFinishedSupportTotalCount() {
		return finishedSupportTotalCount;
	}

	public void setFinishedSupportTotalCount(Integer finishedSupportTotalCount) {
		this.finishedSupportTotalCount = finishedSupportTotalCount;
	}

	public Integer getFinishedSupportEstimationTime() {
		return finishedSupportEstimationTime;
	}

	public void setFinishedSupportEstimationTime(
			Integer finishedSupportEstimationTime) {
		this.finishedSupportEstimationTime = finishedSupportEstimationTime;
	}

	public Integer getFinishedSupportRealTime() {
		return finishedSupportRealTime;
	}

	public void setFinishedSupportRealTime(Integer finishedSupportRealTime) {
		this.finishedSupportRealTime = finishedSupportRealTime;
	}

	public Integer getUnfinishedSupportTotalCount() {
		return unfinishedSupportTotalCount;
	}

	public void setUnfinishedSupportTotalCount(Integer unfinishedSupportTotalCount) {
		this.unfinishedSupportTotalCount = unfinishedSupportTotalCount;
	}

	public Integer getUnfinishedSupportEstimationTime() {
		return unfinishedSupportEstimationTime;
	}

	public void setUnfinishedSupportEstimationTime(
			Integer unfinishedSupportEstimationTime) {
		this.unfinishedSupportEstimationTime = unfinishedSupportEstimationTime;
	}

}
