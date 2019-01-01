package org.netsharp.wx.sdk.mp.pay;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.netsharp.wx.sdk.mp.util.StringHelper;

public class Feedback implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String openId;
	private String appId;
	private String timeStamp;
	private String msgType;
	private String feedBackId;
	private String transId;
	private String reason;
	private String soulition;
	private String extInfo;
	private String appSignature;
	private String signMothed;
	//[XmlArrayItem("item")]
	private ArrayList<FeedBackPicUrl> picInfo;
	
	public Feedback()
	{
	}

	public final boolean getIsNew()
	{
		return this.getMsgType().equals("request");
	}

	public final boolean getIsConform()
	{
		return this.getMsgType().equals("confirm");
	}

	public final boolean getIsReject()
	{
		return this.getMsgType().equals("reject");
	}


	
	public final String getOpenId()
	{
		return openId;
	}
	public final void setOpenId(String value)
	{
		openId = value;
	}

	
	public final String getAppId()
	{
		return appId;
	}
	public final void setAppId(String value)
	{
		appId = value;
	}

	
	public final String getTimeStamp()
	{
		return timeStamp;
	}
	public final void setTimeStamp(String value)
	{
		timeStamp = value;
	}

	
	public final String getMsgType()
	{
		return msgType;
	}
	public final void setMsgType(String value)
	{
		msgType = value;
	}

	
	public final String getFeedBackId()
	{
		return feedBackId;
	}
	public final void setFeedBackId(String value)
	{
		feedBackId = value;
	}

	
	public final String getTransId()
	{
		return transId;
	}
	public final void setTransId(String value)
	{
		transId = value;
	}

	
	public final String getReason()
	{
		return reason;
	}
	public final void setReason(String value)
	{
		reason = value;
	}

	
	public final String getSoulition()
	{
		return soulition;
	}
	public final void setSoulition(String value)
	{
		soulition = value;
	}


	public final String getExtInfo()
	{
		return extInfo;
	}
	public final void setExtInfo(String value)
	{
		extInfo = value;
	}


	public final String getAppSignature()
	{
		return appSignature;
	}
	public final void setAppSignature(String value)
	{
		appSignature = value;
	}

	
	public final String getSignMothed()
	{
		return signMothed;
	}
	public final void setSignMothed(String value)
	{
		signMothed = value;
	}

	public final ArrayList<FeedBackPicUrl> getPicInfo()
	{
		return picInfo;
	}
	public final void setPicInfo(ArrayList<FeedBackPicUrl> value)
	{
		picInfo = value;
	}

	public final Iterable<String> getPics()
	{
		if (getPicInfo() == null || getPicInfo().isEmpty())
		{
			return null;
		}
		
		List<String> ss=new ArrayList<String>();
		
		for(FeedBackPicUrl pi : this.getPicInfo()){
			if(StringHelper.isNullOrEmpty( pi.getPicUrl())){
				ss.add(pi.getPicUrl());
			}
		}
		
		return ss;
	}
}