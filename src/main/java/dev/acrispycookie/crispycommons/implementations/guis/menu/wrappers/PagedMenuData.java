package dev.acrispycookie.crispycommons.implementations.guis.menu.wrappers;

import dev.acrispycookie.crispycommons.api.guis.menu.CrispyMenu;
import dev.acrispycookie.crispycommons.api.guis.menu.MenuPage;

import java.util.Collection;

public class PagedMenuData extends MenuData {

    private final int startIndex;
    private final int endIndex;

    public PagedMenuData(int startingPage, Collection<? extends MenuPage> pages, Collection<CrispyMenu.MenuProperty> properties, int startIndex, int endIndex) {
        super(startingPage, pages, properties);
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }
}
