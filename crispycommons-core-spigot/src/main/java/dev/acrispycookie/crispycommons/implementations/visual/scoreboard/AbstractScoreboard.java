package dev.acrispycookie.crispycommons.implementations.visual.scoreboard;

import dev.acrispycookie.crispycommons.api.visual.scoreboard.CrispyScoreboard;
import dev.acrispycookie.crispycommons.implementations.element.OwnedElement;
import dev.acrispycookie.crispycommons.implementations.visual.abstraction.visual.AbstractVisual;
import dev.acrispycookie.crispycommons.implementations.visual.scoreboard.data.ScoreboardData;
import dev.acrispycookie.crispycommons.implementations.element.type.TextElement;
import dev.acrispycookie.crispycommons.implementations.element.type.TimeToLiveElement;
import dev.acrispycookie.crispycommons.utility.visual.FieldUpdaterHelper;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    AbstractScoreboard(@NotNull ScoreboardData data, @NotNull Set<? extends OfflinePlayer> receivers, @NotNull TimeToLiveElement<?> timeToLive, @NotNull UpdateMode updateMode, boolean isPublic) {
        super(data, receivers, timeToLive, updateMode, isPublic);
    }

    /**
     * Prepares the scoreboard for display by starting the title and lines.
     */
    @Override
    protected void prepareShow() {
        data.getTitle().start();
        data.getLines().forEach(OwnedElement::start);
    }

    /**
     * Prepares the scoreboard for hiding by stopping the title and lines.
     */
    @Override
    protected void prepareHide() {
        data.getTitle().stop();
        data.getLines().forEach(OwnedElement::stop);
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
    public void addLine(int index, @NotNull TextElement<?> line) {
        if (index > data.getLines().size()) {
            return;
        }

        data.addLine(index, line);
        data.getLines().get(index).setUpdate(() -> updateLine(index));
        addLineInternal(index);
        FieldUpdaterHelper.offsetAfterAddText(index, data.getLines(), isAnyoneWatching(), this::updateLine);
    }

    /**
     * Adds a line of text at the end of the scoreboard.
     *
     * @param line the {@link TextElement} representing the line of text.
     */
    @Override
    public void addLine(@NotNull TextElement<?> line) {
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

        data.removeLine(index).destroy();
        removeLineInternal(index);
        FieldUpdaterHelper.offsetAfterRemoveText(index, data.getLines(), isAnyoneWatching(), this::updateLine);
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
    public void setLines(@NotNull Collection<? extends TextElement<?>> lines) {
        List<OwnedElement<TextElement<?>>> oldLines = new ArrayList<>(data.getLines());
        data.setLines(new ArrayList<>(lines));
        updateLines(oldLines.size());
        FieldUpdaterHelper.resetLinesText(oldLines, data.getLines(), isAnyoneWatching(), this::updateLine);
    }

    /**
     * Retrieves the list of text lines currently displayed in the scoreboard.
     *
     * @return a list of {@link TextElement} representing the lines of text.
     */
    @Override
    public @NotNull List<TextElement<?>> getLines() {
        return data.getLines().stream().map(OwnedElement::getElement).collect(Collectors.toList());
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
    public void setTitle(@NotNull TextElement<?> title) {
        FieldUpdaterHelper.setNormalField(title, data::getTitle, data::setTitle, isAnyoneWatching(), this::updateTitle);
    }

    /**
     * Retrieves the title of the scoreboard.
     *
     * @return the {@link TextElement} representing the title of the scoreboard.
     */
    @Override
    public @Nullable TextElement<?> getTitle() {
        return data.getTitle() != null ? data.getTitle().getElement() : null;
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

