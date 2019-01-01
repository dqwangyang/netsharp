package org.netsharp.scrum.entity;

import java.util.Date;

import org.netsharp.core.annotations.BizCode;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.BizEntity;

@BizCode(bizType="IT")
@Table(name="scrum_iteration",header="迭代")
public class Iteration extends BizEntity {
	
	private static final long serialVersionUID = 1L;
	
	@Column(name="start_time")
	private Date startTime;//起始日期
	@Column(name="end_time")
	private Date endTime;//结束日期
	@Column(name="is_current")
	private Boolean isCurrent = false;//当前迭代
	@Column(size=5000)
	private String content;//迭代目标

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Boolean getIsCurrent() {
		return isCurrent;
	}

	public void setIsCurrent(Boolean isCurrent) {
		this.isCurrent = isCurrent;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
}
