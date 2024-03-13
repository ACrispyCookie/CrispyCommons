package dev.acrispycookie.crispycommons.implementations.guis.inventory;

import dev.acrispycookie.crispycommons.api.guis.inventory.CrispyInventory;
import dev.acrispycookie.crispycommons.api.guis.inventory.InventoryItem;
import dev.acrispycookie.crispycommons.api.guis.inventory.InventoryPage;
import dev.acrispycookie.crispycommons.implementations.guis.inventory.wrappers.Holder;
import dev.acrispycookie.crispycommons.implementations.guis.inventory.wrappers.InventoryData;
import dev.acrispycookie.crispycommons.implementations.visuals.abstraction.visual.AbstractVisual;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

public class AbstractInventory extends AbstractVisual<InventoryData> implements CrispyInventory {

    private final HashMap<Integer, Integer> loadedPages = new HashMap<>();

    public AbstractInventory(InventoryData data, Set<? extends OfflinePlayer> receivers, long timeToLive) {
        super(data, receivers, timeToLive, UpdateMode.PER_PLAYER);
        data.forEachPage(p -> p.setParent(this));
    }

    private void loadPage(int index) {
        System.out.println("------------------------");
        System.out.println("Loading " + index + " page...");
        if (!loadedPages.containsKey(index)) {
            System.out.println("Starting elements...");
            getPage(index).forEachItem(item -> item.getDisplay().start());
        }
        System.out.println("Old value: " + loadedPages.get(index));
        loadedPages.put(index, loadedPages.getOrDefault(index, 0) + 1);
        System.out.println("New value: " + loadedPages.get(index));
        System.out.println("------------------------");
    }

    private void unloadPage(int index) {
        System.out.println("------------------------");
        System.out.println("Unloading " + index + " page...");
        if(!loadedPages.containsKey(index))
            return;
        System.out.println("Old value: " + loadedPages.get(index));
        loadedPages.put(index, loadedPages.get(index) - 1);
        System.out.println("New value: " + loadedPages.get(index));
        if (loadedPages.get(index) == 0) {
            System.out.println("Stopping elements...");
            loadedPages.remove(index);
            getPage(index).forEachItem(item -> item.getDisplay().stop());
        }
        System.out.println("------------------------");
    }

    @Override
    public void changePage(Player player, int newIndex) {
        if (newIndex < 0 || newIndex >= data.getTotalPages())
            return;

        unloadPage(data.getCurrentPage(player));
        data.setCurrentPage(player, newIndex);
        loadPage(newIndex);
        if (isDisplayed) {
            this.hide(player);
            this.show(player);
        }
    }

    @Override
    protected void prepareShow() {
        getPlayers().forEach(player -> loadPage(data.getCurrentPage(player)));
    }

    @Override
    protected void prepareHide() {
        getPlayers().forEach(player -> unloadPage(data.getCurrentPage(player)));
    }

    @Override
    public void show(Player player) {
        player.openInventory(getPage(data.getCurrentPage(player)).render(player));
    }

    @Override
    public void hide(Player player) {
        player.closeInventory();
    }

    @Override
    protected void perPlayerUpdate(Player player) {
        getPage(data.getCurrentPage(player)).render(player);
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
    public void addPage(int index, InventoryPage page) {
        data.addPage(index, page);
        page.setParent(this);
    }

    @Override
    public void addPage(InventoryPage page) {
        addPage(data.getTotalPages(), page);
    }

    @Override
    public void setPage(int index, InventoryPage page) {
        data.setPage(index, page);
        page.setParent(this);
    }

    @Override
    public void removePage(int index) {
        data.removePage(index);
    }

    @Override
    public void forEachPage(Consumer<InventoryPage> consumer) {
        data.forEachPage(consumer);
    }

    @Override
    public InventoryPage getCurrentPage(Player p) {
        return data.getPages().get(data.getCurrentPage(p));
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (!(event.getInventory().getHolder() instanceof Holder))
            return;

        CrispyInventory inventory = ((Holder) event.getInventory().getHolder()).getPage().getParent();
        //inventory.hide();
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if(event.getClickedInventory() == null
                || event.getInventory() == null
                || !(event.getInventory().getHolder() instanceof Holder)
                || !(event.getWhoClicked() instanceof Player))
            return;

        InventoryPage page = ((Holder) event.getInventory().getHolder()).getPage();
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
