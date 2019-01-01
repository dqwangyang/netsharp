package org.netsharp.wx.sdk.tp.bean.entity.Card.type;

import org.netsharp.wx.sdk.tp.bean.entity.Card.CardType;
import org.netsharp.wx.sdk.tp.bean.entity.Card.base.Card;
import org.netsharp.wx.sdk.tp.bean.entity.Card.base.Gift;

/**
 * 兑换券.
 *
 * @author vioao
 */
public class GiftCard extends Card {
    private Gift gift;

    public GiftCard() {
        super(CardType.GIFT.name());
    }

    public Gift getGift() {
        return gift;
    }

    public void setGift(Gift gift) {
        this.gift = gift;
    }

    @Override
    public String toString() {
        return "GiftCard{" +
                "cardType='" + getCardType() + '\'' +
                "gift=" + gift +
                "} " + super.toString();
    }
}
