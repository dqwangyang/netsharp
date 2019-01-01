package org.netsharp.panda.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum PartType implements IEnum{
	FORM_PART(1,1, "表单"), 
	REPORT_OR_CHART(2,2, "报表.图表"), 
	DATAGRID_PART(3,3, "主列表"), 
	REFERENCE_FORM_PART(4,4, "引用表单"), 
	TREE_PART(5,5, "树"), 
	QUERY_SOLUTION(6,6, "查询方案"), 
	DETAIL_PART(7,7, "明细列表"), 
	TREEGRID_PART(8,8, "树列表"), 
	TREEGRID_REPORT_PART(9,9, "列表报表"), 
	SELECT_VOUCHER_PART(10,10, "选单界面主列表"), 
	SELECT_VOUCHER_DETAIL_PART(11,11, "选单界面明细列表"),
	OPTION_FORM_PART(12,12, "选项设置表单"),
	FORM_DETAIL_PART(13,13, "表单详情"),
	CUSTOM(14,14, "自定义");
	private int value;
	private Integer id;
	private String text;

	PartType(int value,Integer id, String text) {
		this.value = value;
		this.id = id;
		this.text = text;
	}
    @JsonCreator  
    public static PartType getItem(int value){
    	
        for(PartType item : values()){

            if(item.getValue() == value){  
                return item;  
            }  
        }  
        return null;  
    } 
	public Integer getId() {
		return this.id;
	}

	@Override
	public Integer getValue() {
		return this.value;
	}

	public String getText() {
		return this.text;
	}
}
