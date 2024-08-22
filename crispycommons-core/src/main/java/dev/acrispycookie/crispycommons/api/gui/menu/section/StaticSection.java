package dev.acrispycookie.crispycommons.api.gui.menu.section;

import dev.acrispycookie.crispycommons.api.gui.menu.CrispyMenu;
import dev.acrispycookie.crispycommons.api.gui.menu.MenuItem;
import dev.acrispycookie.crispycommons.implementations.gui.menu.section.SimpleStaticSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * Represents an item section that has fixed dimensions and can contain {@link MenuItem} objects at specific positions.
 * <p>
 * A {@link StaticSection} is used to display items in a grid-like format with predefined height and width.
 * </p>
 */
public interface StaticSection extends Section{

    /**
     * Creates a {@link StaticSection} with a single item positioned in the top-left corner.
     *
     * @param item the {@link MenuItem} to place in the section.
     * @return a {@link StaticSection} instance with the specified item.
     * @throws NullPointerException if {@code item} is {@code null}.
     */
    static @NotNull StaticSection oneItem(@NotNull MenuItem item) {
        StaticSection section = new SimpleStaticSection(1, 1);
        section.setItem(0, item);
        return section;
    }

    /**
     * Creates a {@link StaticSection} with a single row of items.
     *
     * @param width the number of items in the row.
     * @param item the {@link MenuItem} to place in each slot of the row.
     * @return a {@link StaticSection} instance with a row of the specified items.
     * @throws IllegalArgumentException if {@code width} is less than 1.
     * @throws NullPointerException if {@code item} is {@code null}.
     */
    static @NotNull StaticSection oneRow(int width, @NotNull MenuItem item) {
        if (width < 1)
            throw new IllegalArgumentException("Width must be at least 1");
        StaticSection section = new SimpleStaticSection(1, width);
        for (int i = 0; i < width; i++) {
            section.setItem(new Point(i, 0), item);
        }
        return section;
    }

    /**
     * Creates a {@link StaticSection} with a single column of items.
     *
     * @param height the number of items in the column.
     * @param item the {@link MenuItem} to place in each slot of the column.
     * @return a {@link StaticSection} instance with a column of the specified items.
     * @throws IllegalArgumentException if {@code height} is less than 1.
     * @throws NullPointerException if {@code item} is {@code null}.
     */
    static @NotNull StaticSection oneColumn(int height, @NotNull MenuItem item) {
        if (height < 1)
            throw new IllegalArgumentException("Height must be at least 1");
        StaticSection section = new SimpleStaticSection(height, 1);
        for (int i = 0; i < height; i++) {
            section.setItem(new Point(0, i), item);
        }
        return section;
    }

    /**
     * Creates a {@link StaticSection} with custom dimensions.
     *
     * @param height the height of the section.
     * @param width the width of the section.
     * @return a {@link StaticSection} instance with the specified dimensions.
     * @throws IllegalArgumentException if {@code height} or {@code width} is less than 1.
     */
    static @NotNull StaticSection custom(int height, int width) {
        if (height < 1)
            throw new IllegalArgumentException("Height must be at least 1");
        if (width < 1)
            throw new IllegalArgumentException("Width must be at least 1");
        return new SimpleStaticSection(height, width);
    }

    /**
     * Renders an item at the specified position in the bukkit inventory for the given player.
     *
     * @param player the player for whom the item is rendered.
     * @param menu the menu to which this section belongs.
     * @param inventory the bukkit inventory where the item will be placed.
     * @param pasteSlot the slot index where the item should be placed.
     * @param itemPoint the position of the item within the section.
     * @throws NullPointerException if any of the {@code player}, {@code menu}, or {@code inventory} are {@code null}.
     * @throws IllegalArgumentException if the {@code pasteSlot} or {@code itemPoint} is out of range.
     */
    void renderItem(@NotNull Player player, @NotNull CrispyMenu menu, @NotNull Inventory inventory, int pasteSlot, Point itemPoint);

    /**
     * Renders all items in the section into the bukkit inventory, starting from a specified slot and index.
     *
     * @param player the player for whom the items are rendered.
     * @param menu the menu to which this section belongs.
     * @param inv the bukkit inventory where the items will be placed.
     * @param pasteSlot the starting slot index for placing items.
     * @param startingIndex the starting index of items to render.
     * @throws NullPointerException if any of the {@code player}, {@code menu}, or {@code inventory} are {@code null}.
     * @throws IllegalArgumentException if {@code pasteSlot} or {@code startingIndex} is out of range.
     */
    void renderItems(@NotNull Player player, @NotNull CrispyMenu menu, @NotNull Inventory inv, int pasteSlot, int startingIndex);

    /**
     * Renders all items in the section into the inventory, starting from a specified slot and position.
     *
     * @param player the player for whom the items are rendered.
     * @param menu the menu to which this section belongs.
     * @param inv the inventory where the items will be placed.
     * @param pasteSlot the starting slot index for placing items.
     * @param startingPoint the starting position of items within the section.
     * @throws NullPointerException if any of the {@code player}, {@code menu}, or {@code inventory} are {@code null}.
     * @throws IllegalArgumentException if {@code pasteSlot} or {@code startingPoint} are out of range.
     */
    void renderItems(@NotNull Player player, @NotNull CrispyMenu menu, @NotNull Inventory inv, int pasteSlot, Point startingPoint);

    /**
     * Gets the height of the section.
     *
     * @return the height of the section.
     */
    int getHeight();

    /**
     * Gets the width of the section.
     *
     * @return the width of the section.
     */
    int getWidth();

    /**
     * Retrieves the item at the specified position in the section.
     *
     * @param point the position of the item.
     * @return the {@link MenuItem} at the specified position, or null if no item exists at that position.
     * @throws IllegalArgumentException if {@code point} is out of range.
     */
    @NotNull MenuItem getItem(@NotNull Point point);

    /**
     * Sets an item at the specified position in the section.
     *
     * @param point the position where the item should be set.
     * @param item the {@link MenuItem} to set.
     * @throws NullPointerException if {@code item} is {@code null}.
     * @throws IllegalArgumentException if {@code point} is out of range.
     */
    void setItem(@NotNull Point point, @NotNull MenuItem item);

    /**
     * Removes the item at the specified position in the section.
     *
     * @param point the position of the item to remove.
     * @throws IllegalArgumentException if {@code point} is out of range.
     */
    void removeItem(@NotNull Point point);
}
