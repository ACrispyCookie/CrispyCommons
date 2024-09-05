package dev.acrispycookie.crispycommons.implementations.visual.title;

import dev.acrispycookie.crispycommons.api.visual.title.CrispyTitle;
import dev.acrispycookie.crispycommons.implementations.visual.abstraction.visual.AbstractVisual;
import dev.acrispycookie.crispycommons.implementations.visual.title.data.TitleData;
import dev.acrispycookie.crispycommons.implementations.element.type.GeneralElement;
import dev.acrispycookie.crispycommons.implementations.element.type.TextElement;
import dev.acrispycookie.crispycommons.implementations.element.type.TimeToLiveElement;
import dev.acrispycookie.crispycommons.utility.visual.FieldUpdaterHelper;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

/**
 * An abstract class representing the base implementation of a title.
 * <p>
 * {@code AbstractTitle} manages the visual components of a title, including the main title, subtitle,
 * and fade in/out durations. It provides methods to set these components and handles the lifecycle
 * events such as showing and hiding the title. Subclasses are expected to implement the specific logic
 * for displaying and updating the title.
 * </p>
 */
public abstract class AbstractTitle extends AbstractVisual<TitleData> implements CrispyTitle {

    /**
     * Constructs an {@code AbstractTitle} with the specified parameters.
     *
     * @param data the {@link TitleData} containing the visual data for the title.
     * @param receivers the set of players who will receive the title.
     * @param timeToLive the time-to-live (TTL) element controlling the lifespan of the title.
     * @param updateMode the mode in which updates to the title are handled.
     * @param isPublic whether the title should be visible to all players.
     */
    AbstractTitle(@NotNull TitleData data, @NotNull Set<? extends OfflinePlayer> receivers, @NotNull TimeToLiveElement<?> timeToLive, @NotNull UpdateMode updateMode, boolean isPublic) {
        super(data, receivers, timeToLive, updateMode, isPublic);
    }

    /**
     * Prepares the title for display by starting the title and subtitle elements.
     * This method is called when the title is about to be shown to players.
     */
    @Override
    protected void prepareShow() {
        data.getTitle().start();
        data.getSubtitle().start();
    }

    /**
     * Prepares the title for hiding by stopping the title and subtitle elements.
     * This method is called when the title is about to be hidden from players.
     */
    @Override
    protected void prepareHide() {
        data.getTitle().stop();
        data.getSubtitle().stop();
    }

    /**
     * Sets the main title text.
     * <p>
     * Stops the current title text, replaces it with the new text, and sets up the update behavior.
     * If the title is currently being watched by any player, it starts the new title text and updates the display.
     * </p>
     *
     * @param text the new {@link TextElement} representing the main title text.
     */
    @Override
    public void setTitle(@NotNull TextElement<?> text) {
        FieldUpdaterHelper.setNormalField(text, data::getTitle, data::setTitle, isAnyoneWatching(), this::update);
    }

    /**
     * Sets the subtitle text.
     * <p>
     * Stops the current subtitle text, replaces it with the new text, and sets up the update behavior.
     * If the title is currently being watched by any player, it starts the new subtitle text and updates the display.
     * </p>
     *
     * @param text the new {@link TextElement} representing the subtitle text.
     */
    @Override
    public void setSubtitle(@NotNull TextElement<?> text) {
        FieldUpdaterHelper.setNormalField(text, data::getSubtitle, data::setSubtitle, isAnyoneWatching(), this::update);
    }

    /**
     * Sets the fade-in duration for the title.
     *
     * @param fadeIn a {@link GeneralElement} representing the fade-in duration in ticks.
     */
    @Override
    public void setFadeIn(@NotNull GeneralElement<Integer, ?> fadeIn) {
        data.setFadeIn(fadeIn);
    }

    /**
     * Sets the fade-out duration for the title.
     *
     * @param fadeOut a {@link GeneralElement} representing the fade-out duration in ticks.
     */
    @Override
    public void setFadeOut(@NotNull GeneralElement<Integer, ?> fadeOut) {
        data.setFadeOut(fadeOut);
    }

    /**
     * Gets the main title text.
     *
     * @return the {@link TextElement} representing the main title text.
     */
    @Override
    public @Nullable TextElement<?> getTitle() {
        return data.getTitle() != null ? data.getTitle().getElement() : null;
    }

    /**
     * Gets the subtitle text.
     *
     * @return the {@link TextElement} representing the subtitle text.
     */
    @Override
    public @Nullable TextElement<?> getSubtitle() {
        return data.getSubtitle() != null ? data.getSubtitle().getElement() : null;
    }

    /**
     * Gets the fade-in duration for the title.
     *
     * @return a {@link GeneralElement} representing the fade-in duration in ticks.
     */
    @Override
    public @NotNull GeneralElement<Integer, ?> getFadeIn() {
        return this.data.getFadeIn().getElement();
    }

    /**
     * Gets the fade-out duration for the title.
     *
     * @return a {@link GeneralElement} representing the fade-out duration in ticks.
     */
    @Override
    public @NotNull GeneralElement<Integer, ?> getFadeOut() {
        return this.data.getFadeOut().getElement();
    }
}

