package org.netsharp.wx.sdk.tp.bean.entity.material;


import java.util.List;

import org.netsharp.wx.sdk.tp.bean.entity.message.Article;

public class MaterialBatchgetResultItemContent {
    private List<Article> newsItem;

    public List<Article> getNewsItem() {
        return newsItem;
    }

    public void setNewsItem(List<Article> newsItem) {
        this.newsItem = newsItem;
    }

}
