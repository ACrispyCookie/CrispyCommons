package dev.acrispycookie.crispycommons.implementations.gui.book.data;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Represents a page in a book, containing multiple lines of text or actions.
 * <p>
 * A {@link BookPage} is composed of {@link BookLine} instances, which can be simple text lines
 * or lines that perform actions when clicked. The page can be rendered as a single {@link Component}
 * for display in a book.
 * </p>
 */
public class BookPage {

    /**
     * The list of lines that make up this book page.
     */
    private final List<BookLine> lines;

    /**
     * Constructs a {@code BookPage} with the specified lines.
     * <p>
     * This constructor accepts a variable number of {@link BookLine} instances and initializes
     * the page with them.
     * </p>
     *
     * @param lines the lines to be included in the page.
     */
    public BookPage(@NotNull BookLine... lines) {
        this(Arrays.asList(lines));
    }

    /**
     * Constructs a {@code BookPage} with a collection of lines.
     * <p>
     * This constructor accepts a collection of {@link BookLine} instances and initializes
     * the page with them.
     * </p>
     *
     * @param lines the collection of lines to be included in the page.
     */
    public BookPage(@NotNull Collection<? extends BookLine> lines) {
        this.lines = new ArrayList<>(lines);
    }

    /**
     * Combines all lines in the page into a single {@link Component} for display.
     * <p>
     * Each line is appended to the resulting component, with a newline character added
     * between each line.
     * </p>
     *
     * @return a {@link Component} representing the entire page.
     */
    public @NotNull Component getComponent() {
        Component text = Component.empty();
        for (int i = 0; i < lines.size(); i++) {
            text = text.append(lines.get(i).get());
            if (i != lines.size() - 1) {
                text = text.append(Component.newline());
            }
        }
        return text;
    }
}

