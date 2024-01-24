package dev.acrispycookie.crispycommons.implementations.guis.books.wrappers;


import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;

public class BookLine {

    protected final TextComponent text;

    public BookLine(String text) {
        this.text = new TextComponent(ChatColor.translateAlternateColorCodes('&', text));
    }

    public BookLine(TextComponent text) {
        text.setText(ChatColor.translateAlternateColorCodes('&', text.getText()));
        this.text = text;
    }

    public TextComponent get() {
        return text;
    }
}
