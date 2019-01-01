package org.netsharp.wx.sdk.tp.bean.entity.Card.type;


import org.netsharp.wx.sdk.tp.bean.entity.Card.CardType;
import org.netsharp.wx.sdk.tp.bean.entity.Card.base.Card;
import org.netsharp.wx.sdk.tp.bean.entity.Card.base.Groupon;

/**
 * 团购券.
 *
 * @author vioao
 */
public class GrouponCard extends Card {
    private Groupon groupon;

    public GrouponCard() {
        super(CardType.GROUPON.name());
    }

    public Groupon getGroupon() {
        return groupon;
    }

    public void setGroupon(Groupon groupon) {
        this.groupon = groupon;
    }

    @Override
    public String toString() {
        return "GrouponCard{" +
                "cardType='" + getCardType() + '\'' +
                "groupon=" + groupon +
                "} " + super.toString();
    }
}
