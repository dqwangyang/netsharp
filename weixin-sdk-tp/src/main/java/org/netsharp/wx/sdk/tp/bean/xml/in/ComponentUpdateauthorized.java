package org.netsharp.wx.sdk.tp.bean.xml.in;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author ffli <ffli@gongsibao.com>
 * @Description: TODO 更新授权
 * @date 2018/6/23 11:06
 */
@XmlRootElement(name = "xml")
public class ComponentUpdateauthorized {

    private String appId;
    private long createTime;
    private String infoType;
    private String authorizerAppid;
    private String authorizationCode;
    private long authorizationCodeExpiredTime;
    private String preAuthCode;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getInfoType() {
        return infoType;
    }

    public void setInfoType(String infoType) {
        this.infoType = infoType;
    }

    public String getAuthorizerAppid() {
        return authorizerAppid;
    }

    public void setAuthorizerAppid(String authorizerAppid) {
        this.authorizerAppid = authorizerAppid;
    }

    public String getAuthorizationCode() {
        return authorizationCode;
    }

    public void setAuthorizationCode(String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    public long getAuthorizationCodeExpiredTime() {
        return authorizationCodeExpiredTime;
    }

    public void setAuthorizationCodeExpiredTime(long authorizationCodeExpiredTime) {
        this.authorizationCodeExpiredTime = authorizationCodeExpiredTime;
    }

    public String getPreAuthCode() {
        return preAuthCode;
    }

    public void setPreAuthCode(String preAuthCode) {
        this.preAuthCode = preAuthCode;
    }

    @Override
    public String toString() {
        return "ComponentUpdateauthorized{" +
                "appId='" + appId + '\'' +
                ", createTime=" + createTime +
                ", infoType='" + infoType + '\'' +
                ", authorizerAppid='" + authorizerAppid + '\'' +
                ", authorizationCode='" + authorizationCode + '\'' +
                ", authorizationCodeExpiredTime=" + authorizationCodeExpiredTime +
                ", preAuthCode='" + preAuthCode + '\'' +
                '}';
    }
}
