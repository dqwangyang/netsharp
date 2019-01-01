package org.netsharp.wx.sdk.ep.message.send;

import org.netsharp.core.NetsharpException;

public class SendMessageMpNewsRequest extends SendMessageRequest {
	@Override
	protected Object getInnerObject(){
		
		this.setMsgtype(MessageType.mpnews);
		
		throw new NetsharpException("未实现此方法");
	}
}
