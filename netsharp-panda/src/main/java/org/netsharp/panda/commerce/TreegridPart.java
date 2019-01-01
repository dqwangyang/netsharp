package org.netsharp.panda.commerce;

import java.util.List;

import org.netsharp.base.ICatEntityService;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.panda.controls.treegrid.TreeGrid;
import org.netsharp.panda.controls.utility.UrlHelper;
import org.netsharp.panda.core.comunication.IHtmlWriter;
import org.netsharp.util.StringManager;

public class TreegridPart extends ListPart {

	@Override
	protected void onRendering() {

		TreeGrid treegrid = new TreeGrid();
		{
			treegrid.pagination = false;
			treegrid.onLoadSuccess = "function(row,data){" + getJsInstance() + ".onLoadSuccess(row,data);}";
			treegrid.onBeforeExpand = "function(row){" + getJsInstance() + ".onBeforeExpand(row);}";
			if (!StringManager.isNullOrEmpty(this.pdatagrid.getTreeField())) {

				treegrid.treeField = this.pdatagrid.getTreeField();
			}
		}
		this.datagrid = treegrid;
	}

	@Override
	protected String getExtraFilter() {

		if(this.pdatagrid.getLazy()){

			String filter = getRequest("filter");
			if (!StringManager.isNullOrEmpty(filter)) {
				
				return null;
			}
			String id = this.getRequest("id");
			if (StringManager.isNullOrEmpty(id)) {

				return "parentId=0 or parentId is null";
			}
			return "parentId="+id;
		}
		return null;
	}

	@Override
	protected Object serialize(List<?> table, Oql oql) {

		TreegridSerializer jsonSerializor = new TreegridSerializer();
		Object json = jsonSerializor.serialize(table, this.context.getDatagrid());
		return json;
	}

	@Override
	protected void importJs(IHtmlWriter writer) {

		super.importJs(writer);
		writer.write(UrlHelper.getVersionScript("/panda-res/js/panda.tree.list.js"));
	}
	

	public void pathCode() {
		
		ICatEntityService service = ServiceFactory.create(ICatEntityService.class);
		service.generatePathCode(this.context.getEntityId());
		
	}
}
