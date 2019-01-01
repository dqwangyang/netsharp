package org.netsharp.panda.controls.query;

import org.netsharp.core.Mtable;
import org.netsharp.panda.controls.Control;
import org.netsharp.panda.entity.PQueryItem;

public class PropertyQueryDateBox implements IPropertyQueryControl{

	@Override
	public Control create(PQueryItem queryItem, Mtable meta) {
		
		DateBoxQueryControl control = new DateBoxQueryControl();
		control.setQueryItem(queryItem);
		return control;
	}

}
