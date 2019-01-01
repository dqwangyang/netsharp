package org.netsharp.entity;

import java.util.Date;

import org.netsharp.core.annotations.Column;

//业务单据
public abstract class VoucherEntity extends WorkEntity {

	private static final long serialVersionUID = -4893642685143472275L;

	@Column(name="voucher_date",header="单据日期")
	protected Date voucherDate;

	@Column(name="source_id",header="来源单据Id")
	protected Integer sourceId;
	
	@Column(name="source_code",header="来源单据号")
	protected String sourceCode;
	
	@Column(name="source_date",header="来源单日期")
	protected Date sourceDate;

	public Date getVoucherDate() {
		return voucherDate;
	}
	public void setVoucherDate(Date voucherDate) {
		this.voucherDate = voucherDate;
	}
	
	public Integer getSourceId() {
		return sourceId;
	}
	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}
	public String getSourceCode() {
		return sourceCode;
	}
	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}
	public Date getSourceDate() {
		return sourceDate;
	}
	public void setSourceDate(Date sourceDate) {
		this.sourceDate = sourceDate;
	}
}