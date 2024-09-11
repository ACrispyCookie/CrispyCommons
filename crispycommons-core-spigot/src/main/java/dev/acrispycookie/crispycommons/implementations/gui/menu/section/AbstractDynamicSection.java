package dev.acrispycookie.crispycommons.implementations.gui.menu.section;

import dev.acrispycookie.crispycommons.api.gui.menu.CrispyMenu;
import dev.acrispycookie.crispycommons.api.gui.menu.MenuItem;
import dev.acrispycookie.crispycommons.api.gui.menu.section.DynamicSection;
import dev.acrispycookie.crispycommons.api.gui.menu.section.Section;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

/**
 * An abstract base class representing a dynamic section within a menu.
 * <p>
 * {@code AbstractDynamicSection} extends {@link AbstractSection} and implements {@link DynamicSection},
 * providing a foundation for menu sections that contain dynamic items which can be modified, sorted, and rendered
 * within the menu.
 * </p>
 */
public abstract class AbstractDynamicSection extends AbstractSection implements DynamicSection {

    /**
     * The list of {@link MenuItem} objects contained within this section.
     */
    protected final ArrayList<MenuItem> items = new ArrayList<>();

    /**
     * Constructs an {@code AbstractDynamicSection} with the specified collection of items.
     *
     * @param items the initial collection of {@link MenuItem} objects to be contained within this section.
     */
    AbstractDynamicSection(@NotNull Collection<? extends MenuItem> items) {
        this.items.addAll(items);
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
     * @param startPasteSlot the starting slot in the inventory where the first item will be placed.
     * @param endPasteSlot   the ending slot in the inventory where the last item will be placed.
     * @param startingIndex the index of the first item to render.
     */
    protected abstract void renderItemsInternal(@NotNull Player player, @NotNull CrispyMenu menu, @NotNull Inventory toRender, int startPasteSlot, int endPasteSlot, int startingIndex);

    /**
     * Renders items within the section into the specified inventory for the given player, starting
     * from a specific slot and index.
     *
     * @param player         the player for whom the items are being rendered.
     * @param menu           the menu that contains this section.
     * @param inv            the inventory where the items will be rendered.
     * @param startPasteSlot the starting slot in the inventory where the first item will be placed.
     * @param endPasteSlot   the ending slot in the inventory where the last item will be placed.
     * @param startingIndex  the index of the first item to render.
     */
    @Override
    public void renderItems(@NotNull Player player, @NotNull CrispyMenu menu, @NotNull Inventory inv, int startPasteSlot, int endPasteSlot, int startingIndex) {
        if (!Section.isSlotValid(startPasteSlot, inv) || !Section.isSlotValid(endPasteSlot, inv)) {
            return;
        }

        this.renderItemsInternal(player, menu, inv, startPasteSlot, endPasteSlot, startingIndex);
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
        if (index < 0 || index >= items.size()) {
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
        if (index < 0 || index >= items.size()) {
            throw new IllegalArgumentException("Invalid page index given when setting a menu item");
        }
        items.set(index, item);
    }

    /**
     * Adds a {@link MenuItem} to the section.
     *
     * @param item the {@link MenuItem} to add.
     */
    @Override
    public void addItem(@NotNull MenuItem item) {
        items.add(item);
    }

    /**
     * Adds a {@link MenuItem} to the section at the specified index.
     *
     * @param index the index within the section to add the item at.
     * @param item  the {@link MenuItem} to add.
     * @throws IllegalArgumentException if the index is not valid within the section.
     */
    @Override
    public void addItem(int index, @NotNull MenuItem item) {
        if (index < 0 || index >= items.size()) {
            throw new IllegalArgumentException("Invalid page index given when adding a menu item");
        }
        items.add(index, item);
    }

    /**
     * Removes the {@link MenuItem} at the specified index within the section.
     *
     * @param index the index within the section to remove the item from.
     * @throws IllegalArgumentException if the index is not valid within the section.
     */
    @Override
    public void removeItem(int index) {
        if (index < 0 || index >= items.size()) {
            throw new IllegalArgumentException("Invalid page index given when removing a menu item");
        }
        items.remove(index);
    }

    /**
     * Sorts the items in the section using the specified comparator.
     *
     * @param comparator the comparator to determine the order of the items.
     */
    @Override
    public void sortItems(@NotNull Comparator<MenuItem> comparator) {
        items.sort(comparator);
    }

    /**
     * Clears all items from the section.
     */
    @Override
    public void clearItems() {
        items.clear();
    }

    /**
     * Retrieves the list of {@link MenuItem} objects contained within the section.
     *
     * @return a list of {@link MenuItem} objects.
     */
    @Override
    public @NotNull List<MenuItem> getItems() {
        return items;
    }

    /**
     * Checks if the specified slot index is valid within the section.
     *
     * @param index the slot index to check.
     * @return {@code true} if the slot index is valid, {@code false} otherwise.
     */
    @Override
    public boolean isSlotValid(int index) {
        return index >= 0 && index < items.size();
    }

    /**
     * Checks if the section contains any dynamic items.
     *
     * @return {@code true} if the section contains dynamic items, {@code false} otherwise.
     */
    public boolean hasDynamicItems() {
        for (MenuItem item : items) {
            if (item.getDisplay().isDynamic()) {
                return true;
            }
        }
        return false;
    }
}

