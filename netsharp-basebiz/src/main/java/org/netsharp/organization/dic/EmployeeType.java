package org.netsharp.organization.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**
 * @ClassName:EmployeeType
 * @Description:员工类型，outer非公司员工，但是有时需要作为一个用户访问公司的系统
 * @author:hanwei
 * @date:2017年8月16日 上午10:59:57
 */
public enum EmployeeType implements IEnum {

	INNER(1, "内部"), OUTER(0, "外部");
	private int value;
	private String text;

	EmployeeType(int value, String text) {
		this.value = value;
		this.text = text;
	}
    @JsonCreator  
    public static EmployeeType getItem(int value){
    	
        for(EmployeeType item : values()){

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

	@Override
	public String getText() {
		return this.text;
	}
}
