package dev.acrispycookie.crispycommons.implementations.gui.menu.section;

import dev.acrispycookie.crispycommons.api.gui.menu.CrispyMenu;
import dev.acrispycookie.crispycommons.utility.menu.InventoryWidth;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class SimpleStaticSection extends AbstractStaticSection {

    public SimpleStaticSection(int height, int width) {
        super(height, width);
    }

    @Override
    public void renderItemsInternal(Player player, CrispyMenu menu, Inventory toRender, int pasteSlot, int startingIndex) {
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
