package dev.acrispycookie.crispycommons.utility.menu;

import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import java.awt.*;

/**
 * Enumeration representing the width (number of columns) of different types of inventories.
 * <p>
 * This enum provides predefined widths for common inventory types in the game. The width is
 * represented by the number of columns in the inventory. Each constant corresponds to a specific
 * inventory type with its associated width.
 * </p>
 */
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

    /**
     * The width of the inventory in columns.
     */
    private final int width;

    /**
     * Constructs an {@code InventoryWidth} with the specified width.
     *
     * @param width the width (number of columns) of the inventory type.
     */
    InventoryWidth(int width) {
        this.width = width;
    }

    /**
     * Returns the width of this inventory type.
     *
     * @return the width of the inventory type.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Retrieves the width associated with a given {@link InventoryType}.
     *
     * @param type the {@link InventoryType} whose width is to be retrieved.
     * @return the width of the specified inventory type.
     * @throws IllegalArgumentException if the {@code type} does not correspond to a known {@code InventoryWidth}.
     */
    public static int getType(InventoryType type) {
        return InventoryWidth.valueOf(type.name()).getWidth();
    }

    /**
     * Determines if a given {@link Point} is inside a specified {@link Inventory}.
     * <p>
     * This method checks whether the given point is within the bounds of the inventory based on its width and size.
     * </p>
     *
     * @param point the {@link Point} to be checked.
     * @param inv   the {@link Inventory} to check against.
     * @return {@code true} if the point is inside the inventory, {@code false} otherwise.
     */
    public static boolean isPointInsideInventory(Point point, Inventory inv) {
        return point.x + (point.y * getType(inv.getType())) < inv.getSize();
    }
}
