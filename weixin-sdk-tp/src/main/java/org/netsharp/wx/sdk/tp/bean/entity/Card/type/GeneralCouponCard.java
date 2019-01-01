package org.netsharp.wx.sdk.tp.bean.entity.Card.type;

import org.netsharp.wx.sdk.tp.bean.entity.Card.CardType;
import org.netsharp.wx.sdk.tp.bean.entity.Card.base.Card;
import org.netsharp.wx.sdk.tp.bean.entity.Card.base.GeneralCoupon;

/**
 * 优惠券.
 *
 * @author vioao
 */
public class GeneralCouponCard extends Card {
    private GeneralCoupon generalCoupon;

    public GeneralCouponCard() {
        super(CardType.GIFT.name());
    }

    public GeneralCoupon getGeneralCoupon() {
        return generalCoupon;
    }

    public void setGeneralCoupon(GeneralCoupon generalCoupon) {
        this.generalCoupon = generalCoupon;
    }

    @Override
    public String toString() {
        return "GeneralCouponCard{" +
                "cardType='" + getCardType() + '\'' +
                "generalCoupon=" + generalCoupon +
                "} " + super.toString();
    }
}
