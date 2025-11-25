package dev.acrispycookie.crispycommons.implementations.gui.book;

import dev.acrispycookie.crispycommons.SpigotCrispyCommons;
import dev.acrispycookie.crispycommons.api.gui.book.CrispyBook;
import dev.acrispycookie.crispycommons.implementations.gui.book.data.BookData;
import dev.acrispycookie.crispycommons.implementations.gui.book.data.BookPage;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.inventory.Book;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A simple implementation of a book-based GUI.
 * <p>
 * This class extends {@link AbstractBook} and implements the {@link CrispyBook} interface,
 * providing basic functionality for managing and displaying a book with pages to a player.
 * The book content is managed using {@link BookData}, and pages can be set or retrieved.
 * </p>
 */
public class SimpleBook extends AbstractBook implements CrispyBook {

    /**
     * Constructs a {@code SimpleBook} with the specified book data.
     *
     * @param data the {@link BookData} to be associated with this book GUI.
     */
    public SimpleBook(@NotNull BookData data) {
        super(data);
    }

    /**
     * Opens the book GUI for the specified player.
     * <p>
     * This method constructs a {@link Book} object using the pages from {@link BookData}
     * and opens it for the player.
     * </p>
     *
     * @param player the player for whom the book GUI will be opened.
     */
    @Override
    protected void openInternal(@NotNull Player player) {
        Audience audience = SpigotCrispyCommons.getInstance().getBukkitAudiences().player(player);
        Book book = Book.book(Component.empty(), Component.empty(), data.getPages()
                .stream()
                .map(BookPage::getComponent)
                .collect(Collectors.toList()));
        audience.openBook(book);
    }

    /**
     * Sets the pages of the book using a collection of {@link BookPage} objects.
     *
     * @param pages the collection of pages to set in the book.
     */
    @Override
    public void setPages(@NotNull Collection<BookPage> pages) {
        data.setPages(pages);
    }

    /**
     * Sets the pages of the book using an array of {@link BookPage} objects.
     *
     * @param pages the array of pages to set in the book.
     */
    @Override
    public void setPages(@NotNull BookPage... pages) {
        data.setPages(Arrays.asList(pages));
    }

    /**
     * Returns the list of {@link BookPage} objects currently in the book.
     *
     * @return the list of pages in the book.
     */
    @Override
    public @NotNull List<BookPage> getPages() {
        return data.getPages();
    }
}

