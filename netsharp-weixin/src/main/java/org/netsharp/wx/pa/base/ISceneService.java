package org.netsharp.wx.pa.base;

import org.netsharp.base.IPersistableService;
import org.netsharp.wx.pa.entity.Scene;

public interface ISceneService extends IPersistableService<Scene> {

    /**
     * 微信临时二维码最大过期时间7天
     */
    int MAX_EXPIRE_SECONDS = 86400 * 7;

    /**
     * 最小临时二维码场景值
     */
    int MIN_QRCODE_SCENEID = 100001;

    /**
     * 最小用户临时二维码场景值
     */
    int MIN_USER_SCENEID = MIN_QRCODE_SCENEID;

    /**
     * 最大用户临时二维码场景值,最大值2亿
     */
    int MAX_USER_SCENEID = 200000000;

    /**
     * 最小订单临时二维码场景值，最小值2亿+1
     */
    int MIN_ORDER_SCENEID = 200000001;


    /*获取一个个性二维码场景值，已经持久化状态*/
    Scene obtain(String memoto,String originalId);

    void disable(Scene scene);

    Scene getByWxSceneId(int wxSceneId);

    /**
     * 获取临时二维码
     *
     * @param sceneId       临时二维码场景值，必须大于10万
     * @param expireSeconds 以秒为单位，必须大于0
     * @return
     */
    Scene getTimeLimitQrCode(int sceneId, int expireSeconds,String originalId);

    /**
     * 根据用户ID生成临时二维码
     *
     * @param userId
     * @return
     */
    Scene getTimeLimitQrCodeByUserId(Integer userId,String originalId);

    /**
     * 根据订单ID生成临时二维码
     *
     * @param orderId
     * @return
     */
    Scene getTimeLimitQrCodeByOrderId(Integer orderId,String originalId);
}
