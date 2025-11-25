package dev.acrispycookie.crispycommons.api.gui.menu;

import dev.acrispycookie.crispycommons.SpigotCrispyCommons;
import dev.acrispycookie.crispycommons.api.gui.abstraction.TrackedGui;
import dev.acrispycookie.crispycommons.api.gui.menu.section.DynamicSection;
import dev.acrispycookie.crispycommons.api.itemstack.CrispyItemStack;
import dev.acrispycookie.crispycommons.implementations.gui.abstraction.builder.AbstractGuiBuilder;
import dev.acrispycookie.crispycommons.implementations.gui.menu.PagedMenu;
import dev.acrispycookie.crispycommons.implementations.gui.menu.SimpleMenu;
import dev.acrispycookie.crispycommons.implementations.gui.menu.data.MenuData;
import dev.acrispycookie.crispycommons.implementations.gui.menu.data.PagedMenuData;
import dev.acrispycookie.crispycommons.logging.CrispyLogger;
import dev.acrispycookie.crispycommons.utility.menu.InvalidMenuConfiguration;
import dev.acrispycookie.crispycommons.utility.menu.PageInfo;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.BiFunction;

/**
 * Represents a customizable menu interface for managing and displaying in-game menus.
 * <p>
 * A {@link CrispyMenu} can be opened, closed, and tracked for each player. It supports page navigation and
 * properties that influence its behavior.
 * </p>
 */
public interface CrispyMenu extends TrackedGui, Listener {

    /**
     * A map that tracks currently open menus for each player.
     */
    Map<Player, CrispyMenu> openMenus = new HashMap<>();

    /**
     * A map that tracks the history of menus for each player.
     */
    Map<Player, Stack<CrispyMenu>> history = new HashMap<>();

    /**
     * Creates a new instance of {@link MenuBuilder} to construct a standard CrispyMenu.
     *
     * @return a new {@link MenuBuilder} instance.
     */
    static @NotNull MenuBuilder builder() {
        return new MenuBuilder();
    }

    /**
     * Creates a new instance of {@link PagedMenuBuilder} for constructing a paged menu with the specified title format, rows, and columns.
     *
     * @param title        a function to generate the title of each page based on the current page number and the total pages.
     * @param rows         the number of rows in each page.
     * @param columns      the number of columns in each page.
     * @return a new {@link PagedMenuBuilder} instance.
     * @throws NullPointerException if the {@code title} is {@code null}.
     */
    static @NotNull PagedMenuBuilder pagedMenuBuilder(@NotNull BiFunction<Integer, Integer, String> title, int rows, int columns) {
        return new PagedMenuBuilder(title, rows, columns);
    }

    /**
     * Creates a new instance of {@link PagedMenuBuilder} for constructing a paged menu with the specified title format, default starting page, rows, and columns.
     *
     * @param title         a function to generate the title of each page based on the current page number and the total pages.
     * @param rows          the number of rows in each page.
     * @param columns       the number of columns in each page.
     * @param defaultPage  the default page that is initially displayed.
     * @return a new {@link PagedMenuBuilder} instance.
     * @throws NullPointerException if {@code title} is {@code null}.
     */
    static @NotNull PagedMenuBuilder pagedMenuBuilder(@NotNull BiFunction<Integer, Integer, String> title, int rows, int columns, int defaultPage) {
        return new PagedMenuBuilder(title, defaultPage, rows, columns);
    }

    /**
     * Opens the menu for the specified player without adding it to their history.
     *
     * @param player the player to open the menu for.
     * @throws NullPointerException if {@code player} is {@code null}.
     */
    void openWithNoHistory(@NotNull Player player);

    /**
     * Checks if the specified player is currently changing pages.
     *
     * @param player the player to check.
     * @return true if the player is changing pages, false otherwise.
     * @throws NullPointerException if {@code player} is {@code null}.
     */
    boolean isChangingPage(@NotNull Player player);

    /**
     * Changes the current page for the specified player by the given amount.
     *
     * @param player the player.
     * @param offset the number of pages to offset by, positive or negative.
     * @throws NullPointerException if {@code player} is {@code null}.
     */
    void offsetPage(@NotNull Player player, int offset);

    /**
     * Sets the current page for the specified player.
     *
     * @param player the player.
     * @param page   the page index to set.
     * @throws NullPointerException if {@code player} is {@code null}.
     * @throws IllegalArgumentException if {@code page} is out of bounds.
     */
    void setPage(@NotNull Player player, int page);

    /**
     * Retrieves the current page number for the specified player.
     *
     * @param player the player.
     * @return the current page number.
     * @throws NullPointerException if {@code player} is {@code null}.
     */
    int getPage(@NotNull Player player);

