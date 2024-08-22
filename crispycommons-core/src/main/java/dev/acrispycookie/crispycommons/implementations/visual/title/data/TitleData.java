package dev.acrispycookie.crispycommons.implementations.visual.title.data;

import dev.acrispycookie.crispycommons.api.visual.abstraction.visual.VisualData;
import dev.acrispycookie.crispycommons.api.element.CrispyElement;
import dev.acrispycookie.crispycommons.implementations.element.type.GeneralElement;
import dev.acrispycookie.crispycommons.implementations.element.type.TextElement;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

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
    private TextElement<?> title;

    /**
     * The text element representing the subtitle.
     */
    private TextElement<?> subtitle;

    /**
     * The element representing the fade-in time (in ticks) for the title.
     */
    private GeneralElement<Integer, ?> fadeIn;

    /**
     * The element representing the fade-out time (in ticks) for the title.
     */
    private GeneralElement<Integer, ?> fadeOut;

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
    public TitleData(TextElement<?> title, TextElement<?> subtitle, GeneralElement<Integer, ?> fadeIn, GeneralElement<Integer, ?> fadeOut) {
        this.title = title;
        this.subtitle = subtitle;
        this.fadeIn = fadeIn;
        this.fadeOut = fadeOut;
    }

    /**
     * Retrieves the main title element.
     *
     * @return the {@link TextElement} representing the title.
     */
    public TextElement<?> getTitle() {
        return title;
    }

    /**
     * Retrieves the subtitle element.
     *
     * @return the {@link TextElement} representing the subtitle.
     */
    public TextElement<?> getSubtitle() {
        return subtitle;
    }

    /**
     * Retrieves the fade-in time element.
     *
     * @return the {@link GeneralElement} representing the fade-in time.
     */
    public GeneralElement<Integer, ?> getFadeIn() {
        return fadeIn;
    }

    /**
     * Retrieves the fade-out time element.
     *
     * @return the {@link GeneralElement} representing the fade-out time.
     */
    public GeneralElement<Integer, ?> getFadeOut() {
        return fadeOut;
    }

    /**
     * Sets the main title element.
     *
     * @param title the {@link TextElement} to set as the title.
     */
    public void setTitle(TextElement<?> title) {
        this.title = title;
        this.smallestPeriod = CrispyElement.getMinimumPeriod(title, subtitle);
    }

    /**
     * Sets the subtitle element.
     *
     * @param subtitle the {@link TextElement} to set as the subtitle.
     */
    public void setSubtitle(TextElement<?> subtitle) {
        this.subtitle = subtitle;
        this.smallestPeriod = CrispyElement.getMinimumPeriod(title, subtitle);
    }

    /**
     * Sets the fade-in time element.
     *
     * @param fadeIn the {@link GeneralElement} to set as the fade-in time.
     */
    public void setFadeIn(GeneralElement<Integer, ?> fadeIn) {
        this.fadeIn = fadeIn;
    }

    /**
     * Sets the fade-out time element.
     *
     * @param fadeOut the {@link GeneralElement} to set as the fade-out time.
     */
    public void setFadeOut(GeneralElement<Integer, ?> fadeOut) {
        this.fadeOut = fadeOut;
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
    public void assertReady(Player player) {
        if (title.getFromContext(OfflinePlayer.class, player) == null) {
            throw new VisualNotReadyException("The title was not set!");
        }
        if (subtitle.getFromContext(OfflinePlayer.class, player) == null) {
            throw new VisualNotReadyException("The subtitle was not set!");
        }
        if (fadeIn.isDynamic()) {
            throw new VisualNotReadyException("The fade-in cannot be dynamic!");
        }
        if (fadeOut.isDynamic()) {
            throw new VisualNotReadyException("The fade-out cannot be dynamic!");
        }
        this.smallestPeriod = CrispyElement.getMinimumPeriod(title, subtitle);
    }
}

