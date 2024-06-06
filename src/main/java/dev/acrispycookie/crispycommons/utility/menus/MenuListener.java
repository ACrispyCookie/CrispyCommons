package dev.acrispycookie.crispycommons.utility.menus;

import dev.acrispycookie.crispycommons.api.guis.menu.MenuItem;
import dev.acrispycookie.crispycommons.api.guis.menu.MenuPage;
import dev.acrispycookie.crispycommons.api.wrappers.itemstack.CrispyItemStack;
import dev.acrispycookie.crispycommons.implementations.guis.menu.wrappers.Holder;
import dev.acrispycookie.crispycommons.implementations.guis.menu.wrappers.MenuData;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.PlayerInventory;

public class MenuListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if(event.getClickedInventory() == null
                || event.getInventory() == null
                || !(event.getInventory().getHolder() instanceof Holder)
                || !(event.getWhoClicked() instanceof Player))
            return;

        MenuPage page = ((Holder) event.getInventory().getHolder()).getPage();
        MenuData data = page.getMenuData();
        if (event.getCurrentItem() == null
                || event.getCurrentItem().getType() == Material.AIR)
            return;

        Player player = (Player) event.getWhoClicked();
        if(event.getClickedInventory().getHolder() instanceof PlayerInventory) {
            event.setCancelled(!page.onPlayerItemClick(player, new CrispyItemStack(event.getCurrentItem())));
            return;
        }

        MenuItem item = page.getItem(event.getSlot());
        event.setCancelled(!item.canTake(data, player));
        if (item.isLoaded())
            item.onClick(data, player);
        else
            item.onClickUnloaded(data, player);
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        if(event.getInventory() == null
                || event.getInventory() == null
                || !(event.getInventory().getHolder() instanceof Holder))
            return;

        MenuPage page = ((Holder) event.getInventory().getHolder()).getPage();
        page.getMenuData().getMenu().close((Player) event.getPlayer());
    }
}
