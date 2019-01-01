package org.netsharp.wx.meta;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.netsharp.wx.meta.ea.WxeaAppReferenceTest;
import org.netsharp.wx.meta.ea.WxeaAppWorkspaceTest;
import org.netsharp.wx.meta.ea.WxeaMessageWorkspaceTest;
import org.netsharp.wx.meta.pa.biz.FansWorkspaceTest;
import org.netsharp.wx.meta.pa.biz.SceneWorkspaceTest;
import org.netsharp.wx.meta.pa.biz.WeixinLogWorkspaceTest;
import org.netsharp.wx.meta.pa.configuration.NMenuItemWorkspaceTest;
import org.netsharp.wx.meta.pa.configuration.NWeixinResponsorWorkspaceTest;
import org.netsharp.wx.meta.pa.configuration.NWeixinSubscriberWorkspaceTest;
import org.netsharp.wx.meta.pa.configuration.PublicAccountWorkspaceTest;
import org.netsharp.wx.meta.pa.configuration.ReplyGraphicWorkspaceTest;
import org.netsharp.wx.meta.pa.configuration.ReplyTextWorkspaceTest;
import org.netsharp.wx.meta.pa.initialize.WeixinInitializeTest;
import org.netsharp.wx.meta.pa.ref.PublicAccountRefTest;

@RunWith(Suite.class)
@SuiteClasses({
	ResourceTest.class,
	
	PublicAccountRefTest.class,
	
	FansWorkspaceTest.class,
	SceneWorkspaceTest.class,
	WeixinLogWorkspaceTest.class,

	PublicAccountWorkspaceTest.class,
	NWeixinResponsorWorkspaceTest.class,
	NWeixinSubscriberWorkspaceTest.class,
	ReplyTextWorkspaceTest.class,
	ReplyGraphicWorkspaceTest.class,
	NMenuItemWorkspaceTest.class,
	
	WxeaAppReferenceTest.class,
	WxeaAppWorkspaceTest.class,
	WxeaMessageWorkspaceTest.class,
	
	WeixinInitializeTest.class,
	
	NavigationTest.class,
	})
public class AllTests {

}
