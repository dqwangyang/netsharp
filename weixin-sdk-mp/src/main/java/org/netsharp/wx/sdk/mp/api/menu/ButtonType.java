package org.netsharp.wx.sdk.mp.api.menu;

public class ButtonType
{
	public static final String View = "view";
	public static final String Click = "click";
	/** 
	 扫码推事件
	 扫码推事件
	用户点击按钮后，微信客户端将调起扫一扫工具，完成扫码操作后显示扫描结果（如果是URL，将进入URL），且会将扫码的结果传给开发者，开发者可以下发消息。
	*/
	public static final String scancode_push = "scancode_push";

	/** 
	 扫码推事件且弹出"消息接收中"提示框
	用户点击按钮后，微信客户端将调起扫一扫工具，完成扫码操作后，将扫码的结果传给开发者，同时收起扫一扫工具，然后弹出"消息接收中"提示框，随后可能会收到开发者下发的消息。
	*/
	public static final String scancode_waitmsg = "scancode_waitmsg";

	/** 
	 弹出系统拍照发图
	用户点击按钮后，微信客户端将调起系统相机，完成拍照操作后，会将拍摄的相片发送给开发者，并推送事件给开发者，同时收起系统相机，随后可能会收到开发者下发的消息。	 
	*/
	public static final String pic_sysphoto = "pic_sysphoto";

	/** 
	 弹出拍照或者相册发图
	用户点击按钮后，微信客户端将弹出选择器供用户选择"拍照"或者"从手机相册选择"。用户选择后即走其他两种流程。
	*/
	public static final String pic_photo_or_album = "pic_photo_or_album";

	/** 
	弹出微信相册发图器
	用户点击按钮后，微信客户端将调起微信相册，完成选择操作后，将选择的相片发送给开发者的服务器，并推送事件给开发者，同时收起相册，随后可能会收到开发者下发的消息。	 
	*/
	public static final String pic_weixin = "pic_weixin";
	/** 
	 弹出地理位置选择器
	 用户点击按钮后，微信客户端将调起地理位置选择工具，完成选择操作后，将选择的地理位置发送给开发者的服务器，同时收起位置选择工具，随后可能会收到开发者下发的消息。	 
	*/
	public static final String location_select = "location_select";
}