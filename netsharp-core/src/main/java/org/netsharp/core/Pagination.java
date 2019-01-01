package org.netsharp.core;

import java.io.Serializable;
import java.util.List;

public class Pagination implements Serializable {

	private static final long serialVersionUID = 564681041960546569L;

	private Paging paging;
	private List<?> rows;
	
	public Paging getPaging() {
		return paging;
	}
	public void setPaging(Paging paging) {
		this.paging = paging;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	
	
}
