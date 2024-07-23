package dev.acrispycookie.crispycommons.implementations.gui.menu;

import dev.acrispycookie.crispycommons.api.gui.menu.MenuPage;
import dev.acrispycookie.crispycommons.api.gui.menu.section.DynamicSection;
import dev.acrispycookie.crispycommons.api.gui.menu.section.StaticSection;
import dev.acrispycookie.crispycommons.implementations.gui.menu.data.PagedMenuData;

import java.awt.*;


public class PagedMenu extends SimpleMenu {


    public PagedMenu(PagedMenuData data) {
        super(data);
        data.setMenu(this);

        DynamicSection section = data.getDynamicSection();
        int startX = data.getStartIndex() % data.getColumns();
        int endX = data.getEndIndex() % data.getColumns();
        int width = endX - startX + 1;
        int height = ((data.getEndIndex() - endX) - (data.getStartIndex() - startX)) / data.getColumns() + 1;
        int size = width * height;

        int pagesNeeded = section.getItems().size() / size + (section.getItems().size() % size == 0 ? 0 : 1);
        for (int i = 0; i < pagesNeeded; i++) {
            MenuPage page = MenuPage.createEmpty(data.getTitle().apply(i + 1, pagesNeeded), 6, 9, (player, itemStack) -> false);
            if (page == null)
                continue;
            page.addDynamicSection(data.getStartIndex(), data.getEndIndex(), i * size, section);
            page.setMenu(this);
            data.addPage(page);
        }
    }

    public void addStaticSection(Point point, int sectionOffset, StaticSection section) {
        if (isValidPoint(point))
            addStaticSection(pointToIndex(point), sectionOffset, section);
    }

    public void addStaticSection(int startIndex, int sectionOffset, StaticSection section) {
        if(!isValidSlot(startIndex + sectionOffset) || sectionOffset < 0)
            return;

        for (MenuPage page : data.getPages())
            page.addStaticSection(startIndex, sectionOffset, section);
    }

    private boolean isValidPoint(Point point) {
        int columns = ((PagedMenuData) data).getColumns();
        int rows = ((PagedMenuData) data).getRows();
        return point.x >= 0 && point.y >= 0 && point.x < columns && point.y < rows;
    }

    private boolean isValidSlot(int slot) {
        int columns = ((PagedMenuData) data).getColumns();
        int rows = ((PagedMenuData) data).getRows();
        return slot >= 0 && slot < columns * rows;
    }

    private int pointToIndex(Point point) {
        int columns = ((PagedMenuData) data).getColumns();
        return point.x + columns * point.y;
    }
}
