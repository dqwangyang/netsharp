package org.netsharp.session;

import java.io.Serializable;
import java.util.Date;

public class NetsharpSession implements Serializable {
	
	private static final long serialVersionUID = -8476075357474433333L;
	
	private String sessionId;           //sessionId
	private Integer userId;             // 
	private String userName;
	private String mobiles;
	private String userImg;
	private Date loginTime;                          // 登录时间
	private Date lastedTime;
	private Integer postId;                          // 主岗位，多个主岗位，或者无主岗位时取第一个岗位
	private String postName;                        // 岗位名称
	private String postIds;                          // 当前员工的岗位，多个值时以","分割
	private Integer departmentId;                    //岗位所属部门
	private String departmentIds;
	private String departmentName;
	private String departmentPathCode;
	private String[] departmentPathCodes;
	private Integer tenancyId;                       // saas租户id
	private String tenancyName;
	
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getDepartmentPathCode() {
		return departmentPathCode;
	}
	public void setDepartmentPathCode(String departmentPathCode) {
		this.departmentPathCode = departmentPathCode;
	}
	public Integer getTenancyId() {
		return tenancyId;
	}
	public void setTenancyId(Integer tenancyId) {
		this.tenancyId = tenancyId;
	}
	public String getTenancyName() {
		return tenancyName;
	}
	public void setTenancyName(String tenancyName) {
		this.tenancyName = tenancyName;
	}
	public String getDepartmentIds() {
		return departmentIds;
	}
	public void setDepartmentIds(String departmentIds) {
		this.departmentIds = departmentIds;
	}
	public String getMobiles() {
		return mobiles;
	}
	public void setMobiles(String mobiles) {
		this.mobiles = mobiles;
	}
	public String[] getDepartmentPathCodes() {
		return departmentPathCodes;
	}
	public void setDepartmentPathCodes(String[] departmentPathCodes) {
		this.departmentPathCodes = departmentPathCodes;
	}
	public String getUserImg() {
		return userImg;
	}
	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	public Integer getPostId() {
		return postId;
	}
	public void setPostId(Integer postId) {
		this.postId = postId;
	}
	public String getPostName() {
		return postName;
	}
	public void setPostName(String postName) {
		this.postName = postName;
	}
	public String getPostIds() {
		return postIds;
	}
	public void setPostIds(String postIds) {
		this.postIds = postIds;
	}
	public Date getLastedTime() {
		return lastedTime;
	}
	public void setLastedTime(Date lastedTime) {
		this.lastedTime = lastedTime;
	}	
}
