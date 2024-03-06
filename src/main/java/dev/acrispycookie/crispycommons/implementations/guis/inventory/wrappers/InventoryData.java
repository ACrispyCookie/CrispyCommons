package dev.acrispycookie.crispycommons.implementations.guis.inventory.wrappers;

import dev.acrispycookie.crispycommons.api.guis.inventory.InventoryPage;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.VisualData;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;

public class InventoryData implements VisualData {

    private final ArrayList<InventoryPage> pages = new ArrayList<>();
    private final HashMap<Player, Integer> currentPage = new HashMap<>();
    private int startingPage;

    public InventoryData(int startingPage) {
        this.startingPage = startingPage;
    }

    public void setCurrentPage(Player player, int pageIndex) {
        currentPage.put(player, pageIndex);
    }

    public void setCurrentPage(int pageIndex) {
        currentPage.keySet().forEach(player -> currentPage.put(player, pageIndex));
    }

    public void addPage(int index, InventoryPage page) {
        this.pages.add(index, page);
    }

    public void addPage(InventoryPage page) {
        this.pages.add(page);
    }

    public void removePage(int index) {
        this.pages.remove(index);
    }

    public void setPage(int index, InventoryPage page) {
        this.pages.set(index, page);
    }

    public void forEachPage(Consumer<? super InventoryPage> consumer) {
        this.pages.forEach(consumer);
    }

    public ArrayList<InventoryPage> getPages() {
        return pages;
    }

    public int getTotalPages() {
        return pages.size();
    }

    public int getCurrentPage(Player player) {
        if (currentPage.containsKey(player))
            return currentPage.get(player);

        currentPage.put(player, startingPage);
        return startingPage;
    }

    public void setStartingPage(int index) {
        this.startingPage = index;
    }

    @Override
    public void assertReady() {
        if (pages.isEmpty())
            throw new VisualNotReadyException("The inventory pages were not set!");
    }
}