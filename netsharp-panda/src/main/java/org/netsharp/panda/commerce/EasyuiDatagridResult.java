package org.netsharp.panda.commerce;

import java.util.List;

/*easyui 数据查询列表*/
public class EasyuiDatagridResult {
	
	/*数据行*/
    private List<?> rows=null;
    
    private List<?> footer = null;
    
    /*总记录数*/
    private int total;

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	
	public List<?> getFooter() {
		return footer;
	}

	public void setFooter(List<?> footer) {
		this.footer = footer;
	}


	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
}
