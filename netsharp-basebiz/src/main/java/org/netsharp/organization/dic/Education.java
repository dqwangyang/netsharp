package org.netsharp.organization.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**
 * @ClassName:Education
 * @Description:学历
 * @author:hanwei
 * @date:2017年8月16日 上午11:00:12
 */
public enum Education implements IEnum{

	DAZHUAN(1, "大专"), 
	BENKE(2, "本科"), 
	SHUOSHI(3, "硕士"), 
	BOSHI(4, "博士"), 
	UNKNOWN(5, "未知");

	private int value;
	private String text;

	Education(int value, String text) {
		this.value = value;
		this.text = text;
	}
    @JsonCreator  
    public static Education getItem(int value){
    	
        for(Education item : values()){

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