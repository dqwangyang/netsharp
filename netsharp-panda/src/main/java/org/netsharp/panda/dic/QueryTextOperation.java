package org.netsharp.panda.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/*文本查询操作类型*/
public enum QueryTextOperation implements IEnum{

	CONTAINS(0,"包含"), 
	EQUAL(1,"等于"), 
	STARTWITH(2,"起始于"), 
	ENDWIDTH(3,"结束于"), 
	NOCONTAINS(4,"不包含"), 
	NOEQUAL(5,"不等于"), 
	NOSTARTWITH(6,"不起始于"), 
	NOENDWIDTH(7,"不结束于");
	private int value;
	private String text;
	
	QueryTextOperation(int value, String text) {
		this.value = value;
		this.text = text;
	}
    @JsonCreator  
    public static QueryTextOperation getItem(int value){
    	
        for(QueryTextOperation item : values()){

            if(item.getValue() == value){  
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
