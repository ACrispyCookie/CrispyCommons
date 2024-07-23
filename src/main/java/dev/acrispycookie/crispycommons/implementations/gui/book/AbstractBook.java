package dev.acrispycookie.crispycommons.implementations.gui.book;

import dev.acrispycookie.crispycommons.implementations.gui.abstraction.AbstractUntrackedGui;
import dev.acrispycookie.crispycommons.implementations.gui.book.data.BookData;

public abstract class AbstractBook extends AbstractUntrackedGui<BookData> {

    public AbstractBook(BookData data) {
        super(data);
    }
}
