package org.netsharp.wx.sdk.tp.bean.entity.message.massmsg;

import java.util.HashMap;
import java.util.Map;

import org.netsharp.wx.sdk.tp.bean.entity.message.MsgType;

/**
 * 群发文本消息.
 *
 * @author vioao
 */
public class MassTextMessage extends MassMessage {
    private Map<String, String> text;

    /**
     * 构造群发文本消息对象.
     */
    public MassTextMessage(String content) {
        super(MsgType.TEXT.name().toLowerCase());
        text = new HashMap<>();
        text.put("content", content);
    }

    public Map<String, String> getText() {
        return text;
    }

    public void setText(Map<String, String> text) {
        this.text = text;
    }
}
