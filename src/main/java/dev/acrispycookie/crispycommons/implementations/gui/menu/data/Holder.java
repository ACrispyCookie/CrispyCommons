package dev.acrispycookie.crispycommons.implementations.gui.menu.data;

import dev.acrispycookie.crispycommons.api.gui.menu.MenuPage;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class Holder implements InventoryHolder {

    private final MenuPage page;
    private final Inventory inventory;

    public Holder(String title, int rows, int columns, MenuPage page) {
        if (columns == 3)
            this.inventory = Bukkit.createInventory(this, InventoryType.DISPENSER, title);
        else
            this.inventory = Bukkit.createInventory(this, rows * columns, title);
        this.page = page;
    }

    public MenuPage getPage() {
        return page;
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }
}
