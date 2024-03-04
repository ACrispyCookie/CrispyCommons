package dev.acrispycookie.crispycommons.implementations.guis.inventory;

import dev.acrispycookie.crispycommons.CrispyCommons;
import dev.acrispycookie.crispycommons.api.guis.inventory.CrispyInventory;
import dev.acrispycookie.crispycommons.api.guis.inventory.InventoryPage;
import dev.acrispycookie.crispycommons.implementations.guis.inventory.wrappers.InventoryData;
import dev.acrispycookie.crispycommons.implementations.visuals.abstraction.visual.AbstractVisual;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

public class AbstractInventory extends AbstractVisual<InventoryData> implements CrispyInventory {

    public AbstractInventory(InventoryData data, Set<? extends OfflinePlayer> receivers, long timeToLive) {
        super(data, receivers, timeToLive, UpdateMode.GLOBAL);
        data.forEachPage(p -> p.setParent(this));
        Bukkit.getPluginManager().registerEvents(this, CrispyCommons.getPlugin());
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
    public void consumePages(Consumer<InventoryPage> consumer) {
        data.forEachPage(consumer);
    }

    @Override
    protected void prepareShow() {

    }

    @Override
    protected void prepareHide() {

    }

    @Override
    protected void show(Player p) {
        p.openInventory(data.getPages().get(data.getCurrentPage()).getInventory());
    }

    @Override
    protected void hide(Player p) {
        p.closeInventory();
    }

    @Override
    protected void perPlayerUpdate(Player p) {

    }

    @Override
    protected void globalUpdate() {
        data.forEachPage(InventoryPage::renderItems);
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (!(event.getInventory().getHolder() instanceof InventoryPage))
            return;

        InventoryPage page = (InventoryPage) event.getInventory().getHolder();
        if (getPages().contains(page))
            isDisplayed = false;
    }
}
