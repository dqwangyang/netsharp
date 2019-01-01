package org.netsharp.plugin.bean;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.plugin.core.Doozer;
import org.netsharp.plugin.entity.Codonable;

@Table(name="rs_bean",header="Bean",orderBy="seq")
@Doozer(type=DoozerBean.class)
public class Bean extends Codonable {

	private static final long serialVersionUID = -3647770880242491465L;
	
	@Column(size=500,header="类型")
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
