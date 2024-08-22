package dev.acrispycookie.crispycommons.implementations.gui.book.data;

import dev.acrispycookie.crispycommons.api.gui.abstraction.GuiData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Represents the data structure for storing and managing the pages of a book in a GUI context.
 * <p>
 * {@link BookData} encapsulates a collection of {@link BookPage} objects, providing methods to manipulate
 * the book's pages, such as adding, removing, and retrieving them. This class is typically used in conjunction
 * with a GUI system to manage book-like interfaces.
 * </p>
 */
public class BookData implements GuiData {

    /**
     * The list of pages contained in the book.
     * <p>
     * This list is used to store and manage the individual {@link BookPage} instances that make up the book.
     * </p>
     */
    private final ArrayList<BookPage> pages;

    /**
     * Constructs a {@code BookData} instance with the specified list of pages.
     *
     * @param pages the initial list of pages to be included in the book.
     */
    public BookData(ArrayList<BookPage> pages) {
        this.pages = pages;
    }

    /**
     * Returns the total number of pages in the book.
     *
     * @return the total number of pages.
     */
    public int getTotalPages() {
        return pages.size();
    }

    /**
     * Returns an unmodifiable view of the list of pages in the book.
     * <p>
     * This ensures that the pages cannot be modified directly outside of this class.
     * </p>
     *
     * @return an unmodifiable {@link List} of {@link BookPage} objects.
     */
    public List<BookPage> getPages() {
        return Collections.unmodifiableList(pages);
    }

    /**
     * Sets the pages of the book to the specified collection of pages.
     * <p>
     * This method replaces all existing pages in the book with the provided collection of pages.
     * </p>
     *
     * @param pages the collection of {@link BookPage} objects to set as the book's pages.
     */
    public void setPages(Collection<? extends BookPage> pages) {
        this.pages.clear();
        this.pages.addAll(pages);
    }

    /**
     * Adds a page at the specified index in the book.
     * <p>
     * This method inserts the specified {@link BookPage} at the given index, shifting any subsequent pages.
     * </p>
     *
     * @param index the index at which the page should be inserted.
     * @param page  the {@link BookPage} to add.
     */
    public void addPage(int index, BookPage page) {
        this.pages.add(index, page);
    }

    /**
     * Adds a page to the end of the book.
     * <p>
     * This method appends the specified {@link BookPage} to the end of the current list of pages.
     * </p>
     *
     * @param page the {@link BookPage} to add.
     */
    public void addPage(BookPage page) {
        this.pages.add(page);
    }

    /**
     * Removes the page at the specified index from the book.
     *
     * @param pageIndex the index of the page to remove.
     */
    public void removePage(int pageIndex) {
        this.pages.remove(pageIndex);
    }

    /**
     * Ensures that the book data is ready to be used.
     * <p>
     * This method checks that the book contains at least one page and throws an exception if it does not.
     * </p>
     *
     * @throws GuiNotReadyException if the book contains no pages.
     */
    @Override
    public void assertReady() {
        if (pages.isEmpty())
            throw new GuiNotReadyException("The book pages were not set!");
    }
}

