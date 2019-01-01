package org.netsharp.wx.sdk.tp.bean.entity.user;


import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author ffli <ffli@gongsibao.com>
 * @Description: TODO 用户手机号
 * @date 2018/7/16 19:08
 */
public class UserPhone extends BaseBean{

    private @JsonProperty("phoneNumber") String phoneNumber;
    private @JsonProperty("purePhoneNumber") String purePhoneNumber;
    private @JsonProperty("countryCode") String countryCode;

    public UserPhone(){}
    public UserPhone(boolean success,String message){
        super(success,message);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPurePhoneNumber() {
        return purePhoneNumber;
    }

    public void setPurePhoneNumber(String purePhoneNumber) {
        this.purePhoneNumber = purePhoneNumber;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
