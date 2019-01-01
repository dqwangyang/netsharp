package org.netsharp.wx.sdk.tp.bean.entity.message.massmsg;

import java.util.HashMap;
import java.util.Map;

import org.netsharp.wx.sdk.tp.bean.entity.message.MsgType;

/**
 * 群发图片消息.
 *
 * @author vioao
 */
public class MassImageMessage extends MassMessage {
    private Map<String, String> image;

    /**
     * 构造群发图片消息.
     */
    public MassImageMessage(String mediaId) {
        super(MsgType.IMAGE.name().toLowerCase());
        image = new HashMap<>();
        image.put("media_id", mediaId);
    }

    public Map<String, String> getImage() {
        return image;
    }

    public void setImage(Map<String, String> image) {
        this.image = image;
    }
}
