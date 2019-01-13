package com.netsharp.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.netsharp.core.annotations.Auto;
import org.netsharp.core.annotations.Id;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Persistable;

@Table(name="test_sales_order_detail")
public class SalesOrderDetail extends Persistable {
	
	private static final long serialVersionUID = 3240591439050873381L;
	@Auto
	@Id
    private int id;
    private BigDecimal price;
    private BigDecimal quantity;
    private BigDecimal amount;
    private Date createTime;
    private Date updateTime;
    private Long orderId;
    private Long inventoryId;
    @Reference(foreignKey="inventoryId")
    private Inventory inventory;
    
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getQuantity() {
		return quantity;
	}
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Long getIdOrder() {
		return orderId;
	}
	public void setIdOrder(Long idOrder) {
		this.orderId = idOrder;
	}

	public Inventory getInventory() {
		return inventory;
	}
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
		if(this.inventory==null){
			this.setInventoryId(-1L);
		}
		else{
			this.setInventoryId(inventory.getId());
		}
	}
	public Long getInventoryId() {
		return inventoryId;
	}
	public void setInventoryId(Long inventoryId) {
		this.inventoryId = inventoryId;
	}
}
