package org.netsharp.panda.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum AggregateType implements IEnum{

	NONE(0,"无"), 
	COUNT(1,"行数"), 
	SUM(2,"汇总"), 
	MIN(3,"最小值"), 
	MAX(4,"最大值"), 
	AVERAGE(5,"平均值");
	private int value;
	private String text;

	AggregateType(int value, String text) {
		this.value = value;
		this.text = text;
	}
    @JsonCreator  
    public static AggregateType getItem(int value){
    	
        for(AggregateType item : values()){

            if(item.getValue() == value){  
                return item;  
            }  
        }  
        return null;  
    } 
	@Override
	public Integer getValue() {
		return this.value;
	}
	public String getText() {
		return this.text;
	}
}
