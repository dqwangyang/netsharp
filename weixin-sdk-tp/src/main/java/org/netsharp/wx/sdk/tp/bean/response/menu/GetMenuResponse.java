package org.netsharp.wx.sdk.tp.bean.response.menu;

import java.util.List;

import org.netsharp.wx.sdk.tp.bean.entity.menu.MenuButtons;
import org.netsharp.wx.sdk.tp.bean.response.BaseResponse;

public class GetMenuResponse extends BaseResponse {
    private MenuButtons menu;
    private List<MenuButtons> conditionalmenu;

    public MenuButtons getMenu() {
        return menu;
    }

    public void setMenu(MenuButtons menu) {
        this.menu = menu;
    }

    public List<MenuButtons> getConditionalmenu() {
        return conditionalmenu;
    }

    public void setConditionalmenu(List<MenuButtons> conditionalmenu) {
        this.conditionalmenu = conditionalmenu;
    }

    @Override
    public String toString() {
        return "GetMenuResponse{"
                + "menu=" + menu
                + ", conditionalmenu=" + conditionalmenu
                + ", errcode=" + super.getErrcode()
                + ", errmsg='" + super.getErrmsg() + '\''
                + "} ";
    }
}
