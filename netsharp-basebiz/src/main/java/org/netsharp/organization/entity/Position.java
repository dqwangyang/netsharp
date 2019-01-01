package org.netsharp.organization.entity;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.BizEntity;

@Table(name="sys_permission_position",header="职务管理")
public class Position extends BizEntity{

    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -419245045321554643L;

	@Reference(foreignKey="authorizationPrincipalId",header="授权主体")
	private AuthorizationPrincipal authorizationPrincipal;
    
    @Column(name="authorization_principal_id",header="授权主体")
    private Integer authorizationPrincipalId;

    public Integer getAuthorizationPrincipalId(){
        return this.authorizationPrincipalId;
    }
    public Position setAuthorizationPrincipalId(Integer authorizationPrincipalId){
        this.authorizationPrincipalId=authorizationPrincipalId;
        return this;
    }
	public AuthorizationPrincipal getAuthorizationPrincipal() {
		return authorizationPrincipal;
	}
	public void setAuthorizationPrincipal(AuthorizationPrincipal authorizationPrincipal) {
		this.authorizationPrincipal = authorizationPrincipal;
	}
}