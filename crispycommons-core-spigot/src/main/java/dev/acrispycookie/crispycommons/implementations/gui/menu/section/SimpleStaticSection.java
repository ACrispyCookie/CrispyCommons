package dev.acrispycookie.crispycommons.implementations.gui.menu.section;

import dev.acrispycookie.crispycommons.api.gui.menu.CrispyMenu;
import dev.acrispycookie.crispycommons.utility.menu.InventoryWidth;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

/**
 * A simple implementation of a static section in a menu.
 * <p>
 * {@code SimpleStaticSection} extends {@link AbstractStaticSection} and provides
 * a basic implementation for rendering items in a static section of a menu.
 * The section's dimensions are defined by its height and width.
 * </p>
 */
public class SimpleStaticSection extends AbstractStaticSection {

    /**
     * Constructs a {@code SimpleStaticSection} with the specified height and width.
     *
     * @param height the number of rows in the section.
     * @param width  the number of columns in the section.
     */
    public SimpleStaticSection(int height, int width) {
        super(height, width);
    }

    /**
     * Renders items within the section into the specified inventory.
     * <p>
     * This method overrides {@link AbstractStaticSection#renderItemsInternal(Player, CrispyMenu, Inventory, int, int)}
     * and implements the rendering logic for placing items into the inventory based on the section's dimensions.
     * </p>
     *
     * @param player      the player for whom the items are being rendered.
     * @param menu        the menu that contains this section.
     * @param toRender    the inventory where the items will be rendered.
     * @param pasteSlot   the starting slot in the inventory where the first item will be placed.
     * @param startingIndex the index of the first item to render within the section.
     */
    @Override
    public void renderItemsInternal(@NotNull Player player, @NotNull CrispyMenu menu, @NotNull Inventory toRender, int pasteSlot, int startingIndex) {
        int inventoryWidth = InventoryWidth.getType(toRender.getType());
        int height = this.getHeight();
        int width = this.getWidth();
        int size = toRender.getSize();
        int sectionSize = height * width;

        for (int i = startingIndex; i < sectionSize && pasteSlot < size; i++) {
            if (!items.containsKey(startingIndex))
                continue;

            renderValidItem(player, menu, toRender, pasteSlot, i);
            pasteSlot += (i - startingIndex + 1) % width == 0 ? inventoryWidth - width + 1 : 1;
        }
    }
}

