package org.netsharp.wx.pa.entity;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.entity.CatEntity;

public class WeixincatEntity extends CatEntity {
	private static final long serialVersionUID = 1L;

	@Column(name="public_account_id",header="公众号")
	private Integer publicAccountId;
    
    @Reference(foreignKey="publicAccountId")
    private PublicAccount publicAccount;

	public Integer getPublicAccountId() {
		return publicAccountId;
	}

	public void setPublicAccountId(Integer publicAccountId) {
		this.publicAccountId = publicAccountId;
	}

	public PublicAccount getPublicAccount() {
		return publicAccount;
	}

	public void setPublicAccount(PublicAccount publicAccount) {
		this.publicAccount = publicAccount;
		if(this.publicAccount==null){
			this.publicAccountId=null;
		}
		else{
			this.publicAccountId=this.publicAccount.getId();
		}
	}
}
