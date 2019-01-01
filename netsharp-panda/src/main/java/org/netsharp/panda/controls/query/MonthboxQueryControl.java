package org.netsharp.panda.controls.query;

import org.netsharp.panda.controls.input.MonthBox;
import org.netsharp.panda.controls.other.Label;
import org.netsharp.panda.controls.table.TD;
import org.netsharp.panda.controls.table.TR;
import org.netsharp.panda.controls.table.Table;
import org.netsharp.panda.entity.PQueryItem;

public class MonthboxQueryControl extends Table{
	
    public boolean required;
    
	public PQueryItem QueryItem;

	@Override
	public void initialize() {

		
		String id = QueryItem.getPropertyName().replaceAll("\\.", "_");
		MonthBox start = new MonthBox();
		start.collected = false;
		start.required = this.required;
		start.controlType = "MonthBoxDateQueryItem";
		start.setStyle("width:95px;");
		start.setId("Start_" +id);

		MonthBox end = new MonthBox();
		end.collected = false;
		end.required = this.required;
		end.controlType = "MonthBoxDateQueryItem";
		end.setStyle("width:95px;");
		end.setId("End_" +id);
		start.getInnerValues().put("propertyName", QueryItem.getPropertyName());
		end.getInnerValues().put("propertyName", QueryItem.getPropertyName());

		TR tr = new TR();
		this.getControls().add(tr);

		TD td = new TD();
		td.setStyle("width:90px;");
		tr.getControls().add(td);
		td.getControls().add(start);

		td = new TD();
		tr.getControls().add(td);
		Label lbl = new Label("-");
		td.setStyle("margin: 0 2px;");
		td.getControls().add(lbl);

		td = new TD();
		td.setStyle("width:90px;");
		tr.getControls().add(td);
		td.getControls().add(end);

		super.initialize();
	}
}
