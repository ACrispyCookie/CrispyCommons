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

public abstract class AbstractDynamicSection extends AbstractSection implements DynamicSection {

    protected final ArrayList<MenuItem> items = new ArrayList<>();
    protected abstract void renderItemsInternal(Player player, CrispyMenu menu, Inventory toRender, int startPasteSlot, int endPasteSlot, int startingIndex);

    AbstractDynamicSection(Collection<? extends MenuItem> items) {
        this.items.addAll(items);
    }

    @Override
    public void renderItems(@NotNull Player player, @NotNull CrispyMenu menu, @NotNull Inventory inv, int startPasteSlot, int endPasteSlot, int startingIndex) {
        if(!Section.isSlotValid(startPasteSlot, inv) || !Section.isSlotValid(endPasteSlot, inv)) {
            return;
        }

        this.renderItemsInternal(player, menu, inv, startPasteSlot, endPasteSlot, startingIndex);
    }

    @Override
    public @NotNull MenuItem getItem(int index) {
        if (index < 0 || index >= items.size())
            throw new IllegalArgumentException("Invalid page index given when getting a menu item");
        return items.get(index);
    }

    @Override
    public void setItem(int index, @NotNull MenuItem item) {
        if (index < 0 || index >= items.size())
            throw new IllegalArgumentException("Invalid page index given when setting a menu item");
        items.set(index, item);
    }

    @Override
    public void addItem(@NotNull MenuItem item) {
        items.add(item);
    }

    @Override
    public void addItem(int index, @NotNull MenuItem item) {
        if (index < 0 || index >= items.size())
            throw new IllegalArgumentException("Invalid page index given when adding a menu item");
        items.add(index, item);
    }

    @Override
    public void removeItem(int index) {
        if (index < 0 || index >= items.size())
            throw new IllegalArgumentException("Invalid page index given when removing a menu item");
        items.remove(index);
    }

    @Override
    public void sortItems(@NotNull Comparator<MenuItem> comparator) {
        items.sort(comparator);
    }

    @Override
    public void clearItems() {
        items.clear();
    }

    @Override
    public @NotNull List<MenuItem> getItems() {
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
