package dev.acrispycookie.crispycommons.api.guis.book.wrappers;


import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

public class BookLine {

    protected Component text;

    public BookLine(String text) {
        this.text = LegacyComponentSerializer.legacyAmpersand().deserialize(text);
    }

    public BookLine(Component text) {
        this.text = text;
    }

    public Component get() {
        return text;
    }
}
