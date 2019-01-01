package org.netsharp.panda.controls.query;

import org.netsharp.core.Mtable;
import org.netsharp.panda.controls.Control;
import org.netsharp.panda.controls.input.NumberBox;
import org.netsharp.panda.entity.PQueryItem;

public class PropertyQueryNumberBox implements IPropertyQueryControl {

	@Override
	public Control create(PQueryItem queryItem, Mtable meta) {

		NumberQueryControl control = new NumberQueryControl();
		control.setQueryItem(queryItem);
		control.controlType = NumberBox.class;
		control.jsControlType = "NumberBoxQueryItem";
		return control;
	}
}
