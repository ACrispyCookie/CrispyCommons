package dev.acrispycookie.crispycommons.implementations.gui.menu.section;

import dev.acrispycookie.crispycommons.api.gui.menu.CrispyMenu;
import dev.acrispycookie.crispycommons.api.gui.menu.MenuItem;
import dev.acrispycookie.crispycommons.api.gui.menu.section.Section;
import dev.acrispycookie.crispycommons.api.gui.menu.section.StaticSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * An abstract base class representing a static section within a menu.
 * <p>
 * {@code AbstractStaticSection} extends {@link AbstractSection} and implements {@link StaticSection},
 * providing a foundation for menu sections that have fixed dimensions and can contain items at specific
 * positions.
 * </p>
 */
public abstract class AbstractStaticSection extends AbstractSection implements StaticSection {

    /**
     * The height of the section, in terms of the number of rows.
     */
    protected final int height;

    /**
     * The width of the section, in terms of the number of columns.
     */
    protected final int width;

    /**
     * A sorted map that associates slot indices with {@link MenuItem} objects within the section.
     */
    public final SortedMap<Integer, MenuItem> items = new TreeMap<>();

    /**
     * Constructs an {@code AbstractStaticSection} with the specified dimensions.
     *
     * @param height the height of the section, in rows.
     * @param width  the width of the section, in columns.
     */
    AbstractStaticSection(int height, int width) {
        this.height = height;
        this.width = width;
    }

    /**
     * Renders items within the section into the specified inventory for the given player.
     * <p>
     * This method is an internal implementation detail and should be overridden by subclasses to
     * define specific rendering logic.
     * </p>
     *
     * @param player        the player for whom the items are being rendered.
     * @param menu          the menu that contains this section.
     * @param toRender      the inventory where the items will be rendered.
     * @param pasteSlot     the slot in the inventory where the first item will be placed.
     * @param startingIndex the index of the first item to render.
     */
    protected abstract void renderItemsInternal(@NotNull Player player, @NotNull CrispyMenu menu, @NotNull Inventory toRender, int pasteSlot, int startingIndex);

    /**
     * Renders items within the section into the specified inventory for the given player, starting
     * from a specific slot and index.
     *
     * @param player        the player for whom the items are being rendered.
     * @param menu          the menu that contains this section.
     * @param toRender      the inventory where the items will be rendered.
     * @param pasteSlot     the slot in the inventory where the first item will be placed.
     * @param startingIndex the index of the first item to render.
     */
    @Override
    public void renderItems(@NotNull Player player, @NotNull CrispyMenu menu, @NotNull Inventory toRender, int pasteSlot, int startingIndex) {
        if (!Section.isSlotValid(pasteSlot, toRender) || !isSlotValid(startingIndex)) {
            return;
        }

        this.renderItemsInternal(player, menu, toRender, pasteSlot, startingIndex);
    }

    /**
     * Renders a single item within the section at the specified point in the inventory for the given player.
     *
     * @param player        the player for whom the item is being rendered.
     * @param menu          the menu that contains this section.
     * @param inventory     the inventory where the item will be rendered.
     * @param pasteSlot     the slot in the inventory where the item will be placed.
     * @param startingPoint the point within the section where the item will be rendered.
     */
    @Override
    public void renderItem(@NotNull Player player, @NotNull CrispyMenu menu, @NotNull Inventory inventory, int pasteSlot, Point startingPoint) {
        renderItem(player, menu, inventory, pasteSlot, pointToIndex(startingPoint));
    }

    /**
     * Renders multiple items within the section starting from a specific point in the inventory for the given player.
     *
     * @param player        the player for whom the items are being rendered.
     * @param menu          the menu that contains this section.
     * @param toRender      the inventory where the items will be rendered.
     * @param pasteSlot     the slot in the inventory where the first item will be placed.
     * @param startingPoint the point within the section where the first item will be rendered.
     */
    @Override
    public void renderItems(@NotNull Player player, @NotNull CrispyMenu menu, @NotNull Inventory toRender, int pasteSlot, Point startingPoint) {
        renderItems(player, menu, toRender, pasteSlot, pointToIndex(startingPoint));
    }

