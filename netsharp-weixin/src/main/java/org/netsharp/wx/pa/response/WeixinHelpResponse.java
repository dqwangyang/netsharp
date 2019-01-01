package org.netsharp.wx.pa.response;

import org.netsharp.util.StringManager;
import org.netsharp.wx.pa.IWeixinResponsor;
import org.netsharp.wx.pa.PublicAccountContext;
import org.netsharp.wx.pa.entity.NReply;
import org.netsharp.wx.sdk.mp.message.RequestMessage;
import org.netsharp.wx.sdk.mp.message.ResponseMessage;
import org.netsharp.wx.sdk.mp.message.request.TextRequest;
import org.netsharp.wx.sdk.mp.message.response.TextResponse;
import org.netsharp.wx.sdk.mp.util.StringHelper;

/**
 * 微信帮助回复
 */
public class WeixinHelpResponse implements IWeixinResponsor {
    public final String getKey() {
        return "帮助";
    }

    public final void setKey(String value) {
    }

    public final boolean validate(RequestMessage request) {
        TextRequest rt = (TextRequest) ((request instanceof TextRequest) ? request : null);

        if (rt == null) {
            return false;
        }

        return rt.getContent().trim().equals(this.getKey());
    }

    public final ResponseMessage response(RequestMessage request) {
//        PublicAccountReply accountReply = PublicAccountManager.getPublicAccountReply(request.getToUserName());
        String               originalId = request.getToUserName();
        PublicAccountContext pac        = PublicAccountManager.getInstance().get(originalId);

        java.util.ArrayList<String> ss = new java.util.ArrayList<String>();
        ss.add("亲，您可以发送这些关键字，我们会自动给你回复:");

        for (NReply reply : pac.getReplies().values()) {
            String memo = reply.getMemoto();
            if (StringHelper.isNullOrEmpty(memo)) {
                memo = "(无说明)";
            }
            ss.add(reply.getKeyword() + ":" + memo);
        }

        TextResponse tempVar = new TextResponse();
        {
            tempVar.setToUserName(request.getFromUserName());
            tempVar.setFromUserName(request.getToUserName());
            tempVar.setContent(StringManager.join("\r\n", ss));
        }

        TextResponse message = tempVar;

        return message;
    }
}