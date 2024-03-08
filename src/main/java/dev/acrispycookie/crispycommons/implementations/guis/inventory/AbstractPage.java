package dev.acrispycookie.crispycommons.implementations.guis.inventory;

import dev.acrispycookie.crispycommons.api.guis.inventory.InventoryItem;
import dev.acrispycookie.crispycommons.api.guis.inventory.InventoryPage;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.*;
import java.util.function.Consumer;

public class AbstractPage implements InventoryPage {

    private final HashMap<Player, Inventory> cached = new HashMap<>();
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
    public Inventory getCached(Player p) {
        return cached.getOrDefault(p, null);
    }

    @Override
    public void addCached(Player player, Inventory holder) {
        cached.put(player, holder);
    }

    @Override
    public Map<Player, Inventory> getCached() {
        return Collections.unmodifiableMap(cached);
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
    }

    @Override
    public void setItem(int x, int y, InventoryItem<?> item) {
        setItem(x + y * columns, item);
    }

    @Override
    public void forEachItem(Consumer<? super InventoryItem<?>> consumer) {
        items.values().forEach(consumer);
    }

    @Override
    public Map<Integer, InventoryItem<?>> getItems() {
        return Collections.unmodifiableMap(items);
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
}
