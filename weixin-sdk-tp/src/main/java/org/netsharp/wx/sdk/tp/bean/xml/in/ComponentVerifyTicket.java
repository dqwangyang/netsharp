package org.netsharp.wx.sdk.tp.bean.xml.in;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 推送 component_verify_ticket协议 或 取消授权通知 XML 数据.
 */
@XmlRootElement(name = "xml")
public class ComponentVerifyTicket {

    @XmlElement(name = "AppId")
    private String appid;

    @XmlElement(name = "CreateTime")
    private Integer createTime;

    @XmlElement(name = "InfoType")
    private String infoType;

    @XmlElement(name = "ComponentVerifyTicket")
    private String componentVerifyTicket;

    @XmlElement(name = "AuthorizerAppid")
    private String authorizerAppid;

    @XmlTransient
    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    @XmlTransient
    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    @XmlTransient
    public String getInfoType() {
        return infoType;
    }

    public void setInfoType(String infoType) {
        this.infoType = infoType;
    }

    @XmlTransient
    public String getComponentVerifyTicket() {
        return componentVerifyTicket;
    }

    public void setComponentVerifyTicket(String componentVerifyTicket) {
        this.componentVerifyTicket = componentVerifyTicket;
    }

    @XmlTransient
    public String getAuthorizerAppid() {
        return authorizerAppid;
    }

    public void setAuthorizerAppid(String authorizerAppid) {
        this.authorizerAppid = authorizerAppid;
    }

    @Override
    public String toString() {
        return "ComponentMessage{" +
                "appid='" + appid + '\'' +
                ", createTime=" + createTime +
                ", infoType='" + infoType + '\'' +
                ", componentVerifyTicket='" + componentVerifyTicket + '\'' +
                ", authorizerAppid='" + authorizerAppid + '\'' +
                '}';
    }
}
