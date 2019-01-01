package org.netsharp.wx.sdk.tp.bean.response.poi;

import org.netsharp.wx.sdk.tp.bean.entity.poi.BusinessResult;
import org.netsharp.wx.sdk.tp.bean.response.BaseResponse;

/**
 * 门店信息返回.
 *
 * @author vioao
 */
public class PoiResponse extends BaseResponse {
    private BusinessResult business;

    public BusinessResult getBusiness() {
        return business;
    }

    public void setBusiness(BusinessResult business) {
        this.business = business;
    }

    @Override
    public String toString() {
        return "PoiResponse{"
                + "business=" + business
                + ", errcode=" + super.getErrcode()
                + ", errmsg='" + super.getErrmsg() + '\''
                + '}';
    }
}
