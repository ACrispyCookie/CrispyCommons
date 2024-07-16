package dev.acrispycookie.crispycommons.implementations.guis.menu;

import dev.acrispycookie.crispycommons.api.guis.menu.CrispyMenu;
import dev.acrispycookie.crispycommons.api.guis.menu.MenuPage;
import dev.acrispycookie.crispycommons.implementations.guis.abstraction.AbstractTrackedGui;
import dev.acrispycookie.crispycommons.implementations.guis.menu.wrappers.MenuData;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public class SimpleMenu extends AbstractTrackedGui<MenuData> implements CrispyMenu {

    public SimpleMenu(MenuData data) {
        super(data);
        data.setMenu(this);
    }

    @Override
    public void openInternal(Player p) {
        MenuPage page = data.getPage(data.getPage(p));
        p.openInventory(page.render(p));
        viewers.put(p, true);
    }

    @Override
    public void closeInternal(Player p) {
        data.onPageClose(p);
        viewers.put(p, false);
        if (p.getOpenInventory() != null)
            p.closeInventory();
    }

    @Override
    public void offsetPage(OfflinePlayer player, int offset) {
        data.offsetPage(player, offset);
    }

    @Override
    public void setPage(OfflinePlayer player, int page) {
        data.setPage(player, page);
    }

    @Override
    public int getPage(OfflinePlayer player) {
        return data.getPage(player);
    }

    @Override
    public MenuPage getPage(int index) {
        return data.getPage(index);
    }

    @Override
    public List<MenuPage> getPages() {
        return data.getPages();
    }

    @Override
    public int getDefaultPage() {
        return data.getStartingPage();
    }

    @Override
    public void setDefaultPage(int pageIndex) {
        data.setStartingPage(pageIndex);
    }

    @Override
    public Set<MenuProperty> getProperties() {
        return data.getProperties();
    }

    @Override
    public boolean hasProperty(MenuProperty property) {
        return data.getProperties().contains(property);
    }

    @Override
    public void addProperty(MenuProperty property) {
        data.addProperty(property);
    }

    @Override
    public void removeProperty(MenuProperty property) {
        data.removeProperty(property);
    }

    @Override
    public void setProperties(Collection<MenuProperty> properties) {
        data.setProperties(properties);
    }

    @Override
    public boolean isChangingPage(Player player) {
        return data.isChangingPage(player);
    }
}
