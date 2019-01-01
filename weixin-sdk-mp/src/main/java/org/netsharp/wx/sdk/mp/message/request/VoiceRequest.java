package org.netsharp.wx.sdk.mp.message.request;

import org.netsharp.wx.sdk.mp.message.RequestMessage;

public class VoiceRequest extends RequestMessage
{
	private String mediaId;
	private String format;       //语音格式：amr
	private String recognition;  //语音识别结果，UTF8编码
	private String msgsource;    //xml中有此字段，但是不知道做什么用
	
	public final String getMediaId()
	{
		return mediaId;
	}
	public final void setMediaId(String value)
	{
		mediaId = value;
	}
	
	public final String getFormat()
	{
		return format;
	}
	public final void setFormat(String value)
	{
		format = value;
	}
	/** 
	 语音识别结果，UTF8编码
	 开通语音识别功能，用户每次发送语音给公众号时，微信会在推送的语音消息XML数据包中，增加一个Recongnition字段。
	 注：由于客户端缓存，开发者开启或者关闭语音识别功能，对新关注者立刻生效，对已关注用户需要24小时生效。开发者可以重新关注此帐号进行测试。	 
	*/
	public final String getRecognition()
	{
		return recognition;
	}
	public final void setRecognition(String value)
	{
		recognition = value;
	}
	
	public String getMsgsource() {
		return msgsource;
	}
	public void setMsgsource(String msgsource) {
		this.msgsource = msgsource;
	}


	@Override
	public String toString()
	{
		String baseString = super.toString();

		return baseString + ";MediaId" + this.getMediaId() + ";Recognition" + getRecognition() + ";Format" + getFormat();
	}

}