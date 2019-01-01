package com.netsharp.entity;

public enum Color {
	
	red(1, "红色"), blue(2, "蓝色"), yellow(3, "黄色"), white(4, "白色"), blank(5, "黑色");

	private Integer value;
	private String text;

	Color(int value, String text) {
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
