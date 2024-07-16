package dev.acrispycookie.crispycommons.implementations.guis.menu.wrappers;

import dev.acrispycookie.crispycommons.api.guis.abstraction.GuiData;
import dev.acrispycookie.crispycommons.api.guis.menu.CrispyMenu;
import dev.acrispycookie.crispycommons.api.guis.menu.MenuPage;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.*;

public class MenuData implements GuiData, Listener {

    private CrispyMenu menu;
    private final Set<CrispyMenu.MenuProperty> properties = new HashSet<>();
    private final ArrayList<MenuPage> pages = new ArrayList<>();
    private final HashMap<OfflinePlayer, Integer> currentPages = new HashMap<>();
    private final ArrayList<OfflinePlayer> currentlyChangingPage = new ArrayList<>();
    private final HashMap<Integer, Integer> pageUsage = new HashMap<>();
    private int startingPage;

    public MenuData(int startingPage, Collection<? extends MenuPage> pages, Collection<CrispyMenu.MenuProperty> properties) {
        this.startingPage = startingPage;
        this.pages.addAll(pages);
        this.properties.addAll(properties);
    }

    public List<MenuPage> getPages() {
        return pages;
    }

    public int getStartingPage() {
        return startingPage;
    }

    public void setStartingPage(int startingPage) {
        this.startingPage = startingPage;
    }

    public Set<CrispyMenu.MenuProperty> getProperties() {
        return properties;
    }

    public void removeProperty(CrispyMenu.MenuProperty property) {
        properties.remove(property);
    }

    public void addProperty(CrispyMenu.MenuProperty property) {
        properties.add(property);
    }

    public void setProperties(Collection<CrispyMenu.MenuProperty> newProperties) {
        properties.clear();
        properties.addAll(newProperties);
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
            onPageClose((Player) player);
            currentPages.put(player, page);
            currentlyChangingPage.add(player);
            ((Player) player).openInventory(pages.get(page).render((Player) player));
            currentlyChangingPage.remove(player);
        } else {
            currentPages.put(player, page);
        }
        pageUsage.put(page, pageUsage.getOrDefault(page, 0) + 1);
    }

    public int getPage(OfflinePlayer player) {
        if (currentPages.containsKey(player))
            return currentPages.get(player);

        currentPages.put(player, startingPage);
        pageUsage.put(startingPage, pageUsage.getOrDefault(startingPage, 0) + 1);
        return startingPage;
    }

    public MenuPage getPage(int index) {
        if (index < 0 || index >= pages.size())
            return null;

        return pages.get(index);
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
        this.pages.forEach(p -> p.setMenu(menu));
    }

    public boolean isChangingPage(Player player) {
        return currentlyChangingPage.contains(player);
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
