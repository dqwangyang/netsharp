package org.netsharp.scrum.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum Urgency implements IEnum{
	
	urgent(1,"紧急"), general(2,"一般"), noturgent(3,"不紧急");
	
	private int value;
	private String text;

	Urgency(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static Urgency getItem(int value) {

		for (Urgency item : values()) {

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
