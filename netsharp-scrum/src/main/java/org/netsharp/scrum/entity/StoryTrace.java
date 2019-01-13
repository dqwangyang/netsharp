package org.netsharp.scrum.entity;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;
import org.netsharp.organization.entity.Organization;
import org.netsharp.scrum.dic.Importance;

@Table(name="scrum_story_trace",header="任务跟进")
public class StoryTrace extends Entity {

	private static final long serialVersionUID = -3593232205242204113L;
	
	@Column( name = "story_id")
	private Long storyId;
	@Reference(foreignKey="storyId")
    private Story story;
	
	private Importance importance = Importance.general; 
	
	@Column(name="organization_id")
	private Long organizationId;
	
	@Reference(foreignKey="organizationId",header="部门")
	private Organization organization;
	
	
	@Column(size=1000)
	private String content;

	public Long getStoryId() {
		return storyId;
	}

	public void setStoryId(Long storyId) {
		this.storyId = storyId;
	}

	public Story getStory() {
		return story;
	}

	public void setStory(Story project) {
		this.story = project;
		if(this.story==null){
			this.storyId=null;
		}
		else{
			this.storyId=this.story.getId();
		}
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Importance getImportance() {
		return importance;
	}

	public void setImportance(Importance importance) {
		this.importance = importance;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public Organization getOrganization() {
		if(this.organization==null) {
			this.organizationId = null;
		}else {
			this.organizationId = this.organization.getId();
		}
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	
	
}
