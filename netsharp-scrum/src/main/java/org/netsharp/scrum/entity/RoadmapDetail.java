package org.netsharp.scrum.entity;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;
import org.netsharp.scrum.dic.RoadmapStatus;

@Table(name="scrum_roadmap_detail",header="计划路线图明细")
public class RoadmapDetail extends Entity {
	
	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 7249021290872244777L;

	@Column(name="roadmap_id",header="计划路线图")
	private Integer roadmapId;
	
	@JsonBackReference
	@Reference(foreignKey="roadmapId")
	private Roadmap roadmap;
	
	private Integer seq;
	private String name;
	@Column(name="memoto",size=1000,header="备注")
	private String memoto;
	private RoadmapStatus status = RoadmapStatus.longPlaning;
	private String date;
	public Integer getRoadmapId() {
		return roadmapId;
	}
	public void setRoadmapId(Integer roadmapId) {
		this.roadmapId = roadmapId;
	}
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMemoto() {
		return memoto;
	}
	public void setMemoto(String memoto) {
		this.memoto = memoto;
	}
	public RoadmapStatus getStatus() {
		return status;
	}
	public void setStatus(RoadmapStatus status) {
		this.status = status;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Roadmap getRoadmap() {
		return roadmap;
	}
	
	public void setRoadmap(Roadmap roadmap) {
		this.roadmap = roadmap;
		
		if(this.roadmap==null){
			this.roadmapId=null;
		}else{
			this.roadmapId = this.roadmap.getId();
		}
	}
}
