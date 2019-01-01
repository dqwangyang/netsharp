package com.netsharp.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.netsharp.core.annotations.Table;

@Table(name="test_salesOrder")
public class SalesOrderExt extends SalesOrder {
	
	private static final long serialVersionUID = -3604322033528611579L;
	private String p1;
    private Integer p2;
    private Date p3;
    private BigDecimal p4;
    private Boolean p5;
    
	public String getP1() {
		return p1;
	}
	public void setP1(String p1) {
		this.p1 = p1;
	}
	public Integer getP2() {
		return p2;
	}
	public void setP2(Integer p2) {
		this.p2 = p2;
	}
	public Date getP3() {
		return p3;
	}
	public void setP3(Date p3) {
		this.p3 = p3;
	}
	public BigDecimal getP4() {
		return p4;
	}
	public void setP4(BigDecimal p4) {
		this.p4 = p4;
	}
	public Boolean getP5() {
		return p5;
	}
	public void setP5(Boolean p5) {
		this.p5 = p5;
	}
    
    
    
}
