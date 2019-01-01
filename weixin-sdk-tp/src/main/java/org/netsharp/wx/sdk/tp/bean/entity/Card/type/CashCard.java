package org.netsharp.wx.sdk.tp.bean.entity.Card.type;

import org.netsharp.wx.sdk.tp.bean.entity.Card.CardType;
import org.netsharp.wx.sdk.tp.bean.entity.Card.base.Card;
import org.netsharp.wx.sdk.tp.bean.entity.Card.base.Cash;

/**
 * 代金券.
 *
 * @author vioao
 */
public class CashCard extends Card {
    private Cash cash;

    public CashCard() {
        super(CardType.CASH.name());
    }

    public Cash getCash() {
        return cash;
    }

    public void setCash(Cash cash) {
        this.cash = cash;
    }

    @Override
    public String toString() {
        return "CashCard{" +
                "cardType='" + getCardType() + '\'' +
                "cash=" + cash +
                "} " + super.toString();
    }
}
