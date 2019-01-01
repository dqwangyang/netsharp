package org.netsharp.wx.sdk.tp.bean.response.card;

import org.netsharp.wx.sdk.tp.bean.response.BaseResponse;

/**
 * 创建卡券接口返回.
 *
 * @author vioao
 */
public class CreateCardResponse extends BaseResponse {
    private String cardId;

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    @Override
    public String toString() {
        return "CreateCardResponse{"
                + "errcode=" + super.getErrcode()
                + ", errmsg='" + super.getErrmsg() + '\''
                + ", cardId='" + cardId + '\''
                + '}';
    }
}
