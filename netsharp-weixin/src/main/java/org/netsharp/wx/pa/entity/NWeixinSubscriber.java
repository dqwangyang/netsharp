package org.netsharp.wx.pa.entity;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

/**/
@Table(name="wx_pa_subscriber",header="微信关注处理器")
public class NWeixinSubscriber extends WeixinEntity {

	private static final long serialVersionUID = 9170513946538458143L;

	@Column(name="java_type",size=1000)
    private String javaType;
    private Boolean disabled;
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
