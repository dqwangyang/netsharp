package org.netsharp.panda.controls.query;

import org.netsharp.core.Mtable;
import org.netsharp.panda.controls.Control;
import org.netsharp.panda.controls.input.TextBox;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.util.StringManager;

/**
 * @ClassName: TextBoxQueryControl
 * @Description:文本框查询控件
 * @author: 韩伟
 * @date: 2017年8月23日 上午11:46:18
 * 
 * @Copyright: 2017 www.netsharp.org Inc. All rights reserved.
 */
public class PropertyQueryTextBox implements IPropertyQueryControl {

	@Override
	public Control create(PQueryItem queryItem, Mtable meta) {

		TextBox control = new TextBox();
		control.controlType = "TextBoxQueryItem";
		control.setStyle("width:"+queryItem.getWidth()+"px;");
		control.setId(queryItem.getPropertyName());
		control.required = queryItem.isRequired();
		control.getInnerValues().put("query", "1");
		if(!StringManager.isNullOrEmpty(queryItem.getTooltip())){
			
			control.placeholder = queryItem.getTooltip();
		}
		
		if(!StringManager.isNullOrEmpty(queryItem.getOperation())){

			control.getInnerValues().put("intelligentMode", queryItem.getOperation());
		}

		return control;
	}

}
