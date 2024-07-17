package dev.acrispycookie.crispycommons.api.guis.menu;

import dev.acrispycookie.crispycommons.api.guis.abstraction.TrackedGui;
import dev.acrispycookie.crispycommons.implementations.guis.abstraction.builder.AbstractGuiBuilder;
import dev.acrispycookie.crispycommons.implementations.guis.menu.PagedMenu;
import dev.acrispycookie.crispycommons.implementations.guis.menu.SimpleMenu;
import dev.acrispycookie.crispycommons.implementations.guis.menu.wrappers.MenuData;
import dev.acrispycookie.crispycommons.implementations.guis.menu.wrappers.PagedMenuData;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.*;


public interface CrispyMenu extends TrackedGui, Listener {

    Map<Player, CrispyMenu> openMenus = new HashMap<>();

    static MenuBuilder builder() {
        return new MenuBuilder();
    }
    static PagedMenuBuilder pagedMenuBuilder() {
        return new PagedMenuBuilder();
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

    class PagedMenuBuilder extends MenuBuilder {

        private int startIndex;
        private int endIndex;

        public PagedMenuBuilder() {
            this.startIndex = -1;
            this.endIndex = -1;
        }

        public void setEndIndex(int endIndex) {
            this.endIndex = endIndex;
        }

        public void setStartIndex(int startIndex) {
            this.startIndex = startIndex;
        }

        @Override
        public CrispyMenu build() {
            PagedMenuData data = new PagedMenuData(startingPage, pages, properties, startIndex, endIndex);
            toBuild = new PagedMenu(data);
            return toBuild;
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
