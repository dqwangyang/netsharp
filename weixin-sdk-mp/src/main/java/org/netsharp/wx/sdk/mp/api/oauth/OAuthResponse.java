package org.netsharp.wx.sdk.mp.api.oauth;

import org.netsharp.util.StringManager;
import org.netsharp.wx.sdk.mp.api.Response;

public class OAuthResponse extends Response {
    private String   access_token;
    private int      expires_in;
    private String   scope;
    private String   openid;
    private String   refresh_token;
    private String   nickname;
    private int      sex;                 // 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
    private String   province;
    private String   city;
    private String   country;
    private String   headimgurl;        // 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空
    private String[] privilege;       // 用户特权信息，json 数组，如微信沃卡用户为（chinaunicom） 作者注：其实这个格式称不上JSON，只是个单纯数组。
    private String   language;
    private String   unionid; //

    public final String getAccess_token() {
        return access_token;
    }

    public final void setAccess_token(String value) {
        access_token = value;
    }

    public final int getExpires_in() {
        return expires_in;
    }

    public final void setExpires_in(int value) {
        expires_in = value;
    }

    public final String getRefresh_token() {
        return refresh_token;
    }

    public final void setRefresh_token(String value) {
        refresh_token = value;
    }

    public final String getOpenid() {
        return openid;
    }

    public final void setOpenid(String value) {
        openid = value;
    }

    public final String getScope() {
        return scope;
    }

    public final void setScope(String value) {
        scope = value;
    }

    public final String getNickname() {
        return nickname;
    }

    public final void setNickname(String value) {
        nickname = value;
    }

    public final int getSex() {
        return sex;
    }

    public final void setSex(int value) {
        sex = value;
    }

    public final String getProvince() {
        return province;
    }

    public final void setProvince(String value) {
        province = value;
    }

    public final String getCity() {
        return city;
    }

    public final void setCity(String value) {
        city = value;
    }

    public final String getCountry() {
        return country;
    }

    public final void setCountry(String value) {
        country = value;
    }

    public final String getHeadimgurl() {
        return headimgurl;
    }

    public final void setHeadimgurl(String value) {
        headimgurl = value;
    }

    public final String[] getPrivilege() {
        return privilege;
    }

    public final void setPrivilege(String[] value) {
        privilege = value;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    /*当前对象是否拥有userinfo信息*/
    public boolean isSnsapi_userinfo() {
        return StringManager.isNullOrEmpty(this.getNickname());
    }
}