package org.netsharp.wx.pa.base;

import java.util.List;

import org.netsharp.base.IPersistableService;
import org.netsharp.wx.pa.entity.NGraphicReply;
import org.netsharp.wx.pa.entity.PublicAccount;

public interface INGraphicReplyService extends IPersistableService<NGraphicReply> {
    /**
     * @param publicAccountId
     * @return
     */
    List<NGraphicReply> getReplies(Integer publicAccountId);

    /**
     * @param publicAccount
     * @return
     */
    List<NGraphicReply> getReplies(PublicAccount publicAccount);
}
