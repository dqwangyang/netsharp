package com.netsharp.communication.entity;

import java.math.BigDecimal;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

@Table(name="test_warehouseBook")
public class WarehouseBook extends Entity {
	
	private static final long serialVersionUID = -6385560174017367865L;
	
	private BigDecimal quantity;
	private BigDecimal price;

	@Column(name="amount")
    private BigDecimal amount;

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

	
}
