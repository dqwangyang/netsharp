package org.netsharp.scrum.entity;

import org.netsharp.core.annotations.BizCode;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.scrum.dic.ProjectStatus;

@BizCode(bizType="PJ")
@Table(name="scrum_project",header="项目")
public class Project extends TaskBase {

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -6895550606402283471L;
	@Column(name="product_id")
	private Integer productId;
	@Reference(foreignKey="productId")
	private Product product;
		
	@Column(name="deploy_id")
	private Integer deployId;
	@Reference(foreignKey="deployId")
	private Deploy deploy;
	
	private ProjectStatus status = ProjectStatus.hibernate;
	

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
		
		if(this.product==null){
			this.productId=null;
		}else{
			this.productId = this.product.getId();
		}
	}

	public ProjectStatus getStatus() {
		return status;
	}

	public void setStatus(ProjectStatus status) {
		this.status = status;
	}

	public Integer getDeployId() {
		return deployId;
	}

	public void setDeployId(Integer deployId) {
		this.deployId = deployId;
	}

	public Deploy getDeploy() {
		return deploy;
	}

	public void setDeploy(Deploy deploy) {
		this.deploy = deploy;
	}
}
