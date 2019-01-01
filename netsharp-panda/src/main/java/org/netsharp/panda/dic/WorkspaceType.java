package org.netsharp.panda.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum WorkspaceType implements IEnum {

	GENERAL(1, "普通"),WORKBENCH(2, "工作台"), HOME(3, "首页"),LOGIN(4, "登录页") ;
	private int value;
	private String text;

	WorkspaceType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static WorkspaceType getItem(int value) {

		for (WorkspaceType item : values()) {

			if (item.getValue() == value) {
				return item;
			}
		}
		return null;
	}

	public Integer getValue() {
		return value;
	}

	public String getText() {
		return this.text;
	}
}
