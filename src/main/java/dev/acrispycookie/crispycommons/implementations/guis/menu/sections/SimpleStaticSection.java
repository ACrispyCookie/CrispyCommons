package dev.acrispycookie.crispycommons.implementations.guis.menu.sections;

import dev.acrispycookie.crispycommons.implementations.guis.menu.wrappers.MenuData;
import dev.acrispycookie.crispycommons.utility.menus.InventoryWidth;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class SimpleStaticSection extends AbstractStaticSection {

    public SimpleStaticSection(int height, int width) {
        super(height, width);
    }

    @Override
    public void renderItemsInternal(Player player, MenuData data, Inventory toRender, int pasteSlot, int startingIndex) {
        int inventoryWidth = InventoryWidth.getType(toRender.getType());
        int height = this.getHeight();
        int width = this.getWidth();
        int size = toRender.getSize();
        int sectionSize = height * width;

        for (int i = startingIndex; i < sectionSize && pasteSlot < size; i++) {
            if (!items.containsKey(startingIndex))
                continue;

            renderValidItem(player, data, toRender, pasteSlot, i);
            pasteSlot += (i - startingIndex + 1) % width == 0 ? inventoryWidth - width + 1 : 1;
        }
    }
}