    /**
     * Retrieves the page at the specified index.
     *
     * @param index the index of the page.
     * @return the page at the specified index, or null if no page exists at that index.
     * @throws IllegalArgumentException if {@code index} is out of bounds.
     */
    @NotNull MenuPage getPage(int index);

    /**
     * Retrieves a list of all pages in the menu.
     *
     * @return a list of pages.
     */
    @NotNull List<MenuPage> getPages();

    /**
     * Retrieves the index of the default page.
     *
     * @return the default page index.
     */
    int getDefaultPage();

    /**
     * Sets the index of the default page.
     *
     * @param pageIndex the index of the default page.
     * @throws IllegalArgumentException if {@code pageIndex} is out of bounds.
     */
    void setDefaultPage(int pageIndex);

    /**
     * Retrieves a set of properties associated with the menu.
     *
     * @return a set of menu properties.
     */
    @NotNull Set<MenuProperty> getProperties();

    /**
     * Checks if the menu has the specified property.
     *
     * @param property the property to check.
     * @return true if the property is present, false otherwise.
     * @throws NullPointerException if {@code property} is {@code null}.
     */
    boolean hasProperty(@NotNull MenuProperty property);

    /**
     * Adds a property to the menu.
     *
     * @param property the property to add.
     * @throws NullPointerException if {@code property} is {@code null}.
     */
    void addProperty(@NotNull MenuProperty property);

    /**
     * Removes a property from the menu.
     *
     * @param property the property to remove.
     * @throws NullPointerException if {@code property} is {@code null}.
     */
    void removeProperty(@NotNull MenuProperty property);

    /**
     * Sets the properties for the menu.
     *
     * @param properties a collection of properties to set.
     * @throws NullPointerException if {@code properties} or any element in it is {@code null}.
     */
    void setProperties(@NotNull Collection<MenuProperty> properties);

    /**
     * Retrieves the currently open menu for the specified player.
     *
     * @param player the player whose open menu is to be retrieved.
     * @return the open menu for the player, or null if no menu is open.
     * @throws NullPointerException if {@code player} is {@code null}.
     */
    static CrispyMenu getOpenMenu(@NotNull Player player) {
        return openMenus.get(player);
    }

    /**
     * Retrieves the history of menus for the specified player.
     *
     * @param player the player whose menu history is to be retrieved.
     * @return the stack of menus in the player's history.
     * @throws NullPointerException if {@code player} is {@code null}.
     */
    static Stack<CrispyMenu> getHistory(@NotNull Player player) {
        return history.get(player);
    }

    /**
     * Checks if the specified player has a menu history with more than one entry.
     *
     * @param player the player to check.
     * @return true if the player has history with more than one entry, false otherwise.
     * @throws NullPointerException if {@code player} is {@code null}.
     */
    static boolean hasHistory(@NotNull Player player) {
        return history.containsKey(player) && history.get(player).size() > 1;
    }

    /**
     * Opens the previous menu in the history for the specified player.
     * <p>
     * It closes the currently open menu if there is one and it does nothing
     * if the player has no history of menus.
     * </p>
     *
     * @param player the player whose previous menu is to be opened.
     * @throws NullPointerException if {@code player} is {@code null}.
     */
    static void openPrevious(@NotNull Player player) {
        if (!hasHistory(player))
            return;
        Stack<CrispyMenu> menus = getHistory(player);
        menus.pop();
        CrispyMenu toOpen = menus.peek();
        CrispyMenu toClose = getOpenMenu(player);
        if (toClose != null)
            toClose.close(player, false);
        toOpen.openWithNoHistory(player);
    }

    /**
     * A builder class for constructing instances of {@link PagedMenu}.
     */
    class PagedMenuBuilder extends AbstractGuiBuilder<CrispyMenu> {

        /**
         * The information about the pages, including title, rows, columns, and other settings.
         */
        private final PageInfo pageInfo;

        /**
         * The dynamic section of the menu, which defines items that can change across pages.
         */
        private DynamicSection section;

        /**
         * A set of properties that define the behavior and appearance of the menu.
         */
        protected final Set<MenuProperty> properties;

        /**
         * Constructs a new {@link PagedMenuBuilder} with the specified title function, rows, and columns.
         *
         * @param title a function to generate the title of each page based on the page number.
         * @param rows  the number of rows in each page.
         * @param columns the number of columns in each page.
         * @throws NullPointerException if {@code title} is {@code null}.
         */
        public PagedMenuBuilder(@NotNull BiFunction<Integer, Integer, String> title, int rows, int columns) {
            properties = new HashSet<>();
            pageInfo = new PageInfo(title, 0, rows, columns, -1, -1, (m, p) -> false);
        }

