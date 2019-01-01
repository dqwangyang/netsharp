package org.netsharp.panda.controls.query;

import org.netsharp.panda.controls.input.DateBox;
import org.netsharp.panda.controls.input.Select;
import org.netsharp.panda.controls.input.SelectOption;
import org.netsharp.panda.controls.other.Label;
import org.netsharp.panda.controls.table.TD;
import org.netsharp.panda.controls.table.TR;
import org.netsharp.panda.controls.table.Table;
import org.netsharp.panda.entity.PQueryItem;

public class DateBoxQueryControl extends Table {
	
	private PQueryItem queryItem;

	private boolean interval = true;
    
	private boolean shortcut = false;
    
	private String operation;
    
	private boolean required = false;
    
	
	public PQueryItem getQueryItem() {
		return queryItem;
	}

	public void setQueryItem(PQueryItem queryItem) {
		this.queryItem = queryItem;
	}

	public boolean isInterval() {
		return interval;
	}

	public void setInterval(boolean interval) {
		this.interval = interval;
	}

	public boolean isShortcut() {
		return shortcut;
	}

	public void setShortcut(boolean shortcut) {
		this.shortcut = shortcut;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	@Override
	public void initialize() {

		this.id = queryItem.getPropertyName();
		this.required = queryItem.isRequired();
		this.interval = queryItem.isInterzone();
		this.operation = queryItem.getOperation();
		this.shortcut = queryItem.isShortcut();
		if(this.interval){
			
			this.initializeControls();
		}else{
			
			this.initializeIntervalControls();
		}

		super.initialize();
	}
	
	private void initializeControls(){
		
		TR tr = new TR();
		this.getControls().add(tr);
		TD td = new TD();{

			tr.getControls().add(td);
//			Select shortcut = this.GetOperatorControls();
//			td.getControls().add(shortcut);
		}

		td = new TD();{
			td.setStyle("width:120px;");
			DateBox dataBox = new DateBox();{
				dataBox.collected = false;
				dataBox.required = this.required;
				dataBox.controlType = "DateBoxQueryItem";
				dataBox.setStyle("width:110px;") ;
				dataBox.getInnerValues().put("query", "1");
				dataBox.setId("Start_"+ queryItem.getPropertyName().replaceAll("\\.", "_"));
				dataBox.getInnerValues().put("propertyName", queryItem.getPropertyName());
				dataBox.getInnerValues().put("interval","false");
			}
			td.getControls().add(dataBox);
			tr.getControls().add(td);
		}
	}
//    
//	private Select GetOperatorControls(){
//    	
//		Select shortcut = new Select();{
//
//			shortcut.setStyle("width:80px;") ;
//			shortcut.disabled = this.shortcut;
//			shortcut.editable = false;
//			if(!StringManager.isNullOrEmpty(operation)){
//				
//				shortcut.value = operation;
//			}
//			shortcut.setId("shortcut_"+ queryItem.getPropertyName().replaceAll("\\.", "_"));
//		}
//
//		//小于，小于等于，大于，大于等于，等于
//		SelectOption option = null;
//
//		option = new SelectOption();
//		{
//			option.optionValue = ">=";
//			option.value = "大于等于";
//		}
//		shortcut.getControls().add(option);
//
//		option = new SelectOption();
//		{
//			option.optionValue = ">";
//			option.value = "大于";
//		}
//		shortcut.getControls().add(option);
//
//		option = new SelectOption();
//		{
//			option.optionValue = "=";
//			option.value = "等于";
//		}
//		shortcut.getControls().add(option);
//		
//		option = new SelectOption();
//		{
//			option.optionValue = "<>";
//			option.value = "不等于";
//		}
//		shortcut.getControls().add(option);
//
//		option = new SelectOption();
//		{
//			option.optionValue = "<=";
//			option.value = "小于等于";
//		}
//		shortcut.getControls().add(option);
//
//		option = new SelectOption();
//		{
//			option.optionValue = "<";
//			option.value = "小于";
//		}
//		shortcut.getControls().add(option);
//
//		return shortcut;
//    }
	
	private void initializeIntervalControls(){
		
		DateBox start = new DateBox();{
			start.collected = false;
			start.required = this.required;
			start.controlType = "DateBoxQueryItem";
			start.setStyle("width:92px;") ;
			start.getInnerValues().put("query", "1");
			start.setId("Start_"+ queryItem.getPropertyName().replaceAll("\\.", "_"));
			start.getInnerValues().put("propertyName", queryItem.getPropertyName());
			start.getInnerValues().put("interval","true");
		}

		DateBox end = new DateBox();{
			end.collected = false;
			end.required = this.required;
			end.controlType = "DateBoxQueryItem";
			end.setStyle( "width:92px;");
			end.getInnerValues().put("query", "1");
			end.setId("End_" + queryItem.getPropertyName().replaceAll("\\.", "_"));
			end.getInnerValues().put("propertyName", queryItem.getPropertyName());
		}

		TR tr = new TR();
		this.getControls().add(tr);

		TD td = new TD();{
			td.setStyle("width:90px;") ;
			td.getControls().add(start);
			tr.getControls().add(td);
		}

		td = new TD();{
			Label lbl = new Label("-");
			lbl.setStyle("margin: 0 2px;");
			td.getControls().add(lbl);
			tr.getControls().add(td);
		}

		td = new TD();{
			
			td.setStyle("width:90px;") ;
			tr.getControls().add(td);
			td.getControls().add(end);
		}


		// 如果不隐藏快捷键
		if (queryItem.isShortcut()) {

			td = new TD();
			td.setStyle("width:90px;") ;
			tr.getControls().add(td);
			Select shortcut = this.GetShortcutControls();
			td.getControls().add(shortcut);
		}

	}

    
	/**
	 * @Definition: 获取快捷键控件
	 * @author: hanwei
	 * @Created date: 2015-4-28
	 * @return
	 */
	private Select GetShortcutControls() {

		Select shortcut = new Select();
		shortcut.editable = false;
		shortcut.setId("shortcut_" + queryItem.getPropertyName().replaceAll("\\.", "_"));;

		String startControlId = "Start_"
				+ queryItem.getPropertyName().replaceAll("\\.", "_");
		String endControlId = "End_"
				+ queryItem.getPropertyName().replaceAll("\\.", "_");

		shortcut.onChange = "function(newValue, oldValue){System.dateTimeShortcutChange(newValue, oldValue,'"
				+ startControlId + "','" + endControlId + "');}";

		SelectOption option = new SelectOption();
		{
			option.optionValue = "clear";
			option.value = "清空";
		}
		shortcut.getControls().add(option);

		option = new SelectOption();
		{
			option.optionValue = "custom";
			option.value = "自定义";
		}
		shortcut.getControls().add(option);

		option = new SelectOption();
		{
			option.optionValue = "today";
			option.value = "今天";
		}
		shortcut.getControls().add(option);

		option = new SelectOption();
		{
			option.optionValue = "yesterday";
			option.value = "昨天";
		}
		shortcut.getControls().add(option);

		option = new SelectOption();
		{
			option.optionValue = "nearlyTwoDays";
			option.value = "近两天";
		}
		shortcut.getControls().add(option);

		option = new SelectOption();
		{
			option.optionValue = "nearlyThreeDays";
			option.value = "近三天";
		}
		shortcut.getControls().add(option);

		option = new SelectOption();
		{
			option.optionValue = "thisWeek";
			option.value = "本周";
		}
		shortcut.getControls().add(option);

		option = new SelectOption();
		{
			option.optionValue = "lastWeek";
			option.value = "上周";
		}
		shortcut.getControls().add(option);

		option = new SelectOption();
		{
			option.optionValue = "thisMonth";
			option.value = "本月";
		}
		shortcut.getControls().add(option);

		option = new SelectOption();
		{
			option.optionValue = "lastMonth";
			option.value = "上月";
		}
		shortcut.getControls().add(option);

		option = new SelectOption();
		{
			option.optionValue = "thisQuarter";
			option.value = "本季度";
		}
		shortcut.getControls().add(option);

		option = new SelectOption();
		{
			option.optionValue = "lastQuarter";
			option.value = "上季度";
		}
		shortcut.getControls().add(option);

		option = new SelectOption();
		{
			option.optionValue = "thisYear";
			option.value = "今年";
		}
		shortcut.getControls().add(option);

		option = new SelectOption();
		{
			option.optionValue = "lastYear";
			option.value = "去年";
		}
		shortcut.getControls().add(option);

		option = new SelectOption();
		{
			option.optionValue = "firstHalfYear";
			option.value = "上半年";
		}
		shortcut.getControls().add(option);

		option = new SelectOption();
		{
			option.optionValue = "secondHalfYear";
			option.value = "下半年";
		}
		shortcut.getControls().add(option);

		option = new SelectOption();
		{
			option.optionValue = "firstQuarter";
			option.value = "第一季度";
		}
		shortcut.getControls().add(option);

		option = new SelectOption();
		{
			option.optionValue = "secondQuarter";
			option.value = "第二季度";
		}
		shortcut.getControls().add(option);

		option = new SelectOption();
		{
			option.optionValue = "threeQuarter";
			option.value = "第三季度";
		}
		shortcut.getControls().add(option);

		option = new SelectOption();
		{
			option.optionValue = "fourQuarter";
			option.value = "第四季度";
		}
		shortcut.getControls().add(option);

		shortcut.setStyle("width:80px;") ;

		return shortcut;
	}
}
