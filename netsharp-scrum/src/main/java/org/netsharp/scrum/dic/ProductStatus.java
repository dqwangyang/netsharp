package org.netsharp.scrum.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum ProductStatus implements IEnum{
	
	feasibilityAnalysis(1,"可行性分析"), development(2,"开发中"), operation(3,"运营中"), stop(4,"已暂停"),delete(4,"删除");

	private int value;
	private String text;

	ProductStatus(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static ProductStatus getItem(int value) {

		for (ProductStatus item : values()) {

			if (item.getValue() == value) {
				return item;
			}
		}
		return null;
	}

	public String getText() {
		return this.text;
	}

	@Override
	public Integer getValue() {

		return this.value;
	}
}
