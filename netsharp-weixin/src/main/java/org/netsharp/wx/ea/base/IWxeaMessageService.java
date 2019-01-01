package org.netsharp.wx.ea.base;

import org.netsharp.base.IPersistableService;
import org.netsharp.entity.IPersistable;
import org.netsharp.wx.ea.entity.WxeaMessage;

/**
 * 微信企业号发送消息配置
 * 
 * @ClassName: IWxUserconfigurationService
 * @Description: TODO
 * @author 王阳
 * @date 2015年11月28日 下午4:27:03
 *
 */
public interface IWxeaMessageService extends IPersistableService<WxeaMessage> {

	/**
	 * 根据code查询配置信息
	 * 
	 * @param code
	 * @return
	 */
	WxeaMessage byCode(String code);
	
	/**
	 * <p>方法名称：getFixReceiversByCode</p>
	 * <p>方法描述：通过消息code获取消息固定发送人</p>
	 * @param code
	 * @return
	 * @author gaomeng
	 * @since  2016年1月5日
	 * <p> history 2016年1月5日 gaomeng  创建   <p>
	 */
	String getFixReceiversByCode(String code);
	
	/**
	 * <p>方法名称：getSendMessage</p>
	 * <p>方法描述：获取消息内容</p>
	 * @param code
	 * @param entity
	 * @return
	 * @author gaomeng
	 * @since  2016年1月5日
	 * <p> history 2016年1月5日 gaomeng  创建   <p>
	 */
	String getSendMessage(String code,IPersistable entity);
	
	
}
