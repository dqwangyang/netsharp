package org.netsharp.panda.commerce;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.netsharp.core.DataTable;
import org.netsharp.core.Row;
import org.netsharp.panda.controls.utility.UrlHelper;
import org.netsharp.panda.core.comunication.IHtmlWriter;

/* 列表报表
 * 自定义SQL语句查询，而非基于实体的查询
 * 暂不支持分页*/
public class ReportListPart extends ListPart {

	public ReportListPart() {
		super();

		// this.isFieldPermission = false;
	}

	@Override
	public Object query() throws IOException {

		this.pdatagrid = this.context.getDatagrid();
		this.getService();
		DataTable table = this.service.executeTable(this.pdatagrid.getFilter(), null);
		DataTableJson parser = new DataTableJson(table);
		Object json = parser.parse();
		return json;
	}

	public class DataTableJson {
		
		private DataTable table;
		public DataTableJson(DataTable table) {
			
			this.table = table;
		}

		public Object parse() {

			HashMap<String, Object> maps = new HashMap<String, Object>();
			List<Object> rows = new ArrayList<Object>();
			for (Row row : this.table) {

				Map<String, Object> item = row.getInnerValues();
				rows.add(item);
			}

			maps.put("total", this.table.size());
			maps.put("rows", rows);
			return maps;
		}
	}
	
	@Override
	protected void importJs(IHtmlWriter writer) {
		super.importJs(writer);
		writer.write(UrlHelper.getVersionScript("/panda-res/js/panda.report.list.part.js"));
	}
}
