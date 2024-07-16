package dev.acrispycookie.crispycommons.api.guis.menu;

import dev.acrispycookie.crispycommons.CrispyCommons;
import dev.acrispycookie.crispycommons.api.guis.menu.sections.DynamicSection;
import dev.acrispycookie.crispycommons.api.guis.menu.sections.StaticSection;
import dev.acrispycookie.crispycommons.api.wrappers.itemstack.CrispyItemStack;
import dev.acrispycookie.crispycommons.implementations.guis.menu.SimpleMenuPage;
import dev.acrispycookie.crispycommons.implementations.guis.menu.wrappers.SectionInfo;
import dev.acrispycookie.crispycommons.utility.logging.CrispyLogger;
import dev.acrispycookie.crispycommons.utility.menus.InvalidMenuConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.awt.*;
import java.util.ArrayList;
import java.util.function.BiFunction;

public interface MenuPage {

    static MenuPage createEmpty(String title, int rows, int columns, BiFunction<Player, CrispyItemStack, Boolean> onPlayerItemClick) {
        try {
            return new SimpleMenuPage(title, rows, columns) {
                @Override
                public boolean onPlayerItemClick(Player player, CrispyItemStack itemStack) {
                    return onPlayerItemClick.apply(player, itemStack);
                }
            };
        } catch (InvalidMenuConfiguration e) {
            CrispyLogger.printException(CrispyCommons.getPlugin(), e, "Error while creating a menu page!");
        }

        return null;
    }

    MenuItem getItem(int index);
    void renderItem(Player player, int index);
    void renderItem(Player player, Point point);
    void renderItems(Player player);
    Inventory render(Player player);
    void addStaticSection(int pointIndex, int sectionOffset, StaticSection section);
    void addDynamicSection(int startIndex, int endIndex, int sectionOffset, DynamicSection section);
    void removeSection(int pointIndex);
    boolean onPlayerItemClick(Player player, CrispyItemStack itemStack);
    void onClose();
    SectionInfo getSection(int pointIndex);
    ArrayList<SectionInfo> getSections();
    void addStaticSection(Point point, int sectionOffset, StaticSection section);
    void addDynamicSection(Point start, Point end, int sectionOffset, DynamicSection section);
    void removeSection(Point point);
    SectionInfo getSection(Point point);
    String getTitle();
    int getRows();
    int getColumns();
    CrispyMenu getMenu();
    void setMenu(CrispyMenu menu);
}
