package dev.acrispycookie.crispycommons.api.guis.book;

import dev.acrispycookie.crispycommons.implementations.guis.books.wrappers.BookPage;
import dev.acrispycookie.crispycommons.implementations.guis.books.SimpleBook;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface CrispyBook {

    static BookBuilder builder() {
        return new BookBuilder();
    }
    void open(Player p);
    void setPages(Collection<BookPage> pages);
    void setPages(BookPage... pages);
    List<BookPage> getPages();

    class BookBuilder {

        private final List<BookPage> pages = new ArrayList<>();

        public BookBuilder() {

        }

        public BookBuilder addPage(BookPage page) {
            this.pages.add(page);
            return this;
        }

        public BookBuilder addPage(BookPage page, int index) {
            this.pages.add(index, page);
            return this;
        }

        public BookBuilder removePage(int index) {
            this.pages.remove(index);
            return this;
        }

        public CrispyBook build() {
            return new SimpleBook(pages);
        }
    }
}
