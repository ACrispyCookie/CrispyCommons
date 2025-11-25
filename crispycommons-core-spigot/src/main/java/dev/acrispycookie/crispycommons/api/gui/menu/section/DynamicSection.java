package dev.acrispycookie.crispycommons.api.gui.menu.section;

import dev.acrispycookie.crispycommons.api.gui.menu.CrispyMenu;
import dev.acrispycookie.crispycommons.api.gui.menu.MenuItem;
import dev.acrispycookie.crispycommons.implementations.gui.menu.section.SimpleDynamicSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Represents a section within a menu that dynamically manages a collection of {@link MenuItem} objects.
 * <p>
 * Unlike fixed-size {@link StaticSection}, a {@link DynamicSection} does not have predefined dimensions and can be rendered
 * with varying sizes depending on the menu configuration.
 * </p>
 */
public interface DynamicSection extends Section {

    /**
     * Creates a new {@link DynamicSection} with the specified collection of menu items.
     *
     * @param items a collection of {@link MenuItem} objects to initialize the section with.
     * @return a new {@link DynamicSection} instance populated with the given items.
     * @throws NullPointerException if {@code items} is null.
     */
    static @NotNull DynamicSection create(@NotNull Collection<? extends MenuItem> items) {
        return new SimpleDynamicSection(items);
    }

    /**
     * Creates a new {@link DynamicSection} with no items.
     *
     * @return a new {@link DynamicSection} instance with an empty item list.
     */
    static @NotNull DynamicSection create() {
        return new SimpleDynamicSection(Collections.emptyList());
    }

    /**
     * Renders items in the section into the specified inventory, placing them within the given slot range.
     * <p>
     * If the specified slot or index are invalid the method returns early.
     * </p>
     *
     * @param player the player for whom the items are rendered.
     * @param menu the menu to which this section belongs.
     * @param inv the bukkit inventory where the items will be placed.
     * @param startPasteSlot the first slot index where items should be placed.
     * @param endPasteSlot the last slot index where items should be placed.
     * @param startingIndex the starting index in the item list to render.
     * @throws NullPointerException if any of the {@code player}, {@code menu}, or {@code inventory} are {@code null}.
     */
    void renderItems(@NotNull Player player, @NotNull CrispyMenu menu, @NotNull Inventory inv, int startPasteSlot, int endPasteSlot, int startingIndex);

    /**
     * Adds a new item at the specified index in this section.
     *
     * @param index the index where the item should be added.
     * @param item the {@link MenuItem} to add.
     * @throws NullPointerException if {@code item} is {@code null}.
     * @throws IllegalArgumentException if {@code index} is out of bounds.
     */
    void addItem(int index, @NotNull MenuItem item);

    /**
     * Adds a new item to the end of the item list in this section.
     *
     * @param item the {@link MenuItem} to add.
     * @throws NullPointerException if {@code item} is {@code null}.
     */
    void addItem(@NotNull MenuItem item);

    /**
     * Sorts the items in this section using the provided comparator.
     *
     * @param comparator the comparator to determine the order of items.
     * @throws NullPointerException if {@code comparator} is {@code null}.
     */
    void sortItems(@NotNull Comparator<MenuItem> comparator);

    /**
     * Retrieves the list of items currently in this section.
     *
     * @return a list of {@link MenuItem} objects in this section.
     */
    @NotNull List<MenuItem> getItems();
}
