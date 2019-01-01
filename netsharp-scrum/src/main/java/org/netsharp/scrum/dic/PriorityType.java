package org.netsharp.scrum.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum PriorityType implements IEnum{

	P1(1,"高"), P2(2,"中"), P3(3,"低");

	private int value;
	private String text;

	PriorityType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static PriorityType getItem(int value) {

		for (PriorityType item : values()) {

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
