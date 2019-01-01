package org.netsharp.wx.sdk.tp.bean.response.user;

import org.netsharp.wx.sdk.tp.bean.entity.user.Tag;
import org.netsharp.wx.sdk.tp.bean.response.BaseResponse;

/**
 * 用户标签信息返回.
 *
 * @author vioao
 */
public class TagResponse extends BaseResponse {
    private Tag tag;

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "TagResponse{"
                + "tag=" + tag
                + ", errcode=" + super.getErrcode()
                + ", errmsg='" + super.getErrmsg() + '\''
                + '}';
    }
}
