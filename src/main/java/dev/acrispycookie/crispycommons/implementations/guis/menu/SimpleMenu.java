package dev.acrispycookie.crispycommons.implementations.guis.menu;

import dev.acrispycookie.crispycommons.api.guis.menu.CrispyMenu;
import dev.acrispycookie.crispycommons.api.guis.menu.MenuPage;
import dev.acrispycookie.crispycommons.implementations.guis.abstraction.AbstractTrackedGui;
import dev.acrispycookie.crispycommons.implementations.guis.menu.wrappers.MenuData;
import org.bukkit.entity.Player;

public class SimpleMenu extends AbstractTrackedGui<MenuData> implements CrispyMenu {

    public SimpleMenu(MenuData data) {
        super(data);
        data.setMenu(this);
    }

    @Override
    public void openInternal(Player p) {
        MenuPage page = data.getPage(p);
        p.openInventory(page.render(p));
        viewers.put(p, true);
    }

    @Override
    public void closeInternal(Player p) {
        data.onPageClose(p);
        viewers.put(p, false);
    }
}
