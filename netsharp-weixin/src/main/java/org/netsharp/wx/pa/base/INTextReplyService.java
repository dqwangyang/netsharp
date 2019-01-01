package org.netsharp.wx.pa.base;

import java.util.List;

import org.netsharp.base.IPersistableService;
import org.netsharp.wx.pa.entity.NTextReply;
import org.netsharp.wx.pa.entity.PublicAccount;

public interface INTextReplyService extends IPersistableService<NTextReply> {
    /**
     * @param publicAccount
     * @return
     */
    List<NTextReply> getTextReplies(PublicAccount publicAccount);

    /**
     * @param publicAccountId
     * @return
     */
    List<NTextReply> getTextReplies(Integer publicAccountId);
}
