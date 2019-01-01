package org.netsharp.wx.sdk.mp.message.request;

import org.netsharp.wx.sdk.mp.message.RequestMessage;

public class LocationRequest extends RequestMessage
{
	private String location_X;
	private String location_Y;
	private String scale;
	private String label;
	
	/** 
	 地理位置纬度
	*/
	
	public final String getLocation_X()
	{
		return location_X;
	}
	public final void setLocation_X(String value)
	{
		location_X = value;
	}
	/** 
	 地理位置经度
	 
	*/
	
	public final String getLocation_Y()
	{
		return location_Y;
	}
	public final void setLocation_Y(String value)
	{
		location_Y = value;
	}
	
	public final String getScale()
	{
		return scale;
	}
	public final void setScale(String value)
	{
		scale = value;
	}
	
	public final String getLabel()
	{
		return label;
	}
	public final void setLabel(String value)
	{
		label = value;
	}



	@Override
	public String toString()
	{
		String baseString = super.toString();

		return baseString + ";Location_X" + this.getLocation_X() + ";Location_Y" + getLocation_Y() + ";Scale" + getScale() + ";Label:" + this.getLabel();
	}
}