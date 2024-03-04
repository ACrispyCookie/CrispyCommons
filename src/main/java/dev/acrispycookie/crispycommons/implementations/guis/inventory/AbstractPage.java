package dev.acrispycookie.crispycommons.implementations.guis.inventory;

import dev.acrispycookie.crispycommons.CrispyCommons;
import dev.acrispycookie.crispycommons.api.guis.inventory.CrispyInventory;
import dev.acrispycookie.crispycommons.api.guis.inventory.InventoryItem;
import dev.acrispycookie.crispycommons.api.guis.inventory.InventoryPage;
import dev.acrispycookie.crispycommons.utility.logging.CrispyLogger;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import java.util.*;
import java.util.function.Consumer;
import java.util.logging.Level;

public class AbstractPage implements InventoryPage {

    private CrispyInventory parent;
    private final HashMap<Integer, InventoryItem<?>> items = new HashMap<>();
    private final InventoryType type;
    private final Inventory bukkitInventory;
    private final int rows;
    private final int columns;

    public AbstractPage(String title, int rows) {
        this.type = InventoryType.CHEST;
        this.rows = rows;
        this.columns = 9;
        this.bukkitInventory = Bukkit.createInventory(this, rows * columns, title);
        Bukkit.getPluginManager().registerEvents(this, CrispyCommons.getPlugin());
    }

    private AbstractPage(InventoryType type) {
        this.type = type;
        this.rows = 0;
        this.columns = 0;
        this.bukkitInventory = Bukkit.createInventory(this, type, "");
    }

    @Override
    public CrispyInventory getParent() {
        return this.parent;
    }

    @Override
    public void setParent(CrispyInventory parent) {
        this.parent = parent;
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
        return items.get(x + y * rows);
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
        setItem(x + y * rows, item);
    }

    @Override
    public void addItem(InventoryItem<?> item) {
        setItem(items.isEmpty() ? 0 : Collections.max(items.keySet()) + 1, item);
    }

    @Override
    public void renderItem(int slot) {
        InventoryItem<?> item = items.get(slot);
        bukkitInventory.setItem(slot,
                item.isLoaded() ? item.getDisplay().getRaw() : item.getLoadingDisplay().getRaw());
    }

    @Override
    public void renderItem(int x, int y) {
        renderItem(x + y * rows);
    }

    @Override
    public void renderItem(InventoryItem<?> item) {
        if (items.containsValue(item))
            return;

        int slot = items.entrySet()
                .stream()
                .filter(e -> e.getValue().equals(item))
                .map(Map.Entry::getKey).findFirst().get();
        renderItem(slot);
    }

    @Override
    public void renderItems() {
        for (int slot : items.keySet()) {
            renderItem(slot);
        }
    }

    @Override
    public void fillBorder(InventoryItem<?> item, BorderPosition position) {
        switch (position) {
            case TOP:
                for (int i = 0; i < 9; i++) {
                    setItem(i, item);
                }
                break;
            case BOTTOM:
                for (int i = (rows - 1) * 9; i < rows * 9; i++) {
                    setItem(i, item);
                }
                break;
            case RIGHT:
                for (int i = 0; i < rows; i++) {
                    setItem(0, i, item);
                }
                break;
            case LEFT:
                for (int i = 0; i < rows; i++) {
                    setItem(8, i, item);
                }
                break;
        }
    }

    @Override
    public void consumeItems(Consumer<InventoryItem<?>> consumer) {
        items.values().forEach(consumer);
    }

    @Override
    public Inventory getInventory() {
        return bukkitInventory;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if(event.getClickedInventory() == null
                || event.getInventory() == null
                || !event.getInventory().equals(bukkitInventory)
                || !(event.getWhoClicked() instanceof Player))
            return;

        InventoryPage page = (InventoryPage) event.getInventory().getHolder();
        InventoryItem<?> item = page.getItem(event.getSlot());
        event.setCancelled(!item.canTake(page));
        if (item.isLoaded())
            item.onClick(page, (Player) event.getWhoClicked());
        else
            item.onClickUnloaded(page, (Player) event.getWhoClicked());
    }
}
