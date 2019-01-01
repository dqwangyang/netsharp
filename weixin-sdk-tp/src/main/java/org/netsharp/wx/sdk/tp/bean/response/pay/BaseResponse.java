package org.netsharp.wx.sdk.tp.bean.response.pay;

import org.netsharp.wx.sdk.tp.bean.xml.wrapper.BaseSettings;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author ffli <ffli@gongsibao.com>
 * @Description: TODO
 * @date 2018/6/27 13:52
 */
public class BaseResponse extends BaseSettings {

    @JsonProperty("return_code")
    private String returnCode;

    @JsonProperty("return_msg")
    private String returnMessage;

    @JsonProperty("result_code")
    private String resultCode;

    @JsonProperty("err_code")
    private String errorCode;

    @JsonProperty("err_code_des")
    private String errorCodeDesc;

    public boolean success() {
        return "SUCCESS".equals(returnCode) && "SUCCESS".equals(resultCode);
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorCodeDesc() {
        return errorCodeDesc;
    }

    public void setErrorCodeDesc(String errorCodeDesc) {
        this.errorCodeDesc = errorCodeDesc;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "returnCode='" + returnCode + '\'' +
                ", returnMessage='" + returnMessage + '\'' +
                ", resultCode='" + resultCode + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", errorCodeDesc='" + errorCodeDesc + '\'' +
                '}';
    }
}
