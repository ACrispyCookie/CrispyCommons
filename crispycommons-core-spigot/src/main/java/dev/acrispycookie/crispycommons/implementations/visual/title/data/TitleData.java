package dev.acrispycookie.crispycommons.implementations.visual.title.data;

import dev.acrispycookie.crispycommons.api.visual.abstraction.visual.VisualData;
import dev.acrispycookie.crispycommons.api.element.CrispyElement;
import dev.acrispycookie.crispycommons.implementations.element.OwnedElement;
import dev.acrispycookie.crispycommons.implementations.element.type.GeneralElement;
import dev.acrispycookie.crispycommons.implementations.element.type.TextElement;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A data class representing the visual data needed to display a title with a subtitle and fade effects.
 * <p>
 * {@code TitleData} encapsulates the title, subtitle, and fade timings that will be displayed.
 * It also provides methods to manage these elements and ensures the title is ready to be displayed.
 * </p>
 */
public class TitleData implements VisualData {

    /**
     * The text element representing the main title.
     */
    private OwnedElement<TextElement<?>> title;

    /**
     * The text element representing the subtitle.
     */
    private OwnedElement<TextElement<?>> subtitle;

    /**
     * The element representing the fade-in time (in ticks) for the title.
     */
    private OwnedElement<GeneralElement<Integer, ?>> fadeIn;

    /**
     * The element representing the fade-out time (in ticks) for the title.
     */
    private OwnedElement<GeneralElement<Integer, ?>> fadeOut;

    /**
     * The smallest update period between the title and subtitle elements.
     */
    private int smallestPeriod;

    /**
     * Constructs a new {@code TitleData} instance with the specified title, subtitle, fade-in, and fade-out times.
     *
     * @param title the {@link TextElement} representing the main title.
     * @param subtitle the {@link TextElement} representing the subtitle.
     * @param fadeIn the {@link GeneralElement} representing the fade-in time.
     * @param fadeOut the {@link GeneralElement} representing the fade-out time.
     */
    public TitleData(@Nullable TextElement<?> title, @Nullable TextElement<?> subtitle, @Nullable GeneralElement<Integer, ?> fadeIn, @Nullable GeneralElement<Integer, ?> fadeOut) {
        this.title = title != null ? new OwnedElement<>(title, this, "title") : null;
        this.subtitle = subtitle != null ? new OwnedElement<>(subtitle, this, "subtitle") : null;
        this.fadeIn = fadeIn != null ? new OwnedElement<>(fadeIn, this, "fade-in") : new OwnedElement<>(GeneralElement.simple(0), this);
        this.fadeOut = fadeOut != null ? new OwnedElement<>(fadeOut, this, "fade-out") : new OwnedElement<>(GeneralElement.simple(0), this);
    }

    /**
     * Retrieves the main title element.
     *
     * @return the {@link TextElement} representing the title.
     */
    public @Nullable OwnedElement<TextElement<?>> getTitle() {
        return title;
    }

    /**
     * Retrieves the subtitle element.
     *
     * @return the {@link TextElement} representing the subtitle.
     */
    public @Nullable OwnedElement<TextElement<?>> getSubtitle() {
        return subtitle;
    }

    /**
     * Retrieves the fade-in time element.
     *
     * @return the {@link GeneralElement} representing the fade-in time.
     */
    public @NotNull OwnedElement<GeneralElement<Integer, ?>> getFadeIn() {
        return fadeIn;
    }

    /**
     * Retrieves the fade-out time element.
     *
     * @return the {@link GeneralElement} representing the fade-out time.
     */
    public @NotNull OwnedElement<GeneralElement<Integer, ?>> getFadeOut() {
        return fadeOut;
    }

    /**
     * Sets the main title element.
     *
     * @param title the {@link TextElement} to set as the title.
     */
    public void setTitle(@NotNull TextElement<?> title) {
        this.title = new OwnedElement<>(title, this, "title");
        this.smallestPeriod = subtitle != null ? CrispyElement.getMinimumPeriod(title, subtitle.getElement()) : title.getPeriod();
    }

    /**
     * Sets the subtitle element.
     *
     * @param subtitle the {@link TextElement} to set as the subtitle.
     */
    public void setSubtitle(@NotNull TextElement<?> subtitle) {
        this.subtitle = new OwnedElement<>(subtitle, this, "subtitle");
        this.smallestPeriod = title != null ? CrispyElement.getMinimumPeriod(title.getElement(), subtitle) : subtitle.getPeriod();
    }

    /**
     * Sets the fade-in time element.
     *
     * @param fadeIn the {@link GeneralElement} to set as the fade-in time.
     */
    public void setFadeIn(@NotNull GeneralElement<Integer, ?> fadeIn) {
        this.fadeIn = new OwnedElement<>(fadeIn, this, "fade-in");
    }

    /**
     * Sets the fade-out time element.
     *
     * @param fadeOut the {@link GeneralElement} to set as the fade-out time.
     */
    public void setFadeOut(@NotNull GeneralElement<Integer, ?> fadeOut) {
        this.fadeOut = new OwnedElement<>(fadeOut, this, "fade-out");
    }

    /**
     * Retrieves the smallest update period between the title and subtitle elements.
     *
     * @return the smallest period in ticks.
     */
    public int getSmallestPeriod() {
        return smallestPeriod;
    }

    /**
     * Asserts that the visual data is ready for display.
     * <p>
     * This method checks if the title and subtitle elements are not null and are set correctly.
     * It also ensures that the fade-in and fade-out times are not dynamic.
     * If any of these checks fail, a {@link VisualNotReadyException} is thrown.
     * </p>
     *
     * @param player the player for whom the visual data should be ready.
     * @throws VisualNotReadyException if any required data is not set or improperly configured.
     */
    @Override
    public void assertReady(@NotNull Player player) {
        if (fadeIn.getElement().isDynamic())
            throw new VisualNotReadyException("The fade-in is dynamic!");
        if (fadeOut.getElement().isDynamic())
            throw new VisualNotReadyException("The fade-out is dynamic!");
        this.smallestPeriod = CrispyElement.getMinimumPeriod(title.getElement(), subtitle.getElement());
    }
}

