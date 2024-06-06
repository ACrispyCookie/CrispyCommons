package dev.acrispycookie.crispycommons.implementations.guis.menu;

import dev.acrispycookie.crispycommons.api.guis.menu.MenuItem;
import dev.acrispycookie.crispycommons.api.guis.menu.sections.DynamicSection;
import dev.acrispycookie.crispycommons.api.guis.menu.MenuPage;
import dev.acrispycookie.crispycommons.api.guis.menu.sections.Section;
import dev.acrispycookie.crispycommons.api.guis.menu.sections.StaticSection;
import dev.acrispycookie.crispycommons.implementations.guis.menu.wrappers.Holder;
import dev.acrispycookie.crispycommons.utility.menus.InvalidMenuConfiguration;
import dev.acrispycookie.crispycommons.implementations.guis.menu.wrappers.MenuData;
import dev.acrispycookie.crispycommons.implementations.guis.menu.wrappers.SectionInfo;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Function;

public abstract class SimpleMenuPage implements MenuPage {

    private MenuData data;
    private final String title;
    private final int rows;
    private final int columns;
    private final ArrayList<SectionInfo> sections = new ArrayList<>();
    private final HashMap<Integer, SectionInfo> schema = new HashMap<>();
    private Inventory cachedInventory;

    public SimpleMenuPage(String title, int rows, int columns) throws InvalidMenuConfiguration {
        if (columns != 9 && columns != 3) {
            throw new InvalidMenuConfiguration("Invalid number of columns in page!");
        }
        this.title = title;
        this.rows = rows;
        this.columns = columns;
    }

    @Override
    public void renderItem(Player player, int index) {
        if(cachedInventory == null)
            return;
        if (!isValidSlot(index))
            return;
        if (!schema.containsKey(index))
            return;

        SectionInfo info = schema.get(index);
        Section section = info.getSection();
        section.renderItem(player, data, cachedInventory, index, info.getStartIndex() + index);
    }

    @Override
    public void renderItems(Player player) {
        if(cachedInventory == null)
            return;

        for (SectionInfo info : sections) {
            Section section = info.getSection();

            if (section instanceof DynamicSection) {
                ((DynamicSection) section).renderItems(player, data, cachedInventory, info.getStartIndex(), info.getEndIndex(), info.getOffset());
            } else {
                ((StaticSection) section).renderItems(player, data, cachedInventory, info.getStartIndex(), info.getOffset());
            }
        }
    }

    @Override
    public Inventory render(Player player) {
        if (cachedInventory == null) {
            cachedInventory = new Holder(title, rows, columns, this).getInventory();
            renderItems(player);
        }

        return cachedInventory;
    }

    @Override
    public MenuItem getItem(int index) {
        SectionInfo info = schema.get(index);
        int startIndex = info.getStartIndex();
        int endIndex = info.getEndIndex();

        int width;
        if (info.getSection() instanceof StaticSection) {
            width = ((StaticSection) info.getSection()).getWidth();
        } else {
            int height = (endIndex - startIndex)/columns + 1;
            width = endIndex - (height - 1) * columns - startIndex + 1;
        }
        int xPos = (index - startIndex) % width;
        int yPos  = (index - startIndex) / columns;
        int sectionIndex = xPos + yPos * width + info.getOffset();

        return info.getSection().getItem(sectionIndex);
    }

    @Override
    public void addStaticSection(int startIndex, int sectionOffset, StaticSection section) {
        if(!isValidSlot(startIndex + sectionOffset) || sectionOffset < 0)
            return;

        int endIndex = startIndex + (section.getHeight() - 1) * columns + section.getWidth();
        int width = section.getWidth();
        SectionInfo info = new SectionInfo(startIndex, endIndex, sectionOffset, section);
        sections.add(info);
        fillSection(startIndex, sectionOffset, width, width * section.getHeight(), (slot) -> {
            schema.put(slot, info);
            return null;
        });
    }

    @Override
    public void addDynamicSection(int startIndex, int endIndex, int sectionOffset, DynamicSection section) {
        if(!isValidSlot(startIndex + sectionOffset) || sectionOffset < 0)
            return;

        int height = (endIndex - startIndex)/columns + 1;
        int width = endIndex - (height - 1) * columns - startIndex + 1;
        SectionInfo info = new SectionInfo(startIndex, endIndex, sectionOffset, section);
        sections.add(info);
        fillSection(startIndex, sectionOffset, width, width * height + sectionOffset, (slot) -> {
            schema.put(slot, info);
            return null;
        });
    }

    @Override
    public void removeSection(int pointIndex) {
        if (!isValidSlot(pointIndex))
            return;

        SectionInfo info = schema.get(pointIndex);
        int startIndex = info.getStartIndex();
        int endIndex = info.getEndIndex();

        int width, sectionSize;
        if (info.getSection() instanceof DynamicSection) {
            int height = (endIndex - startIndex)/columns + 1;
            width = endIndex - (height - 1) * columns - startIndex + 1;
            sectionSize = height * width + info.getOffset();
        } else {
            StaticSection section = ((StaticSection) info.getSection());
            width = section.getWidth();
            sectionSize = width * section.getHeight();
        }

        fillSection(startIndex, info.getOffset(), width, sectionSize, (slot) -> {
            schema.put(slot, info);
            return null;
        });
    }

    @Override
    public void onClose() {
        for (SectionInfo info : sections) {
            info.getSection().onClose(cachedInventory);
        }
        this.cachedInventory = null;
    }

    @Override
    public void renderItem(Player player, Point pointToRender) {
        if(isValidPoint(pointToRender))
            renderItem(player, pointToIndex(pointToRender));
    }

    @Override
    public SectionInfo getSection(int pointIndex) {
        if (!isValidSlot(pointIndex))
            return null;

        return schema.get(pointIndex);
    }

    @Override
    public void addStaticSection(Point point, int sectionOffset, StaticSection section) {
        if (isValidPoint(point))
            addStaticSection(pointToIndex(point), sectionOffset, section);
    }

    @Override
    public void addDynamicSection(Point start, Point end, int sectionOffset, DynamicSection section) {
        if (isValidPoint(start) && isValidPoint(end))
            addDynamicSection(pointToIndex(start), pointToIndex(end), sectionOffset, section);
    }

    @Override
    public void removeSection(Point point) {
        if (isValidPoint(point))
            removeSection(pointToIndex(point));
    }

    @Override
    public SectionInfo getSection(Point startPoint) {
        if (isValidPoint(startPoint))
            return getSection(pointToIndex(startPoint));
        return null;
    }

    @Override
    public MenuData getMenuData() {
        return data;
    }

    @Override
    public void setMenuData(MenuData data) {
        this.data = data;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public int getRows() {
        return rows;
    }

    @Override
    public int getColumns() {
        return columns;
    }

    @Override
    public ArrayList<SectionInfo> getSections() {
        return sections;
    }

    private void fillSection(int startIndex, int sectionOffset, int width, int sectionSize, Function<Integer, Void> forEach) {
        int size = rows * columns;
        int slot = startIndex;
        for (int i = sectionOffset; slot < size && i < sectionSize; i++) {
            forEach.apply(slot);

            slot += (i - sectionOffset + 1) % width == 0 ? columns - width + 1 : 1;
        }
    }

    private boolean isValidPoint(Point point) {
        return point.x >= 0 && point.y >= 0 && point.x < columns && point.y < rows;
    }

    private boolean isValidSlot(int slot) {
        return slot >= 0 && slot < rows * columns;
    }

    private int pointToIndex(Point point) {
        return point.x + columns * point.y;
    }

    private int getSize() {
        return rows * columns;
    }
}
