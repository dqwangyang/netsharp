package org.netsharp.organization.controller.authorization.operation;

import org.netsharp.organization.entity.AuthorizationPrincipal;
import org.netsharp.organization.entity.Operation;
import org.netsharp.organization.entity.PrincipalOperation;
import org.netsharp.resourcenode.entity.ResourceNode;

public class AuthorizationStruct {
	
	private boolean isAuth;                        // 当前授权主体是否有操作权限，前提是IsHasAuth=true
	private boolean isHasAuth;                     // 针对一个资源节点（TreeNode），否有当前操作
    private AuthorizationPrincipal ap;              // 授权主体
    private Operation operation;                    // 操作
    private PrincipalOperation principalOperation;  // 操作和授权主体中间表
    private ResourceNode resourceNode;              // 资源节点
    
	public boolean isAuth() {
		return isAuth;
	}
	public void setAuth(boolean isAuth) {
		this.isAuth = isAuth;
	}
	public boolean isHasAuth() {
		return isHasAuth;
	}
	public void setHasAuth(boolean isHasAuth) {
		this.isHasAuth = isHasAuth;
	}
	public AuthorizationPrincipal getAp() {
		return ap;
	}
	public void setAp(AuthorizationPrincipal ap) {
		this.ap = ap;
	}
	public Operation getOperation() {
		return operation;
	}
	public void setOperation(Operation operation) {
		this.operation = operation;
	}
	public PrincipalOperation getPrincipalOperation() {
		return principalOperation;
	}
	public void setPrincipalOperation(PrincipalOperation principalOperation) {
		this.principalOperation = principalOperation;
	}
	public ResourceNode getResourceNode() {
		return resourceNode;
	}
	public void setResourceNode(ResourceNode resourceNode) {
		this.resourceNode = resourceNode;
	}
}
