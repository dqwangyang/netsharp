package org.netsharp.wx.sdk.mp.api.customessage;

import java.util.HashMap;
import java.util.Map;

import org.netsharp.wx.sdk.mp.message.WeixinValidation;
import org.netsharp.wx.sdk.mp.message.response.Article;

//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//[Api("发送客服消息,图文")]
public class NewsCustomerMessageRequest extends CustomMessageRequest {
    private java.util.ArrayList<Article> articles;


    public NewsCustomerMessageRequest() {
        this.setArticles(new java.util.ArrayList<Article>());
    }


    @Override
    protected Object GetMessage() {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("touser", this.getOpenId());
        data.put("msgtype", "news");

        Map<String, Object> message = new HashMap<String, Object>();
        message.put("articles", this.getArticles());

        data.put("news", message);

        return data;

        //return new { touser = this.getOpenId(), msgtype = "news", news = new { articles = this.getArticles().Select(z => new { title = z.Title, description = z.getDescription(), url = z.Url, picurl = z.PicUrl }) } };
    }

    @Override
    protected WeixinValidation doValidate() {
        if (this.getArticles().isEmpty()) {
            return WeixinValidation.create(false, "Articles.Count=0");
        }

        return super.doValidate();
    }


    public final java.util.ArrayList<Article> getArticles() {
        return articles;
    }

    public final void setArticles(java.util.ArrayList<Article> value) {
        articles = value;
    }

}