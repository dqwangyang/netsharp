package org.netsharp.dic;

public enum VoucherState {

	unaudit("未审"), audit("审核");

	private String text;

	VoucherState(String text) {
		this.text = text;
	}

	public String getText() {
		return this.text;
	}
}
