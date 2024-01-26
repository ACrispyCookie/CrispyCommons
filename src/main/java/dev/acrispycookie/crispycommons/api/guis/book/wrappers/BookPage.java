package dev.acrispycookie.crispycommons.api.guis.book.wrappers;

import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.chat.ComponentSerializer;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;

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
