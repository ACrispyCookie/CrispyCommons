package dev.acrispycookie.crispycommons.implementations.guis.inventory.wrappers;

import dev.acrispycookie.crispycommons.api.guis.inventory.InventoryPage;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.VisualData;

import java.util.ArrayList;
import java.util.function.Consumer;

public class InventoryData implements VisualData {

    private final ArrayList<InventoryPage> pages = new ArrayList<>();
    private int currentPage;

    public InventoryData() {
        this.currentPage = 0;
    }

    public InventoryData(int currentPage) {
        this.currentPage = 0;
    }

    public void setCurrentPage(int pageIndex) {
        this.currentPage = pageIndex;
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

    public int getCurrentPage() {
        return currentPage;
    }

    @Override
    public void assertReady() {
        if (pages.isEmpty())
            throw new VisualNotReadyException("The inventory pages were not set!");
    }
}