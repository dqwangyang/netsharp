package com.kaibo.poetry.entity;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.BizEntity;

import com.kaibo.core.entity.Student;

@Table(name="kb_poetry_poetry",header="诗")
public class Poetry extends BizEntity {

	private static final long serialVersionUID = -6218625684543044972L;
	
	private String author;
	@Column(name="audio_url",size=500,header="音频")
	private String audioUrl;
	@Column(name="content",size=1000,header="诗歌原文")
	private String content;
	@Column(name="image_url",size=500,header="配图")
	private String imageUrl;
	
	@Column(name="student_id",header="学生")
	private Long studentId;
	@Reference(foreignKey="studentId",header="学生")
	private Student student;
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getAudioUrl() {
		return audioUrl;
	}
	public void setAudioUrl(String audioUrl) {
		this.audioUrl = audioUrl;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public Long getStudentId() {
		return studentId;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
		if(this.student==null) {
			this.studentId = null;
		}else {
			this.studentId=this.student.getId();
		}
	}

}
