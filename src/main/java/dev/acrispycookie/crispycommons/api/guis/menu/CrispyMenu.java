package dev.acrispycookie.crispycommons.api.guis.menu;

import dev.acrispycookie.crispycommons.CrispyCommons;
import dev.acrispycookie.crispycommons.api.guis.abstraction.TrackedGui;
import dev.acrispycookie.crispycommons.api.guis.menu.sections.DynamicSection;
import dev.acrispycookie.crispycommons.api.wrappers.itemstack.CrispyItemStack;
import dev.acrispycookie.crispycommons.implementations.guis.abstraction.builder.AbstractGuiBuilder;
import dev.acrispycookie.crispycommons.implementations.guis.menu.PagedMenu;
import dev.acrispycookie.crispycommons.implementations.guis.menu.SimpleMenu;
import dev.acrispycookie.crispycommons.implementations.guis.menu.wrappers.MenuData;
import dev.acrispycookie.crispycommons.implementations.guis.menu.wrappers.PagedMenuData;
import dev.acrispycookie.crispycommons.utility.logging.CrispyLogger;
import dev.acrispycookie.crispycommons.utility.menus.InvalidMenuConfiguration;
import dev.acrispycookie.crispycommons.utility.menus.PageInfo;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.*;
import java.util.function.BiFunction;


public interface CrispyMenu extends TrackedGui, Listener {

    Map<Player, CrispyMenu> openMenus = new HashMap<>();

    static MenuBuilder builder() {
        return new MenuBuilder();
    }
    static PagedMenuBuilder pagedMenuBuilder(BiFunction<Integer, Integer, String> title, int rows, int columns) {
        return new PagedMenuBuilder(title, rows, columns);
    }
    static PagedMenuBuilder pagedMenuBuilder(BiFunction<Integer, Integer, String> title, int rows, int columns, int startingPage) {
        return new PagedMenuBuilder(title, startingPage, rows, columns);
    }

    boolean isChangingPage(Player player);
    void offsetPage(OfflinePlayer player, int offset);
    void setPage(OfflinePlayer player, int page);
    int getPage(OfflinePlayer player);
    MenuPage getPage(int index);
    List<MenuPage> getPages();
    int getDefaultPage();
    void setDefaultPage(int pageIndex);
    Set<MenuProperty> getProperties();
    boolean hasProperty(MenuProperty property);
    void addProperty(MenuProperty property);
    void removeProperty(MenuProperty property);
    void setProperties(Collection<MenuProperty> properties);

    static CrispyMenu getOpenMenu(Player player) {
        return openMenus.get(player);
    }

    class PagedMenuBuilder extends AbstractGuiBuilder<CrispyMenu> {

        private final PageInfo pageInfo;
        private DynamicSection section;
        protected final Set<MenuProperty> properties;

        public PagedMenuBuilder(BiFunction<Integer, Integer, String> title, int rows, int columns) {
            properties = new HashSet<>();
            pageInfo = new PageInfo(title, 0, rows, columns, -1, -1, (m, p) -> false);
        }

        public PagedMenuBuilder(BiFunction<Integer, Integer, String> title, int defaultPage, int rows, int columns) {
            properties = new HashSet<>();
            pageInfo = new PageInfo(title, defaultPage, rows, columns, -1, -1, (m, p) -> false);
        }

        public PagedMenuBuilder setOnPlayerItemClick(BiFunction<Player, CrispyItemStack, Boolean> function) {
            pageInfo.setOnPlayerItemClick(function);
            return this;
        }

        public PagedMenuBuilder setEndIndex(int endIndex) {
            pageInfo.setEndIndex(endIndex);
            return this;
        }

        public PagedMenuBuilder setStartIndex(int startIndex) {
            pageInfo.setStartIndex(startIndex);
            return this;
        }

        public PagedMenuBuilder setRows(int rows) {
            pageInfo.setRows(rows);
            return this;
        }

        public PagedMenuBuilder setColumns(int columns) {
            pageInfo.setColumns(columns);
            return this;
        }

        public PagedMenuBuilder setTitle(BiFunction<Integer, Integer, String> pageTitle) {
            pageInfo.setPageTitle(pageTitle);
            return this;
        }

        public PagedMenuBuilder setSection(DynamicSection section) {
            this.section = section;
            return this;
        }

        public PagedMenuBuilder addProperty(MenuProperty property) {
            properties.add(property);
            return this;
        }

        public PagedMenuBuilder removeProperty(MenuProperty property) {
            properties.remove(property);
            return this;
        }

        public PagedMenuBuilder setProperties(Collection<MenuProperty> newProperties) {
            properties.clear();
            properties.addAll(newProperties);
            return this;
        }

        @Override
        public CrispyMenu build() {
            try {
                PagedMenuData data = new PagedMenuData(section, pageInfo, properties);
                toBuild = new PagedMenu(data);
                return toBuild;
            } catch (InvalidMenuConfiguration e) {
                CrispyLogger.printException(CrispyCommons.getPlugin(), e, "Error while creating a paged menu!");
            }
            return null;
        }
    }

    class MenuBuilder extends AbstractGuiBuilder<CrispyMenu> {

        protected final List<MenuPage> pages;
        protected final Set<MenuProperty> properties;
        protected final int startingPage;

        public MenuBuilder() {
            pages = new ArrayList<>();
            properties = new HashSet<>();
            startingPage = 0;
        }

        public MenuBuilder(int defaultPage) {
            startingPage = defaultPage;
            pages = new ArrayList<>();
            properties = new HashSet<>();
        }

        public MenuBuilder addEmptyPage(String title, int rows, int columns) {
            pages.add(MenuPage.createEmpty(title, rows, columns, (p, i) -> false));
            return this;
        }

        public MenuBuilder addEmptyPage(int index, String title, int rows, int columns) {
            pages.add(index, MenuPage.createEmpty(title, rows, columns, (p, i) -> false));
            return this;
        }

        public MenuBuilder addPage(MenuPage page) {
            pages.add(page);
            return this;
        }

        public MenuBuilder addPage(int index, MenuPage page) {
            pages.add(index, page);
            return this;
        }

        public MenuBuilder addProperty(MenuProperty property) {
            properties.add(property);
            return this;
        }

        public MenuBuilder removeProperty(MenuProperty property) {
            properties.remove(property);
            return this;
        }

        public MenuBuilder setProperties(Collection<MenuProperty> newProperties) {
            properties.clear();
            properties.addAll(newProperties);
            return this;
        }

        @Override
        public CrispyMenu build() {
            MenuData data = new MenuData(startingPage, pages, properties);
            toBuild = new SimpleMenu(data);
            return toBuild;
        }
    }

    enum MenuProperty {
        PREVENT_CLOSING,
        PREVENT_DAMAGE,
        PREVENT_BELOW_BLOCK_BREAK
    }
}
