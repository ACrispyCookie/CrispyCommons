package dev.acrispycookie.crispycommons.implementations.guis.menu.sections;

import dev.acrispycookie.crispycommons.api.guis.menu.MenuItem;
import dev.acrispycookie.crispycommons.api.guis.menu.sections.Section;
import dev.acrispycookie.crispycommons.api.guis.menu.sections.StaticSection;
import dev.acrispycookie.crispycommons.implementations.guis.menu.wrappers.MenuData;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.awt.*;
import java.util.SortedMap;
import java.util.TreeMap;

public abstract class AbstractStaticSection extends AbstractSection implements StaticSection {

    protected final int height;
    protected final int width;
    public final SortedMap<Integer, MenuItem> items = new TreeMap<>();
    protected abstract void renderItemsInternal(Player player, MenuData data, Inventory toRender, int pasteSlot, int startingIndex);

    AbstractStaticSection(int height, int width) {
        this.height = height;
        this.width = width;
    }

    @Override
    public void renderItems(Player player, MenuData data, Inventory toRender, int pasteSlot, int startingIndex) {
        if(!Section.isSlotValid(pasteSlot, toRender) || !isSlotValid(startingIndex)) {
            return;
        }

        this.renderItemsInternal(player, data, toRender, pasteSlot, startingIndex);
    }

    @Override
    public void renderItem(Player player, MenuData data, Inventory toRender, int pasteSlot, Point startingPoint) {
        renderItem(player, data, toRender, pasteSlot, pointToIndex(startingPoint));
    }

    @Override
    public void renderItems(Player player, MenuData data, Inventory toRender, int pasteSlot, Point startingPoint) {
        renderItems(player, data, toRender, pasteSlot, pointToIndex(startingPoint));
    }

    @Override
    public MenuItem getItem(Point point) {
        return getItem(pointToIndex(point));
    }

    @Override
    public void setItem(Point point, MenuItem item) {
        setItem(pointToIndex(point), item);
    }

    @Override
    public void removeItem(Point point) {
        removeItem(pointToIndex(point));
    }

    @Override
    public MenuItem getItem(int index) {
        if (!isSlotValid(index)) {
            return null;
        }
        return items.get(index);
    }

    @Override
    public void setItem(int index, MenuItem item) {
        if (!isSlotValid(index)) {
            return;
        }
        items.put(index, item);
    }

    @Override
    public void removeItem(int index) {
        if (!isSlotValid(index)) {
            return;
        }
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

    private int pointToIndex(Point point) {
        return point.x + point.y * width;
    }
    
}
