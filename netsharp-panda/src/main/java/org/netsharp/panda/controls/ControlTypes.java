package org.netsharp.panda.controls;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum ControlTypes implements IEnum{
	
	CHECK_BOX(1,"勾选框","CheckBox"), 
	CUSTOM(2,"自定义控件","Custom"), 
	CURRENCY_BOX(3,"货币输入框","CurrencyBox"),
	PERCENTAGE_BOX(4,"百分比输入框","PercentageBox"), 
	NUMBER_BOX(5,"整数输入框","NumberBox"), 
	DECIMAL_BOX(6,"金额输入框","DecimalBox"), 
	DATETIME_BOX(7,"时间输入框","DateTime"), 
	DATE_BOX(8,"日期输入框","DateBox"), 
	MONTH_BOX(9,"年月输入框","MonthBox"), 
	REFERENCE_BOX(10,"下拉参照","ReferenceBox"), 
	TEXTAREA(11,"多行文本框","TextArea"), 
	TEXT_BOX(12,"文本框","TextBox"), 
	ENUM_BOX(13,"下拉枚举","JavaEnumBox"),
	FILE_BOX(16,"普通上传","FileBox"), 
	EDITOR(17,"HTML编辑器","Editor"), 
	BOOLCOMBO_BOX(18,"Boolean下拉框","BoolComboBox"), 
	IMAGE(19,"图片显示","ImageBox"), 
	COMBOTREE_BOX(20,"下拉树参照","ComboTreeBox"),
	ENCRYPTION_BOX(21,"加密文本框","EncryptionBox"),
	QINIUUPLOAD(22,"七牛上传","QiNiuUpload"),
	PCC_BOX(23,"省市区","PccBox"),
	SWITCH_BUTTON(24,"开关","SwitchButton"),
	OPERATION_COLUMN(25,"操作列","OperationColumn"),
	PASSWORDTEXT_BOX(26,"密码输入框","PasswordTextBox"),
	PICTURE_FILE_BOX(27,"图片上传","PictureFileBox"),
	RADIO_BOX_GROUP(28,"单选分组","RadioboxGroup"),
	CHECK_BOX_GROUP(29,"多选分组","CheckboxGroup"),
	DECIMAL_FEN_BOX(30,"金额(分)输入框","DecimalFenBox"),
	OSS_UPLOAD(31,"OSS上传","OSSUpload"),
	MONTH_DATE_BOX(32,"月输入框","MonthDateBox"),
	YEAR_BOX(33,"年输入框","YearBox"),
	LABEL(34,"只读标签","Label");
	private int value;
	private String text;
	private String name;
	ControlTypes(int value, String text,String name) {
		this.value = value;
		this.text = text;
		this.name = name;
	}
	
    @JsonCreator  
    public static ControlTypes getItem(int value){
    	
        for(ControlTypes item : values()){

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
	
	public String getName() {
		return this.name;
	}
}
