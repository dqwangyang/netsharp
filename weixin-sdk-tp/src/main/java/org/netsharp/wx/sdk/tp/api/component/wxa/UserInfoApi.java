package org.netsharp.wx.sdk.tp.api.component.wxa;

import org.netsharp.wx.sdk.tp.bean.entity.user.UserInfo;
import org.netsharp.wx.sdk.tp.bean.entity.user.UserPhone;
import org.netsharp.wx.sdk.tp.utils.aes.AesCBC;
import org.netsharp.wx.sdk.tp.utils.serialize.SerializeUtil;

/**
 * @author ffli <ffli@gongsibao.com>
 * @Description: TODO 微信用戶信息
 * @date 2018/7/16 19:06
 */
public class UserInfoApi {

    private String appId;

    public UserInfoApi() {}

    public UserInfoApi(String appId) {
        this.appId = appId;
    }

    /**
     * 获取用户手机号 - 解密
     *
     * @param encryptedData
     * @param sessionKey
     * @param iv
     * @return
     */
    public UserPhone getUserPhone(String encryptedData, String sessionKey, String iv) {
        try {
            byte[] decrypt = AesCBC.decrypt(encryptedData, sessionKey, iv);
            UserPhone userPhone = SerializeUtil.jsonToBean(new String(decrypt, "UTF-8"), UserPhone.class);
            if (userPhone != null) {
                if (!userPhone.getWatermark().getAppid().equals(this.appId)) {
                    throw new Exception("appId不一致");
                }
            } else {
                return new UserPhone(false, "解析失败!");
            }
            return userPhone;
        } catch (Exception e) {
            return new UserPhone(false, e.getMessage());
        }
    }

    /**
     * 获取用户信息
     *
     * @param encryptedData
     * @param sessionKey
     * @param iv
     * @return
     */
    public UserInfo getUserInfo(String encryptedData, String sessionKey, String iv) {
        try {
            byte[] decrypt = AesCBC.decrypt(encryptedData, sessionKey, iv);
            UserInfo userInfo = SerializeUtil.jsonToBean(new String(decrypt, "UTF-8"), UserInfo.class);
            if (userInfo != null) {
                if (!userInfo.getWatermark().getAppid().equals(this.appId)) {
                    throw new Exception("appId不一致");
                }
            } else {
                return new UserInfo(false, "解析失败!");
            }
            return userInfo;
        } catch (Exception e) {
            return new UserInfo(false, e.getMessage());
        }
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
}
