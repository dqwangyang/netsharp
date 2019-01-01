package org.netsharp.panda.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/*日期查询操作类型*/
public enum QueryDateOperation implements IEnum{
	
	EMPTY(0,"(清空)"),
	CUSTOMER(2,"自定义"), 
	TODAY(3,"今天"), 
	YESTODAY(4,"昨天"), 
	PASSED_TOW_DAY(5,"近两天"), 
	PASSED_THREE_DAY(6,"近三天"), 
	THIS_WEEKEND(7,"本周"), 
	PASSED_WEEKEND(8,"上周"), 
	THIS_MONTH(9,"本月"), 
	PASSED_MONTH(10,"上月"), 
	THIS_QUARTER(11,"本季度"), 
	PASSED_QUARTER(12,"上季度"), 
	THIS_THREE_MONTH(13,"近三月"), 
	THIS_YEAR(14,"今年"), 
	PASSED_YEAR(15,"去年"), 
	HALF_TOP_YEAR(16,"上半年"), 
	HALF_BOTTOM_YEAR(17,"下半年"), 
	QUARTER_1(18,"第一季度"), 
	QUARTER_2(19,"第二季度"), 
	QUARTER_3(20,"第三季度"), 
	QUARTER_4(21,"第四季度");
	private int value;
	private String text;
	QueryDateOperation(int value, String text) {
		this.value = value;
		this.text = text;
	}
    @JsonCreator  
    public static QueryDateOperation getItem(int value){
    	
        for(QueryDateOperation item : values()){

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
