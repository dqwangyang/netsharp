package com.netsharp.communication.tcp.entity;

public class OrderItem extends BaseEntity {

	private static final long serialVersionUID = -7031132174901654049L;
	
	private Product product = new Product();
	private Integer price=3;
	private Integer quantity=4;
	private Integer amount=12;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
}
