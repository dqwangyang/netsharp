package org.netsharp.scrum.entity;

import org.netsharp.core.annotations.BizCode;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.BizEntity;

@BizCode(bizType = "CD")
@Table(name = "scrum_case_detail", header = "用例")
public class UseCaseDetail extends BizEntity {

	private static final long serialVersionUID = -3290456771379651911L;

	@Column(name = "case_id")
	private Integer caseId;
	
	@Column(header="相关者")
	private String actor;
	
	@Column(header="关注点",size=300)
	private String concern;
	
	@Column(name="pre_condition",header="前置条件",size=500)
	private String preCondition;
	
	@Column(name="post_condition",header="后置条件",size=500)
	private String postCondition;
	
	@Column(header="主执行步骤",size=2000)
	private String steps;
	
	@Column(header="业务规则",size=1000)
	private String roles;
	
	@Column(header="关键字段",size=500)
	private String fields;

	public Integer getCaseId() {
		return caseId;
	}

	public void setCaseId(Integer caseId) {
		this.caseId = caseId;
	}

	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	public String getConcern() {
		return concern;
	}

	public void setConcern(String concern) {
		this.concern = concern;
	}

	public String getPreCondition() {
		return preCondition;
	}

	public void setPreCondition(String preCondition) {
		this.preCondition = preCondition;
	}

	public String getPostCondition() {
		return postCondition;
	}

	public void setPostCondition(String postCondition) {
		this.postCondition = postCondition;
	}

	public String getSteps() {
		return steps;
	}

	public void setSteps(String steps) {
		this.steps = steps;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getFields() {
		return fields;
	}

	public void setFields(String fields) {
		this.fields = fields;
	}
	
	
}