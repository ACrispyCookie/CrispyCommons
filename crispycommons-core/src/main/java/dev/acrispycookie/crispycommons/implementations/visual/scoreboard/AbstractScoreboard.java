package dev.acrispycookie.crispycommons.implementations.visual.scoreboard;

import dev.acrispycookie.crispycommons.api.visual.scoreboard.CrispyScoreboard;
import dev.acrispycookie.crispycommons.api.element.DynamicElement;
import dev.acrispycookie.crispycommons.implementations.visual.abstraction.visual.AbstractVisual;
import dev.acrispycookie.crispycommons.implementations.visual.scoreboard.data.ScoreboardData;
import dev.acrispycookie.crispycommons.implementations.element.AbstractDynamicElement;
import dev.acrispycookie.crispycommons.implementations.element.type.TextElement;
import dev.acrispycookie.crispycommons.implementations.element.type.TimeToLiveElement;
import org.bukkit.OfflinePlayer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * An abstract class that represents a scoreboard.
 * <p>
 * {@code AbstractScoreboard} provides the core functionality for managing scoreboards,
 * including adding, removing, and updating lines of text. It extends {@link AbstractVisual}
 * and implements {@link CrispyScoreboard}, allowing scoreboards to be displayed, updated,
 * and managed per player or globally.
 * </p>
 */
public abstract class AbstractScoreboard extends AbstractVisual<ScoreboardData> implements CrispyScoreboard {

    /**
     * Constructs an {@code AbstractScoreboard} with the specified data, receivers, time-to-live, update mode, and visibility settings.
     *
     * @param data       the {@link ScoreboardData} that contains the visual and functional data for the scoreboard.
     * @param receivers  the set of players who will see the scoreboard.
     * @param timeToLive the time-to-live (TTL) element controlling the lifespan of the scoreboard.
     * @param updateMode the mode of updating the scoreboard (e.g., per player, globally).
     * @param isPublic   whether the scoreboard should be visible to all players.
     */
    AbstractScoreboard(ScoreboardData data, Set<? extends OfflinePlayer> receivers, TimeToLiveElement<?> timeToLive, UpdateMode updateMode, boolean isPublic) {
        super(data, receivers, timeToLive, updateMode, isPublic);
    }

    /**
     * Prepares the scoreboard for display by starting the title and lines.
     */
    @Override
    protected void prepareShow() {
        this.data.getTitle().start();
        this.data.getLines().forEach(TextElement::start);
    }

    /**
     * Prepares the scoreboard for hiding by stopping the title and lines.
     */
    @Override
    protected void prepareHide() {
        this.data.getTitle().stop();
        this.data.getLines().forEach(TextElement::stop);
    }

    /**
     * Adds a line of text at the specified index.
     * <p>
     * This method starts the text element and updates the scoreboard accordingly.
     * </p>
     *
     * @param index the index at which to add the line.
     * @param line  the {@link TextElement} representing the line of text.
     */
    @Override
    public void addLine(int index, TextElement<?> line) {
        if (index > data.getLines().size()) {
            return;
        }

        line.setUpdate(() -> updateLine(index));
        if (isAnyoneWatching()) {
            line.start();
        }
        data.addLine(index, line);
        for (int i = index; i < data.getLines().size(); i++) {
            int newIndex = i;
            data.getLines().get(i).setUpdate(() -> updateLine(newIndex));
        }
        addLineInternal(index);
    }

    /**
     * Adds a line of text at the end of the scoreboard.
     *
     * @param line the {@link TextElement} representing the line of text.
     */
    @Override
    public void addLine(TextElement<?> line) {
        addLine(data.getLines().size(), line);
    }

    /**
     * Removes a line of text from the scoreboard at the specified index.
     * <p>
     * This method stops the text element and updates the scoreboard accordingly.
     * </p>
     *
     * @param index the index of the line to remove.
     */
    @Override
    public void removeLine(int index) {
        if (index >= data.getLines().size()) {
            return;
        }

        if (isAnyoneWatching()) {
            data.getLines().get(index).stop();
        }
        data.removeLine(index);
        for (int i = index; i < data.getLines().size(); i++) {
            int newIndex = i;
            data.getLines().get(i).setUpdate(() -> updateLine(newIndex));
        }
        removeLineInternal(index);
    }

    /**
     * Sets the lines of text in the scoreboard.
     * <p>
     * This method stops the current lines, starts the new lines, and updates the scoreboard accordingly.
     * </p>
     *
     * @param lines the collection of {@link TextElement} representing the new lines of text.
     */
    @Override
    public void setLines(Collection<? extends TextElement<?>> lines) {
        List<DynamicElement<?, ?>> lineList = new ArrayList<>(lines);
        for (int i = 0; i < lineList.size(); i++) {
            int index = i;
            DynamicElement<?, ?> line = lineList.get(index);
            line.setUpdate(() -> updateLine(index));
        }
        if (isAnyoneWatching()) {
            data.getLines().forEach(DynamicElement::stop);
            lines.forEach(AbstractDynamicElement::start);
        }
        int oldSize = data.getLines().size();
        data.setLines(new ArrayList<>(lines));
        updateLines(oldSize);
    }

    /**
     * Retrieves the list of text lines currently displayed in the scoreboard.
     *
     * @return a list of {@link TextElement} representing the lines of text.
     */
    @Override
    public List<TextElement<?>> getLines() {
        return data.getLines();
    }

    /**
     * Sets the title of the scoreboard.
     * <p>
     * If the scoreboard is being displayed, this method stops the current title and starts the new title.
     * </p>
     *
     * @param title the {@link TextElement} representing the new title.
     */
    @Override
    public void setTitle(TextElement<?> title) {
        if (isAnyoneWatching()) {
            data.getTitle().stop();
            title.setUpdate(this::updateTitle);
            title.start();
        }
        data.setTitle(title);
        updateTitle();
    }

    /**
     * Retrieves the title of the scoreboard.
     *
     * @return the {@link TextElement} representing the title of the scoreboard.
     */
    @Override
    public TextElement<?> getTitle() {
        return data.getTitle();
    }

    /**
     * Updates the scoreboard lines based on the old size of the lines.
     * <p>
     * This abstract method must be implemented by subclasses to handle specific line updates.
     * </p>
     *
     * @param oldSize the previous size of the lines.
     */
    protected abstract void updateLines(int oldSize);

    /**
     * Removes a line of text from the scoreboard internally.
     * <p>
     * This abstract method must be implemented by subclasses to handle the internal removal of lines.
     * </p>
     *
     * @param index the index of the line to remove.
     */
    protected abstract void removeLineInternal(int index);

    /**
     * Adds a line of text to the scoreboard internally.
     * <p>
     * This abstract method must be implemented by subclasses to handle the internal addition of lines.
     * </p>
     *
     * @param index the index at which to add the line.
     */
    protected abstract void addLineInternal(int index);
}

