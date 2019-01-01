package com.netsharp.entity;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.BizEntity;

@Table(name = "test_customer")
public class Customer extends BizEntity {

	private static final long serialVersionUID = 1L;
	@Column(name = "district_name")
	private String districtName;

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
}
