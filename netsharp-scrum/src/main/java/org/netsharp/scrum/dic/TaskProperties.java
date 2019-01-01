package org.netsharp.scrum.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum TaskProperties implements IEnum{
	
	Work(1,"日常工作"),Innovation(2,"开拓性工作"),Rescue(3,"救援工作");
	
	private int value;
	private String text;

	TaskProperties(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static TaskProperties getItem(int value) {

		for (TaskProperties item : values()) {

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
