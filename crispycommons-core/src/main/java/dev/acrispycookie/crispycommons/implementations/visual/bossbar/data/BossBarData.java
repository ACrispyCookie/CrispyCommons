package dev.acrispycookie.crispycommons.implementations.visual.bossbar.data;

import dev.acrispycookie.crispycommons.api.visual.abstraction.visual.VisualData;
import dev.acrispycookie.crispycommons.implementations.element.type.GeneralElement;
import dev.acrispycookie.crispycommons.implementations.element.type.TextElement;
import net.kyori.adventure.bossbar.BossBar;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A data class that represents the visual data needed to display a boss bar.
 * <p>
 * {@code BossBarData} encapsulates the elements that define a boss bar, including the text, progress,
 * color, and overlay. This class also provides a method to ensure that the boss bar data is ready to be displayed.
 * </p>
 */
public class BossBarData implements VisualData {

    /**
     * The progress element that controls the boss bar's progress.
     * <p>
     * The value should be between 0.0 (empty) and 1.0 (full).
     * </p>
     */
    private GeneralElement<Float, ?> progress;

    /**
     * The color element that controls the boss bar's color.
     */
    private GeneralElement<BossBar.Color, ?> color;

    /**
     * The overlay element that controls the boss bar's overlay style.
     */
    private GeneralElement<BossBar.Overlay, ?> overlay;

    /**
     * The text element that defines the boss bar's title.
     */
    private TextElement<?> text;

    /**
     * Constructs a new {@code BossBarData} instance with the specified progress, color, overlay, and text elements.
     *
     * @param progress the {@link GeneralElement} representing the progress of the boss bar.
     * @param color the {@link GeneralElement} representing the color of the boss bar.
     * @param overlay the {@link GeneralElement} representing the overlay style of the boss bar.
     * @param text the {@link TextElement} representing the title of the boss bar.
     */
    public BossBarData(@NotNull GeneralElement<Float, ?> progress, @Nullable GeneralElement<BossBar.Color, ?> color, @Nullable GeneralElement<BossBar.Overlay, ?> overlay, @Nullable TextElement<?> text) {
        this.progress = progress;
        this.color = color;
        this.overlay = overlay;
        this.text = text;
    }

    /**
     * Retrieves the progress element of the boss bar.
     *
     * @return the {@link GeneralElement} representing the progress.
     */
    public @NotNull GeneralElement<Float, ?> getProgress() {
        return progress;
    }

    /**
     * Sets the progress element of the boss bar.
     *
     * @param progress the {@link GeneralElement} representing the progress to set.
     */
    public void setProgress(@NotNull GeneralElement<Float, ?> progress) {
        this.progress = progress;
    }

    /**
     * Retrieves the color element of the boss bar.
     *
     * @return the {@link GeneralElement} representing the color.
     */
    public @NotNull GeneralElement<BossBar.Color, ?> getColor() {
        return color;
    }

    /**
     * Sets the color element of the boss bar.
     *
     * @param color the {@link GeneralElement} representing the color to set.
     */
    public void setColor(@NotNull GeneralElement<BossBar.Color, ?> color) {
        this.color = color;
    }

    /**
     * Retrieves the overlay element of the boss bar.
     *
     * @return the {@link GeneralElement} representing the overlay.
     */
    public @NotNull GeneralElement<BossBar.Overlay, ?> getOverlay() {
        return overlay;
    }

    /**
     * Sets the overlay element of the boss bar.
     *
     * @param overlay the {@link GeneralElement} representing the overlay to set.
     */
    public void setOverlay(@NotNull GeneralElement<BossBar.Overlay, ?> overlay) {
        this.overlay = overlay;
    }

    /**
     * Retrieves the text element of the boss bar.
     *
     * @return the {@link TextElement} representing the text.
     */
    public @NotNull TextElement<?> getText() {
        return text;
    }

    /**
     * Sets the text element of the boss bar.
     *
     * @param text the {@link TextElement} representing the text to set.
     */
    public void setText(@NotNull TextElement<?> text) {
        this.text = text;
    }

    /**
     * Asserts that the visual data is ready for display.
     * <p>
     * This method checks if all necessary elements (text, color, overlay, and progress) are set and valid.
     * If any element is not set or invalid, a {@link VisualNotReadyException} is thrown.
     * </p>
     *
     * @param player the player for whom the visual data should be ready.
     * @throws VisualNotReadyException if any required element is not set or is invalid.
     */
    @Override
    public void assertReady(@NotNull Player player) {
        if (text == null) {
            throw new VisualNotReadyException("The boss bar text was not set!");
        }
        if (color == null) {
            throw new VisualNotReadyException("The boss bar color was not set!");
        }
        if (overlay == null) {
            throw new VisualNotReadyException("The boss bar overlay was not set!");
        }
        if (progress == null) {
            throw new VisualNotReadyException("The boss bar progress was not set!");
        }
    }
}

