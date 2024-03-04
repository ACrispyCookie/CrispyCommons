package dev.acrispycookie.crispycommons.api.guis.inventory;

import dev.acrispycookie.crispycommons.implementations.guis.inventory.AbstractPage;
import org.bukkit.event.Listener;
import org.bukkit.inventory.InventoryHolder;

import java.util.List;
import java.util.function.Consumer;

public interface InventoryPage extends InventoryHolder, Listener {

    static InventoryPage staticSize(String title, int rows) {
        return new AbstractPage(title, rows);
    }

    static InventoryPage dynamicSize(String title, int size) {
        return null; //FIX
    }

    CrispyInventory getParent();
    void setParent(CrispyInventory inventory);
    List<InventoryItem<?>> getItems();
    InventoryItem<?> getItem(int slot);
    InventoryItem<?> getItem(int x, int y);
    void setItem(int slot, InventoryItem<?> item);
    void setItem(int x, int y, InventoryItem<?> item);
    void addItem(InventoryItem<?> item);
    void renderItem(int slot);
    void renderItem(int x, int y);
    void renderItem(InventoryItem<?> item);
    void renderItems();
    void fillBorder(InventoryItem<?> item, BorderPosition position);
    void consumeItems(Consumer<InventoryItem<?>> consumer);

    enum BorderPosition {
        TOP,
        BOTTOM,
        RIGHT,
        LEFT
    }
}
