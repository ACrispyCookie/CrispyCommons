package dev.acrispycookie.crispycommons.implementations.guis.books;

import dev.acrispycookie.crispycommons.CrispyCommons;
import dev.acrispycookie.crispycommons.api.guis.book.CrispyBook;
import dev.acrispycookie.crispycommons.implementations.guis.books.wrappers.BookData;
import dev.acrispycookie.crispycommons.implementations.guis.books.wrappers.BookPage;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.inventory.Book;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class SimpleBook extends AbstractBook implements CrispyBook {

    public SimpleBook(BookData data) {
        super(data);
    }

    @Override
    public void open(Player p) {
        Audience player = CrispyCommons.getBukkitAudiences().player(p);
        Book book = Book.book(Component.empty(), Component.empty(), data.getPages()
                .stream()
                .map(BookPage::getComponent)
                .collect(Collectors.toList()));
        player.openBook(book);
    }

    @Override
    public void setPages(Collection<BookPage> pages) {
        data.setPages(pages);
    }

    @Override
    public void setPages(BookPage... pages) {
        data.setPages(Arrays.asList(pages));
    }

    @Override
    public List<BookPage> getPages() {
        return data.getPages();
    }

}
