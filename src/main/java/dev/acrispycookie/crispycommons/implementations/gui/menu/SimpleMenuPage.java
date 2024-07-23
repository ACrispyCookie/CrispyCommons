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

        SectionData info = schema.get(index);
        Section section = info.getSection();
        section.renderItem(player, menu, cachedInventory.get(player), index, info.getStartIndex() + index);
    }

    @Override
    public void renderItems(@NotNull Player player) {
        if(!cachedInventory.containsKey(player))
            return;

        for (SectionData info : sections) {
            Section section = info.getSection();

            if (section instanceof DynamicSection) {
                ((DynamicSection) section).renderItems(player, menu, cachedInventory.get(player), info.getStartIndex(), info.getEndIndex(), info.getOffset());
            } else {
                ((StaticSection) section).renderItems(player, menu, cachedInventory.get(player), info.getStartIndex(), info.getOffset());
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
        SectionData info = schema.get(index);
        int startIndex = info.getStartIndex();
        int endIndex = info.getEndIndex();

        int width;
        if (info.getSection() instanceof StaticSection) {
            width = ((StaticSection) info.getSection()).getWidth();
        } else {
            int height = (endIndex - startIndex)/columns + 1;
            width = endIndex - (height - 1) * columns - startIndex + 1;
        }
        int xPos = (index - startIndex) % columns;
        int yPos  = (index - startIndex) / columns;
        int sectionIndex = xPos + yPos * width + info.getOffset();

        return info.getSection().getItem(sectionIndex);
    }

    @Override
    public void addStaticSection(int startIndex, int sectionOffset, @NotNull StaticSection section) {
        if(!isValidSlot(startIndex + sectionOffset) || sectionOffset < 0)
            return;

        int endIndex = startIndex + (section.getHeight() - 1) * columns + section.getWidth();
        int width = section.getWidth();
        SectionData info = new SectionData(startIndex, endIndex, sectionOffset, section);
        sections.add(info);
        fillSection(startIndex, sectionOffset, width, width * section.getHeight(), (slot) -> {
            schema.put(slot, info);
            return null;
        });
    }

    @Override
    public void addDynamicSection(int startIndex, int endIndex, int sectionOffset, @NotNull DynamicSection section) {
        if(!isValidSlot(startIndex + sectionOffset) || sectionOffset < 0)
            return;

        int height = (endIndex - startIndex)/columns + 1;
        int width = endIndex - (height - 1) * columns - startIndex + 1;
        SectionData info = new SectionData(startIndex, endIndex, sectionOffset, section);
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

        SectionData info = schema.get(pointIndex);
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
        for (SectionData info : sections) {
            cachedInventory.forEach((p, i) -> info.getSection().onClose(i));
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
    public @NotNull ArrayList<SectionData> getSections() {
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
