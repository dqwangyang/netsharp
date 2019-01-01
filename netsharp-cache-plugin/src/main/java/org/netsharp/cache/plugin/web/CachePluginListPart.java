package org.netsharp.cache.plugin.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.netsharp.cache.plugin.base.ICachePluginService;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.DataTable;
import org.netsharp.core.Row;
import org.netsharp.panda.commerce.ListPart;
import org.netsharp.util.StringManager;

public class CachePluginListPart extends ListPart {
	
	ICachePluginService cachePluginService = ServiceFactory.create(ICachePluginService.class);

	@Override
	public Object query() throws IOException {
		
		DataTableJson parser = new DataTableJson(getDataTable());
		Object json = parser.parse();
		return json;
	}
	
	private DataTable getDataTable() throws UnsupportedEncodingException{

		this.pdatagrid = this.context.getDatagrid();
		this.getService();

		String f = getRequest("filter");
		if(f!=null){
			f = URLDecoder.decode(f, "utf-8");
			f = f.replaceAll("\\|", "=");
			f = f.replaceAll("fullKey LIKE '%", "");
			f = f.replaceAll("%'", "");
		}
		String pattern = "*";
		if (!StringManager.isNullOrEmpty(f)) {
			pattern = f;
		}

		DataTable table = cachePluginService.getKeys(pattern);
		return table;
	}

	public boolean delteByKey(String fullKey){
		
		return cachePluginService.delteByKey(fullKey);
	}
	
	public Object viewByKey(String fullKey){
		
		return cachePluginService.viewByKey(fullKey);
	}
	
	public boolean flushDB(){
		
		return cachePluginService.flushDB();
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
}
