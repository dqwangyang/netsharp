package org.netsharp.scrum.entity;

import java.util.Date;

import org.netsharp.core.annotations.BizCode;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.BizEntity;
import org.netsharp.organization.entity.Employee;

@BizCode(bizType="AP")
@Table(name="scrum_activity_plan",header="活动计划")
public class ActivityPlan extends BizEntity{
	
	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 1520709229671276364L;
	@Column(name="activity_time",header="活动上线时间")
	private Date activityTime;
	@Column(name="deploy_time",header="正式环境部署时间")
	private Date deployTime;
	
	@Reference(foreignKey="deployerId")
	private Employee deployer; //技术负责人
	@Reference(foreignKey="testorId")
	private Employee testor;   //测试负责人
	
	@Reference(foreignKey="productorId")
	private Employee productor;   //产品负责人
	
	@Reference(foreignKey="bizmanId",header="业务负责人")
	private Employee bizman;   //业务负责人
	
	@Column(name="deployer_id")
	private Long deployerId;
	@Column(name="testor_id")
	private Long testorId;
	@Column(name="productor_id")
	private Long productorId;
	@Column(name="bizman_id")
	private Long bizmanId;
	
	@Column(size=5000)
	private String content;//

	public Date getActivityTime() {
		return activityTime;
	}

	public void setActivityTime(Date activityTime) {
		this.activityTime = activityTime;
	}

	public Date getDeployTime() {
		return deployTime;
	}

	public void setDeployTime(Date deployTime) {
		this.deployTime = deployTime;
	}

	public Employee getDeployer() {
		return deployer;
	}

	public void setDeployer(Employee deployer) {
		this.deployer = deployer;
		
		if(this.deployer==null){
			this.deployerId=null;
		}
		else{
			this.deployerId=this.deployer.getId();
		}
	}

	public Employee getTestor() {
		return testor;
	}

	public void setTestor(Employee testor) {
		this.testor = testor;
		
		if(this.testor==null){
			this.testorId=null;
		}
		else{
			this.testorId=this.testor.getId();
		}
	}

	public Employee getProductor() {
		return productor;
	}

	public void setProductor(Employee productor) {
		this.productor = productor;
		
		if(this.productor==null){
			this.productorId=null;
		}
		else{
			this.productorId=this.productor.getId();
		}
	}

	public Employee getBizman() {
		return bizman;
	}

	public void setBizman(Employee bizman) {
		this.bizman = bizman;
		
		if(this.bizman==null){
			this.bizmanId=null;
		}
		else{
			this.bizmanId=this.bizman.getId();
		}
	}

	public Long getDeployerId() {
		return deployerId;
	}

	public void setDeployerId(Long deployerId) {
		this.deployerId = deployerId;
	}

	public Long getTestorId() {
		return testorId;
	}

	public void setTestorId(Long testorId) {
		this.testorId = testorId;
	}

	public Long getProductorId() {
		return productorId;
	}

	public void setProductorId(Long productorId) {
		this.productorId = productorId;
	}

	public Long getBizmanId() {
		return bizmanId;
	}

	public void setBizmanId(Long bizmanId) {
		this.bizmanId = bizmanId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
