package org.netsharp.scrum.entity;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;
import org.netsharp.organization.entity.Employee;

@Table(name="scrum_viewpoint_fans",header="观点")
public class ViewpointFans extends Entity {
	
	private static final long serialVersionUID = -2515135818723981325L;

	@Column(name="fans_id")
	private Long fansId;
	
	@Reference(foreignKey="fansId")
	private Employee fans;
	
	@Column(name="is_read",header="粉丝")
	private Boolean isRead;
	
	@Column(size=1000,header="评论")
	private String comment;
	
	@Column(name="read_times",header="阅读次数",defaultValue="0")
	private Integer readTimes=0;
	
	@Column(name="viewpoint_id",header="观点")
	private Long viewpointId;
	
	public Long getFansId() {
		return fansId;
	}

	public void setFansId(Long fansId) {
		this.fansId = fansId;
	}

	public Employee getFans() {
		return fans;
	}

	public void setFans(Employee fans) {
		this.fans = fans;
	}

	public Boolean getIsRead() {
		return isRead;
	}

	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getReadTimes() {
		return readTimes;
	}

	public void setReadTimes(Integer readTimes) {
		this.readTimes = readTimes;
	}

	public Long getViewpointId() {
		return viewpointId;
	}

	public void setViewpointId(Long viewpointId) {
		this.viewpointId = viewpointId;
	}
}
