package org.netsharp.autoencoder.entity;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/*
 * 编码类型
 * by gaomeng
 * 
 */
public enum EncoderType implements IEnum{

	A(1, "固定文本"), B(2, "日期"), C(3, "序列");
	private int value;
	private String text;

	EncoderType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static EncoderType getItem(int value) {

		for (EncoderType item : values()) {

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