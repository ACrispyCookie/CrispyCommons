package dev.acrispycookie.crispycommons.implementations.guis.books.wrappers;

import dev.acrispycookie.crispycommons.api.guis.abstraction.GuiData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class BookData implements GuiData {

    private final ArrayList<BookPage> pages;

    public BookData(ArrayList<BookPage> pages) {
        this.pages = pages;
    }

    public int getTotalPages() {
        return pages.size();
    }

    public List<BookPage> getPages() {
        return Collections.unmodifiableList(pages);
    }

    public void setPages(Collection<? extends BookPage> pages) {
        this.pages.clear();
        this.pages.addAll(pages);
    }

    public void addPage(int index, BookPage page) {
        this.pages.add(index, page);
    }

    public void addPage(BookPage page) {
        this.pages.add(page);
    }

    public void removePage(int pageIndex) {
        this.pages.remove(pageIndex);
    }

    @Override
    public void assertReady() {
        if (pages.isEmpty())
            throw new GuiNotReadyException("The book pages were not set!");
    }
}
