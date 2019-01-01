package org.netsharp.wx.sdk.tp.bean.response.material;

import java.util.List;

import org.netsharp.wx.sdk.tp.bean.entity.message.Article;
import org.netsharp.wx.sdk.tp.bean.response.BaseResponse;

/**
 * 图文素材返回.
 *
 * @author vioao
 */
public class NewsItemResponse extends BaseResponse {
    private List<Article> newsItem;

    public List<Article> getNewsItem() {
        return newsItem;
    }

    public void setNewsItem(List<Article> newsItem) {
        this.newsItem = newsItem;
    }

    @Override
    public String toString() {
        return "GetMaterialNewsResponse{"
                + "newsItem=" + newsItem
                + ", errcode=" + super.getErrcode()
                + ", errmsg='" + super.getErrmsg() + '\''
                + '}';
    }
}
