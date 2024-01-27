package dev.acrispycookie.crispycommons.implementations.guis.books.wrappers;

import net.kyori.adventure.text.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class BookPage {

    private final List<BookLine> lines;

    public BookPage(BookLine... lines) {
        this(Arrays.asList(lines));
    }

    public BookPage(Collection<? extends BookLine> lines) {
        this.lines = new ArrayList<>(lines);
    }

    public Component getComponent() {
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
