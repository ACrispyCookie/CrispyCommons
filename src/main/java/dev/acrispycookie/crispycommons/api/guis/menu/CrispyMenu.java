package dev.acrispycookie.crispycommons.api.guis.menu;

import dev.acrispycookie.crispycommons.api.guis.abstraction.CrispyGui;
import dev.acrispycookie.crispycommons.implementations.guis.abstraction.builder.AbstractGuiBuilder;
import dev.acrispycookie.crispycommons.implementations.guis.menu.SimpleMenu;
import dev.acrispycookie.crispycommons.implementations.guis.menu.wrappers.MenuData;
import org.bukkit.event.Listener;

import java.util.ArrayList;


public interface CrispyMenu extends CrispyGui, Listener {

    static MenuBuilder builder() {
        return new MenuBuilder();
    }

    class MenuBuilder extends AbstractGuiBuilder<CrispyMenu> {

        private final ArrayList<MenuPage> pages;
        private final int startingPage;

        public MenuBuilder() {
            pages = new ArrayList<>();
            startingPage = 0;
        }

        public MenuBuilder(int defaultPage) {
            startingPage = defaultPage;
            pages = new ArrayList<>();
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

        @Override
        public CrispyMenu build() {
            MenuData data = new MenuData(startingPage, pages);
            toBuild = new SimpleMenu(data);
            return toBuild;
        }
    }
}
