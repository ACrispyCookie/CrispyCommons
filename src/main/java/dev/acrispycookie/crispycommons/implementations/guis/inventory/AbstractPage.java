package dev.acrispycookie.crispycommons.implementations.guis.inventory;

import dev.acrispycookie.crispycommons.api.guis.inventory.CrispyInventory;
import dev.acrispycookie.crispycommons.api.guis.inventory.InventoryItem;
import dev.acrispycookie.crispycommons.api.guis.inventory.InventoryPage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import java.util.*;

public class AbstractPage implements InventoryPage {

    private final HashMap<Player, PageHolder> inventories = new HashMap<>();
    private final HashMap<Integer, InventoryItem<?>> items = new HashMap<>();
    private final String title;
    private final int rows;
    private final int columns;

    public AbstractPage(String title, int rows) {
        this.title = title;
        this.rows = rows;
        this.columns = 9;

        setItem(2, this.rows - 1, InventoryPage.previousItem);
        setItem(6, this.rows - 1, InventoryPage.nextItem);
    }

    @Override
    public List<InventoryItem<?>> getItems() {
        return new ArrayList<>(items.values());
    }

    @Override
    public InventoryItem<?> getItem(int slot) {
        return items.get(slot);
    }

    @Override
    public InventoryItem<?> getItem(int x, int y) {
        return items.get(x + y * columns);
    }

    @Override
    public void setItem(int slot, InventoryItem<?> item) {
        items.put(slot, item);
        renderItem(slot);
        item.getDisplay().setUpdate(() -> renderItem(slot));
        item.getLoadingDisplay().setUpdate(() -> renderItem(slot));
    }

    @Override
    public void setItem(int x, int y, InventoryItem<?> item) {
        setItem(x + y * columns, item);
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public int getRows() {
        return rows;
    }

    @Override
    public int getColumns() {
        return columns;
    }

    @Override
    public void updateItemDisplay(InventoryItem<?> item) {
        Optional<Integer> slot = items.keySet().stream().filter(s -> items.get(s).equals(item)).findFirst();
        if(!slot.isPresent())
            return;

        item.getDisplay().setUpdate(() -> renderItem(slot.get()));
    }

    @Override
    public void renderItems(Player player) {
        for (int slot : items.keySet()) {
            renderItem(slot, player);
        }
    }

    @Override
    public Inventory getInventory(Player p) {
        if (inventories.containsKey(p))
            return inventories.get(p).getInventory();

        PageHolder holder = new PageHolder(this, title, rows);
        inventories.put(p, holder);
        renderItems(p);
        return holder.getInventory();
    }

    protected void renderItem(int slot) {
        for (Player player : inventories.keySet()) {
            renderItem(slot, player);
        }
    }

    protected void renderItem(int slot, Player player) {
        if (!inventories.containsKey(player))
            return;
        InventoryItem<?> item = items.get(slot);
        if (!item.canSee(this, player))
            return;
        inventories.get(player).getInventory().setItem(slot,
                item.isLoaded() ? item.getDisplay().getRaw() : item.getLoadingDisplay().getRaw());
    }

    static class PageHolder implements InventoryHolder {
        private final Inventory inventory;
        private final InventoryPage page;

        private PageHolder(InventoryPage page, String title, int rows) {
            this.page = page;
            this.inventory = Bukkit.createInventory(this, rows * 9, title);
        }

        public InventoryPage getPage() {
            return page;
        }

        @Override
        public Inventory getInventory() {
            return inventory;
        }
    }
}
