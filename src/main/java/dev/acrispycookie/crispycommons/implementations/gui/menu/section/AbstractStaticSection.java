package dev.acrispycookie.crispycommons.implementations.gui.menu.section;

import dev.acrispycookie.crispycommons.api.gui.menu.CrispyMenu;
import dev.acrispycookie.crispycommons.api.gui.menu.MenuItem;
import dev.acrispycookie.crispycommons.api.gui.menu.section.Section;
import dev.acrispycookie.crispycommons.api.gui.menu.section.StaticSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.SortedMap;
import java.util.TreeMap;

public abstract class AbstractStaticSection extends AbstractSection implements StaticSection {

    protected final int height;
    protected final int width;
    public final SortedMap<Integer, MenuItem> items = new TreeMap<>();
    protected abstract void renderItemsInternal(Player player, CrispyMenu menu, Inventory toRender, int pasteSlot, int startingIndex);

    AbstractStaticSection(int height, int width) {
        this.height = height;
        this.width = width;
    }

    @Override
    public void renderItems(@NotNull Player player, @NotNull CrispyMenu menu, @NotNull Inventory toRender, int pasteSlot, int startingIndex) {
        if(!Section.isSlotValid(pasteSlot, toRender) || !isSlotValid(startingIndex)) {
            return;
        }

        this.renderItemsInternal(player, menu, toRender, pasteSlot, startingIndex);
    }

    @Override
    public void renderItem(@NotNull Player player, @NotNull CrispyMenu menu, @NotNull Inventory inventory, int pasteSlot, Point startingPoint) {
        renderItem(player, menu, inventory, pasteSlot, pointToIndex(startingPoint));
    }

    @Override
    public void renderItems(@NotNull Player player, @NotNull CrispyMenu menu, @NotNull Inventory toRender, int pasteSlot, Point startingPoint) {
        renderItems(player, menu, toRender, pasteSlot, pointToIndex(startingPoint));
    }

    @Override
    public @NotNull MenuItem getItem(@NotNull Point point) {
        return getItem(pointToIndex(point));
    }

    @Override
    public void setItem(@NotNull Point point, @NotNull MenuItem item) {
        setItem(pointToIndex(point), item);
    }

    @Override
    public void removeItem(@NotNull Point point) {
        removeItem(pointToIndex(point));
    }

    @Override
    public @NotNull MenuItem getItem(int index) {
        if (!isSlotValid(index))
            throw new IllegalArgumentException("Invalid page index given when getting a menu item");
        return items.get(index);
    }

    @Override
    public void setItem(int index, @NotNull MenuItem item) {
        if (!isSlotValid(index))
            throw new IllegalArgumentException("Invalid page index given when setting a menu item");
        items.put(index, item);
    }

    @Override
    public void removeItem(int index) {
        if (!isSlotValid(index))
            throw new IllegalArgumentException("Invalid page index given when removing a menu item");
        items.remove(index);
    }

    @Override
    public void clearItems() {
        items.clear();
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    public boolean isSlotValid(int slot) {
        return slot >=0 && slot < height * width;
    }

    public boolean hasDynamicItems() {
        for (MenuItem item : items.values()) {
            if (item.getDisplay().isDynamic())
                return true;
        }
        return false;
    }

    private int pointToIndex(Point point) {
        return point.x + point.y * width;
    }
    
}
