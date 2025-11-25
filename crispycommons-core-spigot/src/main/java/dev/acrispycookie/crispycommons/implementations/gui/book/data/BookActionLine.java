package dev.acrispycookie.crispycommons.implementations.gui.book.data;

import dev.acrispycookie.crispycommons.implementations.gui.book.action.BookAction;
import dev.acrispycookie.crispycommons.implementations.gui.book.action.BookActionCommand;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a line of text in a book that triggers an action when clicked.
 * <p>
 * {@link BookActionLine} extends {@link BookLine} by adding an associated {@link BookAction} that
 * is executed when the line is clicked by a player. This abstract class requires implementing
 * the {@link #onAction(Player)} method to define the action to be performed.
 * </p>
 */
public abstract class BookActionLine extends BookLine {

    /**
     * The {@link BookAction} associated with this line, which is triggered when the line is clicked.
     */
    private final BookAction action;

    /**
     * Constructs a {@code BookActionLine} with the specified text.
     * <p>
     * The provided text is deserialized into a {@link Component} and configured with a click event that
     * triggers the associated {@link BookAction}.
     * </p>
     *
     * @param text the plain text string to be converted into a {@link Component}.
     */
    public BookActionLine(@NotNull String text) {
        super(text);
        action = new BookAction() {
            @Override
            public void run(@NotNull Player p) {
                onAction(p);
            }
        };
        this.text = this.text.clickEvent(ClickEvent.clickEvent(ClickEvent.Action.RUN_COMMAND, "/" + BookActionCommand.getInstance().getName() + " " + action.getUuid()));
    }

    /**
     * Constructs a {@code BookActionLine} with the specified {@link Component}.
     * <p>
     * The provided {@link Component} is configured with a click event that triggers the associated {@link BookAction}.
     * </p>
     *
     * @param text the {@link Component} representing the content of this book line.
     */
    public BookActionLine(@NotNull Component text) {
        super(text);
        action = new BookAction() {
            @Override
            public void run(@NotNull Player p) {
                onAction(p);
            }
        };
        this.text = this.text.clickEvent(ClickEvent.clickEvent(ClickEvent.Action.RUN_COMMAND, "/" + BookActionCommand.getInstance().getName() + " " + action.getUuid()));
    }

    /**
     * The method to be implemented by subclasses to define the action performed when this line is clicked.
     *
     * @param p the {@link Player} who clicked the line.
     */
    public abstract void onAction(@NotNull Player p);
}

