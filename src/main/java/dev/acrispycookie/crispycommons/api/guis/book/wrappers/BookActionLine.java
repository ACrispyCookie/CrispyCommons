package dev.acrispycookie.crispycommons.api.guis.book.wrappers;

import dev.acrispycookie.crispycommons.api.guis.book.actions.BookAction;
import dev.acrispycookie.crispycommons.implementations.guis.books.actions.BookActionCommand;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

public abstract class BookActionLine extends BookLine {

    private final BookAction action;
    public abstract void onAction(Player p);

    public BookActionLine(String text) {
        super(text);
        action = new BookAction() {
            @Override
            public void run(Player p) {
                onAction(p);
            }
        };
        this.text.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,
                "/" + BookActionCommand.getInstance().getName() + " " + action.getUuid().toString()));
    }

    public BookActionLine(TextComponent text) {
        super(text);
        action = new BookAction() {
            @Override
            public void run(Player p) {
                onAction(p);
            }
        };
        this.text.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,
                "/" + BookActionCommand.getInstance().getName() + " " + action.getUuid().toString()));
    }


}
