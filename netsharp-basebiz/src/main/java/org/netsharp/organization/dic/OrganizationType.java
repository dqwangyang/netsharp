package org.netsharp.organization.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**
 * @ClassName:OrganizationType
 * @Description:组织机构类型
 * @author:hanwei
 * @date:2017年8月16日 上午11:02:21
 */
public enum OrganizationType implements IEnum{

	SYSTEM(1, "集团"), 
	CORPORATION(2, "子公司"), 
	DEPARTMENT(3, "部门"), 
	PROJECT(4,"项目"), 
	CATETORY(5, "分类"), 
	POST(6, "岗位");
	
	private int value;
	private String text;

	OrganizationType(int value, String text) {
		this.value = value;
		this.text = text;
	}
	
    @JsonCreator  
    public static OrganizationType getItem(int value){
    	
        for(OrganizationType item : values()){

            if(item.getValue() == value){  
                return item;  
            }  
        }  
        return null;  
    } 

    @Override
	public String getText() {
		return this.text;
	}

	@Override
	public Integer getValue() {
		return this.value;
	}
}
