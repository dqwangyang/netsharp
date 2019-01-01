package org.netsharp.dbm.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class DbmResult implements Serializable{
	
	private static final long serialVersionUID = -7897530640261553L;
	
	private int count;//非查询时的影响行数
	private Column[] columns;//查询时查询列名集合
	private List<Map<String,Object>> items;//查询时查询数据结果
	
	public Column[] getColumns() {
		return columns;
	}
	public void setColumns(Column[] columns) {
		this.columns = columns;
	}
	public List<Map<String, Object>> getItems() {
		return items;
	}
	public void setItems(List<Map<String, Object>> items) {
		this.items = items;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
}
