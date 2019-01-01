package org.netsharp.panda.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**   
 * @ClassName:  IntelligentMode   
 * @Description:TODO 查询条件匹配模式
 * @author: 韩伟
 * @date:   2018年1月5日 下午12:56:27   
 *     
 * @Copyright: 2018 www.yikuaxiu.com Inc. All rights reserved. 
 */
public enum IntelligentMode implements IEnum{
	
	EQUALS(0,"%s='%s'","严格匹配"), 
	LEFT(1," %s like '%s%%'","左匹配"), 
	RIGHT(2," %s like '%%%s'","右匹配"), 
	LIKE(3," %s like '%%%s%%'","模糊匹配"),
	GTR(4,"%s >'%s'","大于"),
	GTE(5,"%s>='%s'","大于等于"),
	LESS(6,"%s<'%s'","小于"),
	LE(7,"%s<='%s'","小于等于"),
	IN(8," %s in (%s)","IN");
	private int value;
	private String expression;
	private String text;

	IntelligentMode(int value,String expression, String text) {
		this.value = value;
		this.text = text;
		this.expression = expression;
	}
	
    @JsonCreator  
    public static IntelligentMode getItem(int value){
    	
        for(IntelligentMode item : values()){

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
	
	public String getExpression() {
		return this.expression;
	}
}
