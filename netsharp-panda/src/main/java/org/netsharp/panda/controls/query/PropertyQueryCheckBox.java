package org.netsharp.panda.controls.query;

import org.netsharp.core.Mtable;
import org.netsharp.panda.controls.Control;
import org.netsharp.panda.controls.input.CheckBox;
import org.netsharp.panda.entity.PQueryItem;

public class PropertyQueryCheckBox implements IPropertyQueryControl{

	@Override
	public Control create(PQueryItem queryItem, Mtable meta) {

		CheckBox control = new CheckBox();
		{
			control.setId(queryItem.getPropertyName());
			control.required = queryItem.isRequired();
			control.controlType = "CheckBoxQueryItem";
			control.getInnerValues().put("query", "1");
		}
		return control;
	}

}
