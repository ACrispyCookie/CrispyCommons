package dev.acrispycookie.crispycommons.api.guis.book;

import dev.acrispycookie.crispycommons.api.guis.abstraction.CrispyGui;
import dev.acrispycookie.crispycommons.implementations.guis.abstraction.builder.AbstractGuiBuilder;
import dev.acrispycookie.crispycommons.implementations.guis.books.SimpleBook;
import dev.acrispycookie.crispycommons.implementations.guis.books.wrappers.BookData;
import dev.acrispycookie.crispycommons.implementations.guis.books.wrappers.BookPage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface CrispyBook extends CrispyGui {

    static BookBuilder builder() {
        return new BookBuilder();
    }
    void setPages(Collection<BookPage> pages);
    void setPages(BookPage... pages);
    List<BookPage> getPages();

    class BookBuilder extends AbstractGuiBuilder<SimpleBook> {

        private final BookData data = new BookData(new ArrayList<>());

        public BookBuilder() {

        }

        public BookBuilder addPage(BookPage page) {
            data.addPage(page);
            return this;
        }

        public BookBuilder addPage(BookPage page, int index) {
            data.addPage(index, page);
            return this;
        }

        public BookBuilder removePage(int index) {
            data.removePage(index);
            return this;
        }

        public SimpleBook build() {
            toBuild = new SimpleBook(data);
            return toBuild;
        }
    }
}
