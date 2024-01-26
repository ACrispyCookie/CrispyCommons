package dev.acrispycookie.crispycommons.api.guis.book.wrappers;

import dev.acrispycookie.crispycommons.api.guis.book.actions.BookAction;
import dev.acrispycookie.crispycommons.implementations.guis.books.actions.BookActionCommand;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
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
        this.text = this.text.clickEvent(ClickEvent.clickEvent(ClickEvent.Action.RUN_COMMAND, "/" + BookActionCommand.getInstance().getName() + " " + action.getUuid().toString()));
    }

    public BookActionLine(Component text) {
        super(text);
        action = new BookAction() {
            @Override
            public void run(Player p) {
                onAction(p);
            }
        };
        this.text = this.text.clickEvent(ClickEvent.clickEvent(ClickEvent.Action.RUN_COMMAND, "/" + BookActionCommand.getInstance().getName() + " " + action.getUuid().toString()));
    }


}
