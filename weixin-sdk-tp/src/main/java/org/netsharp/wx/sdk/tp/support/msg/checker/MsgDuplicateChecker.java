package org.netsharp.wx.sdk.tp.support.msg.checker;

import org.netsharp.wx.sdk.tp.bean.xml.in.InMessage;

/**
 * 微信消息重复校验器.
 *
 * @author vioao
 */
public interface MsgDuplicateChecker {

    /**
     * 判断微信消息是否重复.
     *
     * @param msg 微信消息
     * @return {boolean}
     */
    boolean isDuplicate(InMessage msg);
}
