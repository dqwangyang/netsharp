package org.netsharp.rest.tm;

import java.util.List;

//{"total":213558,"rows":[{"page_no":1,
public class NoticePaging {
	
	private Integer total;
	private List<NoticeItem> rows;
	
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public List<NoticeItem> getRows() {
		return rows;
	}
	public void setRows(List<NoticeItem> rows) {
		this.rows = rows;
	}
	
}
