package dev.acrispycookie.crispycommons.implementations.gui.menu;

import dev.acrispycookie.crispycommons.api.gui.menu.MenuPage;
import dev.acrispycookie.crispycommons.api.gui.menu.section.DynamicSection;
import dev.acrispycookie.crispycommons.api.gui.menu.section.StaticSection;
import dev.acrispycookie.crispycommons.implementations.gui.menu.data.PagedMenuData;
import org.jetbrains.annotations.NotNull;

import java.awt.Point;

/**
 * Represents a paged menu in the CrispyMenu system.
 * <p>
 * {@code PagedMenu} extends {@link SimpleMenu} and adds support for dynamic sections that span multiple pages.
 * </p>
 */
public class PagedMenu extends SimpleMenu {

    /**
     * Constructs a {@code PagedMenu} instance with the specified data.
     * <p>
     * This constructor initializes the paged menu by calculating the number of pages needed for the dynamic section
     * and adding those pages to the menu data.
     * </p>
     *
     * @param data the {@link PagedMenuData} containing the menu's configuration.
     */
    public PagedMenu(@NotNull PagedMenuData data) {
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

    /**
     * Adds a static section to the menu at the specified point.
     *
     * @param point the starting point (row and column) for the section.
     * @param sectionOffset the offset within the section.
     * @param section the {@link StaticSection} to be added.
     */
    public void addStaticSection(@NotNull Point point, int sectionOffset, @NotNull StaticSection section) {
        if (isValidPoint(point))
            addStaticSection(pointToIndex(point), sectionOffset, section);
    }

    /**
     * Adds a static section to the menu at the specified index.
     *
     * @param startIndex the starting index for the section.
     * @param sectionOffset the offset within the section.
     * @param section the {@link StaticSection} to be added.
     */
    public void addStaticSection(int startIndex, int sectionOffset, @NotNull StaticSection section) {
        if (!isValidSlot(startIndex + sectionOffset) || sectionOffset < 0)
            return;

        for (MenuPage page : data.getPages())
            page.addStaticSection(startIndex, sectionOffset, section);
    }

    /**
     * Checks if the specified point is within the valid range of the menu.
     *
     * @param point the point (row and column) to check.
     * @return {@code true} if the point is valid; {@code false} otherwise.
     */
    private boolean isValidPoint(@NotNull Point point) {
        int columns = ((PagedMenuData) data).getColumns();
        int rows = ((PagedMenuData) data).getRows();
        return point.x >= 0 && point.y >= 0 && point.x < columns && point.y < rows;
    }

    /**
     * Checks if the specified slot index is within the valid range of the menu.
     *
     * @param slot the slot index to check.
     * @return {@code true} if the slot is valid; {@code false} otherwise.
     */
    private boolean isValidSlot(int slot) {
        int columns = ((PagedMenuData) data).getColumns();
        int rows = ((PagedMenuData) data).getRows();
        return slot >= 0 && slot < columns * rows;
    }

    /**
     * Converts a point (row and column) to a corresponding slot index.
     *
     * @param point the point to convert.
     * @return the slot index corresponding to the point.
     */
    private int pointToIndex(@NotNull Point point) {
        int columns = ((PagedMenuData) data).getColumns();
        return point.x + columns * point.y;
    }
}

