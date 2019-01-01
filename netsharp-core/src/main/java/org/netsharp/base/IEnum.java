package org.netsharp.base;

import org.codehaus.jackson.annotate.JsonValue;

public interface IEnum {
    
	String getText();
	
	@JsonValue
	Integer getValue();
	
//	IEnum getItem(int value);
}
