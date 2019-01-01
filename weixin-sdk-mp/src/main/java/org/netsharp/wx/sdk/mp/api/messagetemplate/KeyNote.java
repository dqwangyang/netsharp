package org.netsharp.wx.sdk.mp.api.messagetemplate;

public class KeyNote {
	
	private String value;
	private String color = "#173177";
	
	public KeyNote(){}
	
	public KeyNote(String value){
		this.value = value;
	}
	
	public KeyNote(String value,String color){
		this.value=value;
		this.color=color;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}