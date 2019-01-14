package com.kaibo.core.entity;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.BizEntity;

@Table(name="kb_class",header="班级")
public class GradeClass extends BizEntity {

	private static final long serialVersionUID = -8487686646732129182L;
	
	@Column(name="full_name",header="全称")
	private String fullName;
	
	@Column(name="grade_id",header="年级")
	private Long gradeId;
	
	@Reference(foreignKey="gradeId",header="年级")
	private GradeClass grade;
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public Long getGradeId() {
		return gradeId;
	}
	public void setGradeId(Long gradeId) {
		this.gradeId = gradeId;
	}
	public GradeClass getGrade() {
		return grade;
	}
	public void setGrade(GradeClass grade) {
		this.grade = grade;
		if(this.grade==null) {
			this.gradeId=null;
		}else {
			this.gradeId = this.grade.getId();
		}
	}
}
