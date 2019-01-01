package org.netsharp.scrum.entity;

import java.util.Date;

import org.netsharp.core.annotations.BizCode;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.BizEntity;
import org.netsharp.organization.entity.Employee;

@BizCode(bizType="DP")
@Table(name="scrum_deploy_plan",header="部署计划")
public class Deploy extends BizEntity{
	
	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 1906006627768981398L;

	@Column(name="deploy_time")
	private Date deployTime;   //正式环境部署时间
	
	@Reference(foreignKey="deployerId")
	private Employee deployer; //技术负责人
	@Reference(foreignKey="testorId")
	private Employee testor;   //测试负责人
	
	@Column(name="deployer_id")
	private Integer deployerId;
	@Column(name="testor_id")
	private Integer testorId;
	
	private String director;//部门经理签字
	@Column(name="department_director")
	private String departmentDirector;//部门总监签字
	@Column(name="test_director")
	private String testDirector;//质量签字
	
	@Column(size=5000)
	private String content;//

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

	public Integer getDeployerId() {
		return deployerId;
	}

	public void setDeployerId(Integer deployerId) {
		this.deployerId = deployerId;
	}

	public Integer getTestorId() {
		return testorId;
	}

	public void setTestorId(Integer testorId) {
		this.testorId = testorId;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getDepartmentDirector() {
		return departmentDirector;
	}

	public void setDepartmentDirector(String departmentDirector) {
		this.departmentDirector = departmentDirector;
	}

	public String getTestDirector() {
		return testDirector;
	}

	public void setTestDirector(String testDirector) {
		this.testDirector = testDirector;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
