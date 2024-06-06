package dev.acrispycookie.crispycommons.api.guis.menu.sections;

import dev.acrispycookie.crispycommons.api.guis.menu.MenuItem;
import dev.acrispycookie.crispycommons.implementations.guis.menu.wrappers.MenuData;
import dev.acrispycookie.crispycommons.implementations.guis.menu.sections.SimpleStaticSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.awt.*;

public interface StaticSection extends Section{

    static StaticSection oneItem(MenuItem item) {
        StaticSection section = new SimpleStaticSection(1, 1);
        section.setItem(0, item);
        return section;
    }

    static StaticSection oneRow(int width, MenuItem item) {
        StaticSection section = new SimpleStaticSection(1, width);
        for (int i = 0; i < width; i++) {
            section.setItem(new Point(i, 0), item);
        }
        return section;
    }

    static StaticSection oneColumn(int height, MenuItem item) {
        StaticSection section = new SimpleStaticSection(height, 1);
        for (int i = 0; i < height; i++) {
            section.setItem(new Point(0, i), item);
        }
        return section;
    }

    static StaticSection custom(int height, int width) {
        return new SimpleStaticSection(height, width);
    }

    void renderItem(Player player, MenuData data, Inventory inv, int pasteSlot, Point itemPoint);
    void renderItems(Player player, MenuData data, Inventory inv, int pasteSlot, int startingIndex);
    void renderItems(Player player, MenuData data, Inventory inv, int pasteSlot, Point startingPoint);
    int getHeight();
    int getWidth();
    MenuItem getItem(Point point);
    void setItem(Point point, MenuItem item);
    void removeItem(Point point);
}
