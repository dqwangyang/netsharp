package com.netsharp.communication.entity;

public enum InventoryType {
	
	mertirial(1,"原材料"),product(2,"产成品"),yihao(2,"易耗品");
	
	private Integer value;
	private String text;

	InventoryType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	public String getText() {
		return this.text;
	}

	public Integer getValue() {

		return this.value;
	}
}