    /**
     * Retrieves the {@link MenuItem} at the specified point within the section.
     *
     * @param point the point within the section to retrieve the item from.
     * @return the {@link MenuItem} at the specified point.
     * @throws IllegalArgumentException if the point is not valid within the section.
     */
    @Override
    public @NotNull MenuItem getItem(@NotNull Point point) {
        return getItem(pointToIndex(point));
    }

    /**
     * Sets a {@link MenuItem} at the specified point within the section.
     *
     * @param point the point within the section to set the item at.
     * @param item  the {@link MenuItem} to set.
     * @throws IllegalArgumentException if the point is not valid within the section.
     */
    @Override
    public void setItem(@NotNull Point point, @NotNull MenuItem item) {
        setItem(pointToIndex(point), item);
    }

    /**
     * Removes the {@link MenuItem} at the specified point within the section.
     *
     * @param point the point within the section to remove the item from.
     * @throws IllegalArgumentException if the point is not valid within the section.
     */
    @Override
    public void removeItem(@NotNull Point point) {
        removeItem(pointToIndex(point));
    }

    /**
     * Retrieves the {@link MenuItem} at the specified index within the section.
     *
     * @param index the index within the section to retrieve the item from.
     * @return the {@link MenuItem} at the specified index.
     * @throws IllegalArgumentException if the index is not valid within the section.
     */
    @Override
    public @NotNull MenuItem getItem(int index) {
        if (!isSlotValid(index)) {
            throw new IllegalArgumentException("Invalid page index given when getting a menu item");
        }
        return items.get(index);
    }

    /**
     * Sets a {@link MenuItem} at the specified index within the section.
     *
     * @param index the index within the section to set the item at.
     * @param item  the {@link MenuItem} to set.
     * @throws IllegalArgumentException if the index is not valid within the section.
     */
    @Override
    public void setItem(int index, @NotNull MenuItem item) {
        if (!isSlotValid(index)) {
            throw new IllegalArgumentException("Invalid page index given when setting a menu item");
        }
        items.put(index, item);
    }

    /**
     * Removes the {@link MenuItem} at the specified index within the section.
     *
     * @param index the index within the section to remove the item from.
     * @throws IllegalArgumentException if the index is not valid within the section.
     */
    @Override
    public void removeItem(int index) {
        if (!isSlotValid(index)) {
            throw new IllegalArgumentException("Invalid page index given when removing a menu item");
        }
        items.remove(index);
    }

    /**
     * Clears all items from the section.
     */
    @Override
    public void clearItems() {
        items.clear();
    }

    /**
     * Retrieves the height of the section.
     *
     * @return the height of the section, in rows.
     */
    @Override
    public int getHeight() {
        return height;
    }

    /**
     * Retrieves the width of the section.
     *
     * @return the width of the section, in columns.
     */
    @Override
    public int getWidth() {
        return width;
    }

    /**
     * Checks if the specified slot index is valid within the section.
     *
     * @param slot the slot index to check.
     * @return {@code true} if the slot index is valid, {@code false} otherwise.
     */
    public boolean isSlotValid(int slot) {
        return slot >= 0 && slot < height * width;
    }

    /**
     * Checks if the section contains any dynamic items.
     *
     * @return {@code true} if the section contains dynamic items, {@code false} otherwise.
     */
    public boolean hasDynamicItems() {
        for (MenuItem item : items.values()) {
            if (item.getDisplay().isDynamic()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Converts a {@link Point} to its corresponding index within the section.
     *
     * @param point the point to convert.
     * @return the index corresponding to the specified point.
     */
    private int pointToIndex(@NotNull Point point) {
        return point.x + point.y * width;
    }
}

