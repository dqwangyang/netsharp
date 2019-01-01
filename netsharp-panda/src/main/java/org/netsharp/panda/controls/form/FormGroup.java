package org.netsharp.panda.controls.form;

import org.netsharp.panda.annotation.HtmlNode;
import org.netsharp.panda.controls.Control;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.controls.input.Input;
import org.netsharp.panda.controls.input.TextArea;
import org.netsharp.panda.controls.other.I;
import org.netsharp.panda.controls.other.Label;
import org.netsharp.panda.controls.table.TD;
import org.netsharp.panda.controls.table.TR;
import org.netsharp.panda.controls.table.Table;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.util.StringManager;

@HtmlNode(html = "table")
public class FormGroup extends Table {

	public PForm pform;

	public FormFieldGroup grouping;

	public Object obj;
//
//	DateConvertor convertor = new DateConvertor();

	@Override
	public void initialize() {

		this.setClassName("form-panel");
		this.setCellPadding(3);

		// 行信息
		double total = grouping.size();
		double columnCount = pform.getColumnCount();
		double rowCount = total / columnCount;

		int index = 0;
		for (int i = 0; i < rowCount; i++) {

			TR tr = new TR();
			this.getControls().add(tr);

			for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {

				if (index >= total) {
					continue;
				}

				PFormField formField = grouping.get(index);
				//
				TD trl = new TD();
				{
					trl.setClassName("label_td");
					trl.width = pform.getLabelWidth() + "px";
				}
				;
				if (!StringManager.isNullOrEmpty(formField.getTooltip())) {

					//fa-info
					I icon = new I();{

						icon.getInnerValues().put("title", formField.getTooltip());
						icon.setClassName("easyui-tooltip fa fa-info-circle");
					}
					trl.getControls().add(icon);
				}
				String header = formField.getHeader() + "：";
				if (formField.isRequired()) {
					Label lb = new Label("*");
					{
						lb.setStyle("color:Red");
					}
					trl.getControls().add(lb);
				}

				Label title = new Label(header);
				{
					title.getInnerValues().put("for", formField.getPropertyName());
				}

				trl.getControls().add(title);

				TD trc = new TD();
				trc.setClassName("control_td");

				if (formField.getColumnSpan() > 0) {

					trc.colspan = formField.getColumnSpan();
				}

				if (formField.isFullColumn()) {

					trc.colspan = (int) (columnCount * 2 - 1);
					TR trf = new TR();
					trf.getControls().add(trl);
					this.getControls().add(trf);
					trf.getControls().add(trc);
				} else {
					tr.getControls().add(trl);
					tr.getControls().add(trc);
				}

				IPropertyControl propertyControl = PropertyControlFactory.create(formField);
				Control control = propertyControl.create(this.pform, formField, this);
				if (formField.getControlType() == ControlTypes.CUSTOM) {

					if (control instanceof Input) {

						Input ci = (Input) control;
						if (StringManager.isNullOrEmpty(ci.controlType)) {

							ci.controlType = ControlTypes.CUSTOM.name();
							ci.customControlType = formField.getCustomControlType();
						}
					}
				}
				trc.getControls().add(control);
				if (formField.getControlType() == ControlTypes.TEXTAREA) {

					TextArea textArea = (TextArea) control;
					if (textArea != null) {
						if (formField.isReadonly() || this.pform.isReadOnly()) {
							textArea.disabled = true;
							textArea.oldDisabled = true;
						}
					}
				} else {

					if (control instanceof Input) {
						Input input = (Input) control;
						if (formField.isReadonly() || this.pform.isReadOnly()) {
							input.disabled = true;
							input.oldDisabled = true;
						}
					}
				}

				index++;
			}
		}

		super.initialize();
	}
}
