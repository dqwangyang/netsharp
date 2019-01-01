package org.netsharp.organization.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**
 * @ClassName:OperationTypes   
 * @Description:操作类别
 * @author:hanwei
 * @date:2017年8月16日 上午11:01:14
 */  
public enum OperationTypes implements IEnum{
	operation(1, "操作"),
	view(2, "查看"),
	add(3, "新增"),
	update(4, "修改"),
	delete(5, "删除"),
	audit(6, "审核"),
	unaudit(7, "弃审"),
	print(8, "打印"),
	exportExcel(9, "导出"),
	importExcel(10, "导入"),
	attachment(11, "附件");
	
	OperationTypes(int value, String text) {
		this.value = value;
		this.text = text;
	}
    @JsonCreator  
    public static OperationTypes getItem(int value){
    	
        for(OperationTypes item : values()){

            if(item.getValue() == value){  
                return item;  
            }  
        }  
        return null;  
    } 
	private int value;
	private String text;
	
	@Override
	public Integer getValue() {
		return this.value;
	}

	@Override
	public String getText() {

		return this.text;
	}
}
