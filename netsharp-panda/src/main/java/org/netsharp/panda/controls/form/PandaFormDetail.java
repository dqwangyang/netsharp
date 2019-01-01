package org.netsharp.panda.controls.form;

import java.util.Collection;

import org.netsharp.panda.controls.other.Div;
import org.netsharp.panda.controls.other.Fieldset;
import org.netsharp.panda.entity.PForm;
import org.netsharp.util.StringManager;

public class PandaFormDetail extends Form {

	public Object obj;

	private PForm pForm;

	@Override
	public void initialize() {
		Div formContent = new Div();
		formContent.setClassName("formContent");
		Collection<FormFieldGroup> groups = FormFieldGroup.groupby(this.pForm);
		for (FormFieldGroup info : groups) {
			FormGroupDetail formGroup = new FormGroupDetail();
			{
				formGroup.pform = getPForm();
				formGroup.grouping = info;
				formGroup.obj = obj;
			}

			if (!StringManager.isNullOrEmpty(info.getGroupName())) {

				Fieldset fieldse = new Fieldset();
				{
					fieldse.title = info.getGroupName();
					fieldse.getControls().add(formGroup);
					formContent.getControls().add(fieldse);
				}
			} else {

				formContent.getControls().add(formGroup);
			}
		}

		this.getControls().add(formContent);
		super.initialize();
	}

	public PForm getPForm() {

		return pForm;
	}

	public void setPForm(PForm pForm) {

		this.pForm = pForm;
	}
}
