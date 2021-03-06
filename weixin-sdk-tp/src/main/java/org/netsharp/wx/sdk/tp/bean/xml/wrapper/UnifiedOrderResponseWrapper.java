package org.netsharp.wx.sdk.tp.bean.xml.wrapper;

import org.netsharp.wx.sdk.tp.bean.response.pay.UnifiedOrderResponse;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

/**
 * @borball on 1/13/2017.
 */
public class UnifiedOrderResponseWrapper extends BaseSettings {
    @JsonUnwrapped
    private UnifiedOrderResponse response;

    public UnifiedOrderResponse getResponse() {
        return response;
    }

    public void setResponse(UnifiedOrderResponse response) {
        this.response = response;
    }
}