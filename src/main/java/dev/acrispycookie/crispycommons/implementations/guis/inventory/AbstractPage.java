package dev.acrispycookie.crispycommons.implementations.guis.inventory;

import dev.acrispycookie.crispycommons.api.guis.inventory.CrispyInventory;
import dev.acrispycookie.crispycommons.api.guis.inventory.InventoryItem;
import dev.acrispycookie.crispycommons.api.guis.inventory.InventoryPage;
import dev.acrispycookie.crispycommons.implementations.guis.inventory.wrappers.Holder;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class AbstractPage implements InventoryPage {

    private CrispyInventory inventory;
    private final HashMap<Player, Inventory> inventories = new HashMap<>();
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

    private void renderItem(Player p, int slot) {
        System.out.println("Rendering item " + slot + "...");
        InventoryItem<?> item = items.get(slot);
        if (!item.canSee(this, p))
            return;

        inventories.get(p).setItem(slot, item.getDisplay().getRaw());
    }

    private void renderItems(Player p) {
        for (int slot : items.keySet()) {
            renderItem(p, slot);
        }
    }

    private void renderItems(int slot) {
        for (Player player : inventories.keySet()) {
            renderItem(player, slot);
        }
    }

    @Override
    public Inventory render(Player p) {
        Inventory inventory;
        if (!inventories.containsKey(p)) {
            inventory = new Holder(title, rows, this).getInventory();
            inventories.put(p, inventory);
        } else {
            inventory = inventories.get(p);
            inventory.clear();
        }
        renderItems(p);

        return inventory;
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
        item.getDisplay().setUpdate(() -> renderItems(slot));
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
    public CrispyInventory getParent() {
        return inventory;
    }

    @Override
    public void setParent(CrispyInventory inventory) {
        this.inventory = inventory;
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
