package org.netsharp.wx.sdk.tp.bean.response.poi;

import java.util.List;

import org.netsharp.wx.sdk.tp.bean.entity.poi.BusinessResult;
import org.netsharp.wx.sdk.tp.bean.response.BaseResponse;

/**
 * 门店列表信息返回.
 *
 * @author vioao
 */
public class PoiListResponse extends BaseResponse {
    private List<BusinessResult> businessList;
    private Integer totalCount;

    public List<BusinessResult> getBusinessList() {
        return businessList;
    }

    public void setBusinessList(List<BusinessResult> businessList) {
        this.businessList = businessList;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    public String toString() {
        return "PoiListResponse{"
                + "businessList=" + businessList
                + ", totalCount=" + totalCount
                + ", errcode=" + super.getErrcode()
                + ", errmsg='" + super.getErrmsg() + '\''
                + '}';
    }
}
