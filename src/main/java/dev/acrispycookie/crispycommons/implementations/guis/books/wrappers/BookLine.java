package dev.acrispycookie.crispycommons.implementations.guis.books.wrappers;


import net.md_5.bungee.api.chat.TextComponent;

public class BookLine {

    protected final TextComponent text;

    public BookLine(String text) {
        this.text = new TextComponent(text);
    }

    public BookLine(TextComponent text) {
        this.text = text;
    }

    public TextComponent get() {
        return text;
    }
}
