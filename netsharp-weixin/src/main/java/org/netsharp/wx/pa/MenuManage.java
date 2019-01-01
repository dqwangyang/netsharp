package org.netsharp.wx.pa;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.panda.controls.utility.UrlHelper;
import org.netsharp.panda.core.HttpContext;
import org.netsharp.panda.core.PandaException;
import org.netsharp.panda.core.comunication.IRequest;
import org.netsharp.util.ReflectManager;
import org.netsharp.util.StringManager;
import org.netsharp.wx.pa.dic.OAuthType;
import org.netsharp.wx.pa.entity.NMenuItem;
import org.netsharp.wx.pa.entity.PublicAccount;
import org.netsharp.wx.pa.response.PublicAccountManager;
import org.netsharp.wx.sdk.mp.WeixinException;
import org.netsharp.wx.sdk.mp.api.accesstoken.AccessToken;
import org.netsharp.wx.sdk.mp.api.menu.ButtonInfo;
import org.netsharp.wx.sdk.mp.api.menu.ButtonType;
import org.netsharp.wx.sdk.mp.api.menu.MenuCreateRequest;
import org.netsharp.wx.sdk.mp.api.menu.MenuData;
import org.netsharp.wx.sdk.mp.api.menu.MenuDeleteRequest;
import org.netsharp.wx.sdk.mp.api.menu.SubButton;
import org.netsharp.wx.sdk.mp.api.menu.TopButton;

/*微信菜单管理*/
public class MenuManage {
	
	private static Log logger = LogFactory.getLog( MenuManage.class.getSimpleName());
	
    private String originalId = null;
    private PublicAccount pa=null;


    /*生成微信菜单*/
    public void create(String originalId, List<NMenuItem> tops)  {
        if (tops.size() > 3) {
            throw new WeixinException("一级菜单不能超过三个");
        }

        this.originalId = originalId;

        MenuData group = new MenuData();
        for (NMenuItem item : tops) {
        	
            this.topMenu(group, item);
        }

        WeixinMessageListener listner = new WeixinMessageListener();
        AccessToken token = listner.getAccessToken(this.originalId);

        MenuCreateRequest mcr = new MenuCreateRequest();
        {
            mcr.setTokenInfo(token);
        }

        mcr.setMenuData(group);
        mcr.getResponse();
    }

    private void topMenu(MenuData group, NMenuItem item) throws WeixinException {

        if (item.getName().length() > 4) {
            throw new WeixinException("\"" + item.getName() + "\"不合法,(一级菜单文字不能超过四个字)");
        }
        
        if (item.getItems()==null || item.getItems().size() == 0) {
        	//没有子级菜单
            TopButton button = this.create(TopButton.class, item);
            group.getButton().add(button);
        } else {
        	//有子级菜单
            TopButton subButton = new TopButton();
            {
                subButton.setName(item.getName());
            }

            group.getButton().add(subButton);

            for (Object obj : item.getItems()) {
                NMenuItem subItem = (NMenuItem) obj;
                SubButton button = this.create(SubButton.class, subItem);
                subButton.getSub_button().add(button);
            }
        }
    }
    
    // 生成微信菜单(主要处理OAuth情况)
    private String generateUrl(NMenuItem item){
    	
    	HttpContext ctx = HttpContext.getCurrent();
		if(ctx == null){
			throw new PandaException("当前环境HttpContext.getCurrent()为空");
		}
		
		
		IRequest request = ctx.getRequest();
		logger.warn("getRequestURL:"+request.getRequestURL());
		logger.warn("getServletPath:"+request.getServletPath());
    	
		logger.warn("hostname:"+UrlHelper.getHost());
    	
    	String redirectUrl =  UrlHelper.join(item.getUrl(), "originalId=" + originalId);
    	redirectUrl = UrlHelper.getFullUrl(redirectUrl);
    	
    	if(item.getOauthType() == OAuthType.none){
    		return redirectUrl;
    	}
    	
    	if(this.pa == null){
    		this.pa = PublicAccountManager.getInstance().get(originalId).getAccount();
    	}
    	
    	logger.warn("redirectUrl:"+redirectUrl);
    	
    	redirectUrl = UrlHelper.encode( redirectUrl ); 
    	
    	String url = "https://open.weixin.qq.com/connect/oauth2/authorize?from=weixin&appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s#wechat_redirect";
    	
    	url = String.format(url, this.pa.getAppId(),redirectUrl,item.getOauthType().name(),item.getState());
    	
    	logger.warn("url:"+url);
    	
    	return url;
    }

    @SuppressWarnings("unchecked")
    private <T extends ButtonInfo> T create(Class<?> type, NMenuItem item) throws WeixinException {
    	
        if (!StringManager.isNullOrEmpty(item.getUrl())) {

            ButtonInfo button = (ButtonInfo) ReflectManager.newInstance(type);
            {
                button.setName(item.getName());
                button.setUrl(this.generateUrl(item));
                button.setType(ButtonType.View);
            }

            return (T) button;
        } else if (!StringManager.isNullOrEmpty(item.getReply())) {
            ButtonInfo button = (ButtonInfo) ReflectManager.newInstance(type);
            {
                button.setName(item.getName());
                button.setKey(item.getReply());
                button.setType(ButtonType.Click);
            }

            return (T) button;
        } else {
            throw new WeixinException("创建微信菜单【"+item.getName()+"】失败:必须设置回复keywords或者url" );
        }
    }

    /*删除微信菜单*/
    public void delete()  {
        WeixinMessageListener listner = new WeixinMessageListener();
        AccessToken token = listner.getAccessToken(this.originalId);

        MenuDeleteRequest mdr = new MenuDeleteRequest();
        {
            mdr.setTokenInfo(token);
        }

        mdr.getResponse();
    }
}