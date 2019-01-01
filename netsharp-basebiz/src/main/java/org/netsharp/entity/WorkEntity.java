package org.netsharp.entity;

import java.util.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.dic.VoucherState;
import org.netsharp.organization.entity.Employee;
import org.netsharp.organization.entity.Organization;

/* 工作实体
 * 涉及到当前实体属于哪个部门和业务员的实体
 * 一般在工作流或者审核相关的单据继承此类*/
public class WorkEntity extends BizEntity {

	private static final long serialVersionUID = -1064097335172845738L;

	@Column(name="voucher_state",header="单据状态")
	protected VoucherState voucherState;
	
	@Column(name="audited_date",header="审核日期")
	protected Date auditedDate;
	
	@Reference(foreignKey = "bizUserId",  header = "业务员")
	protected Employee bizUser;
	
	@org.netsharp.core.annotations.Column(name="bizuser_id",header="业务员")
	protected Integer bizUserId;
	
	@Reference(foreignKey = "auditorId", header = "审核人")
	protected Employee auditor;
	
	@org.netsharp.core.annotations.Column(name="auditor_id",header="审核人")
	protected Integer auditorId;
	
	@Reference(foreignKey = "organizationId", header = "部门")
	protected Organization organization;
	
	@org.netsharp.core.annotations.Column(name="organization_id",header="部门")
	protected Integer organizationId;
	
	
	public VoucherState getVoucherState() {
		return voucherState;
	}
	public void setVoucherState(VoucherState voucherState) {
		this.voucherState = voucherState;
	}
	public Date getAuditedDate() {
		return auditedDate;
	}
	public void setAuditedDate(Date auditedDate) {
		this.auditedDate = auditedDate;
	}
	
	public Employee getBizUser() {
		return bizUser;
	}
	public void setBizUser(Employee bizUser) {
		this.bizUser = bizUser;
		if(this.bizUser==null){
			this.bizUserId=null;
		}else{
			this.bizUserId=this.bizUser.getId();
		}
	}
	public Integer getBizUserId() {
		return bizUserId;
	}
	public void setBizUserId(Integer idBizUser) {
		this.bizUserId = idBizUser;
	}
	public Employee getAuditor() {
		return auditor;
	}
	public void setAuditor(Employee auditor) {
		this.auditor = auditor;
		if(this.auditor==null){
			this.auditorId=null;
		}else{
			this.auditorId=this.auditor.getId();
		}
	}
	public Integer getAuditorId() {
		return auditorId;
	}
	public void setAuditorId(Integer idAuditor) {
		this.auditorId = idAuditor;
	}
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
		if(this.organization==null){
			this.organizationId=null;
		}
		else{
			this.organizationId = this.organization.getId();
		}
	}
	public Integer getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}
}
