package org.netsharp.scrum.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum Importance implements IEnum{
	
	important(1,"重要"), general(2,"一般"), notimportant(3,"不重要");

	private int value;
	private String text;

	Importance(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static Importance getItem(int value) {

		for (Importance item : values()) {

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
