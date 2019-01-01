package org.netsharp.wx.sdk.mp.api.menu;

import org.netsharp.wx.sdk.mp.WeixinException;
import org.netsharp.wx.sdk.mp.util.StringHelper;

public class ButtonInfo {
	private String key;
	private String name;
	private String type;
	private String url;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}


	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}


	public void Validate() throws WeixinException {
		if (this.getType().equals(ButtonType.Click)) {
			if (StringHelper.isNullOrEmpty(this.getKey())) {
				throw new WeixinException("菜单类型为Click Key不能为空");
			}
		} else if (this.getType().equals(ButtonType.View)) {
			if (StringHelper.isNullOrEmpty(this.getUrl())) {
				throw new WeixinException("菜单类型为View Url不能为空");
			}
		} else {
			throw new WeixinException("菜单类型不能为空");
		}
	}
}