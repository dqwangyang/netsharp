package org.netsharp.wx.meta;

import org.junit.Before;
import org.netsharp.meta.base.NavigationBase;
import org.netsharp.panda.plugin.entity.PNavigation;
import org.netsharp.wx.ea.entity.WxeaApp;
import org.netsharp.wx.ea.entity.WxeaMessage;
import org.netsharp.wx.pa.entity.Fans;
import org.netsharp.wx.pa.entity.NGraphicReply;
import org.netsharp.wx.pa.entity.NMenuItem;
import org.netsharp.wx.pa.entity.NTextReply;
import org.netsharp.wx.pa.entity.NWeixinResponsor;
import org.netsharp.wx.pa.entity.NWeixinSubscriber;
import org.netsharp.wx.pa.entity.PublicAccount;
import org.netsharp.wx.pa.entity.Scene;
import org.netsharp.wx.pa.entity.WeixinLog;

public class NavigationTest extends NavigationBase {

	@Before
	public void setup() {
		this.treeName = "微信管理";
		this.treePath = "panda/navigation/weixin";
		this.resourceNode = "weixin";
	}

	@Override
	public void createAccodions() {

		this.doCreateAccodions("weixin", "微信管理","fa fa-weixin fa-fw", 110);

	}

	@Override
	protected void doCreateTree(PNavigation tree) {

		createPTreeNode(tree, null, null, "public-account", "公众号", "");
		{
			createPTreeNode(tree, "public-account", null, PublicAccount.class.getSimpleName(), "公众号", "/wx/public/account/list");
			createPTreeNode(tree, "public-account", null, NWeixinResponsor.class.getSimpleName(), "回复处理器", "/wx/responsor/list");
			createPTreeNode(tree, "public-account", null, NWeixinSubscriber.class.getSimpleName(), "关注处理器", "/wx/subscriber/list");
			createPTreeNode(tree, "public-account", null, NTextReply.class.getSimpleName(), "文字回复", "/wx/reply/text/list");
			createPTreeNode(tree, "public-account", null, NGraphicReply.class.getSimpleName(), "图文回复", "/wx/reply/graphic/list");
			createPTreeNode(tree, "public-account", null, NMenuItem.class.getSimpleName(), "微信菜单", "/wx/menuitem/list");
			createPTreeNode(tree, "public-account", null, Fans.class.getSimpleName(), "粉丝管理", "/wx/pa/fans/list");
			createPTreeNode(tree, "public-account", null, Scene.class.getSimpleName(), "参数二维码", "/wx/pa/scene/list");
			createPTreeNode(tree, "public-account", null, WeixinLog.class.getSimpleName(), "微信日志", "/wx/pa/wxlog/list");
		}

		createPTreeNode(tree, null, null, "enterprise-account", "企业号", "");
		{
			createPTreeNode(tree, "enterprise-account", null, WxeaApp.class.getSimpleName(), "应用列表", "/wx/ea/app/list");
			createPTreeNode(tree, "enterprise-account", null, WxeaMessage.class.getSimpleName(), "消息列表", "/wx/ea/message/list");
		}
	}
}