package org.netsharp.wx.sdk.mp.message.request.event;

public class LocationEvent extends EventRequest
{
	private double latitude;  //地理位置维度，事件类型为LOCATION的时存在
	private double longitude; //地理位置经度，事件类型为LOCATION的时存在
	private double precision; //地理位置精度，事件类型为LOCATION的时存在
	
	public final double getLatitude()
	{
		return latitude;
	}
	public final void setLatitude(double value)
	{
		latitude = value;
	}
	
	public final double getLongitude()
	{
		return longitude;
	}
	public final void setLongitude(double value)
	{
		longitude = value;
	}
	
	public final double getPrecision()
	{
		return precision;
	}
	public final void setPrecision(double value)
	{
		precision = value;
	}
}