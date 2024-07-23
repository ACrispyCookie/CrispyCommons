package dev.acrispycookie.crispycommons.api.gui.menu;

import dev.acrispycookie.crispycommons.CrispyCommons;
import dev.acrispycookie.crispycommons.api.gui.menu.section.DynamicSection;
import dev.acrispycookie.crispycommons.api.gui.menu.section.StaticSection;
import dev.acrispycookie.crispycommons.api.itemstack.CrispyItemStack;
import dev.acrispycookie.crispycommons.implementations.gui.menu.SimpleMenuPage;
import dev.acrispycookie.crispycommons.implementations.gui.menu.data.SectionData;
import dev.acrispycookie.crispycommons.utility.logging.CrispyLogger;
import dev.acrispycookie.crispycommons.utility.menu.InvalidMenuConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.ArrayList;
import java.util.function.BiFunction;

public interface MenuPage {

    /**
     * Creates a new empty {@link MenuPage} with the specified title, dimensions, and item click handler.
     *
     * @param title the title of the {@link MenuPage}. This will be displayed at the top of the menu.
     * @param rows the number of rows in the menu. Must be positive.
     * @param columns the number of columns in the menu. Must be positive.
     * @param onPlayerItemClick a {@link BiFunction} that handles click events on items within the menu. It receives
     *                          a {@link Player} and a {@link CrispyItemStack}, and returns {@code Boolean}. It
     *                          determines whether the click event was successfully handled.
     * @return a new {@link MenuPage} instance with the specified title, dimensions, and item click handler.
     * @throws NullPointerException if {@code title} or {@code onPlayerItemClick} is {@code null}.
     */
    static MenuPage createEmpty(@NotNull String title, int rows, int columns, @NotNull BiFunction<Player, CrispyItemStack, Boolean> onPlayerItemClick) {
        try {
            return new SimpleMenuPage(title, rows, columns) {
                @Override
                public boolean onPlayerItemClick(@NotNull Player player, @NotNull CrispyItemStack itemStack) {
                    return onPlayerItemClick.apply(player, itemStack);
                }
            };
        } catch (InvalidMenuConfiguration e) {
            CrispyLogger.printException(CrispyCommons.getPlugin(), e, "Error while creating a menu page!");
        }

        return null;
    }

    /**
     * Retrieves the {@link MenuItem} at the specified index.
     *
     * @param index the index of the {@link MenuItem} to retrieve.
     * @return the {@link MenuItem} at the specified index.
     * @throws IllegalArgumentException if {@code index} is out of range.
     */
    @NotNull MenuItem getItem(int index);

    /**
     * Renders the item at the specified index for the given player.
     *
     * @param player the {@link Player} who will see the rendered item.
     * @param index the index of the item to render.
     * @throws IllegalArgumentException if {@code index} is out of range.
     * @throws NullPointerException if {@code player} is {@code null}.
     */
    void renderItem(@NotNull Player player, int index);

    /**
     * Renders the item at the specified location for the given player.
     *
     * @param player the {@link Player} who will see the rendered item.
     * @param point the {@link Point} representing the location of the item to rendered.
     * @throws NullPointerException if {@code player} or {@code point} is {@code null}.
     */
    void renderItem(@NotNull Player player, Point point);

    /**
     * Renders all items for the given player.
     *
     * @param player the {@link Player} for whom all items should be rendered.
     * @throws NullPointerException if {@code player} is {@code null}.
     */
    void renderItems(@NotNull Player player);

    /**
     * Renders and returns the inventory view for the given player.
     * <p>
     * This method generates and returns the {@link Inventory} instance that represents the current state of the items
     * for the specified {@link Player}.
     * </p>
     *
     * @param player the {@link Player} for whom the inventory is rendered.
     * @return the {@link Inventory} representing the rendered view of the items for the specified player.
     * @throws NullPointerException if {@code player} is {@code null}.
     */
    @NotNull Inventory render(@NotNull Player player);


    void addStaticSection(int pointIndex, int sectionOffset, @NotNull StaticSection section);
    void addDynamicSection(int startIndex, int endIndex, int sectionOffset, @NotNull DynamicSection section);
    void removeSection(int pointIndex);
    boolean onPlayerItemClick(@NotNull Player player, @NotNull CrispyItemStack itemStack);
    void onClose();
    SectionData getSection(int pointIndex);
    @NotNull ArrayList<SectionData> getSections();
    void addStaticSection(@NotNull Point point, int sectionOffset, @NotNull StaticSection section);
    void addDynamicSection(@NotNull Point start, @NotNull Point end, int sectionOffset, @NotNull DynamicSection section);
    void removeSection(@NotNull Point point);
    SectionData getSection(@NotNull Point point);
    @NotNull String getTitle();
    int getRows();
    int getColumns();
    @NotNull CrispyMenu getMenu();
    void setMenu(@NotNull CrispyMenu menu);
}
