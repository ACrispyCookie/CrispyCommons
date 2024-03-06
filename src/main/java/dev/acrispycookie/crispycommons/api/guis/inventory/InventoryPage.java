package dev.acrispycookie.crispycommons.api.guis.inventory;

import dev.acrispycookie.crispycommons.api.wrappers.itemstack.CrispyItemStack;
import dev.acrispycookie.crispycommons.implementations.guis.inventory.AbstractPage;
import dev.acrispycookie.crispycommons.implementations.visuals.abstraction.elements.types.ItemElement;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.List;

public interface InventoryPage  {
    InventoryItem<?> previousItem = InventoryItem.staticItem(
            ItemElement.simple(new CrispyItemStack(Material.ARROW).name("&dPrevious page")),
            (page, player) ->
            {
                page.getParent().changePage(player, page.getParent().getPages().indexOf(page) - 1);
                return null;
            }
    ).setCanSee((page, player) -> {
        int index = page.getParent().getPages().indexOf(page);
        int totalPages = page.getParent().getPages().size();
        return totalPages > 1 && index > 0;
    });

    InventoryItem<?> nextItem = InventoryItem.staticItem(
            ItemElement.simple(new CrispyItemStack(Material.ARROW).name("&dNext page")),
            (page, player) ->
            {
                page.getParent().changePage(player, page.getParent().getPages().indexOf(page) + 1);
                return null;
            }
    ).setCanSee((page, player) -> {
        int index = page.getParent().getPages().indexOf(page);
        int totalPages = page.getParent().getPages().size();
        return totalPages > 1 && index < totalPages - 1;
    });

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
    void renderItems(Player player);
    void fillBorder(InventoryItem<?> item, BorderPosition position);
    Inventory getInventory(Player p);

    enum BorderPosition {
        TOP,
        BOTTOM,
        RIGHT,
        LEFT
    }
}
