package org.netsharp.wx.sdk.tp.bean.response.menu;


import java.util.List;

import org.netsharp.wx.sdk.tp.bean.entity.menu.Button;
import org.netsharp.wx.sdk.tp.bean.response.BaseResponse;

public class TryMatchResponse extends BaseResponse {
    private List<Button> button;

    public List<Button> getButton() {
        return button;
    }

    public void setButton(List<Button> button) {
        this.button = button;
    }

    @Override
    public String toString() {
        return "TryMatchResponse{"
                + "button=" + button
                + ", errcode=" + super.getErrcode()
                + ", errmsg='" + super.getErrmsg() + '\''
                + '}';
    }
}
