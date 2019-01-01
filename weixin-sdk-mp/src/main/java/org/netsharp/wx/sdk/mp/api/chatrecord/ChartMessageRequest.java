package org.netsharp.wx.sdk.mp.api.chatrecord;

import org.apache.commons.lang.NotImplementedException;
import org.netsharp.wx.sdk.mp.api.Request;

/** 
 多客服聊天记录
 目前这个接口从wiki文档上看有问题  分页查询  不知道总数 没法查
 暂时不实现
*/

//[Api("多客服聊天记录")]
public class ChartMessageRequest extends Request<ChartMessageResponse>
{
	@Override
	public String getUrl()
	{
		throw new NotImplementedException();
	}

	@Override
	protected ChartMessageResponse doResponse()
	{
		throw new NotImplementedException();
	}
}