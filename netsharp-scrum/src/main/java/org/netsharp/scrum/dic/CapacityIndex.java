package org.netsharp.scrum.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

//能力指数
public enum CapacityIndex implements IEnum{

	One(1,"高"), Two(2,"中"), Three(3,"低");

	private int value;
	private String text;

	CapacityIndex(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static CapacityIndex getItem(int value) {

		for (CapacityIndex item : values()) {

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
