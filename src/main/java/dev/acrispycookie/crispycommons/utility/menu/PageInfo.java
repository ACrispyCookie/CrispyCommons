package dev.acrispycookie.crispycommons.utility.menu;

import dev.acrispycookie.crispycommons.api.itemstack.CrispyItemStack;
import org.bukkit.entity.Player;

import java.util.function.BiFunction;

/**
 * Represents configuration information for a menu page in the CrispyMenu system.
 * <p>
 * This class encapsulates details about a specific page in a menu, including the page title, layout (rows and columns),
 * index range, and a callback function for handling item clicks by the player.
 * </p>
 */
public class PageInfo {

    private BiFunction<Integer, Integer, String> pageTitle;
    private int defaultPage;
    private int rows;
    private int columns;
    private int startIndex;
    private int endIndex;
    private BiFunction<Player, CrispyItemStack, Boolean> onPlayerItemClick;

    /**
     * Constructs a {@link PageInfo} instance with the specified parameters.
     *
     * @param pageTitle a {@link BiFunction} that generates the page title based on the current page number and total page count.
     * @param defaultPage the default page number when the menu is first opened.
     * @param rows the number of rows in the page layout.
     * @param columns the number of columns in the page layout.
     * @param startIndex the starting index of the page.
     * @param endIndex the ending index of the page.
     * @param onPlayerItemClick a {@link BiFunction} that handles item clicks in the player's inventory, returning {@code true} if a significant action was performed.
     */
    public PageInfo(BiFunction<Integer, Integer, String> pageTitle, int defaultPage, int rows, int columns, int startIndex, int endIndex, BiFunction<Player, CrispyItemStack, Boolean> onPlayerItemClick) {
        this.pageTitle = pageTitle;
        this.defaultPage = defaultPage;
        this.rows = rows;
        this.columns = columns;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.onPlayerItemClick = onPlayerItemClick;
    }

    /**
     * Gets the {@link BiFunction} used to generate the page title.
     *
     * @return the page title generator function.
     */
    public BiFunction<Integer, Integer, String> getPageTitle() {
        return pageTitle;
    }

    /**
     * Gets the default page number for the menu.
     *
     * @return the default page number.
     */
    public int getDefaultPage() {
        return defaultPage;
    }

    /**
     * Gets the number of rows in the page layout.
     *
     * @return the number of rows.
     */
    public int getRows() {
        return rows;
    }

    /**
     * Gets the number of columns in the page layout.
     *
     * @return the number of columns.
     */
    public int getColumns() {
        return columns;
    }

    /**
     * Gets the starting index of the page.
     *
     * @return the starting index.
     */
    public int getStartIndex() {
        return startIndex;
    }

    /**
     * Gets the ending index of the page.
     *
     * @return the ending index.
     */
    public int getEndIndex() {
        return endIndex;
    }

    /**
     * Gets the {@link BiFunction} that handles item clicks by the player.
     *
     * @return the item click handler function.
     */
    public BiFunction<Player, CrispyItemStack, Boolean> getOnPlayerItemClick() {
        return onPlayerItemClick;
    }

    /**
     * Sets the {@link BiFunction} used to generate the page title.
     *
     * @param pageTitle the page title generator function to set.
     */
    public void setPageTitle(BiFunction<Integer, Integer, String> pageTitle) {
        this.pageTitle = pageTitle;
    }

    /**
     * Sets the default page number for the menu.
     *
     * @param defaultPage the default page number to set.
     */
    public void setDefaultPage(int defaultPage) {
        this.defaultPage = defaultPage;
    }

    /**
     * Sets the number of rows in the page layout.
     *
     * @param rows the number of rows to set.
     */
    public void setRows(int rows) {
        this.rows = rows;
    }

    /**
     * Sets the number of columns in the page layout.
     *
     * @param columns the number of columns to set.
     */
    public void setColumns(int columns) {
        this.columns = columns;
    }

    /**
     * Sets the starting index of the page.
     *
     * @param startIndex the starting index to set.
     */
    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    /**
     * Sets the ending index of the page.
     *
     * @param endIndex the ending index to set.
     */
    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }

    /**
     * Sets the {@link BiFunction} that handles item clicks in the player's inventory.
     *
     * @param onPlayerItemClick the item click handler function to set.
     */
    public void setOnPlayerItemClick(BiFunction<Player, CrispyItemStack, Boolean> onPlayerItemClick) {
        this.onPlayerItemClick = onPlayerItemClick;
    }
}
