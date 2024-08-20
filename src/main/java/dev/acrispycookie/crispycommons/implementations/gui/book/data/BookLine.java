package dev.acrispycookie.crispycommons.implementations.gui.book.data;


import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

/**
 * Represents a single line of text in a book.
 * <p>
 * {@link BookLine} encapsulates a line of text, which can be represented as a {@link Component} object.
 * This class provides constructors to create a book line from either a plain text string or a pre-existing
 * {@link Component}.
 * </p>
 */
public class BookLine {

    /**
     * The text component representing the content of this book line.
     */
    protected Component text;

    /**
     * Constructs a {@code BookLine} from a plain text string.
     * <p>
     * The string is deserialized into a {@link Component} using the legacy ampersand format.
     * </p>
     *
     * @param text the plain text string to be converted into a {@link Component}.
     */
    public BookLine(String text) {
        this.text = LegacyComponentSerializer.legacyAmpersand().deserialize(text);
    }

    /**
     * Constructs a {@code BookLine} from an existing {@link Component}.
     *
     * @param text the {@link Component} representing the content of this book line.
     */
    public BookLine(Component text) {
        this.text = text;
    }

    /**
     * Returns the {@link Component} representing the text of this book line.
     *
     * @return the {@link Component} of this book line.
     */
    public Component get() {
        return text;
    }
}

