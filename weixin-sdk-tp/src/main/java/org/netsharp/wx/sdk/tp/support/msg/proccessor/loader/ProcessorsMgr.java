package org.netsharp.wx.sdk.tp.support.msg.proccessor.loader;

import java.util.HashSet;

import org.netsharp.wx.sdk.tp.support.msg.proccessor.MsgProcessor;

/**
 * 微信消息处理器管理器.
 *
 * @author vioao
 */
public interface ProcessorsMgr {
    /**
     * 获取所有的消息处理器.
     */
    HashSet<MsgProcessor> getAll();
}
