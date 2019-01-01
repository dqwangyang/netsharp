package org.netsharp.scrum.entity;

import java.util.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.entity.WorkEntity;
import org.netsharp.organization.entity.Employee;
import org.netsharp.scrum.dic.CSR;
import org.netsharp.scrum.dic.CapacityIndex;
import org.netsharp.scrum.dic.Importance;
import org.netsharp.scrum.dic.TaskProperties;
import org.netsharp.scrum.dic.Urgency;

public class TaskBase extends WorkEntity {
	
	private static final long serialVersionUID = 4017916060746615223L;
	
	@Column(name="owner_id")
	private Integer ownerId;
	@Reference(foreignKey="ownerId")
	private Employee owner;//项目负责人
	@Column(name="develop_time")
	private Date developTime;//开发提测时间
	
	@Column(name="testor_id")
	private Integer testorId;
	@Reference(foreignKey="testorId")
	private Employee testor;//测试负责人
	@Column(name="test_time")
	private Date testTime;//测试完成日期
	
	@Column(name="productor_id")
	private Integer productorId;
	@Reference(foreignKey="productorId")
	private Employee productor;//产品负责人
	@Column(name="productor_time")
	private Date productorTime;//产品提交时间

	@Column(name="start_time")
	private Date startTime;//起始日期
	@Column(name="end_time")
	private Date endTime;//结束日期
	@Column(name="estimate_hours")
	private Double estimateHours;//估时，以小时为单位
	@Column(name="actual_hours")
	private Double actualHours;//实际耗时，以小时为单位

	@Column(size=5000)
	private String content;//项目说明

	private Importance importance = Importance.general;         //重要性
	private Urgency urgency = Urgency.general;                  //紧急性
	
	@Column(name="task_properties")
	private TaskProperties taskProperties=TaskProperties.Work;  //工作性质
	@Column(name="capacity_index")
	private CapacityIndex capacityIndex=CapacityIndex.Two;      //能力指数
	private CSR csr;//=CSR.Three;                                //客户满意度
	
	public Integer getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}

	public Employee getOwner() {
		return owner;
	}

	public void setOwner(Employee owner) {
		this.owner = owner;
	}

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	

	public Double getEstimateHours() {
		return estimateHours;
	}

	public void setEstimateHours(Double estimateHours) {
		this.estimateHours = estimateHours;
	}

	public Importance getImportance() {
		return importance;
	}

	public void setImportance(Importance importance) {
		this.importance = importance;
	}

	public Urgency getUrgency() {
		return urgency;
	}

	public void setUrgency(Urgency urgency) {
		this.urgency = urgency;
	}

	public Double getActualHours() {
		return actualHours;
	}

	public void setActualHours(Double actualHours) {
		this.actualHours = actualHours;
	}

	public TaskProperties getTaskProperties() {
		return taskProperties;
	}

	public void setTaskProperties(TaskProperties taskProperties) {
		this.taskProperties = taskProperties;
	}

	public CapacityIndex getCapacityIndex() {
		return capacityIndex;
	}

	public void setCapacityIndex(CapacityIndex capacityIndex) {
		this.capacityIndex = capacityIndex;
	}

	public CSR getCsr() {
		return csr;
	}

	public void setCsr(CSR csr) {
		this.csr = csr;
	}

	public Integer getTestorId() {
		return testorId;
	}

	public void setTestorId(Integer testorId) {
		this.testorId = testorId;
	}

	public Employee getTestor() {
		return testor;
	}

	public void setTestor(Employee testor) {
		this.testor = testor;
	}

	public Integer getProductorId() {
		return productorId;
	}

	public void setProductorId(Integer productorId) {
		this.productorId = productorId;
	}

	public Employee getProductor() {
		return productor;
	}

	public void setProductor(Employee productor) {
		this.productor = productor;
	}

	public Date getDevelopTime() {
		return developTime;
	}

	public void setDevelopTime(Date developTime) {
		this.developTime = developTime;
	}

	public Date getTestTime() {
		return testTime;
	}

	public void setTestTime(Date testTime) {
		this.testTime = testTime;
	}

	public Date getProductorTime() {
		return productorTime;
	}

	public void setProductorTime(Date productorTime) {
		this.productorTime = productorTime;
	}
}
