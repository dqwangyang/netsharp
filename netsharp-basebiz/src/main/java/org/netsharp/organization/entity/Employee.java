package org.netsharp.organization.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Exclusive;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Subs;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.ArchiveEntity;
import org.netsharp.organization.dic.Education;
import org.netsharp.organization.dic.EmployeeType;
import org.netsharp.organization.dic.Gender;

//员工
@Table(name="sys_permission_employee",header="员工信息")
public class Employee extends ArchiveEntity {

	private static final long serialVersionUID = 653818769249220564L;

	@Column(name="email",header="电子邮件",sensitive = true)
    private String email;
    
	@Column(name="nationality",header="国籍")
    private String nationality;
    
	@Column(name="address",header="地址")
    private String address;
    
    @Column(name="mobile",header="移动电话",mobiles = true)
    private String mobile;
    
    @Column(name="qq",header="QQ")
    private String qq;
    
    @Column(name="weixin",header="微信号",sensitive = true)
    private String weixin;
    
    @Column(name="fans_id",header="fansId")
    private Long fansId;
    
    @Column(name="birthday",header="出生日期")
    private Date birthday;
    
    @Column(name="gender",header="性别")
    private Gender gender;
    
    @Column(name="native_place",header="籍贯")
    private String nativePlace;
    
    @Column(name="education",header="学历")
    private Education education;
    
    @Column(name="zip_code",header="邮政编码")
    private String zipCode;
    
    @Column(name="type",header="员工类型,默认为内部员工")
    private EmployeeType type = EmployeeType.INNER;
    
    @Column(name="bank_no",header="工资卡号")  
    private String bankNo;
    
    @Column(name="entry_date",header="入职日期")
    private Date entryDate;
    
    @Column(name="quit_date",header="离职日期")
    private Date quitDate;
    
    @Column(name="photo",header="照片",size=500)
    private String photo;
    
    @Column(name="post_id",header="主岗位")
    private Long postId;
    
    @Reference(foreignKey="postId",header="主岗位")
    private Organization post;
    
    @Column(name="department_id",header="主部门")
    private Long departmentId;
    
    @Reference(foreignKey="departmentId",header="主部门")
    private Organization department;

	@Column(name="login_name",header="登陆名称",sensitive = true)
	private String loginName;
	
	@Column(name="pwd",header="密码")
    private String pwd;
    
    @Column(name="authorization_principal_id",header="授权主体Id")
    private Long authorizationPrincipalId;
    
    @Column(name="last_login_time",header="最后登录时间")
    private Date lastLoginTime;
    
    @Column(name="login_num",header="登录次数")
    private int loginNum = 0;
    
//	@Column(name="ticket",header="ticket")
    @Exclusive
    private String ticket;
	
	@Column(name="ding_qr_code_url",header="钉钉二维码")
    private String dingQrCodeUrl;

    @Subs(subType=OrganizationEmployee.class,foreignKey="employeeId",header="岗位用户")
    private List<OrganizationEmployee> organizations;

    @Subs(subType=RoleEmployee.class,foreignKey="employeeId",header="角色用户")
    private List<RoleEmployee> roles;
    
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public Education getEducation() {
		return education;
	}
	public void setEducation(Education education) {
		this.education = education;
	}

    public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getAddress(){
        return this.address;
    }
    public Employee setAddress(String address){
        this.address=address;
        return this;
    }
    public String getMobile(){
        return this.mobile;
    }
    public Employee setMobile(String mobile){
        this.mobile=mobile;
        return this;
    }
    public String getEmail(){
        return this.email;
    }
    public Employee setEmail(String email){
        this.email=email;
        return this;
    }
    public Date getBirthday(){
        return this.birthday;
    }
    public Employee setBirthday(Date birthday){
        this.birthday=birthday;
        return this;
    }
    public String getNativePlace(){
        return this.nativePlace;
    }
    public Employee setNativePlace(String nativePlace){
        this.nativePlace=nativePlace;
        return this;
    }
    
    public List<OrganizationEmployee> getOrganizations(){
        if(this.organizations==null){
            this.organizations=new ArrayList<OrganizationEmployee>();
        }
        return this.organizations;
    }
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getWeixin() {
		return weixin;
	}
	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}
	public EmployeeType getType() {
		return type;
	}
	public void setType(EmployeeType type) {
		this.type = type;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getBankNo() {
		return bankNo;
	}
	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}
	
	public Date getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public Organization getDepartment() {
		return department;
	}
	public void setDepartment(Organization department) {
		this.department = department;
	}
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public Organization getPost() {
		return post;
	}
	public void setPost(Organization post) {
		this.post = post;
	}
	public Long getPostId() {
		return postId;
	}
	public void setPostId(Long postId) {
		this.postId = postId;
	}
	public Date getQuitDate() {
		return quitDate;
	}
	public void setQuitDate(Date quitDate) {
		this.quitDate = quitDate;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public Long getAuthorizationPrincipalId() {
		return authorizationPrincipalId;
	}
	public void setAuthorizationPrincipalId(Long authorizationPrincipalId) {
		this.authorizationPrincipalId = authorizationPrincipalId;
	}
	public void setOrganizations(List<OrganizationEmployee> organizations) {
		this.organizations = organizations;
	}
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public int getLoginNum() {
		return loginNum;
	}
	public void setLoginNum(int loginNum) {
		this.loginNum = loginNum;
	}
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public String getDingQrCodeUrl() {
		return dingQrCodeUrl;
	}
	public void setDingQrCodeUrl(String dingQrCodeUrl) {
		this.dingQrCodeUrl = dingQrCodeUrl;
	}
	public List<RoleEmployee> getRoles() {
		return roles;
	}
	public void setRoles(List<RoleEmployee> roles) {
		this.roles = roles;
	}
	public Long getFansId() {
		return fansId;
	}
	public void setFansId(Long fansId) {
		this.fansId = fansId;
	}
}