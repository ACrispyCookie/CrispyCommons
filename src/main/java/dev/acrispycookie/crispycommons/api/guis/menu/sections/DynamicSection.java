package dev.acrispycookie.crispycommons.api.guis.menu.sections;

import dev.acrispycookie.crispycommons.api.guis.menu.CrispyMenu;
import dev.acrispycookie.crispycommons.api.guis.menu.MenuItem;
import dev.acrispycookie.crispycommons.implementations.guis.menu.sections.SimpleDynamicSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public interface DynamicSection extends Section {

    static DynamicSection create(Collection<? extends MenuItem> items) {
        return new SimpleDynamicSection(items);
    }

    static DynamicSection create() {
        return new SimpleDynamicSection(Collections.emptyList());
    }

    void renderItems(Player player, CrispyMenu menu, Inventory inv, int startPasteSlot, int endPasteSlot, int startingIndex);
    void addItem(int index, MenuItem item);
    void addItem(MenuItem item);
    void sortItems(Comparator<MenuItem> comparator);
    List<MenuItem> getItems();
}
