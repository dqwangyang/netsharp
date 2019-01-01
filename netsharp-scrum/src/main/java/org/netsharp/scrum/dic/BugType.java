package org.netsharp.scrum.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum BugType implements IEnum{

	BUG(0, "BUG"),IMPROVEMENT(1, "改进"), TASK(2, "任务"), NEW_FUNCTION(3, "新功能");

	private int value;
	private String text;

	BugType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static BugType getItem(int value) {

		for (BugType item : values()) {

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
