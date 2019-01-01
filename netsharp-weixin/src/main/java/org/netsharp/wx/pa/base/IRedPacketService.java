package org.netsharp.wx.pa.base;


import java.util.List;

import org.netsharp.base.IPersistableService;
import org.netsharp.wx.pa.entity.Fans;
import org.netsharp.wx.pa.entity.RedPacket;
import org.netsharp.wx.pa.entity.RedPacketUser;

/**
 * @author kxh
 */
public interface IRedPacketService extends IPersistableService<RedPacket> {
    /**
     * 创建红包
     * @param packet
     * @return
     */
    RedPacket createRedPacket(RedPacket packet);

    /**
     * 更新红包
     * @param packet
     * @return
     */
    int updateRedPacketStatus(RedPacket packet);

    /**
     * 根据ID查找红包
     * @param redPacketId
     * @return
     */
    RedPacket getRedPacketById(Integer redPacketId);

    /**
     * 根据粉丝ID，查找某用户领取的所有红包
     * @param fansId 粉丝ID
     * @return
     */
    List<RedPacketUser> getRedPacketUserListByFansId(Integer fansId);

    /**
     * 根据微信用户的OPENID，查找某用户领取的所有红包
     * @param openId 微信用户的openId
     * @return
     */
    List<RedPacketUser> getRedPacketUserListByOpenId(String openId);

    /**
     * 根据用户的id，查找某用户领取的所有红包
     * @param userId 用户id
     * @return
     */
    List<RedPacketUser> getRedPacketUserListByUserId(Integer userId);

    /**
     * 根据红包和红包活动id查询某粉丝是否已领取过红包
     *
     * @param fansId 粉丝id
     * @param redPacketId 红包活动id
     * @return
     */
    List<RedPacketUser> getRedPacketUserByFansId(Integer fansId, Integer redPacketId);

    /**
     * 根据微信用户openid和红包活动id查询某微信用户是否已领取过红包
     *
     * @param openId
     * @param redPacketId
     * @return
     */
    List<RedPacketUser> getRedPacketUserByOpenid(String openId, Integer redPacketId);

    /**
     * 发送红包接口
     * @param redPacketId 红包活动唯一ID
     * @param fans 红包接收用户
     * @return
     */
    RedPacketUser sendRedPacket(Integer redPacketId, Fans fans);
    
    RedPacketUser sendRedPacket(RedPacket redPacket, Fans fans);
}
