package org.netsharp.scrum.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum CaseStatus implements IEnum {

	hibernate(1, "未启动"), 
	process(2, "进行中"), 
	submitTest(3, "已完成"), 
	stop(4, "已中止");
	
	private int value;
	private String text;

	CaseStatus(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static CaseStatus getItem(int value) {

		for (CaseStatus item : values()) {

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