package dev.acrispycookie.crispycommons.api.guis.menu.sections;

import dev.acrispycookie.crispycommons.api.guis.menu.MenuItem;
import dev.acrispycookie.crispycommons.implementations.guis.menu.wrappers.MenuData;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public interface Section {

    void renderItem(Player player, MenuData data, Inventory inv, int pasteSlot, int itemIndex);
    void onClose(Inventory inventory);
    MenuItem getItem(int index);
    void setItem(int index, MenuItem item);
    void removeItem(int index);
    void clearItems();
    boolean isSlotValid(int slot);
    boolean hasDynamicItems();

    static boolean isSlotValid(int slot, Inventory inventory) {
        return slot >= 0 && slot < inventory.getSize();
    }
}
