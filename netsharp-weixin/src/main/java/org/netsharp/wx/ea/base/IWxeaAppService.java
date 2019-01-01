package org.netsharp.wx.ea.base;

import org.netsharp.base.IPersistableService;
import org.netsharp.wx.ea.entity.WxeaApp;

/**
 * 微信配置
 */
public interface IWxeaAppService extends IPersistableService<WxeaApp> {
	WxeaApp byCode(String code);
}
