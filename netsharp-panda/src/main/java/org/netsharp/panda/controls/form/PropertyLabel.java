package org.netsharp.panda.controls.form;

import org.netsharp.panda.controls.Control;
import org.netsharp.panda.controls.other.Label;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.util.StringManager;

public class PropertyLabel implements IPropertyControl {

	@Override
	public Control create(PForm form, PFormField formField, FormGroup group) {
	
		String id =  formField.getPropertyName().replaceAll("\\.", "_");
		Label label = new Label();
		{
			label.collected = true;
			label.setId(id);
			label.controlType = Label.class.getSimpleName();
			
			if(!StringManager.isNullOrEmpty(formField.getDataOptions())){

				label.dataOptions.add(formField.getDataOptions());
			}
		};
		return label;
	}

}
