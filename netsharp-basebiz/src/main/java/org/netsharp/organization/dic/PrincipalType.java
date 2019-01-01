package org.netsharp.organization.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonValue;
import org.netsharp.base.IEnum;

/**
 * @ClassName:PrincipalType   
 * @Description:授权主体类型
 * @author:hanwei
 * @date:2017年8月16日 上午11:07:59
 */  
public enum PrincipalType implements IEnum{
	
	ORGANIZATION(1,"部门"), 
	EMPLOYEE(3,"员工"), 
	STATION(4,"职务"),
	POST(6,"岗位"),
	Role(7,"角色");
	
	private int value;
	private String text;

	PrincipalType(int value,String text) {
		this.value = value;
		this.text = text;
	}
    @JsonCreator  
    public static PrincipalType getItem(int value){
    	
        for(PrincipalType item : values()){

            if(item.getValue() == value){  
                return item;  
            }  
        }  
        return null;  
    } 
	@JsonValue
	@Override
	public Integer getValue() {
		return this.value;
	}
	public String getText() {
		return this.text;
	}
}
