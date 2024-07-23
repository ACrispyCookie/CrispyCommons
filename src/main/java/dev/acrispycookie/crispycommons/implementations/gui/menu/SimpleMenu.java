package dev.acrispycookie.crispycommons.implementations.gui.menu;

import dev.acrispycookie.crispycommons.CrispyCommons;
import dev.acrispycookie.crispycommons.api.gui.menu.CrispyMenu;
import dev.acrispycookie.crispycommons.api.gui.menu.MenuPage;
import dev.acrispycookie.crispycommons.implementations.gui.abstraction.AbstractTrackedGui;
import dev.acrispycookie.crispycommons.implementations.gui.menu.data.MenuData;
import dev.acrispycookie.crispycommons.utility.menu.SizedStack;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class SimpleMenu extends AbstractTrackedGui<MenuData> implements CrispyMenu {

    public SimpleMenu(MenuData data) {
        super(data);
        data.setMenu(this);
    }

    @Override
    public void openWithNoHistory(@NotNull Player p) {
        if (isPlayerViewing(p))
            return;

        openMenus.put(p, this);
        MenuPage page = data.getPage(data.getPage(p));
        p.openInventory(page.render(p));
        viewers.put(p, true);
    }

    @Override
    public void openInternal(Player p) {
        if (isPlayerViewing(p))
            return;

        Stack<CrispyMenu> stack = history.getOrDefault(p, new SizedStack<>(CrispyCommons.getSettings().getMaximumMenuHistory()));
        stack.push(this);
        history.put(p, stack);
        MenuPage page = data.getPage(data.getPage(p));
        p.openInventory(page.render(p));
        openMenus.put(p, this);
        viewers.put(p, true);
    }

    @Override
    public void closeInternal(Player p) {
        if (!isPlayerViewing(p))
            return;

        openMenus.remove(p);
        data.onPageClose(p);
        viewers.put(p, false);
        if (p.getOpenInventory() != null)
            p.closeInventory();
    }

    @Override
    public void offsetPage(@NotNull OfflinePlayer player, int offset) {
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

    @Override
    public void setPage(@NotNull OfflinePlayer player, int page) {
        if (!isPlayerViewing(player))
            return;

        data.setPage(player, page);
    }

    @Override
    public int getPage(@NotNull OfflinePlayer player) {
        return data.getPage(player);
    }

    @Override
    public @NotNull MenuPage getPage(int index) {
        if (index < 0 || index >= data.getPages().size())
            throw new IllegalArgumentException("Invalid page index given when getting a menu page");
        return data.getPage(index);
    }

    @Override
    public @NotNull List<MenuPage> getPages() {
        return data.getPages();
    }

    @Override
    public int getDefaultPage() {
        return data.getStartingPage();
    }

    @Override
    public void setDefaultPage(int pageIndex) {
        if (pageIndex < 0 || pageIndex >= data.getPages().size())
            throw new IllegalArgumentException("Invalid page index given when changing the default page index");
        data.setStartingPage(pageIndex);
    }

    @Override
    public @NotNull Set<MenuProperty> getProperties() {
        return data.getProperties();
    }

    @Override
    public boolean hasProperty(@NotNull MenuProperty property) {
        return data.getProperties().contains(property);
    }

    @Override
    public void addProperty(@NotNull MenuProperty property) {
        data.addProperty(property);
    }

    @Override
    public void removeProperty(@NotNull MenuProperty property) {
        data.removeProperty(property);
    }

    @Override
    public void setProperties(@NotNull Collection<MenuProperty> properties) {
        data.setProperties(properties);
    }

    @Override
    public boolean isChangingPage(@NotNull Player player) {
        return data.isChangingPage(player);
    }
}
