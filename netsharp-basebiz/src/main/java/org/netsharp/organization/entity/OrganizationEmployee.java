package org.netsharp.organization.entity;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;
import org.netsharp.organization.dic.PostType;

//岗位用户
@Table(name="sys_permission_organization_employee",header="员工岗位")
public class OrganizationEmployee extends Entity  implements Serializable {

 
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -1621317309215627516L;

//	@Column(name = "mained",header="主岗位")
//	private boolean mained = false;

    @Reference(foreignKey="organizationId")
    private Organization organization;
    
    @Column(name="organization_id",header="组织机构")
    private Integer organizationId;

    @JsonIgnore
    @Reference(foreignKey="employeeId")
    private Employee employee;
    
    @Column(name="employee_id",header="员工Id")
    private Integer employeeId;
    
    @Column(name="post_type",header="岗位任职类型")
    private PostType postType = PostType.MAINTIME;

//    public boolean isMained() {
//		return mained;
//	}
//	public void setMained(boolean mained) {
//		this.mained = mained;
//	}
	public Integer getOrganizationId(){
        return this.organizationId;
    }
    public OrganizationEmployee setOrganizationId(Integer organizationId){
        this.organizationId=organizationId;
        return this;
    }
    public Integer getEmployeeId(){
        return this.employeeId;
    }
    public OrganizationEmployee setEmployeeId(Integer employeeId){
        this.employeeId=employeeId;
        return this;
    }

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public PostType getPostType() {
		return postType;
	}
	public void setPostType(PostType postType) {
		this.postType = postType;
	}
	
	
}