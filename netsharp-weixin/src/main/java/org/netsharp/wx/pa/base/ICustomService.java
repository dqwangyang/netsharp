package org.netsharp.wx.pa.base;

import java.util.List;

import org.netsharp.wx.sdk.mp.api.Response;
import org.netsharp.wx.sdk.mp.api.customessage.CustomMessageRequest;
import org.netsharp.wx.sdk.mp.api.customservice.OnlineKfInfo;

/**
 * Created by kxh on 2015/3/26.
 */
public interface ICustomService {

	/**
	 * 获取客服的在线状态信息
	 *
	 * @return
	 */
	List<OnlineKfInfo> getOnlineKfInfo(String originalId);

	/**
	 * 获取在线的客服数
	 *
	 * @return
	 */
	int getOnlineKfNums(String originalId);

	Response sendMessge(CustomMessageRequest messageRequest);

	Response sendTextMessage(String content, String openId,String originalId);
}
