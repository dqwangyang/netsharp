package org.netsharp.panda.controls.form;

import org.netsharp.core.convertor.impl.DateConvertor;
import org.netsharp.panda.annotation.HtmlNode;
import org.netsharp.panda.controls.other.Label;
import org.netsharp.panda.controls.other.Span;
import org.netsharp.panda.controls.table.TD;
import org.netsharp.panda.controls.table.TR;
import org.netsharp.panda.controls.table.Table;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;

@HtmlNode(html = "table")
public class FormGroupDetail extends Table {

	public PForm pform;

	public FormFieldGroup grouping;

	public Object obj;

	DateConvertor convertor = new DateConvertor();

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
				TD trl = new TD();{
					trl.setClassName("label_td");
				};

				String header = formField.getHeader();
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
				Span span = new Span();
				span.id = formField.getPropertyName();
				trc.getControls().add(span);
				index++;
			}
		}

		super.initialize();
	}
}
