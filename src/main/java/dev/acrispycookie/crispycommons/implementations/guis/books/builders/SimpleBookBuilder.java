package dev.acrispycookie.crispycommons.implementations.guis.books.builders;

import dev.acrispycookie.crispycommons.implementations.guis.books.SimpleCrispyBook;
import dev.acrispycookie.crispycommons.api.guis.book.CrispyBook;
import dev.acrispycookie.crispycommons.api.guis.book.wrappers.BookPage;

import java.util.ArrayList;
import java.util.List;

public class SimpleBookBuilder {

    private final List<BookPage> pages = new ArrayList<>();

    public SimpleBookBuilder(BookPage firstPage) {
        this.pages.add(firstPage);
    }

    public SimpleBookBuilder() {

    }

    public SimpleBookBuilder addPage(BookPage page) {
        this.pages.add(page);
        return this;
    }

    public SimpleBookBuilder addPage(BookPage page, int index) {
        this.pages.add(index, page);
        return this;
    }

    public SimpleBookBuilder removePage(int index) {
        this.pages.remove(index);
        return this;
    }

    public CrispyBook build() {
        return new SimpleCrispyBook(pages);
    }
}
