package dev.acrispycookie.crispycommons.implementations.gui.menu;

import dev.acrispycookie.crispycommons.SpigotCrispyCommons;
import dev.acrispycookie.crispycommons.api.gui.menu.CrispyMenu;
import dev.acrispycookie.crispycommons.api.gui.menu.MenuPage;
import dev.acrispycookie.crispycommons.implementations.gui.abstraction.AbstractTrackedGui;
import dev.acrispycookie.crispycommons.implementations.gui.menu.data.MenuData;
import dev.acrispycookie.crispycommons.utility.menu.SizedStack;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * A simple implementation of the {@link CrispyMenu} interface that manages a menu with multiple pages.
 * <p>
 * {@code SimpleMenu} extends {@link AbstractTrackedGui} to provide tracking of players viewing the menu and
 * offers basic functionality for navigating between pages, opening, and closing the menu.
 * </p>
 */
public class SimpleMenu extends AbstractTrackedGui<MenuData> implements CrispyMenu {

    /**
     * Constructs a {@code SimpleMenu} with the given {@link MenuData}.
     * <p>
     * The provided {@code MenuData} is associated with this menu instance, and the menu is set as the owner of the data.
     * </p>
     *
     * @param data the {@link MenuData} associated with this menu.
     */
    public SimpleMenu(@NotNull MenuData data) {
        super(data);
        data.setMenu(this);
    }

    /**
     * Opens the menu for the player without adding it to the history stack.
     * <p>
     * This method is used to open the menu without tracking the player's previous menu in the history.
     * </p>
     *
     * @param p the player for whom the menu will be opened.
     */
    @Override
    public void openWithNoHistory(@NotNull Player p) {
        if (isPlayerViewing(p))
            return;

        viewers.put(p, true);
        openMenus.put(p, this);
        MenuPage page = data.getPage(data.getPage(p));
        p.openInventory(page.render(p));
    }

    /**
     * Opens the menu for the player and adds it to the history stack.
     * <p>
     * This method tracks the menu in the player's history, allowing them to return to the previous menu.
     * </p>
     *
     * @param p the player for whom the menu will be opened.
     */
    @Override
    public void openInternal(@NotNull Player p) {
        if (isPlayerViewing(p))
            return;

        viewers.put(p, true);
        openMenus.put(p, this);
        Stack<CrispyMenu> stack = history.getOrDefault(p, new SizedStack<>(SpigotCrispyCommons.getInstance().getSettings().getMaximumMenuHistory()));
        stack.push(this);
        history.put(p, stack);
        MenuPage page = data.getPage(data.getPage(p));
        p.openInventory(page.render(p));
    }

    /**
     * Closes the menu for the player.
     * <p>
     * This method removes the menu from the player's view and performs any necessary cleanup.
     * </p>
     *
     * @param p the player for whom the menu will be closed.
     */
    @Override
    public void closeInternal(@NotNull Player p, boolean closeView) {
        if (!isPlayerViewing(p))
            return;

        openMenus.remove(p);
        data.onPageClose(p);
        viewers.put(p, false);
        if (p.getOpenInventory() != null && closeView)
            p.closeInventory();
    }

    /**
     * Adjusts the current page for the specified player by the given offset.
     * <p>
     * The offset is constrained to ensure that the page number remains within valid bounds.
     * </p>
     *
     * @param player the player whose page will be adjusted.
     * @param offset the number of pages to move forward (positive) or backward (negative).
     */
    @Override
    public void offsetPage(@NotNull Player player, int offset) {
        if (!isPlayerViewing(player))
            return;

        int currentPage = getPage(player);
        int safeOffset;
        if (offset < 0) {
            safeOffset = Math.max(offset, -currentPage);
        } else {
            safeOffset = Math.min(offset, data.getPages().size() - currentPage);
        }
        if (safeOffset == 0)
            return;

        data.offsetPage(player, safeOffset);
    }

    /**
     * Sets the current page for the specified player.
     * <p>
     * The page number is constrained to ensure it remains within valid bounds.
     * </p>
     *
     * @param player the player whose page will be set.
     * @param page the page number to set.
     */
    @Override
    public void setPage(@NotNull Player player, int page) {
        if (!isPlayerViewing(player))
            return;

        data.setPage(player, page);
    }

    /**
     * Retrieves the current page number for the specified player.
     *
     * @param player the player whose current page will be retrieved.
     * @return the current page number.
     */
    @Override
    public int getPage(@NotNull Player player) {
        return data.getPage(player);
    }

    /**
     * Retrieves the {@link MenuPage} at the specified index.
     *
     * @param index the index of the page to retrieve.
     * @return the {@link MenuPage} at the given index.
     * @throws IllegalArgumentException if the index is out of bounds.
     */
    @Override
    public @NotNull MenuPage getPage(int index) {
        if (index < 0 || index >= data.getPages().size())
            throw new IllegalArgumentException("Invalid page index given when getting a menu page");
        return data.getPage(index);
    }

    /**
     * Retrieves all the {@link MenuPage} instances associated with this menu.
     *
     * @return a list of all {@link MenuPage} instances.
     */
    @Override
    public @NotNull List<MenuPage> getPages() {
        return data.getPages();
    }

    /**
     * Retrieves the default page index for this menu.
     *
     * @return the default page index.
     */
    @Override
    public int getDefaultPage() {
        return data.getStartingPage();
    }

    /**
     * Sets the default page index for this menu.
     * <p>
     * The page index is constrained to ensure it remains within valid bounds.
     * </p>
     *
     * @param pageIndex the default page index to set.
     * @throws IllegalArgumentException if the index is out of bounds.
     */
    @Override
    public void setDefaultPage(int pageIndex) {
        if (pageIndex < 0 || pageIndex >= data.getPages().size())
            throw new IllegalArgumentException("Invalid page index given when changing the default page index");
        data.setStartingPage(pageIndex);
    }

    /**
     * Retrieves the set of {@link MenuProperty} associated with this menu.
     *
     * @return the set of {@link MenuProperty}.
     */
    @Override
    public @NotNull Set<MenuProperty> getProperties() {
        return data.getProperties();
    }

    /**
     * Checks if the specified {@link MenuProperty} is present in this menu.
     *
     * @param property the {@link MenuProperty} to check.
     * @return {@code true} if the property is present, otherwise {@code false}.
     */
    @Override
    public boolean hasProperty(@NotNull MenuProperty property) {
        return data.getProperties().contains(property);
    }

    /**
     * Adds a {@link MenuProperty} to this menu.
     *
     * @param property the {@link MenuProperty} to add.
     */
    @Override
    public void addProperty(@NotNull MenuProperty property) {
        data.addProperty(property);
    }

    /**
     * Removes a {@link MenuProperty} from this menu.
     *
     * @param property the {@link MenuProperty} to remove.
     */
    @Override
    public void removeProperty(@NotNull MenuProperty property) {
        data.removeProperty(property);
    }

    /**
     * Sets the collection of {@link MenuProperty} for this menu, replacing any existing properties.
     *
     * @param properties the collection of {@link MenuProperty} to set.
     */
    @Override
    public void setProperties(@NotNull Collection<MenuProperty> properties) {
        data.setProperties(properties);
    }

    /**
     * Checks if the player is currently in the process of changing pages.
     *
     * @param player the player to check.
     * @return {@code true} if the player is changing pages, otherwise {@code false}.
     */
    @Override
    public boolean isChangingPage(@NotNull Player player) {
        return data.isChangingPage(player);
    }
}

