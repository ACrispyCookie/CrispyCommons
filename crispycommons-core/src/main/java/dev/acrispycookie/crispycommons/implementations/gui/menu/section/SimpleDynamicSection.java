package dev.acrispycookie.crispycommons.implementations.gui.menu.section;

import dev.acrispycookie.crispycommons.api.gui.menu.CrispyMenu;
import dev.acrispycookie.crispycommons.api.gui.menu.MenuItem;
import dev.acrispycookie.crispycommons.utility.menu.InventoryWidth;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

/**
 * A simple implementation of a dynamic section in a menu.
 * <p>
 * {@code SimpleDynamicSection} extends {@link AbstractDynamicSection} and provides
 * a basic implementation for rendering items dynamically in a section of a menu.
 * The items in this section can be added, removed, or rearranged, and the section
 * adjusts its content accordingly.
 * </p>
 */
public class SimpleDynamicSection extends AbstractDynamicSection {

    /**
     * Constructs a {@code SimpleDynamicSection} with the specified collection of menu items.
     *
     * @param items the collection of {@link MenuItem} objects to be included in this section.
     */
    public SimpleDynamicSection(@NotNull Collection<? extends MenuItem> items) {
        super(items);
    }

    /**
     * Renders the items dynamically within the specified inventory.
     * <p>
     * This method overrides {@link AbstractDynamicSection#renderItemsInternal(Player, CrispyMenu, Inventory, int, int, int)}
     * and implements the rendering logic for placing items into the inventory based on the dynamic section's dimensions.
     * The items are rendered from the starting index within the defined paste slot range.
     * </p>
     *
     * @param player        the player for whom the items are being rendered.
     * @param menu          the menu that contains this section.
     * @param toRender      the inventory where the items will be rendered.
     * @param pasteSlot     the starting slot in the inventory where the first item will be placed.
     * @param endPasteSlot  the ending slot in the inventory where the last item will be placed.
     * @param startingIndex the index of the first item to render within the section.
     */
    @Override
    public void renderItemsInternal(@NotNull Player player, @NotNull CrispyMenu menu, @NotNull Inventory toRender, int pasteSlot, int endPasteSlot, int startingIndex) {
        int inventoryWidth = InventoryWidth.getType(toRender.getType());
        int height = (endPasteSlot - pasteSlot) / inventoryWidth + 1;
        int width = endPasteSlot - (height - 1) * inventoryWidth - pasteSlot + 1;
        int size = toRender.getSize();
        int sectionSize = width * height;

        for (int i = startingIndex; i < sectionSize + startingIndex && pasteSlot < size; i++) {
            if (i >= items.size())
                break;

            renderValidItem(player, menu, toRender, pasteSlot, i);
            pasteSlot += (i - startingIndex + 1) % width == 0 ? inventoryWidth - width + 1 : 1;
        }
    }
}

