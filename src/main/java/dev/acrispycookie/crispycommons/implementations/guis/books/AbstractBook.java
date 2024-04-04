package dev.acrispycookie.crispycommons.implementations.guis.books;

import dev.acrispycookie.crispycommons.implementations.guis.abstraction.AbstractUntrackedGui;
import dev.acrispycookie.crispycommons.implementations.guis.books.wrappers.BookData;

public abstract class AbstractBook extends AbstractUntrackedGui<BookData> {

    public AbstractBook(BookData data) {
        super(data);
    }
}
