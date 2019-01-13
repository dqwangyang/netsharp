package org.netsharp.scrum.entity;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.core.annotations.BizCode;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Subs;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.BizEntity;
import org.netsharp.organization.entity.Employee;
import org.netsharp.organization.entity.Organization;
import org.netsharp.scrum.dic.CaseStatus;

@BizCode(bizType = "CS")
@Table(name = "scrum_case", header = "用例")
public class UseCase extends BizEntity {

	private static final long serialVersionUID = 3021873048137307596L;
	
	@Column(name = "owner_id")
	private Long ownerId;
	@Reference(foreignKey = "ownerId")
	private Employee owner;// 项目负责人

	@Column(name = "project_id")
	private Long projectId;

	@Reference(foreignKey = "projectId")
	private Project project;

	private CaseStatus status = CaseStatus.hibernate;

	@Column(name = "organization_id")
	private Long organizationId;

	@Reference(foreignKey = "organizationId")
	private Organization organization;
	
	@Column(size=5000)
	private String content;

	@Subs(subType = UseCaseDetail.class, foreignKey = "caseId")
	private List<UseCaseDetail> details;

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public Employee getOwner() {
		return owner;
	}

	public void setOwner(Employee owner) {
		this.owner = owner;
		if(this.owner==null) {
			this.ownerId = null;
		}else {
			this.ownerId = this.owner.getId();
		}
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public CaseStatus getStatus() {
		return status;
	}

	public void setStatus(CaseStatus status) {
		this.status = status;
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
		if(this.organization==null) {
			this.organizationId=null;
		}else{
			this.organizationId=this.organization.getId();
		}
	}

	public List<UseCaseDetail> getDetails() {
		if(details==null) {
			details = new ArrayList<UseCaseDetail>();
		}
		return details;
	}

	public void setDetails(List<UseCaseDetail> details) {
		this.details = details;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}