package org.netsharp.wx.sdk.mp.mch.redpack;

/**
 *
 *
 * @author kxh
 */
public enum ResultCodeEnum {
    SUCCESS("SUCCESS"), FAIL("FAIL");

    private String text;

    ResultCodeEnum(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }
}
