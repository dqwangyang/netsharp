package org.netsharp.scrum.entity;

import java.util.Date;

import org.netsharp.core.annotations.BizCode;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.WorkEntity;
import org.netsharp.organization.entity.Employee;
import org.netsharp.scrum.dic.DemandPriority;
import org.netsharp.scrum.dic.DemandProgress;
import org.netsharp.scrum.dic.DemandType;
import org.netsharp.scrum.dic.DemandUrgency;

@BizCode(bizType="SPD_")
@Table(name="scrum_product_demand",header="产品需求")
public class ProductDemand extends WorkEntity{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -8913495294028639638L;
	
	@Column(name="product_id")
	private Integer productId;
	
	@Reference(foreignKey="productId")
	private Product product;//产品
	
	@Column(name="version_number")
	private String versionNumber;
	
	@Column(size=2000)
	private String content;//需求描述
	
	@Column(name="putor_id")
	private Integer putorId;
	
	@Reference(foreignKey="putorId")
	private Employee putor;//提出人
	
	@Column(name="owner_id")
	private Integer ownerId;	
	
	@Reference(foreignKey="ownerId")
	private Employee owner;//处理人
	
	@Column(name="sender_id")
	private Integer senderId;
	
	@Reference(foreignKey="senderId")
	private Employee sender;//抄送人
	
	private DemandType type ;//类别
	
	private DemandUrgency urgency;//等级
	
	private DemandPriority priority;//优先级
	
	private DemandProgress progress;//进度
	
	@Column(name="online_date",header="上线日期")
	private Date onlineDate;
	
	
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
	}

	public Integer getPutorId() {
		return putorId;
	}

	public void setPutorId(Integer putorId) {
		this.putorId = putorId;
	}

	public Employee getPutor() {
		return putor;
	}

	public void setPutor(Employee putor) {
		this.putor = putor;
	}

	public Integer getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}

	public Employee getOwner() {
		return owner;
	}

	public void setOwner(Employee owner) {
		this.owner = owner;
	}

	public Integer getSenderId() {
		return senderId;
	}

	public void setSenderId(Integer senderId) {
		this.senderId = senderId;
	}

	public Employee getSender() {
		return sender;
	}

	public void setSender(Employee sender) {
		this.sender = sender;
	}

	public String getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(String versionNumber) {
		this.versionNumber = versionNumber;
	}

	public DemandType getType() {
		return type;
	}

	public void setType(DemandType type) {
		this.type = type;
	}

	public DemandUrgency getUrgency() {
		return urgency;
	}

	public void setUrgency(DemandUrgency urgency) {
		this.urgency = urgency;
	}

	public DemandPriority getPriority() {
		return priority;
	}

	public void setPriority(DemandPriority priority) {
		this.priority = priority;
	}

	public DemandProgress getProgress() {
		return progress;
	}

	public void setProgress(DemandProgress progress) {
		this.progress = progress;
	}

	public Date getOnlineDate() {
		return onlineDate;
	}

	public void setOnlineDate(Date onlineDate) {
		this.onlineDate = onlineDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
