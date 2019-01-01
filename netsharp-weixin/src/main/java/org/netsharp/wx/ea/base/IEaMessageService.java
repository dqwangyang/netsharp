package org.netsharp.wx.ea.base;

//微信企业号主动推送消息
public interface IEaMessageService {

	/**
	 * <p>方法名称：send</p>
	 * <p>方法描述：根据企业消息配置发送消息</p>
	 * @param appCode 企业消息配置的code
	 * @param content 消息文本
	 * @param userIds 发送的人员，实际发送的人员是此参数的所有人员和wxUserConfiguration对应的固定接收人的和
	 * @author gaomeng
	 * @since  2016年2月29日
	 * <p> history 2016年2月29日 gaomeng  创建   <p>
	 */
	void send(String appCode, String content, String userIds);
	
	/**
	 * <p>方法名称：send2</p>
	 * <p>方法描述：给指定应用中的某些人发送消息</p>
	 * @param wxpaConfigurationCode,微信应用代码
	 * @param content 消息文本
	 * @param userIds 发送的人员id组
	 * @author gaomeng
	 * @since  2016年3月2日
	 * <p> history 2016年3月2日 gaomeng  创建   <p>
	 */
	void send2(String appCode, String content, String userIds);
	
	/**
	 * <p>方法名称：sendAll</p>
	 * <p>方法描述：发送消息给指定应用中的所有用户</p>
	 * @param appCode
	 * @param content
	 * @author gaomeng
	 * @since  2016年2月29日
	 * <p> history 2016年2月29日 gaomeng  创建   <p>
	 */
	void sendAll(String appCode, String content);

}
