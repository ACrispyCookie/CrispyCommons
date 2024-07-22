package dev.acrispycookie.crispycommons.implementations.guis.menu.sections;

import dev.acrispycookie.crispycommons.api.guis.menu.CrispyMenu;
import dev.acrispycookie.crispycommons.api.guis.menu.MenuItem;
import dev.acrispycookie.crispycommons.api.guis.menu.sections.DynamicSection;
import dev.acrispycookie.crispycommons.api.guis.menu.sections.Section;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public abstract class AbstractDynamicSection extends AbstractSection implements DynamicSection {

    protected final ArrayList<MenuItem> items = new ArrayList<>();
    protected abstract void renderItemsInternal(Player player, CrispyMenu menu, Inventory toRender, int startPasteSlot, int endPasteSlot, int startingIndex);

    AbstractDynamicSection(Collection<? extends MenuItem> items) {
        this.items.addAll(items);
    }

    @Override
    public void renderItems(Player player, CrispyMenu menu, Inventory inv, int startPasteSlot, int endPasteSlot, int startingIndex) {
        if(!Section.isSlotValid(startPasteSlot, inv) || !Section.isSlotValid(endPasteSlot, inv)) {
            return;
        }

        this.renderItemsInternal(player, menu, inv, startPasteSlot, endPasteSlot, startingIndex);
    }

    @Override
    public MenuItem getItem(int index) {
        return items.get(index);
    }

    @Override
    public void setItem(int index, MenuItem item) {
        items.set(index, item);
    }

    @Override
    public void addItem(MenuItem item) {
        items.add(item);
    }

    @Override
    public void addItem(int index, MenuItem item) {
        items.add(index, item);
    }

    @Override
    public void removeItem(int index) {
        items.remove(index);
    }

    @Override
    public void sortItems(Comparator<MenuItem> comparator) {
        items.sort(comparator);
    }

    @Override
    public void clearItems() {
        items.clear();
    }

    @Override
    public List<MenuItem> getItems() {
        return items;
    }

    @Override
    public boolean isSlotValid(int index) {
        return index >=0 && index < items.size();
    }

    public boolean hasDynamicItems() {
        for (MenuItem item : items) {
            if (item.getDisplay().isDynamic())
                return true;
        }
        return false;
    }
}
