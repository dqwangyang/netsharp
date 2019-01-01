package org.netsharp.wx.sdk.mp.api.menu;

import java.util.ArrayList;

import org.netsharp.wx.sdk.mp.WeixinException;

public class TopButton extends ButtonInfo
{
	private ArrayList<SubButton> sub_button;
	
	public TopButton()
	{
		setSub_button(new ArrayList<SubButton>());
	}

	@Override
	public void Validate() throws WeixinException
	{
		if (this.getSub_button().size() > 0)
		{
			super.Validate();
		}
	}
	
	public final ArrayList<SubButton> getSub_button()
	{
		return sub_button;
	}
	public final void setSub_button(ArrayList<SubButton> value)
	{
		sub_button = value;
	}
}