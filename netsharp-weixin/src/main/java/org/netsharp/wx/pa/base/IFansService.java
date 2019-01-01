package org.netsharp.wx.pa.base;

import java.util.List;

import org.netsharp.base.IPersistableService;
import org.netsharp.core.annotations.Transaction;
import org.netsharp.wx.pa.entity.Fans;
import org.netsharp.wx.pa.entity.PublicAccount;

/*粉丝服务*/
public interface IFansService extends IPersistableService<Fans> {

    /**
     * 根据用户ID查询他的邀请人(Fans)
     *
     * @param userId
     * @return
     */
    Fans getInviterByUser(Integer userId);

    /**
     * 根据粉丝ID查找他的邀请人
     *
     * @param fansId
     * @return
     */
    Fans getInviter(Integer fansId);

    /**
     * 粉丝关注公众号
     *
     * @param openid
     * @param wcp
     * @param senceId
     * @return
     */
    @Transaction
    Fans subscribe(String openid, PublicAccount wcp, int senceId, boolean isSubscribeEvent);

    /*粉丝取消关注公众号*/
    @Transaction
    Fans unsubscribe(String openid, PublicAccount wcp);

    /*根据微信请求的用户名查找用户*/
    Fans byOpenId(String openId);

    /*
     * 查询粉丝信息，服务号且通过了认证(开通了OAuth)的情况下才使用此接口
     * 调用微信接口（UserInfoRequest），根据openid查询粉丝基本信息
     * senceId : 粉丝关注公众号时，才会传递此参数；如果oauth场景则不需要传递(传递-1即可)
     * */
    Fans queryFansInfo(String openId, PublicAccount pa);


    /*OAuth的仅OpenId模式下，附加Fans*/
    @Transaction
    Fans attachByOpenId(String code, String originalId);

    Integer getUserIdByFansId(Integer fansId);

    /**
     * 获取今天新fans
     *
     * @param fromFansId 如果为0，表示查找今天的所有fans,否则查找大于指定的fansId的所有fans
     * @return
     */
    List<Fans> getFansOfToday(Integer fromFansId);

    /**
     * 获取某段时间的新fans
     *
     * @param fromDate   起始时间, yyyy-MM-dd HH:mm:ss格式
     * @param toDate     结束时间, yyyy-MM-dd HH:mm:ss格式
     * @param fromFansId 如果为0，表示查找今天的所有fans,否则查找大于指定的fansId的所有fans
     * @return
     */
    List<Fans> getFansByDate(String fromDate, String toDate, Integer fromFansId);

    /**
     * 获取今天的新关注者
     *
     * @param fromFansId 如果为0，表示查找今天的所有fans,否则查找大于指定的fansId的所有fans
     * @return
     */
    List<Fans> getSubscriberOfToday(Integer fromFansId);

    /**
     * 获取某段时间的新关注者
     *
     * @param fromDate   起始时间, yyyy-MM-dd HH:mm:ss格式
     * @param toDate     结束时间, yyyy-MM-dd HH:mm:ss格式
     * @param fromFansId 如果为0，表示查找今天的所有fans,否则查找大于指定的fansId的所有fans
     * @return
     */
    List<Fans> getSubscriberOfToday(String fromDate, String toDate, Integer fromFansId);

    /**
     * 更新粉丝信息
     *
     * @param fans
     * @return
     */
    Integer updateFans(Fans fans);

    /**
     * 根据用户查询fans,如果有多个，只返回最近的一个
     *
     * @param userId
     * @return
     */
    Fans getFansByUserId(Integer userId);

    /**
     * 将fans 和 user 进行绑定
     *
     * @param userId
     * @param fansId
     */
    void bindUserToFans(Integer userId, Integer fansId);
}
