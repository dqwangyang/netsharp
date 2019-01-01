package org.netsharp.scrum.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum DemandPriority implements IEnum{

	//1立刻修正、2下版考虑、3后续规划、4暂不考虑。
	
	PRIORITY_1(1,"立刻修正"), PRIORITY_2(2,"下版考虑"), PRIORITY_3(3,"后续规划"), PRIORITY_4(3,"暂不考虑");
	
	private int value;
	private String text;

	DemandPriority(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static DemandPriority getItem(int value) {

		for (DemandPriority item : values()) {

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
