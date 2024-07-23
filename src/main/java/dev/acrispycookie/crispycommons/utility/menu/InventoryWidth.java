package dev.acrispycookie.crispycommons.utility.menu;

import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import java.awt.*;

public enum InventoryWidth {
    CHEST(9),
    DISPENSER(3),
    DROPPER(3),
    ENDER_CHEST(9),
    HOPPER(5), //below this one they don't work
    PLAYER(9),
    CREATIVE(9),
    FURNACE(1),
    WORKBENCH(3),
    CRAFTING(2),
    ENCHANTING(1),
    BREWING(1),
    MERCHANT(1),
    ANVIL(1),
    BEACON(1);

    private final int width;

    InventoryWidth(int width) {
        this.width = width;
    }

    public int getWidth() {
        return width;
    }

    public static int getType(InventoryType type) {
        return InventoryWidth.valueOf(type.name()).getWidth();
    }

    public static boolean isPointInsideInventory(Point point, Inventory inv) {
        return point.x + (point.y * getType(inv.getType())) < inv.getSize();
    }
}
