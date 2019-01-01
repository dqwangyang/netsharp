package org.netsharp.wx.sdk.tp.bean.response.user;

import java.util.List;

import org.netsharp.wx.sdk.tp.bean.response.BaseResponse;

/**
 * 标签ID列表.
 *
 * @author vioao
 */
public class TagIdListResponse extends BaseResponse {
    private List<String> tagidList;

    public List<String> getTagidList() {
        return tagidList;
    }

    public void setTagidList(List<String> tagidList) {
        this.tagidList = tagidList;
    }

    @Override
    public String toString() {
        return "TagIdListResponse{"
                + "tagidList=" + tagidList
                + ", errcode=" + super.getErrcode()
                + ", errmsg='" + super.getErrmsg() + '\''
                + '}';
    }
}
