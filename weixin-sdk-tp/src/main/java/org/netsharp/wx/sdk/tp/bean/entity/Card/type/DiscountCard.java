package org.netsharp.wx.sdk.tp.bean.entity.Card.type;


import org.netsharp.wx.sdk.tp.bean.entity.Card.CardType;
import org.netsharp.wx.sdk.tp.bean.entity.Card.base.Card;
import org.netsharp.wx.sdk.tp.bean.entity.Card.base.Discount;

/**
 * 折扣券.
 *
 * @author vioao
 */
public class DiscountCard extends Card {
    private Discount discount;

    public DiscountCard() {
        super(CardType.DISCOUNT.name());
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "DiscountCard{" +
                "cardType='" + getCardType() + '\'' +
                "discount=" + discount +
                "} " + super.toString();
    }
}
