package dev.acrispycookie.crispycommons.api.gui.menu;

import dev.acrispycookie.crispycommons.SpigotCrispyCommons;
import dev.acrispycookie.crispycommons.api.gui.menu.section.DynamicSection;
import dev.acrispycookie.crispycommons.api.gui.menu.section.StaticSection;
import dev.acrispycookie.crispycommons.api.itemstack.CrispyItemStack;
import dev.acrispycookie.crispycommons.implementations.gui.menu.SimpleMenuPage;
import dev.acrispycookie.crispycommons.implementations.gui.menu.data.SectionData;
import dev.acrispycookie.crispycommons.logging.CrispyLogger;
import dev.acrispycookie.crispycommons.utility.menu.InvalidMenuConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.List;
import java.util.function.BiFunction;

/**
 * Represents a single page within a menu, containing various items and sections that can be rendered and interacted with by players.
 * <p>
 * The {@code MenuPage} interface defines methods for managing and rendering the content of a page, including handling player interactions
 * with items, managing static and dynamic sections, and supporting pagination. Implementations of this interface are responsible for
 * defining the behavior of individual pages within a menu.
 * </p>
 */
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
            CrispyLogger.printException(SpigotCrispyCommons.getInstance().getPlugin(), e, "Error while creating a menu page!");
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
     * Renders the page and returns the inventory view for the given player.
     *
     * @param player the {@link Player} for whom the page is rendered.
     * @return the {@link Inventory} representing the rendered view of the items for the specified player.
     * @throws NullPointerException if {@code player} is {@code null}.
     */
    @NotNull Inventory render(@NotNull Player player);

    /**
     * Pastes a static section into the page in the specified point index with an optional offset.
     * <p>
     * If the point index is invalid then the method returns early.
     * </p>
     *
     * @param pointIndex the starting index within the page where the section will be added.
     * @param sectionOffset the offset to apply within the section when adding it to the page.
     * @param section the {@link StaticSection} to be added to the page.
     * @throws NullPointerException if {@code section} is {@code null}.
     */
    void addStaticSection(int pointIndex, int sectionOffset, @NotNull StaticSection section);

    /**
     * Pastes a dynamic section in the specified region on the page with an optional offset.
     * <p>
     * If either indices are invalid the method returns early.
     * </p>
     *
     * @param startIndex the starting index within the page where the section will begin.
     * @param endIndex the ending index within the page where the section will end.
     * @param sectionOffset the offset to apply within the section when adding it to the page.
     * @param section the {@link DynamicSection} to be added to the page.
     * @throws NullPointerException if {@code section} is {@code null}.
     */
    void addDynamicSection(int startIndex, int endIndex, int sectionOffset, @NotNull DynamicSection section);

    /**
     * Pastes a static section into the page in the specified point with an optional offset.
     * <p>
     * If the point is invalid the method returns early.
     * </p>
     *
     * @param point the starting index within the page where the section will be added.
     * @param sectionOffset the offset to apply within the section when adding it to the page.
     * @param section the {@link StaticSection} to be added to the page.
     * @throws NullPointerException if {@code section} is {@code null}.
     */
    void addStaticSection(@NotNull Point point, int sectionOffset, @NotNull StaticSection section);

    /**
     * Pastes a dynamic section in the specified region on the page with an optional offset.
     * <p>
     * If either points are invalid the method returns early.
     * </p>
     *
     * @param start the starting point within the page where the section will begin.
     * @param end the ending point within the page where the section will end.
     * @param sectionOffset the offset to apply within the section when adding it to the page.
     * @param section the {@link DynamicSection} to be added to the page.
     * @throws NullPointerException if {@code section} is {@code null}.
     */
    void addDynamicSection(@NotNull Point start, @NotNull Point end, int sectionOffset, @NotNull DynamicSection section);

    /**
     * Removes a section from the page at the specified point index.
     *
     * @param pointIndex the index within the page at which the section will be removed.
     */
    void removeSection(int pointIndex);

    /**
     * Retrieves the {@link SectionData} at the specified point index in the page.
     *
     * @param pointIndex the index within the page from which the section data will be retrieved.
     * @return the {@link SectionData} located at the specified {@code pointIndex}.
     * @throws IndexOutOfBoundsException if {@code pointIndex} is out of the valid range.
     */
    @NotNull SectionData getSection(int pointIndex);

    /**
     * Removes a section from the page at the specified point index.
     *
     * @param point the point within the page at which the section will be removed.
     */
    void removeSection(@NotNull Point point);

    /**
     * Retrieves the {@link SectionData} at the specified point index in the page.
     *
     * @param point the point within the page from which the section data will be retrieved.
     * @return the {@link SectionData} located at the specified {@code pointIndex}.
     * @throws IndexOutOfBoundsException if {@code pointIndex} is out of the valid range.
     */
    @NotNull SectionData getSection(@NotNull Point point);

    /**
     * Handles a click event on an item on the player's inventory based on the {@link CrispyItemStack} that was clicked.
     *
     * @param player the {@link Player} who clicked the item.
     * @param itemStack the {@link CrispyItemStack} that was clicked.
     * @return {@code true} if the click event shouldn't be ignored; {@code false} otherwise.
     * @throws NullPointerException if {@code player} or {@code itemStack} is {@code null}.
     */
    boolean onPlayerItemClick(@NotNull Player player, @NotNull CrispyItemStack itemStack);

    /**
     * Method that runs when the page is closed for every viewer.
     */
    void onClose();

    /**
     * Retrieves the sections that are used in this page.
     *
     * @return a {@link List} of {@link SectionData} that contains all the data of the sections in this page.
     */
    @NotNull List<SectionData> getSections();

    /**
     * Retrieves the title of this page.
     *
     * @return the title.
     */
    @NotNull String getTitle();

    /**
     * Retrieves the number of rows in the page.
     *
     * @return the number of rows in the page.
     */
    int getRows();

    /**
     * Retrieves the number of columns in the page.
     *
     * @return the number of columns in the page.
     */
    int getColumns();

    /**
     * Retrieves the parent menu of this page.
     *
     * @return the menu that this page belongs to.
     */
    @NotNull CrispyMenu getMenu();

    /**
     * Sets the {@link CrispyMenu} to which this page belongs to.
     *
     * @param menu the parent menu that contains this page.
     */
    void setMenu(@NotNull CrispyMenu menu);
}
