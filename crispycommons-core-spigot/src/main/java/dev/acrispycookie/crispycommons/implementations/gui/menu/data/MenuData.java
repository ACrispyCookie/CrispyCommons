package dev.acrispycookie.crispycommons.implementations.gui.menu.data;

import dev.acrispycookie.crispycommons.api.gui.abstraction.GuiData;
import dev.acrispycookie.crispycommons.api.gui.menu.CrispyMenu;
import dev.acrispycookie.crispycommons.api.gui.menu.MenuPage;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * Represents the data structure for a menu in the CrispyMenu system.
 * <p>
 * {@code MenuData} manages the pages, properties, and state of a menu, including the current page
 * for each player and the usage statistics of each page. It also provides methods for navigation and
 * interaction with the menu.
 * </p>
 */
public class MenuData implements GuiData, Listener {

    /**
     * The associated {@link CrispyMenu} instance that this data belongs to.
     */
    private CrispyMenu menu;

    /**
     * A set of properties that define the behavior and characteristics of the menu.
     */
    private final Set<CrispyMenu.MenuProperty> properties = new HashSet<>();

    /**
     * A list of pages in the menu, each represented by a {@link MenuPage}.
     */
    protected final ArrayList<MenuPage> pages = new ArrayList<>();

    /**
     * A map that tracks the current page each player is on, keyed by the player's UUID.
     */
    private final HashMap<OfflinePlayer, Integer> currentPages = new HashMap<>();

    /**
     * A list of players who are currently in the process of changing pages.
     */
    private final ArrayList<OfflinePlayer> currentlyChangingPage = new ArrayList<>();

    /**
     * A map that tracks how many players are currently viewing each page, keyed by the page index.
     */
    private final HashMap<Integer, Integer> pageUsage = new HashMap<>();

    /**
     * The index of the starting page when the menu is first opened.
     */
    private int startingPage;

    /**
     * Constructs a {@code MenuData} instance with the specified starting page, pages, and properties.
     *
     * @param startingPage the index of the starting page.
     * @param pages a collection of {@link MenuPage} instances that make up the menu.
     * @param properties a collection of {@link CrispyMenu.MenuProperty} that define the menu's properties.
     */
    public MenuData(int startingPage, @NotNull Collection<? extends MenuPage> pages, @NotNull Collection<CrispyMenu.MenuProperty> properties) {
        this.startingPage = startingPage;
        this.pages.addAll(pages);
        this.properties.addAll(properties);
    }

    /**
     * Retrieves the list of pages in the menu.
     *
     * @return a list of {@link MenuPage} instances.
     */
    public @NotNull List<MenuPage> getPages() {
        return pages;
    }

    /**
     * Retrieves the index of the starting page.
     *
     * @return the starting page index.
     */
    public int getStartingPage() {
        return startingPage;
    }

    /**
     * Sets the index of the starting page.
     *
     * @param startingPage the new starting page index.
     */
    public void setStartingPage(int startingPage) {
        this.startingPage = startingPage;
    }

    /**
     * Retrieves the set of properties that define the menu.
     *
     * @return a set of {@link CrispyMenu.MenuProperty} instances.
     */
    public @NotNull Set<CrispyMenu.MenuProperty> getProperties() {
        return properties;
    }

    /**
     * Removes a specific property from the menu.
     *
     * @param property the {@link CrispyMenu.MenuProperty} to remove.
     */
    public void removeProperty(@NotNull CrispyMenu.MenuProperty property) {
        properties.remove(property);
    }

    /**
     * Adds a specific property to the menu.
     *
     * @param property the {@link CrispyMenu.MenuProperty} to add.
     */
    public void addProperty(@NotNull CrispyMenu.MenuProperty property) {
        properties.add(property);
    }

    /**
     * Sets the properties of the menu, replacing any existing properties.
     *
     * @param newProperties a collection of new {@link CrispyMenu.MenuProperty} instances to set.
     */
    public void setProperties(Collection<CrispyMenu.MenuProperty> newProperties) {
        properties.clear();
        properties.addAll(newProperties);
    }

    /**
     * Offsets the current page for the specified player by the given amount.
     *
     * @param player the {@link OfflinePlayer} whose page is being offset.
     * @param offset the amount to offset the page index by.
     */
    public void offsetPage(@NotNull OfflinePlayer player, int offset) {
        if (!currentPages.containsKey(player))
            return;

        int page = currentPages.get(player) + offset;
        setPage(player, page);
    }

    /**
     * Sets the current page for the specified player to the given page index.
     *
     * @param player the {@link OfflinePlayer} whose page is being set.
     * @param page the page index to set.
     */
    public void setPage(@NotNull OfflinePlayer player, int page) {
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

    /**
     * Retrieves the current page index for the specified player.
     *
     * @param player the {@link OfflinePlayer} to check.
     * @return the current page index.
     */
    public int getPage(@NotNull OfflinePlayer player) {
        if (currentPages.containsKey(player))
            return currentPages.get(player);

        currentPages.put(player, startingPage);
        pageUsage.put(startingPage, pageUsage.getOrDefault(startingPage, 0) + 1);
        return startingPage;
    }

    /**
     * Retrieves the {@link MenuPage} at the specified index.
     *
     * @param index the index of the page to retrieve.
     * @return the {@link MenuPage} at the specified index, or {@code null} if the index is out of bounds.
     */
    public @NotNull MenuPage getPage(int index) {
        if (index < 0 || index >= pages.size())
            throw new IndexOutOfBoundsException("Menu page index is out of bounds: " + index);

        return pages.get(index);
    }

    /**
     * Handles the actions that should occur when a page is closed for a specific player.
     *
     * @param p the {@link Player} who closed the page.
     */
    public void onPageClose(@NotNull Player p) {
        int index = currentPages.get(p);
        pageUsage.put(index, pageUsage.get(index) - 1);
        currentPages.remove(p);

        if (pageUsage.get(index) == 0)
            pages.get(index).onClose();
    }

    /**
     * Sets the associated {@link CrispyMenu} instance for this menu data.
     *
     * @param menu the {@link CrispyMenu} to associate.
     */
    public void setMenu(@NotNull CrispyMenu menu) {
        this.menu = menu;
        this.pages.forEach(p -> p.setMenu(menu));
    }

    /**
     * Checks if the specified player is currently changing pages.
     *
     * @param player the {@link Player} to check.
     * @return {@code true} if the player is currently changing pages, otherwise {@code false}.
     */
    public boolean isChangingPage(@NotNull Player player) {
        return currentlyChangingPage.contains(player);
    }

    /**
     * Retrieves the associated {@link CrispyMenu} instance for this menu data.
     *
     * @return the associated {@link CrispyMenu}.
     */
    public @NotNull CrispyMenu getMenu() {
        return menu;
    }

    /**
     * Asserts that the menu is properly configured and ready to be used.
     * <p>
     * This method checks that the menu has at least one page. If no pages have been added, a {@link GuiNotReadyException} is thrown.
     * </p>
     *
     * @throws GuiNotReadyException if no pages have been added to the menu.
     */
    @Override
    public void assertReady() {
        if (pages.isEmpty()) {
            throw new GuiNotReadyException("No pages have been added to the menu.");
        }
    }
}

