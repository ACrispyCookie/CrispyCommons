package dev.acrispycookie.crispycommons.api.gui.book;

import dev.acrispycookie.crispycommons.api.gui.abstraction.CrispyGui;
import dev.acrispycookie.crispycommons.implementations.gui.abstraction.builder.AbstractGuiBuilder;
import dev.acrispycookie.crispycommons.implementations.gui.book.SimpleBook;
import dev.acrispycookie.crispycommons.implementations.gui.book.data.BookData;
import dev.acrispycookie.crispycommons.implementations.gui.book.data.BookPage;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Represents a customizable book GUI.
 * A {@link CrispyBook} consists of multiple pages that players can interact with.
 */
public interface CrispyBook extends CrispyGui {

    /**
     * Creates a new instance of {@link BookBuilder} to build a {@link CrispyBook}.
     *
     * @return a new instance of {@link BookBuilder}.
     */
    static @NotNull BookBuilder builder() {
        return new BookBuilder();
    }

    /**
     * Sets the pages of this book using a collection of {@link BookPage}.
     * <p>
     * Players currently viewing the book will not get the new pages until
     * the book is reopened.
     * </p>
     *
     * @param pages the collection of pages to set in the book.
     * @throws NullPointerException if {@code pages} is {@code null}.
     */
    void setPages(@NotNull Collection<BookPage> pages);

    /**
     * Sets the pages of this book using an array of {@link BookPage}.
     * <p>
     * Players currently viewing the book will not get the new pages until
     * the book is reopened.
     * </p>
     *
     * @param pages the array of pages to set in the book.
     * @throws NullPointerException if {@code pages} is {@code null}.
     */
    void setPages(@NotNull BookPage... pages);

    /**
     * Gets the list of pages in this book.
     *
     * @return a list of {@link BookPage} representing the pages in this book.
     */
    @NotNull List<BookPage> getPages();

    /**
     * A builder class for constructing instances of {@link SimpleBook}.
     */
    class BookBuilder extends AbstractGuiBuilder<SimpleBook> {

        private final BookData data = new BookData(new ArrayList<>());

        /**
         * Constructs a new {@link BookBuilder}.
         */
        public BookBuilder() {

        }

        /**
         * Adds a page to the book.
         *
         * @param page the page to add.
         * @return the current instance of {@link BookBuilder}.
         * @throws NullPointerException if {@code page} is {@code null}.
         */
        public @NotNull BookBuilder addPage(@NotNull BookPage page) {
            data.addPage(page);
            return this;
        }

        /**
         * Adds a page to the book at the specified index.
         *
         * @param page the page to add.
         * @param index the index at which to add the page.
         * @return the current instance of {@link BookBuilder}.
         * @throws NullPointerException if {@code page} is {@code null}.
         */
        public @NotNull BookBuilder addPage(@NotNull BookPage page, int index) {
            data.addPage(index, page);
            return this;
        }

        /**
         * Removes the page at the specified index.
         *
         * @param index the index of the page to remove.
         * @return the current instance of {@link BookBuilder}.
         */
        public @NotNull BookBuilder removePage(int index) {
            data.removePage(index);
            return this;
        }

        /**
         * Builds the {@link SimpleBook} instance using the configured pages.
         *
         * @return the constructed {@link SimpleBook}.
         */
        public @NotNull SimpleBook build() {
            toBuild = new SimpleBook(data);
            return toBuild;
        }
    }
}
