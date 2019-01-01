package org.netsharp.scrum.entity;

import java.util.List;

import org.codehaus.jackson.annotate.JsonManagedReference;
import org.netsharp.core.annotations.BizCode;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Subs;
import org.netsharp.core.annotations.Table;
import org.netsharp.organization.entity.Organization;
import org.netsharp.scrum.dic.StoryStatus;

@BizCode(bizType="ST")
@Table(name="scrum_story",header="用户任务")
public class Story extends TaskBase {
	
	private static final long serialVersionUID = 4596426814557432542L;

	@Column(name="project_id")
	private Integer projectId;
	
	@Reference(foreignKey="projectId")
	private Project project;
	
	private StoryStatus status = StoryStatus.hibernate;
	
	@Column(name="iteration_id")
	private Integer iterationId;
	
	@Reference(foreignKey="iterationId")
	private Iteration iteration;//迭代
	
	@Column(name="organization_id")
	private Integer organizationId;
	
	@Reference(foreignKey="organizationId",header="部门")
	private Organization organization;
	
	@Subs(subType=StoryParticipant.class,foreignKey="storyId")
	private List<StoryParticipant> participants;
	
	@Column(name="participant_str")
	private String participantStr;
	
	public StoryStatus getStatus() {
		return status;
	}

	public void setStatus(StoryStatus status) {
		this.status = status;
	}


	public List<StoryParticipant> getParticipants() {
		return participants;
	}

	@JsonManagedReference
	public void setParticipants(List<StoryParticipant> participants) {
		this.participants = participants;
	}

	public Integer getIterationId() {
		return iterationId;
	}

	public void setIterationId(Integer iterationId) {
		this.iterationId = iterationId;
	}

	public Iteration getIteration() {
		return iteration;
	}

	public void setIteration(Iteration iteration) {
		this.iteration = iteration;
		
		if(this.iteration==null){
			this.iterationId= null;
		}
		else{
			this.iterationId=this.iteration.getId();
		}
	}


	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
		if(this.project==null){
			this.projectId=null;
		}
		else{
			this.projectId=this.project.getId();
		}
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

	public String getParticipantStr() {
		return participantStr;
	}

	public void setParticipantStr(String participantStr) {
		this.participantStr = participantStr;
	}
	
}
