package com.netsharp.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Subs;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.BizEntity;

@Table(name="test_salesOrder")
public class SalesOrder extends BizEntity {
	
	private static final long serialVersionUID = -610816080091358636L;
	
	private BigDecimal quantity;
	private BigDecimal price;

	@Column(name="amount")
    private BigDecimal amount;
    
    @Column(name="saved_amount")
    private BigDecimal savedAmount;
    private Long customerId;
    @Reference(foreignKey="customerId")
    private Customer customer;
    
    @Subs(subType=SalesOrderDetail.class,header="销售订单明细", foreignKey="orderId")
    private List<SalesOrderDetail> details;

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public List<SalesOrderDetail> getDetails() {
		if(details==null){
			details=new ArrayList<SalesOrderDetail>();
		}
		return details;
	}

	public void setDetails(List<SalesOrderDetail> details) {
		this.details = details;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
		
		if(this.customer==null){
			this.setCustomerId(0L);
		}
		else{
			this.setCustomerId(customer.getId());
		}
	}

	public BigDecimal getSavedAmount() {
		return savedAmount;
	}

	public void setSavedAmount(BigDecimal savedAmount) {
		this.savedAmount = savedAmount;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

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
}
