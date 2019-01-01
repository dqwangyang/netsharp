package org.netsharp.resourcenode.entity;

import java.io.Serializable;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

/*插件*/
@Table(name="sys_plugin")
public class Plugin extends Entity implements Serializable{
	
	private static final long serialVersionUID = 3618572700667612062L;
	
	@Column(name="name",header="名称")
	private String name;
	
	@Column(name="version",header="版本")
	private String version;
	
	@Column(name="author",header="开发者")
	private String author;
	
	@Column(name="icon",header="图标")
	private String icon;
	
	@Column(name="url",header="路径")
	private String url;
	
	@Column(name="disabled",header="停用")
	private Boolean disabled=false;
	
	@Column(name="memoto",header="说明")
	private String memoto;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMemoto() {
		return memoto;
	}

	public void setMemoto(String memoto) {
		this.memoto = memoto;
	}
}
