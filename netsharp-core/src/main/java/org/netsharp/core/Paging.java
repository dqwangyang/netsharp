package org.netsharp.core;

import java.io.Serializable;

import org.netsharp.util.StringManager;

public class Paging implements Serializable {
	
	private static final long serialVersionUID = 2554426802921283423L;
	public int pageNo;
	public int pageSize;
	public int totalCount;

	public Paging() {
	}

	public Paging(int pagingNo, int pageSize) {
		this.pageNo = pagingNo;
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append(StringManager.NewLine);
		builder.append("pageNo : ").append(this.pageNo).append(StringManager.NewLine);
		builder.append("pageSize  : ").append(this.pageSize).append(StringManager.NewLine);
		builder.append("totalCount   : ").append(this.totalCount).append(StringManager.NewLine);

		return builder.toString();
	}
}
