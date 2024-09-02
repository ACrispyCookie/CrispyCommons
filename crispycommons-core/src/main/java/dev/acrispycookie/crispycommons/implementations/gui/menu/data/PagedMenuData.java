package dev.acrispycookie.crispycommons.implementations.gui.menu.data;

import dev.acrispycookie.crispycommons.api.gui.menu.CrispyMenu;
import dev.acrispycookie.crispycommons.api.gui.menu.MenuPage;
import dev.acrispycookie.crispycommons.api.gui.menu.section.DynamicSection;
import dev.acrispycookie.crispycommons.api.itemstack.CrispyItemStack;
import dev.acrispycookie.crispycommons.utility.menu.InvalidMenuConfiguration;
import dev.acrispycookie.crispycommons.utility.menu.PageInfo;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.BiFunction;

/**
 * Represents the data structure for a paged menu in the CrispyMenu system.
 * <p>
 * {@code PagedMenuData} extends {@link MenuData} and adds additional functionality to support
 * paginated menus, including management of dynamic sections and page information.
 * </p>
 */
public class PagedMenuData extends MenuData {

    /**
     * The {@link PageInfo} object that contains information about the menu's pages, including layout and title generation.
     */
    private final PageInfo pageInfo;

    /**
     * The {@link DynamicSection} associated with this paged menu, representing the dynamic content.
     */
    private final DynamicSection section;

    /**
     * Constructs a {@code PagedMenuData} instance with the specified dynamic section, page information, and properties.
     * <p>
     * Validates the number of columns in the page to ensure it conforms to accepted values.
     * </p>
     *
     * @param section    the {@link DynamicSection} that represents the dynamic content of the menu.
     * @param pageInfo   the {@link PageInfo} that contains information about the menu's pages.
     * @param properties a collection of {@link CrispyMenu.MenuProperty} that defines the properties of the menu.
     * @throws InvalidMenuConfiguration if the number of columns specified in {@code pageInfo} is not valid.
     */
    public PagedMenuData(@NotNull DynamicSection section, @NotNull PageInfo pageInfo, @NotNull Collection<CrispyMenu.MenuProperty> properties) throws InvalidMenuConfiguration {
        super(pageInfo.getDefaultPage(), new ArrayList<>(), properties);
        if (pageInfo.getColumns() != 9 && pageInfo.getColumns() != 3) {
            throw new InvalidMenuConfiguration("Invalid number of columns in page!");
        }
        this.pageInfo = pageInfo;
        this.section = section;
    }

    /**
     * Returns the {@link DynamicSection} associated with this paged menu.
     *
     * @return the dynamic section.
     */
    public @NotNull DynamicSection getDynamicSection() {
        return section;
    }

    /**
     * Returns the title generator function for the menu pages.
     *
     * @return a {@link BiFunction} that generates the page title based on the current page number and total page count.
     */
    public @NotNull BiFunction<Integer, Integer, String> getTitle() {
        return pageInfo.getPageTitle();
    }

    /**
     * Returns the number of columns in the menu layout.
     *
     * @return the number of columns.
     */
    public int getColumns() {
        return pageInfo.getColumns();
    }

    /**
     * Returns the number of rows in the menu layout.
     *
     * @return the number of rows.
     */
    public int getRows() {
        return pageInfo.getRows();
    }

    /**
     * Returns the starting index of the dynamic section within the menu.
     *
     * @return the starting index.
     */
    public int getStartIndex() {
        return pageInfo.getStartIndex();
    }

    /**
     * Returns the ending index of the dynamic section within the menu.
     *
     * @return the ending index.
     */
    public int getEndIndex() {
        return pageInfo.getEndIndex();
    }

    /**
     * Returns the function that handles item clicks in the player's inventory.
     *
     * @return a {@link BiFunction} that handles item clicks, returning {@code true} if a significant action was performed.
     */
    public @NotNull BiFunction<Player, CrispyItemStack, Boolean> getOnPlayerItemClick() {
        return pageInfo.getOnPlayerItemClick();
    }

    /**
     * Adds a {@link MenuPage} to the paged menu.
     *
     * @param page the page to be added.
     */
    public void addPage(@NotNull MenuPage page) {
        this.pages.add(page);
    }

    /**
     * Asserts that the paged menu is properly configured and ready to be used.
     * <p>
     * This method checks that the title, start index, end index, and dynamic section are all set. If any of these
     * elements are missing, a {@link GuiNotReadyException} is thrown.
     * </p>
     *
     * @throws GuiNotReadyException if any required data is not set.
     */
    @Override
    public void assertReady() {
        if (getStartIndex() == -1) {
            throw new GuiNotReadyException("The start index for the dynamic section on the page menu was not set.");
        }
        if (getEndIndex() == -1) {
            throw new GuiNotReadyException("The end index for the dynamic section on the page menu was not set.");
        }
        super.assertReady();
    }
}

