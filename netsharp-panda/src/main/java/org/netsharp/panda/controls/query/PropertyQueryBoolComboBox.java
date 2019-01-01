package org.netsharp.panda.controls.query;

import org.netsharp.core.Mtable;
import org.netsharp.panda.controls.Control;
import org.netsharp.panda.controls.input.BoolComboBox;
import org.netsharp.panda.entity.PQueryItem;

public class PropertyQueryBoolComboBox implements IPropertyQueryControl{

	@Override
	public Control create(PQueryItem queryItem, Mtable meta) {

		BoolComboBox control = new BoolComboBox();
		{
			control.setId(queryItem.getPropertyName());
			control.required = queryItem.isRequired();
			control.controlType = "BoolComboBoxQueryItem";
			control.width = 150;
			control.getInnerValues().put("query", "1");
		}
		return control;
	}

}