        /**
         * Constructs a new {@link PagedMenuBuilder} with the specified title function, default page, rows, and columns.
         *
         * @param title         a function to generate the title of each page based on the page number.
         * @param defaultPage   the default page number.
         * @param rows          the number of rows in each page.
         * @param columns       the number of columns in each page.
         * @throws NullPointerException if {@code title} is {@code null}.
         */
        public PagedMenuBuilder(@NotNull BiFunction<Integer, Integer, String> title, int defaultPage, int rows, int columns) {
            properties = new HashSet<>();
            pageInfo = new PageInfo(title, defaultPage, rows, columns, -1, -1, (m, p) -> false);
        }

        /**
         * Sets a function to handle item clicks in the player's inventory.
         *
         * @param function the function to handle item clicks.
         * @return the current {@link PagedMenuBuilder} instance.
         * @throws NullPointerException if {@code function} is {@code null}.
         */
        public @NotNull PagedMenuBuilder setOnPlayerItemClick(@NotNull BiFunction<Player, CrispyItemStack, Boolean> function) {
            pageInfo.setOnPlayerItemClick(function);
            return this;
        }

        /**
         * Sets the end index for pagination.
         *
         * @param endIndex the end index.
         * @return the current {@link PagedMenuBuilder} instance.
         */
        public @NotNull PagedMenuBuilder setEndIndex(int endIndex) {
            pageInfo.setEndIndex(endIndex);
            return this;
        }

        /**
         * Sets the start index for pagination.
         *
         * @param startIndex the start index.
         * @return the current {@link PagedMenuBuilder} instance.
         */
        public @NotNull PagedMenuBuilder setStartIndex(int startIndex) {
            pageInfo.setStartIndex(startIndex);
            return this;
        }

        /**
         * Sets the number of rows in each page.
         *
         * @param rows the number of rows.
         * @return the current {@link PagedMenuBuilder} instance.
         */
        public @NotNull PagedMenuBuilder setRows(int rows) {
            pageInfo.setRows(rows);
            return this;
        }

        /**
         * Sets the number of columns in each page.
         *
         * @param columns the number of columns.
         * @return the current {@link PagedMenuBuilder} instance.
         */
        public @NotNull PagedMenuBuilder setColumns(int columns) {
            pageInfo.setColumns(columns);
            return this;
        }

        /**
         * Sets the title function for each page.
         *
         * @param pageTitle a function to generate the title of each page based on the page number.
         * @return the current {@link PagedMenuBuilder} instance.
         * @throws NullPointerException if {@code pageTitle} is {@code null}.
         */
        public @NotNull PagedMenuBuilder setTitle(@NotNull BiFunction<Integer, Integer, String> pageTitle) {
            pageInfo.setPageTitle(pageTitle);
            return this;
        }

        /**
         * Sets the dynamic section of the menu.
         *
         * @param section the dynamic section.
         * @return the current {@link PagedMenuBuilder} instance.
         * @throws NullPointerException if {@code section} is {@code null}.
         */
        public @NotNull PagedMenuBuilder setSection(@NotNull DynamicSection section) {
            this.section = section;
            return this;
        }

        /**
         * Adds a property to the menu.
         *
         * @param property the property to add.
         * @return the current {@link PagedMenuBuilder} instance.
         * @throws NullPointerException if {@code property} is {@code null}.
         */
        public @NotNull PagedMenuBuilder addProperty(@NotNull MenuProperty property) {
            properties.add(property);
            return this;
        }

        /**
         * Removes a property from the menu.
         *
         * @param property the property to remove.
         * @return the current {@link PagedMenuBuilder} instance.
         * @throws NullPointerException if {@code property} is {@code null}.
         */
        public @NotNull PagedMenuBuilder removeProperty(@NotNull MenuProperty property) {
            properties.remove(property);
            return this;
        }

        /**
         * Sets multiple properties for the menu.
         *
         * @param newProperties a collection of properties to set.
         * @return the current {@link PagedMenuBuilder} instance.
         * @throws NullPointerException if {@code newProperties} or any element in it is {@code null}.
         */
        public @NotNull PagedMenuBuilder setProperties(@NotNull Collection<MenuProperty> newProperties) {
            properties.clear();
            properties.addAll(newProperties);
            return this;
        }

        /**
         * Builds the paged menu based on the current configuration.
         *
         * @return the constructed {@link CrispyMenu} instance.
         */
        @Override
        public CrispyMenu build() {
            try {
                PagedMenuData data = new PagedMenuData(section, pageInfo, properties);
                toBuild = new PagedMenu(data);
                return toBuild;
            } catch (InvalidMenuConfiguration e) {
                CrispyLogger.printException(SpigotCrispyCommons.getInstance().getPlugin(), e, "Error while creating a paged menu!");
            }
            return null;
        }
    }

