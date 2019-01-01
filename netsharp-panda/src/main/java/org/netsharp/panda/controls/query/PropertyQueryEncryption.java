package org.netsharp.panda.controls.query;

import org.netsharp.core.Mtable;
import org.netsharp.panda.controls.Control;
import org.netsharp.panda.controls.input.TextBox;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.util.StringManager;

public class PropertyQueryEncryption implements IPropertyQueryControl{

	@Override
	public Control create(PQueryItem queryItem, Mtable meta) {

		TextBox control = new TextBox();
		{
			control.controlType = "EncryptionBoxQueryItem";
			control.setStyle("width:"+queryItem.getWidth()+"px;");
			control.setId(queryItem.getPropertyName());
			control.required = queryItem.isRequired();
			control.getInnerValues().put("query", "1");
		}
		
		if(!StringManager.isNullOrEmpty(queryItem.getOperation())){

			control.getInnerValues().put("operation", queryItem.getOperation());
		}
		return control;
	}

}
