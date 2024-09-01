package dev.acrispycookie.crispycommons.api.visual.scoreboard;

import dev.acrispycookie.crispycommons.api.visual.abstraction.visual.CrispyVisual;
import dev.acrispycookie.crispycommons.implementations.visual.abstraction.builder.AbstractVisualBuilder;
import dev.acrispycookie.crispycommons.implementations.visual.scoreboard.SimpleScoreboard;
import dev.acrispycookie.crispycommons.implementations.visual.scoreboard.data.ScoreboardData;
import dev.acrispycookie.crispycommons.implementations.element.type.TextElement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Represents a customizable scoreboard display in the game.
 * <p>
 * The {@code CrispyScoreboard} interface provides methods to set and retrieve the title and lines
 * of a scoreboard. It also includes a nested {@link ScoreboardBuilder} class for constructing
 * scoreboard instances with a fluent API.
 * </p>
 */
public interface CrispyScoreboard extends CrispyVisual {

    /**
     * Creates a new {@link ScoreboardBuilder} instance for constructing a scoreboard.
     *
     * @return a new {@link ScoreboardBuilder} instance.
     */
    static ScoreboardBuilder builder() {
        return new ScoreboardBuilder();
    }

    /**
     * Sets the title of the scoreboard.
     *
     * @param title the {@link TextElement} representing the title of the scoreboard.
     */
    void setTitle(TextElement<?> title);

    /**
     * Adds a line of text to the scoreboard.
     *
     * @param line the {@link TextElement} representing the line of text to add.
     */
    void addLine(TextElement<?> line);

    /**
     * Adds a line of text to the scoreboard at the specified index.
     *
     * @param index the index at which to add the line.
     * @param line the {@link TextElement} representing the line of text to add.
     */
    void addLine(int index, TextElement<?> line);

    /**
     * Removes the line of text at the specified index from the scoreboard.
     *
     * @param index the index of the line to remove.
     */
    void removeLine(int index);

    /**
     * Sets the lines of text for the scoreboard.
     *
     * @param lines a collection of {@link TextElement} instances representing the lines of the scoreboard.
     */
    void setLines(Collection<? extends TextElement<?>> lines);

    /**
     * Retrieves the title of the scoreboard.
     *
     * @return the {@link TextElement} representing the title of the scoreboard.
     */
    TextElement<?> getTitle();

    /**
     * Retrieves the lines of text in the scoreboard.
     *
     * @return a {@link List} of {@link TextElement} instances representing the lines of the scoreboard.
     */
    List<TextElement<?>> getLines();

    /**
     * Updates the title of the scoreboard.
     */
    void updateTitle();

    /**
     * Updates the specified line in the scoreboard.
     *
     * @param index the index of the line to update.
     */
    void updateLine(int index);

    /**
     * Builder class for constructing {@link CrispyScoreboard} instances.
     */
    class ScoreboardBuilder extends AbstractVisualBuilder<CrispyScoreboard> {

        private final ScoreboardData data = new ScoreboardData(null, new ArrayList<>());

        /**
         * Sets the title of the scoreboard being built.
         *
         * @param title the {@link TextElement} representing the title.
         * @return this {@link ScoreboardBuilder} instance for method chaining.
         */
        public ScoreboardBuilder setTitle(TextElement<?> title) {
            this.data.setTitle(title);
            title.setUpdate(() -> toBuild.updateTitle());
            return this;
        }

        /**
         * Adds a line of text to the scoreboard being built.
         *
         * @param text the {@link TextElement} representing the line of text.
         * @return this {@link ScoreboardBuilder} instance for method chaining.
         */
        public ScoreboardBuilder addTextLine(TextElement<?> text) {
            this.data.addLine(text);
            int index = this.data.getLines().size() - 1;
            text.setUpdate(() -> toBuild.updateLine(index));
            return this;
        }

        /**
         * Builds and returns the {@link CrispyScoreboard} instance.
         *
         * @return the constructed {@link CrispyScoreboard}.
         */
        public CrispyScoreboard build() {
            toBuild = SimpleScoreboard.newInstance(data, receivers, timeToLive, isPublic);
            return toBuild;
        }
    }
}

