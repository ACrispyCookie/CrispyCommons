package dev.acrispycookie.crispycommons.api.visual.tablist;

import dev.acrispycookie.crispycommons.api.visual.abstraction.visual.CrispyVisual;
import dev.acrispycookie.crispycommons.implementations.element.type.TextElement;
import dev.acrispycookie.crispycommons.implementations.visual.abstraction.builder.AbstractVisualBuilder;
import dev.acrispycookie.crispycommons.implementations.visual.tablist.SimpleTabList;
import dev.acrispycookie.crispycommons.implementations.visual.tablist.data.TabListData;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Represents a customizable tab list display in the game, including headers and footers.
 * <p>
 * The {@code CrispyTabList} interface provides methods to set and retrieve the header and footer
 * lines of a tab list. It also includes a nested {@link TabListBuilder} class for constructing
 * tab list instances with a fluent API.
 * </p>
 */
public interface CrispyTabList extends CrispyVisual {

    /**
     * Creates a new {@link TabListBuilder} instance for constructing a tab list.
     *
     * @return a new {@link TabListBuilder} instance.
     */
    static @NotNull TabListBuilder builder() {
        return new TabListBuilder();
    }

    /**
     * Adds a line of text to the header of the tab list.
     *
     * @param line the {@link TextElement} representing the line of text to add.
     */
    void addHeaderLine(@NotNull TextElement<?> line);

    /**
     * Adds a line of text to the header of the tab list at the specified index.
     *
     * @param index the index at which to add the line.
     * @param line the {@link TextElement} representing the line of text to add.
     */
    void addHeaderLine(int index, @NotNull TextElement<?> line);

    /**
     * Removes the line of text at the specified index from the header of the tab list.
     *
     * @param index the index of the line to remove.
     */
    void removeHeaderLine(int index);

    /**
     * Adds a line of text to the footer of the tab list.
     *
     * @param line the {@link TextElement} representing the line of text to add.
     */
    void addFooterLine(@NotNull TextElement<?> line);

    /**
     * Adds a line of text to the footer of the tab list at the specified index.
     *
     * @param index the index at which to add the line.
     * @param line the {@link TextElement} representing the line of text to add.
     */
    void addFooterLine(int index, @NotNull TextElement<?> line);

    /**
     * Removes the line of text at the specified index from the footer of the tab list.
     *
     * @param index the index of the line to remove.
     */
    void removeFooterLine(int index);

    /**
     * Sets the header lines of the tab list.
     *
     * @param element a collection of {@link TextElement} instances representing the header lines.
     */
    void setHeader(@NotNull Collection<? extends TextElement<?>> element);

    /**
     * Sets the footer lines of the tab list.
     *
     * @param element a collection of {@link TextElement} instances representing the footer lines.
     */
    void setFooter(@NotNull Collection<? extends TextElement<?>> element);

    /**
     * Retrieves the header lines of the tab list.
     *
     * @return a {@link List} of {@link TextElement} instances representing the header lines.
     */
    @NotNull List<TextElement<?>> getHeader();

    /**
     * Retrieves the footer lines of the tab list.
     *
     * @return a {@link List} of {@link TextElement} instances representing the footer lines.
     */
    @NotNull List<TextElement<?>> getFooter();

    /**
     * Builder class for constructing {@link CrispyTabList} instances.
     */
    class TabListBuilder extends AbstractVisualBuilder<CrispyTabList> {

        private final TabListData data = new TabListData(new ArrayList<>(), new ArrayList<>());

        /**
         * Adds a line of text to the header of the tab list being built.
         *
         * @param text the {@link TextElement} representing the line of text.
         * @return this {@link TabListBuilder} instance for method chaining.
         */
        public @NotNull TabListBuilder addHeaderLine(@NotNull TextElement<?> text) {
            text.setUpdate(() -> toBuild.update());
            this.data.addHeaderLine(text);
            return this;
        }

        /**
         * Adds a line of text to the footer of the tab list being built.
         *
         * @param text the {@link TextElement} representing the line of text.
         * @return this {@link TabListBuilder} instance for method chaining.
         */
        public @NotNull TabListBuilder addFooterLine(@NotNull TextElement<?> text) {
            text.setUpdate(() -> toBuild.update());
            this.data.addFooterLine(text);
            return this;
        }

        /**
         * Builds and returns the {@link CrispyTabList} instance.
         *
         * @return the constructed {@link CrispyTabList}.
         */
        @Override
        public @NotNull CrispyTabList build() {
            toBuild = new SimpleTabList(data, receivers, timeToLive, isPublic);
            return toBuild;
        }
    }
}

