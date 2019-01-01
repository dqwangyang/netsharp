package org.netsharp.wx.sdk.tp.bean.entity.user;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author ffli <ffli@gongsibao.com>
 * @Description: TODO 用户手机号
 * @date 2018/7/17 11:32
 */
public class UserInfo extends BaseBean{

    private @JsonProperty("openId") String openId;
    private @JsonProperty("nickName") String nickName;
    private @JsonProperty("gender") int gender;
    private @JsonProperty("language") String language;
    private @JsonProperty("city") String city;
    private @JsonProperty("province") String province;
    private @JsonProperty("country") String country;
    private @JsonProperty("avatarUrl") String avatarUrl;
    private @JsonProperty("unionId") String unionId;

    public UserInfo(){}
    public UserInfo(boolean success,String message){
        super(success,message);
    }


    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }
}
