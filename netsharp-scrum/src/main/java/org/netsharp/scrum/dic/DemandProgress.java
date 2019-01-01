package org.netsharp.scrum.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum DemandProgress implements IEnum{

	//1规划论证、2产品设计、3正在开发、4提交测试、5、上线完成。6、暂不处理
	
	PROGRESS_1(1,"规划论证"), PROGRESS_2(2,"产品设计"), PROGRESS_3(3,"正在开发"), PROGRESS_4(4,"提交测试"), PROGRESS_5(5,"上线完成"), PROGRESS_6(6,"正在开发"), PROGRESS_7(7,"暂不处理");
	
	private int value;
	private String text;

	DemandProgress(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static DemandProgress getItem(int value) {

		for (DemandProgress item : values()) {

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
