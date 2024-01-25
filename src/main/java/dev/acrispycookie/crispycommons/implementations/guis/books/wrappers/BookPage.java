package dev.acrispycookie.crispycommons.implementations.guis.books.wrappers;

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
    private final IChatBaseComponent page;

    public BookPage(BookLine... lines) {
        this(Arrays.asList(lines));
    }

    public BookPage(Collection<? extends BookLine> lines) {
        this.lines = new ArrayList<>(lines);
        this.page = getPageComponent();
    }

    public IChatBaseComponent getBukkitComponent() {
        return page;
    }

    private IChatBaseComponent getPageComponent() {
        TextComponent text = new TextComponent(lines.get(0).get());
        for (int i = 1; i < lines.size(); i++) {
            text.addExtra("\n");
            text.addExtra(lines.get(i).get());
        }

        return IChatBaseComponent.ChatSerializer.a(ComponentSerializer.toString(new BaseComponent[]{text}));
    }
}