    /**
     * A builder class for constructing instances of {@link SimpleMenu}.
     */
    class MenuBuilder extends AbstractGuiBuilder<CrispyMenu> {

        /**
         * The list of pages that will be included in the menu.
         */
        protected final List<MenuPage> pages;

        /**
         * The set of properties that define the behavior and appearance of the menu.
         */
        protected final Set<MenuProperty> properties;

        /**
         * The index of the page that will be displayed when the menu is first opened.
         */
        protected final int startingPage;

        /**
         * Constructs a new {@link MenuBuilder} with default page settings.
         */
        public MenuBuilder() {
            pages = new ArrayList<>();
            properties = new HashSet<>();
            startingPage = 0;
        }

        /**
         * Constructs a new {@link MenuBuilder} with the specified default page.
         *
         * @param defaultPage the default page number.
         */
        public MenuBuilder(int defaultPage) {
            startingPage = defaultPage;
            pages = new ArrayList<>();
            properties = new HashSet<>();
        }

        /**
         * Adds an empty page to the menu.
         *
         * @param title    the title of the page.
         * @param rows     the number of rows in the page.
         * @param columns  the number of columns in the page.
         * @return the current {@link MenuBuilder} instance.
         * @throws NullPointerException if {@code title} is {@code null}.
         */
        public @NotNull MenuBuilder addEmptyPage(@NotNull String title, int rows, int columns) {
            pages.add(MenuPage.createEmpty(title, rows, columns, (p, i) -> false));
            return this;
        }

        /**
         * Adds an empty page at the specified index.
         *
         * @param index    the index to add the page at.
         * @param title    the title of the page.
         * @param rows     the number of rows in the page.
         * @param columns  the number of columns in the page.
         * @return the current {@link MenuBuilder} instance.
         * @throws NullPointerException if {@code title} is {@code null}.
         */
        public @NotNull MenuBuilder addEmptyPage(int index, @NotNull String title, int rows, int columns) {
            pages.add(index, MenuPage.createEmpty(title, rows, columns, (p, i) -> false));
            return this;
        }

        /**
         * Adds a page to the menu.
         *
         * @param page the page to add.
         * @return the current {@link MenuBuilder} instance.
         * @throws NullPointerException if {@code page} is {@code null}.
         */
        public @NotNull MenuBuilder addPage(@NotNull MenuPage page) {
            pages.add(page);
            return this;
        }

        /**
         * Adds a page at the specified index.
         *
         * @param index the index to add the page at.
         * @param page  the page to add.
         * @return the current {@link MenuBuilder} instance.
         * @throws NullPointerException if {@code page} is {@code null}.
         */
        public @NotNull MenuBuilder addPage(int index, @NotNull MenuPage page) {
            pages.add(index, page);
            return this;
        }

        /**
         * Adds a property to the menu.
         *
         * @param property the property to add.
         * @return the current {@link MenuBuilder} instance.
         * @throws NullPointerException if {@code property} is {@code null}.
         */
        public @NotNull MenuBuilder addProperty(@NotNull MenuProperty property) {
            properties.add(property);
            return this;
        }

        /**
         * Removes a property from the menu.
         *
         * @param property the property to remove.
         * @return the current {@link MenuBuilder} instance.
         * @throws NullPointerException if {@code property} is {@code null}.
         */
        public @NotNull MenuBuilder removeProperty(@NotNull MenuProperty property) {
            properties.remove(property);
            return this;
        }

        /**
         * Sets multiple properties for the menu.
         *
         * @param newProperties a collection of properties to set.
         * @return the current {@link MenuBuilder} instance.
         * @throws NullPointerException if {@code newProperties} or any element in it is {@code null}.
         */
        public @NotNull MenuBuilder setProperties(@NotNull Collection<MenuProperty> newProperties) {
            properties.clear();
            properties.addAll(newProperties);
            return this;
        }

        /**
         * Builds the standard menu based on the current configuration.
         *
         * @return the constructed {@link CrispyMenu} instance.
         */
        @Override
        public @NotNull CrispyMenu build() {
            MenuData data = new MenuData(startingPage, pages, properties);
            toBuild = new SimpleMenu(data);
            return toBuild;
        }
    }

    /**
     * Enum representing different properties that can be applied to a menu.
     */
    enum MenuProperty {
        /**
         * Prevent the menu from closing by pressing E or ESC.
         */
        PREVENT_CLOSING,
        /**
         * Prevent damage to the viewers of this menu.
         */
        PREVENT_DAMAGE,
        /**
         * Prevent breaking the block under the viewers of this menu.
         */
        PREVENT_BELOW_BLOCK_BREAK
    }
}
