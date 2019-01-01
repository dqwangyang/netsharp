package org.netsharp.wx.pa.base;

import org.netsharp.base.IPersistableService;
import org.netsharp.wx.pa.entity.NMenuItem;

public interface INMenuItemService extends IPersistableService<NMenuItem> {
	void generate(String originalId);
}

