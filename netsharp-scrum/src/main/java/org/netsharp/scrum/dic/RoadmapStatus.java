package org.netsharp.scrum.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum RoadmapStatus implements IEnum{
	
	finished(1,"已完成","green"), processing(2,"执行中","red"),shortPlaning(3,"近期计划","blue"), longPlaning(4,"长期规划","gray");

	private int value;
	private String text;
	private String color;

	private RoadmapStatus(int value,String text ,String color) {
		this.text = text;
		this.color=color;
	}

	public String getText() {
		return this.text;
	}
	
	public String getColor(){
		return this.color;
	}
	
	@JsonCreator
	public static RoadmapStatus getItem(int value) {

		for (RoadmapStatus item : values()) {

			if (item.getValue() == value) {
				return item;
			}
		}
		return null;
	}

	@Override
	public Integer getValue() {
		return this.value;
	}
}
