package org.netsharp.wx.pa.entity;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.entity.BizEntity;

public class WeixinEntity extends BizEntity  {
	
	private static final long serialVersionUID = 8982755935754160272L;

	@Column(name="public_account_id",header="公众号")
	private Long publicAccountId;
    
    @Reference(foreignKey="publicAccountId")
    private PublicAccount publicAccount;

	public PublicAccount getPublicAccount() {
		return publicAccount;
	}

	public void setPublicAccount(PublicAccount publicAccount) {
		this.publicAccount = publicAccount;
		if(this.publicAccount==null){
			this.setPublicAccountId(null);
		}
		else{
			this.setPublicAccountId(this.publicAccount.getId());
		}
	}

	public Long getPublicAccountId() {
		return publicAccountId;
	}

	public void setPublicAccountId(Long publicAccountId) {
		this.publicAccountId = publicAccountId;
	}
}
