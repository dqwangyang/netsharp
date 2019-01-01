package org.netsharp.scrum.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum DemandUrgency implements IEnum{

	//1非常重要、2重要、3一般；
	
	URGENCY_1(1,"非常重要"), URGENCY_2(2,"重要"), URGENCY_3(3,"一般");
	
	private int value;
	private String text;

	DemandUrgency(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static DemandUrgency getItem(int value) {

		for (DemandUrgency item : values()) {

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
