package dev.acrispycookie.crispycommons.implementations.guis.inventory;

import dev.acrispycookie.crispycommons.api.guis.inventory.CrispyInventory;
import dev.acrispycookie.crispycommons.api.guis.inventory.InventoryItem;
import dev.acrispycookie.crispycommons.api.guis.inventory.InventoryPage;
import dev.acrispycookie.crispycommons.implementations.guis.inventory.wrappers.InventoryData;
import dev.acrispycookie.crispycommons.implementations.visuals.abstraction.visual.AbstractVisual;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import java.util.*;
import java.util.function.Consumer;

public class AbstractInventory extends AbstractVisual<InventoryData> implements CrispyInventory {

    private final Map<OfflinePlayer, Boolean> isDisplayedMap = new HashMap<>();

    public AbstractInventory(InventoryData data, Set<? extends OfflinePlayer> receivers, long timeToLive) {
        super(data, receivers, timeToLive, UpdateMode.PER_PLAYER);
        receivers.forEach(player -> isDisplayedMap.put(player, false));
    }

    @Override
    public void changePage(Player player, int newIndex) {
        if (newIndex < 0 || newIndex >= data.getTotalPages())
            return;

        data.getPages().get(data.getCurrentPage(player)).forEachItem(item -> item.getDisplay().stop());
        data.setCurrentPage(player, newIndex);
        data.getPages().get(newIndex).forEachItem(item -> item.getDisplay().start());
        if (isDisplayed) {
            this.hide(player);
            this.show(player);
        }
    }

    @Override
    protected void prepareShow() {
        Set<Integer> pagesToLoad = new HashSet<>();
        getPlayers().forEach(player -> pagesToLoad.add(data.getCurrentPage(player)));

        for (int pageIndex : pagesToLoad) {
            getPage(pageIndex).forEachItem(item -> item.getDisplay().start());
        }
    }

    @Override
    protected void prepareHide() {
        Set<Integer> pagesToLoad = new HashSet<>();
        getPlayers().forEach(player -> pagesToLoad.add(data.getCurrentPage(player)));

        for (int pageIndex : pagesToLoad) {
            getPage(pageIndex).forEachItem(item -> item.getDisplay().stop());
        }
    }

    @Override
    public void show(Player player) {
        player.openInventory(getInventory(data.getCurrentPage(player), player));
    }

    @Override
    public void hide(Player player) {
        player.closeInventory();
    }

    @Override
    protected void perPlayerUpdate(Player p) {
        renderItems(data.getCurrentPage(p), p);
    }

    @Override
    protected void globalUpdate() {

    }

    @Override
    public void addPlayer(OfflinePlayer player) {
        super.addPlayer(player);
        isDisplayedMap.put(player, isDisplayed);
    }

    @Override
    public void removePlayer(OfflinePlayer player) {
        super.removePlayer(player);
        isDisplayedMap.remove(player);
    }

    @Override
    public void setPlayers(Collection<? extends OfflinePlayer> players) {
        super.setPlayers(players);
        isDisplayedMap.clear();
        players.forEach(p -> isDisplayedMap.put(p, isDisplayed));
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
    }

    @Override
    public void addPage(InventoryPage page) {
        addPage(data.getTotalPages(), page);
    }

    @Override
    public void setPage(int index, InventoryPage page) {
        data.setPage(index, page);
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
    public Inventory getInventory(int pageIndex, Player player) {
        InventoryPage page = getPage(pageIndex);
        Inventory cached = page.getCached(player);
        if (cached != null)
            return cached;

        BukkitInventoryHolder holder = new BukkitInventoryHolder(page.getTitle(), page.getRows(), this, page);
        page.addCached(player, holder.getInventory());
        renderItems(pageIndex, player);
        return holder.getInventory();
    }

    protected void renderItems(int pageIndex, Player player) {
        if (pageIndex < 0 || pageIndex > data.getTotalPages() - 1)
            return;
        InventoryPage page = data.getPages().get(pageIndex);
        Map<Integer, InventoryItem<?>> items = page.getItems();
        Inventory cached = page.getCached(player);
        if(cached == null)
            page.addCached(player, cached = new BukkitInventoryHolder(page.getTitle(), page.getRows(), this, page)
                    .getInventory());

        for(int slot : items.keySet()) {
            InventoryItem<?> item = items.get(slot);
            if (!item.canSee(this, page, player))
                continue;

            item.getDisplay().setUpdate(() -> renderItems(pageIndex, slot));
            cached.setItem(slot, item.getDisplay().getRaw());
        }
    }

    protected void renderItems(int pageIndex, int slot) {
        InventoryPage page = data.getPages().get(pageIndex);
        InventoryItem<?> item = page.getItem(slot);

        for (Player player : page.getCached().keySet()) {
            if(!item.canSee(this, page, player))
                return;
            item.getDisplay().setUpdate(() -> renderItems(pageIndex, slot));
            page.getCached().get(player).setItem(slot, item.getDisplay().getRaw());
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (!(event.getInventory().getHolder() instanceof BukkitInventoryHolder))
            return;

        CrispyInventory inventory = ((BukkitInventoryHolder) event.getInventory().getHolder()).getCrispyInventory();
        //TODO handle close
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if(event.getClickedInventory() == null
                || event.getInventory() == null
                || !(event.getInventory().getHolder() instanceof BukkitInventoryHolder)
                || !(event.getWhoClicked() instanceof Player))
            return;

        InventoryPage page = ((BukkitInventoryHolder) event.getInventory().getHolder()).getPage();
        if (!getPages().contains(page)
                || event.getCurrentItem() == null
                || event.getCurrentItem().getType() == Material.AIR)
            return;

        Player player = (Player) event.getWhoClicked();
        InventoryItem<?> item = page.getItem(event.getSlot());
        event.setCancelled(!item.canTake(this, page, player));
        if (item.isLoaded())
            item.onClick(this, page, player);
        else
            item.onClickUnloaded(this, page, player);
    }

    public static class BukkitInventoryHolder implements InventoryHolder {

        private final Inventory inventory;
        private final CrispyInventory crispyInventory;
        private final InventoryPage page;

        public BukkitInventoryHolder(String title, int rows, CrispyInventory inventory, InventoryPage page) {
            this.inventory = Bukkit.createInventory(this, rows * 9, title);
            this.crispyInventory = inventory;
            this.page = page;
        }

        public InventoryPage getPage() {
            return page;
        }

        public CrispyInventory getCrispyInventory() {
            return crispyInventory;
        }

        @Override
        public Inventory getInventory() {
            return inventory;
        }
    }
}
