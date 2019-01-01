package org.netsharp.wx.pa;

import java.util.List;
import java.util.Map;

import org.netsharp.wx.pa.entity.NReply;
import org.netsharp.wx.pa.entity.PublicAccount;
import org.netsharp.wx.pa.response.subscribe.IWeixinSubscriber;

public class PublicAccountContext {
    String                  originalId;
    PublicAccount           account;
    Map<String, NReply>     replies;
    List<IWeixinSubscriber> subscriberList;

    public String getOriginalId() {
        return originalId;
    }

    public void setOriginalId(String originalId) {
        this.originalId = originalId;
    }

    public PublicAccount getAccount() {
        return account;
    }

    public void setAccount(PublicAccount account) {
        this.account = account;
    }

    public Map<String, NReply> getReplies() {
        return replies;
    }

    public void setReplies(Map<String, NReply> replies) {
        this.replies = replies;
    }

    public List<IWeixinSubscriber> getSubscriberList() {
        return subscriberList;
    }

    public void setSubscriberList(List<IWeixinSubscriber> subscriberList) {
        this.subscriberList = subscriberList;
    }
}
