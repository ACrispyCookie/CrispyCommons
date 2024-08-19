package dev.acrispycookie.crispycommons.implementations.gui.menu;

import dev.acrispycookie.crispycommons.api.gui.menu.CrispyMenu;
import dev.acrispycookie.crispycommons.api.gui.menu.MenuItem;
import dev.acrispycookie.crispycommons.api.gui.menu.MenuPage;
import dev.acrispycookie.crispycommons.api.gui.menu.section.DynamicSection;
import dev.acrispycookie.crispycommons.api.gui.menu.section.Section;
import dev.acrispycookie.crispycommons.api.gui.menu.section.StaticSection;
import dev.acrispycookie.crispycommons.implementations.gui.menu.data.Holder;
import dev.acrispycookie.crispycommons.implementations.gui.menu.data.SectionData;
import dev.acrispycookie.crispycommons.utility.menu.InvalidMenuConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.function.Function;

public abstract class SimpleMenuPage implements MenuPage {

    private CrispyMenu menu;
    private final String title;
    private final int rows;
    private final int columns;
    private final ArrayList<SectionData> sections = new ArrayList<>();
    private final HashMap<Integer, SectionData> schema = new HashMap<>();
    private final HashMap<Player, Inventory> cachedInventory = new HashMap<>();

    public SimpleMenuPage(String title, int rows, int columns) throws InvalidMenuConfiguration {
        if (columns != 9 && columns != 3) {
            throw new InvalidMenuConfiguration("Invalid number of columns in page!");
        }
        this.title = title;
        this.rows = rows;
        this.columns = columns;
    }

    @Override
    public void renderItem(@NotNull Player player, int index) {
        if(!cachedInventory.containsKey(player))
            return;
        if (!isValidSlot(index))
            throw new IllegalArgumentException("Invalid index while rendering a MenuItem in a MenuPage");
        if (!schema.containsKey(index))
            return;

        SectionData data = schema.get(index);
        Section section = data.getSection();
        section.renderItem(player, menu, cachedInventory.get(player), index, data.getStartIndex() + index);
    }

    @Override
    public void renderItems(@NotNull Player player) {
        if(!cachedInventory.containsKey(player))
            return;

        for (SectionData data : sections) {
            Section section = data.getSection();

            if (section instanceof DynamicSection) {
                ((DynamicSection) section).renderItems(player, menu, cachedInventory.get(player), data.getStartIndex(), data.getEndIndex(), data.getOffset());
            } else {
                ((StaticSection) section).renderItems(player, menu, cachedInventory.get(player), data.getStartIndex(), data.getOffset());
            }
        }
    }

    @Override
    public @NotNull Inventory render(@NotNull Player player) {
        if (!cachedInventory.containsKey(player)) {
            cachedInventory.put(player, new Holder(title, rows, columns, this).getInventory());
            renderItems(player);
        }

        return cachedInventory.get(player);
    }

    @Override
    public @NotNull MenuItem getItem(int index) {
        if (!isValidSlot(index))
            throw new IllegalArgumentException("Invalid index while getting a MenuItem from a MenuPage");
        SectionData data = schema.get(index);
        int startIndex = data.getStartIndex();
        int endIndex = data.getEndIndex();

        int width;
        if (data.getSection() instanceof StaticSection) {
            width = ((StaticSection) data.getSection()).getWidth();
        } else {
            int height = (endIndex - startIndex)/columns + 1;
            width = endIndex - (height - 1) * columns - startIndex + 1;
        }
        int xPos = (index - startIndex) % columns;
        int yPos  = (index - startIndex) / columns;
        int sectionIndex = xPos + yPos * width + data.getOffset();

        return data.getSection().getItem(sectionIndex);
    }

    @Override
    public void addStaticSection(int startIndex, int sectionOffset, @NotNull StaticSection section) {
        if(!isValidSlot(startIndex + sectionOffset) || sectionOffset < 0)
            return;

        int endIndex = startIndex + (section.getHeight() - 1) * columns + section.getWidth();
        int width = section.getWidth();
        SectionData data = new SectionData(startIndex, endIndex, sectionOffset, section);
        sections.add(data);
        fillSection(startIndex, sectionOffset, width, width * section.getHeight(), (slot) -> {
            schema.put(slot, data);
            return null;
        });
    }

    @Override
    public void addDynamicSection(int startIndex, int endIndex, int sectionOffset, @NotNull DynamicSection section) {
        if(!isValidSlot(startIndex + sectionOffset) || sectionOffset < 0)
            return;

        int height = (endIndex - startIndex)/columns + 1;
        int width = endIndex - (height - 1) * columns - startIndex + 1;
        SectionData data = new SectionData(startIndex, endIndex, sectionOffset, section);
        sections.add(data);
        fillSection(startIndex, sectionOffset, width, width * height + sectionOffset, (slot) -> {
            schema.put(slot, data);
            return null;
        });
    }

    @Override
    public void removeSection(int pointIndex) {
        if (!isValidSlot(pointIndex))
            return;

        SectionData data = schema.get(pointIndex);
        int startIndex = data.getStartIndex();
        int endIndex = data.getEndIndex();

        int width, sectionSize;
        if (data.getSection() instanceof DynamicSection) {
            int height = (endIndex - startIndex)/columns + 1;
            width = endIndex - (height - 1) * columns - startIndex + 1;
            sectionSize = height * width + data.getOffset();
        } else {
            StaticSection section = ((StaticSection) data.getSection());
            width = section.getWidth();
            sectionSize = width * section.getHeight();
        }

        fillSection(startIndex, data.getOffset(), width, sectionSize, (slot) -> {
            schema.put(slot, data);
            return null;
        });
    }

    @Override
    public void onClose() {
        for (SectionData data : sections) {
            cachedInventory.forEach((p, i) -> data.getSection().onClose(i));
        }
        this.cachedInventory.clear();
    }

    @Override
    public void renderItem(@NotNull Player player, Point pointToRender) {
        if(isValidPoint(pointToRender))
            renderItem(player, pointToIndex(pointToRender));
    }

    @Override
    public SectionData getSection(int pointIndex) {
        if (!isValidSlot(pointIndex))
            return null;

        return schema.get(pointIndex);
    }

    @Override
    public void addStaticSection(@NotNull Point point, int sectionOffset, @NotNull StaticSection section) {
        if (isValidPoint(point))
            addStaticSection(pointToIndex(point), sectionOffset, section);
    }

    @Override
    public void addDynamicSection(@NotNull Point start, @NotNull Point end, int sectionOffset, @NotNull DynamicSection section) {
        if (isValidPoint(start) && isValidPoint(end))
            addDynamicSection(pointToIndex(start), pointToIndex(end), sectionOffset, section);
    }

    @Override
    public void removeSection(@NotNull Point point) {
        if (isValidPoint(point))
            removeSection(pointToIndex(point));
    }

    @Override
    public SectionData getSection(@NotNull Point startPoint) {
        if (isValidPoint(startPoint))
            return getSection(pointToIndex(startPoint));
        return null;
    }

    @Override
    public @NotNull CrispyMenu getMenu() {
        return menu;
    }

    @Override
    public void setMenu(@NotNull CrispyMenu menu) {
        this.menu = menu;
    }

    @Override
    public @NotNull String getTitle() {
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
    public @NotNull List<SectionData> getSections() {
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
