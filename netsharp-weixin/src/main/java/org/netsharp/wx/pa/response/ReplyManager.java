package org.netsharp.wx.pa.response;

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.wx.pa.entity.NReply;

public class ReplyManager {
    //key:公众账号的原始Id
    private static HashMap<String, PublicAccountReply> replies = new HashMap<String, PublicAccountReply>();
    private static Log                                 logger  = LogFactory.getLog(ReplyManager.class.getSimpleName());
    private static ReentrantLock                       lock    = new ReentrantLock();

    public static NReply getReply(String keywords, String oid) {
        if (keywords == null) {
            return null;
        }

        PublicAccountReply accountReply = getPublicAccountReply(oid);

        // 将所有的关键字转为小写，以免用户输入大小写混合的消息，导致无法匹配
        keywords = keywords.toUpperCase();

        return accountReply.getReply(keywords);
    }

    public static PublicAccountReply getPublicAccountReply(String oid) {
        PublicAccountReply accountReply = replies.get(oid);
        if (accountReply != null && accountReply.getPublicAccount() != null) {
            return accountReply;
        }
        try {
            lock.lock();
            accountReply = replies.get(oid);
            if (accountReply == null) {
                accountReply = new PublicAccountReply();
                accountReply.setOriginalId(oid);

                replies.put(oid, accountReply);
                try {
                    accountReply.reload(oid);
                } catch (Exception e) {
                    logger.error("accountReply.reload", e);
                }
            } else if (accountReply.getPublicAccount() == null) {
                accountReply.setOriginalId(oid);
                accountReply.reload(oid);
            }
            return accountReply;
        } finally {
            lock.unlock();
        }
    }
}