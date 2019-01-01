package org.netsharp.wx.sdk.tp.bean.response.user;

import java.util.List;

import org.netsharp.wx.sdk.tp.bean.entity.user.Tag;
import org.netsharp.wx.sdk.tp.bean.response.BaseResponse;

/**
 * 用户标签信息列表返回.
 *
 * @author vioao
 */
public class TagsResponse extends BaseResponse {
    private List<Tag> tags;

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "TagResponse{"
                + ", tags=" + tags
                + ", errcode=" + super.getErrcode()
                + ", errmsg='" + super.getErrmsg() + '\''
                + '}';
    }
}
