package org.netsharp.wx.pa.entity;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.wx.pa.dic.OAuthType;

/*微信菜单*/
@Table(name="wx_pa_menu_item",header="微信菜单")
public class NMenuItem extends WeixincatEntity {
	
	private static final long serialVersionUID = 1L;
	
	@Column(header="如果是回复，则配置图文回复或者文字回复的keywords")
	private String reply;
    @Column(size=1000,header="如果是打开连接，则配置url地址")
    private String url;
    
    @Column(name="oauth_type",header="OAuth认证方式，非服务号只能选择none")
    private OAuthType oauthType = OAuthType.none;
    
    @Column(header="OAuth认证方式下传递的state值，非服务号不使用此字段")
    private String state;
    
    @Column(header="顺序")
    private Integer seq;
    
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public OAuthType getOauthType() {
		return oauthType;
	}
	public void setOauthType(OAuthType oauthType) {
		this.oauthType = oauthType;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
