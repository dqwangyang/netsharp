package org.netsharp.scrum.entity;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;
import org.netsharp.organization.entity.Employee;

@Table(name="scrum_story_participant",header="任务参与者")
public class StoryParticipant extends Entity {
	
	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 4813868132855507622L;
	@Column(name="story_id")
	private Integer storyId;
	@Reference(foreignKey="storyId")
	private Story story;

	@Column(name="participant_id")
	private Integer participantId;
	@Reference(foreignKey="participantId")
	private Employee participant;
	
	@Column(name="estimate_hours")
	private Double estimateHours;//估时，以小时为单位
	
    @Column(name = "memoto",header="备注", size = 1000)
    private String memoto;
    
	public Integer storyId() {
		return storyId;
	}
	public void seStoryId(Integer storyId) {
		this.storyId = storyId;
	}
	public Story getStory() {
		return story;
	}
	
	@JsonBackReference
	public void setStory(Story project) {
		this.story = project;
		if(this.story==null){
			this.storyId=null;
		}
		else{
			this.storyId=this.story.getId();
		}
	}
	public Integer getParticipantId() {
		return participantId;
	}
	public void setParticipantId(Integer participantId) {
		this.participantId = participantId;
	}
	public Employee getParticipant() {
		return participant;
	}
	public void setParticipant(Employee participant) {
		this.participant = participant;
		if(this.participant==null){
			this.participantId=null;
		}
		else{
			this.participantId=this.participant.getId();
		}
	}
	public Double getEstimateHours() {
		return estimateHours;
	}
	public void setEstimateHours(Double estimateHours) {
		this.estimateHours = estimateHours;
	}
	public Integer getStoryId() {
		return storyId;
	}
	public void setStoryId(Integer storyId) {
		this.storyId = storyId;
	}
	public String getMemoto() {
		return memoto;
	}
	public void setMemoto(String memoto) {
		this.memoto = memoto;
	}
	
}
