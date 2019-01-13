package com.kaibo.core.entity;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.BizEntity;

@Table(name="kb_student",header="学生")
public class Student extends BizEntity {

	private static final long serialVersionUID = 3806402093870718430L;
	
	@Column(name="fans_id")
	private Long fansId;
	
	@Column(name="grade_class_id",header="班级")
	private Long gradeClassId;
	@Reference(foreignKey="gradeClassId",header="班级")
	private GradeClass gradeClass;
	
	public Long getFansId() {
		return fansId;
	}
	public void setFansId(Long fansId) {
		this.fansId = fansId;
	}
	public Long getGradeClassId() {
		return gradeClassId;
	}
	public void setGradeClassId(Long gradeClassId) {
		this.gradeClassId = gradeClassId;
	}
	public GradeClass getGradeClass() {
		return gradeClass;
	}
	public void setGradeClass(GradeClass gradeClass) {
		this.gradeClass = gradeClass;
		if(this.gradeClass==null) {
			this.gradeClassId=null;
		}else {
			this.gradeClassId = this.getGradeClass().getId();
		}
	}
	
	

}
