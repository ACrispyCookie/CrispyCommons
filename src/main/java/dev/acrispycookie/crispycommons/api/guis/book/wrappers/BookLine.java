package dev.acrispycookie.crispycommons.api.guis.book.wrappers;


import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;

public class BookLine {

    protected final TextComponent text;

    public BookLine(String text) {
        this.text = new TextComponent(ChatColor.translateAlternateColorCodes('&', text));
    }

    public BookLine(TextComponent text) {
        this(text.getText());
    }

    public TextComponent get() {
        return text;
    }
}
