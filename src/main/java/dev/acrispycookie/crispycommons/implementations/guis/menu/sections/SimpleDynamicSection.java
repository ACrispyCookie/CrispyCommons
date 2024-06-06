package dev.acrispycookie.crispycommons.implementations.guis.menu.sections;

import dev.acrispycookie.crispycommons.api.guis.menu.MenuItem;
import dev.acrispycookie.crispycommons.implementations.guis.menu.wrappers.MenuData;
import dev.acrispycookie.crispycommons.utility.menus.InventoryWidth;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.Collection;

public class SimpleDynamicSection extends AbstractDynamicSection {

    public SimpleDynamicSection(Collection<? extends MenuItem> items) {
        super(items);
    }

    @Override
    public void renderItemsInternal(Player player, MenuData data, Inventory toRender, int pasteSlot, int endPasteSlot, int startingIndex) {
        int inventoryWidth = InventoryWidth.getType(toRender.getType());
        int height = (endPasteSlot - pasteSlot)/inventoryWidth + 1;
        int width = endPasteSlot - (height - 1) * inventoryWidth - pasteSlot + 1;
        int size = toRender.getSize();
        int sectionSize = width * height;

        for (int i = startingIndex; i < sectionSize + startingIndex && pasteSlot < size; i++) {
            if(i >= items.size())
                break;

            renderValidItem(player, data, toRender, pasteSlot, i);
            pasteSlot += (i - startingIndex + 1) % width == 0 ? inventoryWidth - width + 1 : 1;
        }
    }
}
