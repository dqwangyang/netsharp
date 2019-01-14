package org.netsharp.organization.entity;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Subs;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;
import org.netsharp.organization.dic.PrincipalType;

@Table(name = "sys_permission_authorization_principal", header = "授权主体")
public class AuthorizationPrincipal extends Entity {

	private static final long serialVersionUID = -5499224707229093628L;

	@Column(name="principal_type",header="主体类型")
	private PrincipalType principalType;
	
	@Column(name="principal_id",header="主体Id")
	private Long principalId;
	
	@Column(name="principal_name",header="主体名称")
	private String principalName;
	
	@Column(name = "disabled", header = "停用")
	private Boolean disabled = false;

	// 操作
	@Subs(subType = PrincipalOperation.class, foreignKey = "principalId", header = "操作")
	private List<PrincipalOperation> principalOperations;

	// 数据权限
	// @Subs(subType=RowGateway.class,foreignKey="principalId",referenceName="授权主体",subName="数据权限")
	// private List<RowGateway> rowGateways;
	
	public Long getPrincipalId() {
		return this.principalId;
	}

	public Boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}

	public AuthorizationPrincipal setPrincipalId(Long principalId) {
		this.principalId = principalId;
		return this;
	}

	public String getPrincipalName() {
		return this.principalName;
	}

	public AuthorizationPrincipal setPrincipalName(String principalName) {
		this.principalName = principalName;
		return this;
	}

	public List<PrincipalOperation> getPrincipalOperations() {
		if (this.principalOperations == null) {
			this.principalOperations = new ArrayList<PrincipalOperation>();
		}
		return this.principalOperations;
	}

	// public List<RowGateway> getRowGateways(){
	// if(this.rowGateways==null){
	// this.rowGateways=new ArrayList<RowGateway>();
	// }
	// return this.rowGateways;
	// }
	public PrincipalType getPrincipalType() {
		return principalType;
	}

	public void setPrincipalType(PrincipalType principalType) {
		this.principalType = principalType;
	}
}