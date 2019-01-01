package org.netsharp.wx.sdk.tp.bean.xml.in;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author ffli <ffli@gongsibao.com>
 * @Description: TODO 取消授权
 * @date 2018/6/23 11:06
 */
@XmlRootElement(name = "xml")
public class ComponentUnauthorized {

    private String appId;
    private long createTime;
    private String infoType;
    private String authorizerAppid;

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
}
