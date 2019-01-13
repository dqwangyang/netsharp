package org.netsharp.scrum.entity;

import java.util.List;

import org.netsharp.core.annotations.BizCode;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Subs;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.BizEntity;
import org.netsharp.organization.entity.Employee;

@BizCode(bizType="VP")
@Table(name="scrum_viewpoint",header="观点")
public class Viewpoint extends BizEntity {

	private static final long serialVersionUID = 6488250423675953253L;
	
	@Column(name="owner_id")
    private Long ownerId;
    @Reference(foreignKey="ownerId",header="作者")
    private Employee owner;

    @Column(size=5000,header="观点内容")
    private String content;

    @Column(size=500,header="标签")
    private String labels;

    @Column(name="fans_display",size=1000,header="粉丝列表")
    private String fansDisplay;
    
    @Subs(foreignKey="viewpointId",subType=ViewpointFans.class,header="粉丝")
    private List<ViewpointFans> fans;

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public Employee getOwner() {
		return owner;
	}

	public void setOwner(Employee owner) {
		this.owner = owner;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getLabels() {
		return labels;
	}

	public void setLabels(String labels) {
		this.labels = labels;
	}

	public String getFansDisplay() {
		return fansDisplay;
	}

	public void setFansDisplay(String fansDisplay) {
		this.fansDisplay = fansDisplay;
	}

	public List<ViewpointFans> getFans() {
		return fans;
	}

	public void setFans(List<ViewpointFans> fans) {
		this.fans = fans;
	}
}
