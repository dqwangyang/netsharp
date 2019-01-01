package org.netsharp.wx.sdk.mp.api.menu;

import org.netsharp.wx.sdk.mp.api.Request;
import org.netsharp.wx.sdk.mp.api.Response;
import org.netsharp.wx.sdk.mp.message.WeixinValidation;

//[Api("创建自定义菜单")]
public class MenuCreateRequest extends Request<Response>
{
	private MenuData menuData;
	
	public final MenuData getMenuData()
	{
		return menuData;
	}
	public final void setMenuData(MenuData value)
	{
		menuData = value;
	}

	@Override
	public String getUrl()
	{
		return String.format(" https://api.weixin.qq.com/cgi-bin/menu/create?access_token=%1$s", this.getAccessToken());
	}

	@Override
	protected WeixinValidation doValidate()
	{
		if (this.getMenuData() == null)
		{
			return WeixinValidation.create(false, "MenuData is null");
		}

		if (this.getMenuData().getButton().isEmpty() || this.getMenuData().getButton().size() > 3)
		{
			return WeixinValidation.create(false, "主菜单必须是1-3个");
		}

		for (TopButton x : this.getMenuData().getButton())
		{
			if (x.getSub_button().size() > 5)
			{
				return WeixinValidation.create(false, "二级菜单数量不能多于5个 " + x.getName());
			}
		}

		return super.doValidate();
	}

	@Override
	protected Response doResponse() 
	{
		String json = this.serialize(this.getMenuData());
		return this.executeHttpPost(json);
	}
}