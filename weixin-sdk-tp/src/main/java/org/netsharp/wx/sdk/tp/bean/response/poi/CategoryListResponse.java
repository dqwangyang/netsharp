package org.netsharp.wx.sdk.tp.bean.response.poi;

import java.util.List;

import org.netsharp.wx.sdk.tp.bean.response.BaseResponse;

/**
 * 门店类目列表返回.
 *
 * @author vioao
 */
public class CategoryListResponse extends BaseResponse {
    private List<String> categoryList;

    public List<String> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<String> categoryList) {
        this.categoryList = categoryList;
    }

    @Override
    public String toString() {
        return "CategoryListResponse{"
                + "categoryList=" + categoryList
                + ", errcode=" + super.getErrcode()
                + ", errmsg='" + super.getErrmsg() + '\''
                + '}';
    }
}
