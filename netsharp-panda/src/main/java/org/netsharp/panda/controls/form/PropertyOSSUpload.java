package org.netsharp.panda.controls.form;

import org.netsharp.panda.controls.Control;
import org.netsharp.panda.controls.upload.OSSUpload;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.util.StringManager;

public class PropertyOSSUpload implements IPropertyControl {

	@Override
	public Control create(PForm form, PFormField formField, FormGroup group) {

		OSSUpload upload = new OSSUpload();
		{
			upload.collected = true;
			upload.setId(formField.getPropertyName());
			upload.required = formField.isRequired();
		}

		if (formField.isFullColumn()) {
			upload.setStyle("width:100%;");
		} else if (formField.getWidth() > 100) {

			upload.setStyle("width:" + formField.getWidth() + "px;");
		} else {

			upload.setStyle("width:151;");
		}

		if (formField.isFullColumn()) {
			upload.setStyle("width:100%;");
		} else {

			upload.setStyle("width:" + formField.getWidth() + "px;");
		}

		if (!StringManager.isNullOrEmpty(formField.getDataOptions())) {

			upload.filters = formField.getDataOptions();
		}

		return upload;
	}

}
