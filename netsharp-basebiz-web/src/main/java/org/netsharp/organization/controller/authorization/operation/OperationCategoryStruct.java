package org.netsharp.organization.controller.authorization.operation;

import org.netsharp.organization.entity.AuthorizationPrincipal;
import org.netsharp.resourcenode.entity.ResourceNode;

public class OperationCategoryStruct {
	
	private Boolean isAuth = false;
	private AuthorizationPrincipal principal;
    private ResourceNode resourceNode;
    
	public Boolean getIsAuth() {
		return isAuth;
	}
	public void setIsAuth(Boolean isAuth) {
		this.isAuth = isAuth;
	}
	public AuthorizationPrincipal getPrincipal() {
		return principal;
	}
	public void setPrincipal(AuthorizationPrincipal principal) {
		this.principal = principal;
	}
	public ResourceNode getResourceNode() {
		return resourceNode;
	}
	public void setResourceNode(ResourceNode resourceNode) {
		this.resourceNode = resourceNode;
	}
    
    
}
