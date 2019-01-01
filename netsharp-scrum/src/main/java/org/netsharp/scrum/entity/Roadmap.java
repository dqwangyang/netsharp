package org.netsharp.scrum.entity;

import java.util.List;

import org.netsharp.core.annotations.BizCode;
import org.netsharp.core.annotations.Subs;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.BizEntity;

@Table(name="scrum_roadmap",header="计划路线图")
@BizCode
public class Roadmap extends BizEntity {

	private static final long serialVersionUID = 7033854902076129797L;
	
	@Subs(foreignKey="roadmapId",subType=RoadmapDetail.class)
    private List<RoadmapDetail> details;

	public List<RoadmapDetail> getDetails() {
		return details;
	}

	public void setDetails(List<RoadmapDetail> details) {
		this.details = details;
	}
}
