package org.netsharp.scrum.entity;

import org.netsharp.core.annotations.BizCode;
import org.netsharp.core.annotations.Table;
import org.netsharp.scrum.dic.ProductStatus;

@BizCode(bizType="PD")
@Table(name="scrum_product",header="产品")
public class Product extends TaskBase {

	private static final long serialVersionUID = 7049213423463588004L;
	
	//产品状态
	private ProductStatus status = ProductStatus.development;

	public ProductStatus getStatus() {
		return status;
	}

	public void setStatus(ProductStatus status) {
		this.status = status;
	}

}
