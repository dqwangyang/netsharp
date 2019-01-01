package org.netsharp.panda.commerce;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.core.convertor.ITypeConvertor;
import org.netsharp.entity.IPersistable;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.json.DatagridResultJson;
import org.netsharp.util.CategoryUtil;

public class TreegridSerializer {

	private HashMap<String, ITypeConvertor> properties = new HashMap<String, ITypeConvertor>();
	private List<IPersistable> list;
	private CategoryUtil catUtil = new CategoryUtil();
	private PDatagrid pdatagrid;
	private Mtable mtable;

	@SuppressWarnings("unchecked")
	public Object serialize(List<?> list, PDatagrid grid) {
		if (list == null) {
			return null;
		}

		this.list = (List<IPersistable>) list;
		this.pdatagrid = grid;

		String[] selects = grid.getSelectedFields().split(",");
		mtable = MtableManager.getMtable(grid.getEntityId());

		for (String property : selects) {
			ITypeConvertor tc = mtable.findProperty(property).getConvertor();
			properties.put(property.replace(".", "_"), tc);
		}

		List<HashMap<String, Object>> rows = new ArrayList<HashMap<String, Object>>();

		// 异步加载
		if (grid.getLazy()) {

			for (Object obj : list) {

				HashMap<String, Object> j = lazySerialize((IPersistable) obj);
				rows.add(j);
			}
			return rows;
		}

		// 全部加载
		List<IPersistable> tree = catUtil.listToTree(this.list);
		
		if (tree.size() == 0) {
			
			for (Object obj : list) {

				HashMap<String, Object> j = lazySerialize((IPersistable) obj);
				rows.add(j);
			}
		}else{
			
			for (IPersistable obj : tree) {
				HashMap<String, Object> j = serialize(obj);
				rows.add(j);
			}
		}
		
		DatagridResultJson.setSensitive(rows, grid, mtable);

		HashMap<String, Object> map = new HashMap<String, Object>();
		{
			map.put("total", list.size());
			map.put("rows", rows);
		}

		return map;
	}

	private HashMap<String, Object> lazySerialize(IPersistable obj) {

		HashMap<String, Object> j = new HashMap<String, Object>();
		for (String key : this.properties.keySet()) {

			Object value = obj.get(key.replace("_", "."));

			j.put(key, value);
		}
		boolean isLeaf = Boolean.parseBoolean(obj.get("isLeaf").toString());
		if (isLeaf) {

			j.put("state", "open");
		} else {

			j.put("state", "closed");
		}

		return j;
	}

	private HashMap<String, Object> serialize(IPersistable obj) {

		HashMap<String, Object> j = new HashMap<String, Object>();
		for (String key : this.properties.keySet()) {

			Object value = obj.get(key.replace("_", "."));

			j.put(key, value);
		}

		List<IPersistable> subs = catUtil.getChildren(obj, this.list);

		// if(subs.size()==0){
		// return j;
		// }

		List<HashMap<String, Object>> jsubs = new ArrayList<HashMap<String, Object>>();

		j.put("children", jsubs);

		for (IPersistable sub : subs) {
			HashMap<String, Object> jsub = serialize(sub);

			jsubs.add(jsub);
		}

		DatagridResultJson.setSensitive(jsubs, pdatagrid, mtable);
		
		j.put("state", "open");

		return j;
	}
}
