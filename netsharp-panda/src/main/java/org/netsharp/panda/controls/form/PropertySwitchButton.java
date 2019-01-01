package org.netsharp.panda.controls.form;

import org.netsharp.panda.controls.Control;
import org.netsharp.panda.controls.input.SwitchButton;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.util.StringManager;

public class PropertySwitchButton implements IPropertyControl{

	@Override
	public Control create(PForm form, PFormField formField, FormGroup group) {
		
		SwitchButton button = new SwitchButton();
		button.collected = true;
		button.width = formField.getWidth();
		button.setId(formField.getPropertyName());
		String optins = formField.getDataOptions();
		if(!StringManager.isNullOrEmpty(optins)){
			
			String[] ss = optins.split("\\|");
			button.onText = ss[0];
			button.offText = ss[1];
		}
		
		if(!StringManager.isNullOrEmpty(formField.getTroikaTrigger())){
			
			button.onChange = "function(checked){"+formField.getTroikaTrigger()+"}";
		}

        return button;
	}

}
