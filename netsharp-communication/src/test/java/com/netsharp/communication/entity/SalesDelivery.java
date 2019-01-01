package com.netsharp.communication.entity;

import java.math.BigDecimal;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.BizEntity;

@Table(name="test_salesDelivery")
public class SalesDelivery extends BizEntity {

	private static final long serialVersionUID = 2199519432876109341L;
	
	private BigDecimal quantity;
	private BigDecimal price;

	@Column(name="amount")
    private BigDecimal amount;
    
    @Column(name="saved_amount")
    private BigDecimal savedAmount;
    private Integer customerId;
    
	public BigDecimal getQuantity() {
		return quantity;
	}
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getSavedAmount() {
		return savedAmount;
	}
	public void setSavedAmount(BigDecimal savedAmount) {
		this.savedAmount = savedAmount;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
    
    
}
