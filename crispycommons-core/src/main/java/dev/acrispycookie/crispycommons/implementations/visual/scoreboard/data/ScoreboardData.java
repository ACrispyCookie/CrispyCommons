package dev.acrispycookie.crispycommons.implementations.visual.scoreboard.data;

import dev.acrispycookie.crispycommons.api.visual.abstraction.visual.VisualData;
import dev.acrispycookie.crispycommons.implementations.element.type.TextElement;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * A data class representing the visual data needed to display a scoreboard.
 * <p>
 * {@code ScoreboardData} encapsulates the title and lines that will be displayed
 * on the scoreboard. This class also provides methods to manipulate the lines and
 * ensures that the data is ready to be displayed.
 * </p>
 */
public class ScoreboardData implements VisualData {

    /**
     * The title element to be displayed on the scoreboard.
     */
    private TextElement<?> title;

    /**
     * The list of line elements to be displayed on the scoreboard.
     */
    private List<TextElement<?>> lines;

    /**
     * Constructs a new {@code ScoreboardData} instance with the specified title and lines.
     *
     * @param title the {@link TextElement} to be displayed as the scoreboard title.
     * @param lines the collection of {@link TextElement} objects representing the lines on the scoreboard.
     */
    public ScoreboardData(TextElement<?> title, Collection<? extends TextElement<?>> lines) {
        this.title = title;
        this.lines = new ArrayList<>(lines);
    }

    /**
     * Retrieves the title element that will be displayed on the scoreboard.
     *
     * @return the {@link TextElement} representing the scoreboard title.
     */
    public TextElement<?> getTitle() {
        return title;
    }

    /**
     * Sets the title element to be displayed on the scoreboard.
     *
     * @param title the {@link TextElement} to set as the scoreboard title.
     */
    public void setTitle(TextElement<?> title) {
        this.title = title;
    }

    /**
     * Retrieves the list of line elements that will be displayed on the scoreboard.
     *
     * @return the list of {@link TextElement} objects representing the scoreboard lines.
     */
    public List<TextElement<?>> getLines() {
        return lines;
    }

    /**
     * Adds a new line element to the end of the scoreboard.
     *
     * @param line the {@link TextElement} to be added.
     */
    public void addLine(TextElement<?> line) {
        this.lines.add(line);
    }

    /**
     * Adds a new line element at the specified index of the scoreboard.
     *
     * @param index the index at which to add the line.
     * @param line  the {@link TextElement} to be added.
     */
    public void addLine(int index, TextElement<?> line) {
        this.lines.add(index, line);
    }

    /**
     * Removes the line element at the specified index of the scoreboard.
     *
     * @param index the index of the line to be removed.
     */
    public void removeLine(int index) {
        this.lines.remove(index);
    }

    /**
     * Sets the list of line elements to be displayed on the scoreboard.
     *
     * @param lines the list of {@link TextElement} objects representing the new scoreboard lines.
     */
    public void setLines(List<TextElement<?>> lines) {
        this.lines = lines;
    }

    /**
     * Asserts that the visual data is ready for display.
     * <p>
     * This method checks if the title and lines are properly set and ready to be displayed.
     * If the title or lines are not set, a {@link VisualNotReadyException} is thrown.
     * </p>
     *
     * @param player the player for whom the visual data should be ready.
     * @throws VisualNotReadyException if the title or lines are not set or cannot be retrieved.
     */
    @Override
    public void assertReady(Player player) {
        if (title.getFromContext(OfflinePlayer.class, player) == null) {
            throw new VisualNotReadyException("The scoreboard title was not set!");
        }
        if (lines.isEmpty()) {
            throw new VisualNotReadyException("The scoreboard lines were not set!");
        }
    }
}
