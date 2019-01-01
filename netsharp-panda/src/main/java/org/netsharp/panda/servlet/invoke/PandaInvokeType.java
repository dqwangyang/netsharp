package org.netsharp.panda.servlet.invoke;

public enum PandaInvokeType {
	
	html(1,"html界面渲染"),rest(2,"rest service 调用"),rest_easyui(3,"rest easyui 调用"), reference(4,"下拉列表参照"), comboxtree(5,"下拉树参照"), enumbox(6,"下拉枚举参照"), nav(7,"页面跳转");
		
	private int value;
	private String text;

	PandaInvokeType(int value, String text) {
		this.setValue(value);
		this.setText(text);
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
