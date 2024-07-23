package dev.acrispycookie.crispycommons.utility.menu;

import dev.acrispycookie.crispycommons.api.itemstack.CrispyItemStack;
import org.bukkit.entity.Player;

import java.util.function.BiFunction;

public class PageInfo {

    private BiFunction<Integer, Integer, String> pageTitle;
    private int defaultPage;
    private int rows;
    private int columns;
    private int startIndex;
    private int endIndex;
    private BiFunction<Player, CrispyItemStack, Boolean> onPlayerItemClick;

    public PageInfo(BiFunction<Integer, Integer, String> pageTitle, int defaultPage, int rows, int columns, int startIndex, int endIndex, BiFunction<Player, CrispyItemStack, Boolean> onPlayerItemClick) {
        this.pageTitle = pageTitle;
        this.defaultPage = defaultPage;
        this.rows = rows;
        this.columns = columns;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.onPlayerItemClick = onPlayerItemClick;
    }

    public BiFunction<Integer, Integer, String> getPageTitle() {
        return pageTitle;
    }

    public int getDefaultPage() {
        return defaultPage;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public BiFunction<Player, CrispyItemStack, Boolean> getOnPlayerItemClick() {
        return onPlayerItemClick;
    }

    public void setPageTitle(BiFunction<Integer, Integer, String> pageTitle) {
        this.pageTitle = pageTitle;
    }

    public void setDefaultPage(int defaultPage) {
        this.defaultPage = defaultPage;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }

    public void setOnPlayerItemClick(BiFunction<Player, CrispyItemStack, Boolean> onPlayerItemClick) {
        this.onPlayerItemClick = onPlayerItemClick;
    }
}
