package org.netsharp.panda.controls.query;

import java.util.List;

import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.panda.controls.Control;
import org.netsharp.panda.controls.form.Form;
import org.netsharp.panda.controls.other.Div;
import org.netsharp.panda.controls.other.Linkbutton;
import org.netsharp.panda.controls.tree.Li;
import org.netsharp.panda.controls.tree.Ul;
import org.netsharp.panda.dic.QueryProjectType;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.panda.entity.PQueryProject;

public class QueryPanel extends Form {
	private PQueryProject queryProject;
	private String datagridId;// datagrid part js对象的变量名称
	protected PDatagrid pdatagrid;

	@Override
	public void initialize() {
		
		this.setId("queryFrom");
		String entityId = this.queryProject.getEntityId();
		Mtable meta = MtableManager.getMtable(entityId);

		Ul ul = new Ul();
		ul.setClassName("query-panel");
		List<PQueryItem> itemList = getQueryProject().getQueryItems();
		for (PQueryItem item : itemList) {

			if (item.isVisible()) {

				Li li = new Li();
				li.setClassName("item");
				
				Div label = new Div();
				label.setClassName("label");
				label.value = item.getHeader() + "：";
				li.getControls().add(label);

				Div ctrl = new Div();
				ctrl.setClassName("ctrl");
				IPropertyQueryControl propertyQueryControl = PropertyQueryControlFactory.create(item);
				Control queryControl = propertyQueryControl.create(item, meta);
				ctrl.getControls().add(queryControl);
				li.getControls().add(ctrl);

				ul.getControls().add(li);
			}
		}

		this.createQueryButton(ul);
		Div bothDiv = new Div();
		bothDiv.setStyle("clear:both;");
		ul.getControls().add(bothDiv);
		this.controls.add(ul);
		super.initialize();
	}

	private void createQueryButton(Ul ul) {

		if (ul.getControls().size() > 0) {

			Li li = new Li();
			li.setClassName("item btn");
			if (queryProject.getType() == QueryProjectType.SIMPLE) {

				Linkbutton button = new Linkbutton();
				{
					button.setId("btn_query");
					button.setClassName("easyui-linkbutton btn");
					button.value = "查 询";
					button.iconCls = "fa fa-search";
					button.href = "javascript:" + this.datagridId + ".query();";
				}
				li.getControls().add(button);

				button = new Linkbutton();
				{
					button.setId("btn_reset");
					button.setClassName("easyui-linkbutton btn");
					button.value = "重 置";
					button.iconCls = "fa fa-reply";
					button.href = "javascript:" + this.datagridId + ".resetQuery();";
				}
				li.getControls().add(button);
			}

			if (this.pdatagrid != null && this.pdatagrid.getAdvancedQueryProjectId() != null) {

				Linkbutton button = new Linkbutton();
				{
					button.setId("btn_advanced");
					button.iconCls = "fa fa-search-plus";
					button.setClassName("easyui-linkbutton btn primary");
					button.value = "高 级";
					button.href = "javascript:" + this.datagridId + ".showAdvancedQuery('" + this.pdatagrid.getAdvancedQueryProjectId() + "', '" + datagridId + "');";
				}
				li.getControls().add(button);
			}
			
			ul.getControls().add(li);
		}
	}

	public PQueryProject getQueryProject() {
		return queryProject;
	}

	public void setQueryProject(PQueryProject queryProject) {
		this.queryProject = queryProject;
	}

	public String getDatagridId() {
		return datagridId;
	}

	public void setDatagridId(String datagridId) {
		this.datagridId = datagridId;
	}

	public PDatagrid getPdatagrid() {
		return pdatagrid;
	}

	public void setPdatagrid(PDatagrid pdatagrid) {
		this.pdatagrid = pdatagrid;
	}

}
