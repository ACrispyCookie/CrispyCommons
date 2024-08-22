package dev.acrispycookie.crispycommons.implementations.visual.tablist;

import dev.acrispycookie.crispycommons.api.visual.tablist.CrispyTabList;
import dev.acrispycookie.crispycommons.api.element.DynamicElement;
import dev.acrispycookie.crispycommons.implementations.visual.abstraction.visual.AbstractVisual;
import dev.acrispycookie.crispycommons.implementations.visual.tablist.data.TabListData;
import dev.acrispycookie.crispycommons.implementations.element.AbstractDynamicElement;
import dev.acrispycookie.crispycommons.implementations.element.type.TextElement;
import dev.acrispycookie.crispycommons.implementations.element.type.TimeToLiveElement;
import org.bukkit.OfflinePlayer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * An abstract base class for managing and displaying a tab list with header and footer lines.
 * <p>
 * {@code AbstractTabList} provides the core functionality for adding, removing, and updating
 * header and footer lines in a tab list. Implementations of this class handle the specifics
 * of how the tab list is shown to players and how the lines are managed internally.
 * </p>
 */
public abstract class AbstractTabList extends AbstractVisual<TabListData> implements CrispyTabList {

    /**
     * Updates the lines in the header or footer and triggers a global update if necessary.
     * <p>
     * This method is called internally when header or footer lines are modified.
     * Implementations should define how the lines are updated.
     * </p>
     *
     * @param header {@code true} if updating the header, {@code false} if updating the footer.
     */
    protected abstract void updateLines(boolean header);

    /**
     * Removes a line from the header or footer and triggers an update if necessary.
     * <p>
     * This method is called internally when a line is removed from the header or footer.
     * Implementations should define how the line is removed.
     * </p>
     *
     * @param index the index of the line to remove.
     * @param header {@code true} if removing from the header, {@code false} if removing from the footer.
     */
    protected abstract void removeLineInternal(int index, boolean header);

    /**
     * Adds a line to the header or footer and triggers an update if necessary.
     * <p>
     * This method is called internally when a line is added to the header or footer.
     * Implementations should define how the line is added.
     * </p>
     *
     * @param index the index where the line will be added.
     * @param header {@code true} if adding to the header, {@code false} if adding to the footer.
     */
    protected abstract void addLineInternal(int index, boolean header);

    /**
     * Constructs an {@code AbstractTabList} with the specified parameters.
     *
     * @param data the {@link TabListData} containing the header and footer lines.
     * @param receivers the set of players who will receive the tab list updates.
     * @param timeToLive the time-to-live (TTL) element controlling the lifespan of the tab list.
     * @param updateMode the mode in which updates to the tab list are applied (global or per-player).
     * @param isPublic whether the tab list should be visible to all players.
     */
    AbstractTabList(TabListData data, Set<? extends OfflinePlayer> receivers, TimeToLiveElement<?> timeToLive, UpdateMode updateMode, boolean isPublic) {
        super(data, receivers, timeToLive, updateMode, isPublic);
    }

    /**
     * Prepares the tab list to be shown by starting the header and footer text elements.
     * <p>
     * This method is called before the tab list is displayed to any players.
     * </p>
     */
    @Override
    protected void prepareShow() {
        data.getHeader().forEach(TextElement::start);
        data.getFooter().forEach(TextElement::start);
    }

    /**
     * Prepares the tab list to be hidden by stopping the header and footer text elements.
     * <p>
     * This method is called before the tab list is hidden from any players.
     * </p>
     */
    @Override
    protected void prepareHide() {
        data.getHeader().forEach(TextElement::stop);
        data.getFooter().forEach(TextElement::stop);
    }

