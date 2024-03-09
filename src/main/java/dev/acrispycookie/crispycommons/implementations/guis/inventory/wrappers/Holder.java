package dev.acrispycookie.crispycommons.implementations.guis.inventory.wrappers;

import dev.acrispycookie.crispycommons.api.guis.inventory.InventoryPage;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class Holder implements InventoryHolder {

    private final InventoryPage page;
    private final Inventory inventory;

    public Holder(String title, int rows, InventoryPage page) {
        this.inventory = Bukkit.createInventory(this, rows * 9, title);
        this.page = page;
    }

    public InventoryPage getPage() {
        return page;
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }
}
