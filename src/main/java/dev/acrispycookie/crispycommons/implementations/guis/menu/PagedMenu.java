package dev.acrispycookie.crispycommons.implementations.guis.menu;

import dev.acrispycookie.crispycommons.implementations.guis.menu.wrappers.MenuData;

public class PagedMenu extends SimpleMenu {

    public PagedMenu(MenuData data) {
        super(data);
        data.setMenu(this);
    }
}
