package dev.acrispycookie.crispycommons.api.gui.menu.section;

import dev.acrispycookie.crispycommons.api.gui.menu.CrispyMenu;
import dev.acrispycookie.crispycommons.api.gui.menu.MenuItem;
import dev.acrispycookie.crispycommons.implementations.element.type.ItemElement;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an item section that can be used in multiple {@link CrispyMenu} instances.
 * <p>
 * Manages and renders a collection of {@link MenuItem} objects.
 * </p>
 */
public interface Section {

    /**
     * Renders the item at a specified slot in the given bukkit inventory for the specified player.
     * <p>
     * If the specified slot or index are invalid the method returns early.
     * </p>
     *
     * @param player the player for whom the item is rendered.
     * @param menu the menu to which this section belongs.
     * @param inv the inventory where the item will be placed.
     * @param pasteSlot the slot index where the item should be placed in the inventory.
     * @param itemIndex the index of the item in this section's item list.
     * @throws NullPointerException if any of the {@code player}, {@code menu}, or {@code inventory} are {@code null}.
     */
    void renderItem(@NotNull Player player, @NotNull CrispyMenu menu, @NotNull Inventory inv, int pasteSlot, int itemIndex);

    /**
     * Handles the event when a bukkit inventory containing this section is closed.
     *
     * @param inventory the bukkit inventory that was closed.
     * @throws NullPointerException if {@code inventory} is {@code null}.
     */
    void onClose(@NotNull Inventory inventory);

    /**
     * Retrieves the item at the specified index in this section.
     *
     * @param index the index of the item to retrieve.
     * @return the {@link MenuItem} at the specified index.
     * @throws IllegalArgumentException if {@code index} is out of the valid range.
     */
    @NotNull MenuItem getItem(int index);

    /**
     * Sets the item at the specified index in this section.
     *
     * @param index the index at which to set the item.
     * @param item the {@link MenuItem} to set at the specified index.
     * @throws NullPointerException if {@code item} is null.
     * @throws IllegalArgumentException if {@code index} is out of bounds.
     */
    void setItem(int index, @NotNull MenuItem item);

    /**
     * Removes the item at the specified index in this section.
     *
     * @param index the index of the item to remove.
     * @throws IllegalArgumentException if {@code index} is out of bounds.
     */
    void removeItem(int index);

    /**
     * Clears all items from this section.
     */
    void clearItems();

    /**
     * Checks if the specified slot index is valid for this section.
     *
     * @param slot the slot index to check.
     * @return true if the slot index is valid, false otherwise.
     */
    boolean isSlotValid(int slot);

    /**
     * Checks if this section contains any {@link ItemElement} that are dynamic.
     *
     * @return true if this section has dynamic items, false otherwise.
     */
    boolean hasDynamicItems();

    /**
     * Static utility method to check if a slot index is valid within a given bukkit inventory.
     *
     * @param slot the slot index to check.
     * @param inventory the inventory to check against.
     * @return true if the slot index is within the bounds of the inventory size, false otherwise.
     * @throws NullPointerException if {@code inventory} is {@code null}.
     */
    static boolean isSlotValid(int slot, @NotNull Inventory inventory) {
        return slot >= 0 && slot < inventory.getSize();
    }
}