    /**
     * Adds a header line at the specified index.
     * <p>
     * The line is started if the tab list is currently displayed to any players.
     * </p>
     *
     * @param index the index where the line will be added.
     * @param line the text element representing the line.
     */
    @Override
    public void addHeaderLine(int index, TextElement<?> line) {
        if (index > data.getHeader().size())
            return;

        line.setUpdate(this::update);
        if (isAnyoneWatching())
            line.start();
        data.addHeaderLine(index, line);
        addLineInternal(index, true);
    }

    /**
     * Adds a header line at the end of the current header lines.
     *
     * @param line the text element representing the line.
     */
    @Override
    public void addHeaderLine(TextElement<?> line) {
        addHeaderLine(data.getHeader().size(), line);
    }

    /**
     * Removes a header line at the specified index.
     * <p>
     * The line is stopped if the tab list is currently displayed to any players.
     * </p>
     *
     * @param index the index of the line to remove.
     */
    @Override
    public void removeHeaderLine(int index) {
        if (index >= data.getHeader().size())
            return;

        if (isAnyoneWatching())
            data.getHeader().get(index).stop();
        data.removeHeaderLine(index);
        removeLineInternal(index, true);
    }

    /**
     * Sets the header lines with the specified collection of text elements.
     * <p>
     * The existing header lines are stopped, and the new lines are started
     * if the tab list is currently displayed to any players.
     * </p>
     *
     * @param lines the collection of text elements to set as the header lines.
     */
    @Override
    public void setHeader(Collection<? extends TextElement<?>> lines) {
        lines.forEach((l) -> l.setUpdate(this::update));
        if (isAnyoneWatching()) {
            data.getHeader().forEach(DynamicElement::stop);
            lines.forEach(AbstractDynamicElement::start);
        }
        data.setHeader(new ArrayList<>(lines));
        updateLines(true);
    }

    /**
     * Adds a footer line at the specified index.
     * <p>
     * The line is started if the tab list is currently displayed to any players.
     * </p>
     *
     * @param index the index where the line will be added.
     * @param line the text element representing the line.
     */
    @Override
    public void addFooterLine(int index, TextElement<?> line) {
        if (index > data.getFooter().size())
            return;

        line.setUpdate(this::update);
        if (isAnyoneWatching())
            line.start();
        data.addFooterLine(index, line);
        addLineInternal(index, false);
    }

    /**
     * Adds a footer line at the end of the current footer lines.
     *
     * @param line the text element representing the line.
     */
    @Override
    public void addFooterLine(TextElement<?> line) {
        addFooterLine(data.getFooter().size(), line);
    }

    /**
     * Removes a footer line at the specified index.
     * <p>
     * The line is stopped if the tab list is currently displayed to any players.
     * </p>
     *
     * @param index the index of the line to remove.
     */
    @Override
    public void removeFooterLine(int index) {
        if (index >= data.getFooter().size())
            return;

        if (isAnyoneWatching())
            data.getFooter().get(index).stop();
        data.removeFooterLine(index);
        removeLineInternal(index, false);
    }

    /**
     * Sets the footer lines with the specified collection of text elements.
     * <p>
     * The existing footer lines are stopped, and the new lines are started
     * if the tab list is currently displayed to any players.
     * </p>
     *
     * @param lines the collection of text elements to set as the footer lines.
     */
    @Override
    public void setFooter(Collection<? extends TextElement<?>> lines) {
        lines.forEach((l) -> l.setUpdate(this::update));
        if (isAnyoneWatching()) {
            data.getFooter().forEach(DynamicElement::stop);
            lines.forEach(AbstractDynamicElement::start);
        }
        data.setFooter(new ArrayList<>(lines));
        updateLines(false);
    }

    /**
     * Retrieves the current header lines.
     *
     * @return a list of {@link TextElement} representing the header lines.
     */
    public List<TextElement<?>> getHeader() {
        return this.data.getHeader();
    }

    /**
     * Retrieves the current footer lines.
     *
     * @return a list of {@link TextElement} representing the footer lines.
     */
    public List<TextElement<?>> getFooter() {
        return this.data.getFooter();
    }
}

