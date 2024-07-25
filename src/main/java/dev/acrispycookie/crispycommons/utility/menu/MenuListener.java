package dev.acrispycookie.crispycommons.utility.menu;

import dev.acrispycookie.crispycommons.CrispyCommons;
import dev.acrispycookie.crispycommons.api.gui.menu.CrispyMenu;
import dev.acrispycookie.crispycommons.api.gui.menu.MenuItem;
import dev.acrispycookie.crispycommons.api.gui.menu.MenuPage;
import dev.acrispycookie.crispycommons.api.itemstack.CrispyItemStack;
import dev.acrispycookie.crispycommons.implementations.gui.menu.data.Holder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.util.Vector;

import java.util.Collection;

public class MenuListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if(event.getClickedInventory() == null
                || event.getInventory() == null
                || !(event.getInventory().getHolder() instanceof Holder)
                || !(event.getWhoClicked() instanceof Player))
            return;

        MenuPage page = ((Holder) event.getInventory().getHolder()).getPage();
        CrispyMenu menu = page.getMenu();
        if (event.getCurrentItem() == null
                || event.getCurrentItem().getType() == Material.AIR)
            return;

        Player player = (Player) event.getWhoClicked();
        if(event.getClickedInventory().getHolder() instanceof PlayerInventory) {
            event.setCancelled(!page.onPlayerItemClick(player, new CrispyItemStack(event.getCurrentItem())));
            return;
        }

        MenuItem item = page.getItem(event.getSlot());
        event.setCancelled(!item.canTake(menu, player));
        if (item.canSee(menu, player) && item.isLoaded() || !item.canSee(menu, player) && item.isAlternativeLoaded())
            item.onClick(menu, player);
        else if (item.canSeeUnloaded(menu, player))
            item.onClickUnloaded(menu, player);
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        if(event.getInventory() == null
                || event.getInventory() == null
                || !(event.getInventory().getHolder() instanceof Holder))
            return;

        MenuPage page = ((Holder) event.getInventory().getHolder()).getPage();
        Player player = (Player) event.getPlayer();
        if (page.getMenu().isChangingPage(player))
            return;
        if(page.getMenu().hasProperty(CrispyMenu.MenuProperty.PREVENT_CLOSING) && page.getMenu().isPlayerViewing(player)) {
            Bukkit.getScheduler().runTaskLater(CrispyCommons.getPlugin(), () -> event.getPlayer().openInventory(event.getInventory()), 1);
            return;
        }

        if (page.getMenu().isPlayerViewing(player))
            page.getMenu().close(player);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        Location above = block.getLocation().add(new Vector(0, 1, 0));
        Collection<Entity> entities = above.getWorld().getNearbyEntities(above, 0.5, 0.5, 0.5);

        for (Entity entity : entities) {
            if (!(entity instanceof Player))
                continue;

            Player player = (Player) entity;
            if (!(player.getOpenInventory().getTopInventory().getHolder() instanceof Holder))
                continue;

            MenuPage page = ((Holder) player.getOpenInventory().getTopInventory().getHolder()).getPage();
            if (page.getMenu().hasProperty(CrispyMenu.MenuProperty.PREVENT_BELOW_BLOCK_BREAK)) {
                event.setCancelled(true);
                break;
            }
        }
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {
        Entity entity = event.getEntity();
        if (!(entity instanceof Player))
            return;

        Player player = (Player) entity;
        if (!(player.getOpenInventory().getTopInventory().getHolder() instanceof Holder))
            return;

        MenuPage page = ((Holder) player.getOpenInventory().getTopInventory().getHolder()).getPage();
        if (page.getMenu().hasProperty(CrispyMenu.MenuProperty.PREVENT_DAMAGE))
            event.setCancelled(true);
    }
}
