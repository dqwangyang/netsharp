package org.netsharp.scrum.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum DemandType implements IEnum{

	//严重缺陷、功能缺陷、体验优化、新需求；
	TYPE_1(1,"严重缺陷"), TYPE_2(2,"功能缺陷"), TYPE_3(3,"体验优化"), TYPE_4(4,"新需求");
	
	private int value;
	private String text;

	DemandType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static DemandType getItem(int value) {

		for (DemandType item : values()) {

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
