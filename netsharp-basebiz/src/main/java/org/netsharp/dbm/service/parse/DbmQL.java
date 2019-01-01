package org.netsharp.dbm.service.parse;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.util.StringManager;

public class DbmQL {
	
	private boolean isOql;
	private String select;
	private String from;
	private String where;
	private String orderby;
	private String limit;
	
	public boolean isOql() {
		return isOql;
	}
	public void setOql(boolean isOql) {
		this.isOql = isOql;
	}
	public String getSelect() {
		return select;
	}
	public void setSelect(String select) {
		this.select = select;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getWhere() {
		return where;
	}
	public void setWhere(String where) {
		this.where = where;
	}
	public String getOrderby() {
		return orderby;
	}
	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}
	public String getLimit() {
		return limit;
	}
	public void setLimit(String limit) {
		this.limit = limit;
	}
	
	@Override
	public String toString() {
		List<String> ss = new ArrayList<String>();
		ss.add("--------------------");
		ss.add("select:"+this.select);
		ss.add("from:"+this.from);
		ss.add("where:"+this.where);
		ss.add("order by :"+this.orderby);
		ss.add("limit:"+this.limit);
		ss.add("isoql:"+this.isOql);
		ss.add("--------------------");
		
		String str = StringManager.join(StringManager.NewLine, ss);
		
		return str;
	}
}
