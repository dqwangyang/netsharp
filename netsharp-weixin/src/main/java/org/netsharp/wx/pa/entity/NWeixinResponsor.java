package org.netsharp.wx.pa.entity;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

@Table(name="wx_pa_responsor",header="微信回复处理器")
public class NWeixinResponsor extends WeixinEntity	 {
	
	private static final long serialVersionUID = 1888549651119503476L;
	@Column(name="java_type",size=1000)
    private String javaType;
    private Boolean disabled=false;
    private Double seq;
    
	public String getJavaType() {
		return javaType;
	}
	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}

	public Boolean getDisabled() {
		return disabled;
	}
	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}
	public Double getSeq() {
		return seq;
	}
	public void setSeq(Double seq) {
		this.seq = seq;
	}
    
}
