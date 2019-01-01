package org.netsharp.wx.sdk.tp.bean.response.component.wxa;

import java.util.List;

import org.netsharp.wx.sdk.tp.bean.response.BaseResponse;

public class GetPageResponse extends BaseResponse {
    private List<String> pageList; //page_list 页面配置列表

    public List<String> getPageList() {
        return pageList;
    }

    public void setPageList(List<String> pageList) {
        this.pageList = pageList;
    }

    @Override
    public String toString() {
        return "GetPageResponse{"
                + "pageList=" + pageList
                + ", errcode=" + super.getErrcode()
                + ", errmsg='" + super.getErrmsg() + '\''
                + '}';
    }
}
