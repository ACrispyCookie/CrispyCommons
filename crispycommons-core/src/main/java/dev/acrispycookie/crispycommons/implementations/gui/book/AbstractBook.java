package dev.acrispycookie.crispycommons.implementations.gui.book;

import dev.acrispycookie.crispycommons.implementations.gui.abstraction.AbstractUntrackedGui;
import dev.acrispycookie.crispycommons.implementations.gui.book.data.BookData;

/**
 * An abstract base class for creating book-based GUIs.
 * <p>
 * This class extends {@link AbstractUntrackedGui} and is specifically designed for GUIs that represent
 * or interact with books. The {@link BookData} associated with this GUI can be used to manage the state
 * and content of the book. Since this class extends {@link AbstractUntrackedGui}, it does not track
 * which players are viewing the book.
 * </p>
 */
public abstract class AbstractBook extends AbstractUntrackedGui<BookData> {

    /**
     * Constructs an {@code AbstractBook} with the specified book data.
     *
     * @param data the {@link BookData} to be associated with this book GUI.
     */
    public AbstractBook(BookData data) {
        super(data);
    }
}

