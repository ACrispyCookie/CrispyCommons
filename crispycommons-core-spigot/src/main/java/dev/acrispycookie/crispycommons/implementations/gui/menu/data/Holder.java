package dev.acrispycookie.crispycommons.implementations.gui.menu.data;

import dev.acrispycookie.crispycommons.api.gui.menu.MenuPage;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

/**
 * A class that implements {@link InventoryHolder} to associate a custom inventory with a {@link MenuPage}.
 * <p>
 * The {@code Holder} class is used to create and manage a custom inventory in the Bukkit framework,
 * linking it to a specific {@link MenuPage}. This allows for custom menu interfaces with varying sizes
 * and titles, depending on the specified number of rows and columns.
 * </p>
 */
public class Holder implements InventoryHolder {

    /**
     * The {@link MenuPage} associated with this holder, representing the custom menu page.
     */
    private final MenuPage page;

    /**
     * The {@link Inventory} instance that this holder manages.
     */
    private final Inventory inventory;

    /**
     * Constructs a {@code Holder} with a specified title, dimensions, and associated {@link MenuPage}.
     * <p>
     * The inventory type is determined by the number of columns.
     * </p>
     *
     * @param title the title of the inventory.
     * @param rows the number of rows in the inventory.
     * @param columns the number of columns in the inventory.
     * @param page the {@link MenuPage} associated with this inventory.
     */
    public Holder(@NotNull String title, int rows, int columns, @NotNull MenuPage page) {
        if (columns == 3) {
            this.inventory = Bukkit.createInventory(this, InventoryType.DISPENSER, title);
        } else {
            this.inventory = Bukkit.createInventory(this, rows * columns, title);
        }
        this.page = page;
    }

    /**
     * Retrieves the {@link MenuPage} associated with this holder.
     *
     * @return the {@link MenuPage} instance.
     */
    public @NotNull MenuPage getPage() {
        return page;
    }

    /**
     * Retrieves the inventory associated with this holder.
     * <p>
     * This method is required by the {@link InventoryHolder} interface and provides access to the
     * custom inventory managed by this holder.
     * </p>
     *
     * @return the {@link Inventory} associated with this holder.
     */
    @Override
    public @NotNull Inventory getInventory() {
        return inventory;
    }
}

