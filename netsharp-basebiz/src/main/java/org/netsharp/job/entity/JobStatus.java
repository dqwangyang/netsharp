package org.netsharp.job.entity;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;


/*服务类型*/
public enum JobStatus implements IEnum{
	
	NONE(0,"已完成"),
	NORMAL(1,"运行"),
	PAUSED(2,"暂停"),
	COMPLETE(3,"提交"),
	BLOCKED(4,"阻塞"),
	ERROR(5,"错误"),
	Stop(6,"未启动");

	private int value;
	private String text;

	JobStatus(int value, String text) {
		this.value = value;
		this.text = text;
	}
    @JsonCreator  
    public static JobStatus getItem(int value){
    	
        for(JobStatus item : values()){

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
