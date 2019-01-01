package org.netsharp.scrum.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

//客户满意度CSR(Consumer satisfactional research)
public enum CSR implements IEnum{
	
	One(1,"完美"),Two(2,"比较满意"),Three(3,"基本满意"),Four(4,"不满意"),Five(5,"很生气");
	
	private int value;
	private String text;

	CSR(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static CSR getItem(int value) {

		for (CSR item : values()) {

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
