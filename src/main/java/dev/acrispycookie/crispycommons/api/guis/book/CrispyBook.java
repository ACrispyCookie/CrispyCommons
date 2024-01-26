package dev.acrispycookie.crispycommons.api.guis.book;

import dev.acrispycookie.crispycommons.api.guis.book.wrappers.BookPage;
import net.md_5.bungee.api.chat.ClickEvent;
import org.bukkit.entity.Player;

import java.util.Collection;

public interface CrispyBook {
    void open(Player p);
    void setPages(Collection<BookPage> pages);
    void setPages(BookPage... pages);
}
