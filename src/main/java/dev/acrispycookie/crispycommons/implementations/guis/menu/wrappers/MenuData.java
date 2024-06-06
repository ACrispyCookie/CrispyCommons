package dev.acrispycookie.crispycommons.implementations.guis.menu.wrappers;

import dev.acrispycookie.crispycommons.api.guis.abstraction.GuiData;
import dev.acrispycookie.crispycommons.api.guis.menu.CrispyMenu;
import dev.acrispycookie.crispycommons.api.guis.menu.MenuPage;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class MenuData implements GuiData, Listener {

    private CrispyMenu menu;
    private final ArrayList<MenuPage> pages = new ArrayList<>();
    private final HashMap<OfflinePlayer, Integer> currentPages = new HashMap<>();
    private final HashMap<Integer, Integer> pageUsage = new HashMap<>();
    private final int startingPage;

    public MenuData(int startingPage, Collection<? extends MenuPage> pages) {
        this.startingPage = startingPage;
        this.pages.addAll(pages);
        this.pages.forEach(p -> p.setMenuData(this));
    }

    public ArrayList<MenuPage> getPages() {
        return pages;
    }

    public int getStartingPage() {
        return startingPage;
    }

    public void offsetPage(OfflinePlayer player, int offset) {
        if (!currentPages.containsKey(player))
            return;

        int page = currentPages.get(player) + offset;
        setPage(player, page);
    }

    public void setPage(OfflinePlayer player, int page) {
        if (page < 0 || page >= pages.size())
            return;

        if (player instanceof Player) {
            ((Player) player).openInventory(pages.get(page).render((Player) player));
        }
        currentPages.put(player, page);
        pageUsage.put(page, pageUsage.getOrDefault(page, 0) + 1);
    }

    public MenuPage getPage(OfflinePlayer player) {
        if (currentPages.containsKey(player))
            return pages.get(currentPages.get(player));

        currentPages.put(player, startingPage);
        pageUsage.put(startingPage, pageUsage.getOrDefault(startingPage, 0) + 1);
        return pages.get(startingPage);
    }

    public void onPageClose(Player p) {
        int index = currentPages.get(p);
        pageUsage.put(index, pageUsage.get(index) - 1);
        currentPages.remove(p);

        if (pageUsage.get(index) == 0)
            pages.get(index).onClose();
    }

    public void setMenu(CrispyMenu menu) {
        this.menu = menu;
    }

    public CrispyMenu getMenu() {
        return menu;
    }

    @Override
    public void assertReady() {
        if (pages.isEmpty()) {
            throw new GuiNotReadyException("No pages have been added to the menu.");
        }
    }
}
