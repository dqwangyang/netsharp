package org.netsharp.wx.meta;

import org.junit.Before;
import org.netsharp.meta.base.ResourceCreationBase;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.wx.ea.base.IWxeaAppService;
import org.netsharp.wx.ea.base.IWxeaMessageService;
import org.netsharp.wx.ea.entity.WxeaApp;
import org.netsharp.wx.ea.entity.WxeaMessage;
import org.netsharp.wx.ea.entity.WxeaMessageReciver;
import org.netsharp.wx.pa.base.IFansService;
import org.netsharp.wx.pa.base.INGraphicReplyService;
import org.netsharp.wx.pa.base.INMenuItemService;
import org.netsharp.wx.pa.base.INTextReplyService;
import org.netsharp.wx.pa.base.INWeixinResponsorService;
import org.netsharp.wx.pa.base.INWeixinSubscriberService;
import org.netsharp.wx.pa.base.IPublicAccountService;
import org.netsharp.wx.pa.base.ISceneService;
import org.netsharp.wx.pa.base.IWeixinLogService;
import org.netsharp.wx.pa.entity.Fans;
import org.netsharp.wx.pa.entity.NArticle;
import org.netsharp.wx.pa.entity.NGraphicReply;
import org.netsharp.wx.pa.entity.NMenuItem;
import org.netsharp.wx.pa.entity.NTextReply;
import org.netsharp.wx.pa.entity.NWeixinResponsor;
import org.netsharp.wx.pa.entity.NWeixinSubscriber;
import org.netsharp.wx.pa.entity.PublicAccount;
import org.netsharp.wx.pa.entity.Scene;
import org.netsharp.wx.pa.entity.WeixinLog;


public class ResourceTest extends ResourceCreationBase {

	@Before
	public void setup() {

		parentNodeName = "微信管理";
		parentNodeCode = "weixin";
		pluginName = "微信管理";
		seq = 99;
		entityClass = PublicAccount.class;
	}

	@Override
	protected void createResourceNodeVouchers(ResourceNode node) {

		ResourceNode node1 = this.createResourceNodeCategory("公众号", "public-account", node.getId());
		
		this.createResourceNodeVoucher(PublicAccount.class.getName(), "公众号",PublicAccount.class.getSimpleName(), IPublicAccountService.class.getName(), node1.getId());
		this.createResourceNodeVoucher(NWeixinResponsor.class.getName(), "回复处理器",NWeixinResponsor.class.getSimpleName(),INWeixinResponsorService.class.getName(), node1.getId());
		this.createResourceNodeVoucher(NWeixinSubscriber.class.getName(), "关注处理器",NWeixinSubscriber.class.getSimpleName(),INWeixinSubscriberService.class.getName(), node1.getId());
		this.createResourceNodeVoucher(NTextReply.class.getName(),"文字回复",NTextReply.class.getSimpleName(), INTextReplyService.class.getName(), node1.getId());
		this.createResourceNodeVoucher(NGraphicReply.class.getName(), "图文回复",NGraphicReply.class.getSimpleName(),INGraphicReplyService.class.getName(), node1.getId());
		this.createResourceNodeVoucher(NArticle.class.getName(), "图文文章",NArticle.class.getSimpleName(),INGraphicReplyService.class.getName(), node1.getId());
		this.createResourceNodeVoucher(NMenuItem.class.getName(), "微信菜单",NMenuItem.class.getSimpleName(),INMenuItemService.class.getName(), node1.getId());
		this.createResourceNodeVoucher(Fans.class.getName(), "粉丝列表",Fans.class.getSimpleName(),IFansService.class.getName(), node1.getId());
		this.createResourceNodeVoucher(Scene.class.getName(), "参数二维码",Scene.class.getSimpleName(),ISceneService.class.getName(), node1.getId());
		this.createResourceNodeVoucher(WeixinLog.class.getName(), "微信日志",WeixinLog.class.getSimpleName(),IWeixinLogService.class.getName(), node1.getId());
		
		
		node1 = this.createResourceNodeCategory("企业号", "enterprise-account", node.getId());
		this.createResourceNodeVoucher(WxeaApp.class.getName(), "应用列表",WxeaApp.class.getSimpleName(),IWxeaAppService.class.getName(), node1.getId());
		this.createResourceNodeVoucher(WxeaMessage.class.getName(), "消息列表",WxeaMessage.class.getSimpleName(),IWxeaMessageService.class.getName(), node1.getId());
		this.createResourceNodeVoucher(WxeaMessageReciver.class.getName(), "消息接收人",WxeaMessageReciver.class.getSimpleName(),IWxeaMessageService.class.getName(), node1.getId());
		
	}
}
