package dev.acrispycookie.crispycommons.implementations.visual.tablist.data;

import dev.acrispycookie.crispycommons.api.element.DynamicElement;
import dev.acrispycookie.crispycommons.api.visual.abstraction.visual.VisualData;
import dev.acrispycookie.crispycommons.implementations.element.OwnedElement;
import dev.acrispycookie.crispycommons.implementations.element.type.TextElement;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * A data class representing the visual data needed to display a tab list with header and footer lines.
 * <p>
 * {@code TabListData} encapsulates the header and footer text elements that will be displayed
 * in the tab list. This class also provides methods to manage and manipulate these elements,
 * and ensures that the tab list is ready to be displayed.
 * </p>
 */
public class TabListData implements VisualData {

    /**
     * The list of {@link TextElement} representing the header lines of the tab list.
     */
    private List<OwnedElement<TextElement<?>>> header;

    /**
     * The list of {@link TextElement} representing the footer lines of the tab list.
     */
    private List<OwnedElement<TextElement<?>>> footer;

    /**
     * Constructs a new {@code TabListData} instance with the specified header and footer elements.
     *
     * @param header the list of {@link TextElement} representing the header lines.
     * @param footer the list of {@link TextElement} representing the footer lines.
     */
    public TabListData(@NotNull List<TextElement<?>> header, @NotNull List<TextElement<?>> footer) {
        this.header = new ArrayList<>();
        header.forEach(line -> this.header.add(new OwnedElement<>(line, this)));
        this.footer = new ArrayList<>();
        footer.forEach(line -> this.footer.add(new OwnedElement<>(line, this)));
    }

    /**
     * Retrieves the header lines of the tab list.
     *
     * @return a list of {@link TextElement} representing the header lines.
     */
    public @NotNull List<OwnedElement<TextElement<?>>> getHeader() {
        return header;
    }

    /**
     * Retrieves the footer lines of the tab list.
     *
     * @return a list of {@link TextElement} representing the footer lines.
     */
    public @NotNull List<OwnedElement<TextElement<?>>> getFooter() {
        return footer;
    }

    /**
     * Sets the header lines of the tab list.
     *
     * @param header a list of {@link TextElement} representing the new header lines.
     */
    public void setHeader(@NotNull List<TextElement<?>> header) {
        this.header.clear();
        header.forEach(line -> this.header.add(new OwnedElement<>(line, this)));
    }

    /**
     * Adds a line to the end of the current header lines.
     *
     * @param text the {@link TextElement} to add to the header.
     */
    public void addHeaderLine(@NotNull TextElement<?> text) {
        addHeaderLine(this.header.size(), text);
    }

    /**
     * Adds a line at the specified index in the header lines.
     *
     * @param index the position at which to add the header line.
     * @param text the {@link TextElement} to add to the header.
     */
    public void addHeaderLine(int index, @NotNull TextElement<?> text) {
        this.header.add(index, new OwnedElement<>(text, this));
    }

    /**
     * Removes a header line at the specified index.
     *
     * @param index the index of the header line to remove.
     */
    public OwnedElement<TextElement<?>> removeHeaderLine(int index) {
        return this.header.remove(index);
    }

    /**
     * Sets the footer lines of the tab list.
     *
     * @param footer a list of {@link TextElement} representing the new footer lines.
     */
    public void setFooter(@NotNull List<TextElement<?>> footer) {
        this.footer.clear();
        footer.forEach(line -> this.footer.add(new OwnedElement<>(line, this)));
    }

    /**
     * Adds a line to the end of the current footer lines.
     *
     * @param text the {@link TextElement} to add to the footer.
     */
    public void addFooterLine(@NotNull TextElement<?> text) {
        addFooterLine(this.footer.size(), text);
    }

    /**
     * Adds a line at the specified index in the footer lines.
     *
     * @param index the position at which to add the footer line.
     * @param text the {@link TextElement} to add to the footer.
     */
    public void addFooterLine(int index, TextElement<?> text) {
        this.footer.add(index, new OwnedElement<>(text, this));
    }

    /**
     * Removes a footer line at the specified index.
     *
     * @param index the index of the footer line to remove.
     */
    public OwnedElement<TextElement<?>> removeFooterLine(int index) {
        return this.footer.remove(index);
    }

    /**
     * Asserts that the visual data is ready for display.
     * <p>
     * This method checks if the header and footer lists are not empty.
     * If either is empty, a {@link VisualNotReadyException} is thrown.
     * </p>
     *
     * @param player the player for whom the visual data should be ready.
     * @throws VisualNotReadyException if the header or footer lines are not set.
     */
    @Override
    public void assertReady(@NotNull Player player) {
        if (header.isEmpty()) {
            throw new VisualNotReadyException("The tab list header was not set!");
        }
        if (footer.isEmpty()) {
            throw new VisualNotReadyException("The tab list footer was not set!");
        }
    }
}

