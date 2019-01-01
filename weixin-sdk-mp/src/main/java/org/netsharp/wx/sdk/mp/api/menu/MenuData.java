package org.netsharp.wx.sdk.mp.api.menu;

import java.util.ArrayList;

public class MenuData
{
	private ArrayList<TopButton> button;
	
	public MenuData()
	{
		this.setButton(new ArrayList<TopButton>());
	}

	public ArrayList<TopButton> getButton() {
		return button;
	}

	public void setButton(ArrayList<TopButton> button) {
		this.button = button;
	}
}