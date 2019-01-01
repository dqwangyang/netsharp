package org.netsharp.wx.sdk.tp.bean.xml.out;

import org.netsharp.wx.sdk.tp.bean.entity.message.MsgType;

public class XmlImageMessage extends XmlMessage {
    private String mediaId;

    public XmlImageMessage(String toUserName, String fromUserName, String mediaId) {
        super(toUserName, fromUserName, MsgType.IMAGE.name().toLowerCase());
        this.mediaId = mediaId;
    }

    @Override
    public String subXml() {
        return "<Image><MediaId><![CDATA[" + mediaId + "]]></MediaId></Image>";
    }
}
