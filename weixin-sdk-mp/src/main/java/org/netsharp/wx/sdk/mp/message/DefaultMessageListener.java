package org.netsharp.wx.sdk.mp.message;

import org.netsharp.wx.sdk.mp.WeixinException;
import org.netsharp.wx.sdk.mp.api.accesstoken.AccessToken;
import org.netsharp.wx.sdk.mp.message.request.ImageRequest;
import org.netsharp.wx.sdk.mp.message.request.LinkRequest;
import org.netsharp.wx.sdk.mp.message.request.LocationRequest;
import org.netsharp.wx.sdk.mp.message.request.TextRequest;
import org.netsharp.wx.sdk.mp.message.request.VideoRequest;
import org.netsharp.wx.sdk.mp.message.request.VoiceRequest;
import org.netsharp.wx.sdk.mp.message.request.event.EventRequest;
import org.netsharp.wx.sdk.mp.message.request.event.LocationEvent;
import org.netsharp.wx.sdk.mp.message.request.event.MassSendFinishEvent;
import org.netsharp.wx.sdk.mp.message.request.event.SubscribeEvent;
import org.netsharp.wx.sdk.mp.message.response.Article;
import org.netsharp.wx.sdk.mp.message.response.NewsResponse;
import org.netsharp.wx.sdk.mp.message.response.TextResponse;

/** 
 默认监听器
*/
public class DefaultMessageListener implements IMessageListener
{
	/*处理请求，得到回复*/
	public ResponseMessage processRequest(RequestMessage request) 
	{
		if (request.getMsgType().equals(MessageType.Text))
		{
				return TextRequest((TextRequest)((request instanceof TextRequest) ? request : null));
		}
		else if (request.getMsgType().equals(MessageType.Image))
		{
				return ImageRequest((ImageRequest)((request instanceof ImageRequest) ? request : null));
		}
		else if (request.getMsgType().equals(MessageType.Voice))
		{
				return VoiceRequest((VoiceRequest)((request instanceof VoiceRequest) ? request : null));
		}
		else if (request.getMsgType().equals(MessageType.Video))
		{
				return VideoRequest((VideoRequest)((request instanceof VideoRequest) ? request : null));
		}
		else if (request.getMsgType().equals(MessageType.Link))
		{
				return LinkRequest((LinkRequest)((request instanceof LinkRequest) ? request : null));
		}
		else if (request.getMsgType().equals(MessageType.Location))
		{
				return LocationRequest((LocationRequest)((request instanceof LocationRequest) ? request : null));
		}
		else if (request.getMsgType().equals(MessageType.Event))
		{
			EventRequest er = (EventRequest)((request instanceof EventRequest) ? request : null);

			if (er.getEvent().toLowerCase().equals(EventType.Subscribe))
			{
					return SubscribeEventRequest((SubscribeEvent)((er instanceof SubscribeEvent) ? er : null));
			}
			else if (er.getEvent().toLowerCase().equals(EventType.Unsubscribe))
			{
					return this.UnsubscribeEventRequest(er);
			}
			else if (er.getEvent().toLowerCase().equals(EventType.Enter))
			{
					return EnterEventRequest(er);
			}
			else if (er.getEvent().toLowerCase().equals(EventType.MASSSENDJOBFINISH))
			{
					return MassSendFinishEventRequest((MassSendFinishEvent)((er instanceof MassSendFinishEvent) ? er : null));
			}
			else if (er.getEvent().toLowerCase().equals(EventType.click))
			{
					return ClickEventReqeust(er);
			}
			else if (er.getEvent().toLowerCase().equals(EventType.View))
			{
					return ViewEventRequest(er);
			}
			else if (er.getEvent().toLowerCase().equals(EventType.Scan))
			{
					return ScanEventRequest((SubscribeEvent)((er instanceof SubscribeEvent) ? er : null));
			}
			else if (er.getEvent().toLowerCase().equals(EventType.Location))
			{
					return LocationEventRequest((LocationEvent)((er instanceof LocationEvent) ? er : null));
			}
			else
			{
					throw new WeixinException("Not Support EventKey");
			}
		}
		else
		{
				throw new WeixinException("NotSupport MessageType");
		}

	}

	/*得到公众号的Token*/
	public String getToken(String oid){
		return "netsharp2020";
	}
	
	public void dispose()
	{
	}

	protected ResponseMessage TextRequest(TextRequest textRequest)
	{
		TextResponse tr = new TextResponse(textRequest);

		tr.setContent(textRequest.getContent());

		tr.setContent(tr.getContent() + "<a href='http://www.qanso.com'>首页</a>");

		return tr;
	}

	private ResponseMessage DefaultText(RequestMessage request)
	{
		TextResponse tr = new TextResponse(request);

		tr.setContent(request.toString());

		return tr;
	}



	protected ResponseMessage ImageRequest(ImageRequest imgRequest)
	{
		NewsResponse nr = new NewsResponse(imgRequest);

		Article tempVar = new Article();
		tempVar.setTitle("Title");
		tempVar.setDescription("Description:来源消息");
		tempVar.setPicUrl(imgRequest.getPicUrl());
		nr.getArticles().add(tempVar);

		return nr;
	}

	protected ResponseMessage VoiceRequest(VoiceRequest voiceRequest)
	{
		TextResponse tr = new TextResponse(voiceRequest);

		tr.setContent("公众号收到了一个您的语音："+voiceRequest.getRecognition());

		return tr;
	}

	protected ResponseMessage VideoRequest(VideoRequest videoRequest)
	{
		return DefaultText(videoRequest);
	}

	protected ResponseMessage LinkRequest(LinkRequest linkRequest)
	{
		return DefaultText(linkRequest);
	}

	protected ResponseMessage LocationRequest(LocationRequest locationRequest)
	{
		return DefaultText(locationRequest);
	}

	protected ResponseMessage EnterEventRequest(EventRequest evRequest)
	{
		return DefaultText(evRequest);
	}

	protected ResponseMessage SubscribeEventRequest(SubscribeEvent subscribeEvent)
	{
		return DefaultText(subscribeEvent);
	}

	protected ResponseMessage ScanEventRequest(SubscribeEvent subscribeEvent)
	{
		return DefaultText(subscribeEvent);
	}

	protected ResponseMessage MassSendFinishEventRequest(MassSendFinishEvent msfRequest)
	{
		return DefaultText(msfRequest);
	}

	protected ResponseMessage ViewEventRequest(EventRequest evRequest)
	{
		return DefaultText(evRequest);
	}

	protected ResponseMessage ClickEventReqeust(EventRequest evRequest)
	{
		return DefaultText(evRequest);
	}


	protected ResponseMessage LocationEventRequest(LocationEvent leRequest)
	{
		return DefaultText(leRequest);
	}

	protected ResponseMessage UnsubscribeEventRequest(EventRequest evRequest)
	{
		return DefaultText(evRequest);
	}

	@Override
	public AccessToken getAccessToken(String oid) {
		throw new WeixinException("未实现此方法！");
	}
}