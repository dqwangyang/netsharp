package org.netsharp.wx.sdk.tp.bean.response.component.wxa;

import java.util.List;

import org.netsharp.wx.sdk.tp.bean.entity.component.wxa.Category;
import org.netsharp.wx.sdk.tp.bean.response.BaseResponse;

public class GetCategoryResponse extends BaseResponse {
    private List<Category> categoryList; //可填选的类目列表

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    @Override
    public String toString() {
        return "GetCategoryResponse{"
                + "categoryList=" + categoryList
                + ", errcode=" + super.getErrcode()
                + ", errmsg='" + super.getErrmsg() + '\''
                + '}';
    }
}
