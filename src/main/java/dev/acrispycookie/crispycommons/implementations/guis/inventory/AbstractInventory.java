package dev.acrispycookie.crispycommons.implementations.guis.inventory;

import dev.acrispycookie.crispycommons.api.guis.inventory.CrispyInventory;
import dev.acrispycookie.crispycommons.api.guis.inventory.InventoryItem;
import dev.acrispycookie.crispycommons.api.guis.inventory.InventoryPage;
import dev.acrispycookie.crispycommons.implementations.guis.inventory.wrappers.InventoryData;
import dev.acrispycookie.crispycommons.implementations.visuals.abstraction.visual.AbstractVisual;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

public class AbstractInventory extends AbstractVisual<InventoryData> implements CrispyInventory {

    public AbstractInventory(InventoryData data, Set<? extends OfflinePlayer> receivers, long timeToLive) {
        super(data, receivers, timeToLive, UpdateMode.PER_PLAYER);
        forEachPage(p -> p.setParent(this));
    }

    @Override
    public void changePage(Player player, int newIndex) {
        if (newIndex < 0 || newIndex >= data.getTotalPages())
            return;

        data.setCurrentPage(newIndex);
        this.hide(player);
        this.show(player);
    }

    @Override
    protected void prepareShow() {
        data.getPages().forEach(p -> p.getItems().forEach(i -> i.getDisplay().start()));
    }

    @Override
    protected void prepareHide() {
        data.getPages().forEach(p -> p.getItems().forEach(i -> i.getDisplay().stop()));
    }

    @Override
    protected void show(Player p) {
        p.openInventory(data.getPages().get(data.getCurrentPage()).getInventory(p));
    }

    @Override
    protected void hide(Player p) {
        p.closeInventory();
    }

    @Override
    protected void perPlayerUpdate(Player p) {
        data.forEachPage(page -> page.renderItems(p));

    }

    @Override
    protected void globalUpdate() {

    }

    @Override
    public List<InventoryPage> getPages() {
        return data.getPages();
    }

    @Override
    public InventoryPage getPage(int index) {
        return data.getPages().get(index);
    }

    @Override
    public void addPage(InventoryPage page, int index) {
        data.addPage(index, page);
        page.setParent(this);
    }

    @Override
    public void addPage(InventoryPage page) {
        addPage(page, data.getTotalPages());
    }

    @Override
    public void removePage(int index) {
        data.removePage(index);
    }

    @Override
    public void forEachPage(Consumer<InventoryPage> consumer) {
        data.forEachPage(consumer);
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (!(event.getInventory().getHolder() instanceof AbstractPage.PageHolder))
            return;

        InventoryPage page = ((AbstractPage.PageHolder) event.getInventory().getHolder()).getPage();
        if (getPages().contains(page))
            isDisplayed = false;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if(event.getClickedInventory() == null
                || event.getInventory() == null
                || !(event.getInventory().getHolder() instanceof AbstractPage.PageHolder)
                || !(event.getWhoClicked() instanceof Player))
            return;

        InventoryPage page = ((AbstractPage.PageHolder) event.getInventory().getHolder()).getPage();
        if (!getPages().contains(page)
                || event.getCurrentItem() == null
                || event.getCurrentItem().getType() == Material.AIR)
            return;

        Player player = (Player) event.getWhoClicked();
        InventoryItem<?> item = page.getItem(event.getSlot());
        event.setCancelled(!item.canTake(page, player));
        if (item.isLoaded())
            item.onClick(page, player);
        else
            item.onClickUnloaded(page, player);
    }
}
