package org.netsharp.panda.plugin.entity;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.panda.dic.OpenMode;
import org.netsharp.panda.plugin.doozer.DoozerNavigationItem;
import org.netsharp.plugin.core.Doozer;
import org.netsharp.plugin.entity.Codonable;

@Table(name="rs_navigation_item",orderBy="seq")
@Doozer(type=DoozerNavigationItem.class)
public class PNavigationItem extends Codonable {
	
	private static final long serialVersionUID = 3129072520544511626L;

	@Column(size=1000)
	private String url;
	
	@Column(name="open_mode")
	private OpenMode openMode = OpenMode.TABS;

	@Column(name="window_width")
	private Integer windowWidth;
	
	@Column(name="window_height")
	private Integer windowHeight;
	
	@Column(name="icon")
	private String icon;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public OpenMode getOpenMode() {
		return openMode;
	}

	public void setOpenMode(OpenMode openMode) {
		this.openMode = openMode;
	}

	public Integer getWindowWidth() {
		return windowWidth;
	}

	public void setWindowWidth(Integer windowWidth) {
		this.windowWidth = windowWidth;
	}

	public Integer getWindowHeight() {
		return windowHeight;
	}

	public void setWindowHeight(Integer windowHeight) {
		this.windowHeight = windowHeight;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
}
