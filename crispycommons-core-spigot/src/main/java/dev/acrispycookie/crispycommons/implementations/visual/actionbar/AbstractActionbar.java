package dev.acrispycookie.crispycommons.implementations.visual.actionbar;

import dev.acrispycookie.crispycommons.api.visual.actionbar.CrispyActionbar;
import dev.acrispycookie.crispycommons.implementations.visual.abstraction.visual.AbstractVisual;
import dev.acrispycookie.crispycommons.implementations.visual.actionbar.data.ActionbarData;
import dev.acrispycookie.crispycommons.implementations.element.type.TextElement;
import dev.acrispycookie.crispycommons.implementations.element.type.TimeToLiveElement;
import dev.acrispycookie.crispycommons.utility.visual.FieldUpdaterHelper;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

/**
 * An abstract base class for action bars in the CrispyCommons plugin.
 * <p>
 * {@code AbstractActionbar} extends {@link AbstractVisual} and implements {@link CrispyActionbar},
 * providing core functionality for managing action bars. This class handles the display, updating,
 * and management of text elements in an action bar.
 * </p>
 */
public abstract class AbstractActionbar extends AbstractVisual<ActionbarData> implements CrispyActionbar {

    /**
     * Constructs an {@code AbstractActionbar} with the specified parameters.
     *
     * @param data the data associated with this action bar.
     * @param receivers the set of players who will receive the action bar.
     * @param timeToLive the time-to-live (TTL) element controlling the lifespan of the action bar.
     * @param updateMode the update mode for the action bar (per-player, global, or both).
     * @param isPublic whether the action bar should be visible to all players.
     */
    AbstractActionbar(@NotNull ActionbarData data, @NotNull Set<? extends OfflinePlayer> receivers, @NotNull TimeToLiveElement<?> timeToLive, @NotNull UpdateMode updateMode, boolean isPublic) {
        super(data, receivers, timeToLive, updateMode, isPublic);
    }

    /**
     * Prepares the action bar for display by starting the associated text element.
     */
    @Override
    protected void prepareShow() {
        data.getText().start();
    }

    /**
     * Prepares the action bar for hiding by stopping the associated text element.
     */
    @Override
    protected void prepareHide() {
        data.getText().stop();
    }

    /**
     * Retrieves the current text of the action bar.
     *
     * @return the {@link TextElement} representing the action bar's text.
     */
    @Override
    public @Nullable TextElement<?> getText() {
        return data.getText() != null ? data.getText().getElement() : null;
    }

    /**
     * Sets the text of the action bar and updates its display.
     * <p>
     * If the action bar is currently being displayed, the new text element will start
     * and the action bar will be updated immediately.
     * </p>
     *
     * @param text the {@link TextElement} to set as the action bar's text.
     */
    @Override
    public void setText(@NotNull TextElement<?> text) {
        FieldUpdaterHelper.setNormalField(text, data::getText, data::setText, isAnyoneWatching(), this::update);
    }
}

