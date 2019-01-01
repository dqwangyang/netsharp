package org.netsharp.panda.controls.input;

import org.netsharp.panda.annotation.HtmlAttr;
import org.netsharp.panda.annotation.HtmlNode;

/**
 * @author hw
 * 表单复选框组合，一般用于枚举多选，使用子表处理。
 */
@HtmlNode(html = "select")
public class CheckboxGroup extends Input
{
    @HtmlAttr(html="foreignKey")
    public String foreignKey;
    
    @HtmlAttr(html="enumFieldName")
    public String enumFieldName;
    
	@Override
	public void initialize() {

		this.controlType = "CheckboxGroup";
		this.setClassName("easyui-checkboxgroup");
		this.collected = true;
		super.initialize();
	}
}
