package dev.acrispycookie.crispycommons.implementations.visual.bossbar.data;

import dev.acrispycookie.crispycommons.api.visual.abstraction.visual.VisualData;
import dev.acrispycookie.crispycommons.implementations.element.OwnedElement;
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
    private OwnedElement<GeneralElement<Float, ?>> progress;

    /**
     * The color element that controls the boss bar's color.
     */
    private OwnedElement<GeneralElement<BossBar.Color, ?>> color;

    /**
     * The overlay element that controls the boss bar's overlay style.
     */
    private OwnedElement<GeneralElement<BossBar.Overlay, ?>> overlay;

    /**
     * The text element that defines the boss bar's title.
     */
    private OwnedElement<TextElement<?>> text;

    /**
     * Constructs a new {@code BossBarData} instance with the specified progress, color, overlay, and text elements.
     *
     * @param progress the {@link GeneralElement} representing the progress of the boss bar.
     * @param color the {@link GeneralElement} representing the color of the boss bar.
     * @param overlay the {@link GeneralElement} representing the overlay style of the boss bar.
     * @param text the {@link TextElement} representing the title of the boss bar.
     */
    public BossBarData(@Nullable GeneralElement<Float, ?> progress, @Nullable GeneralElement<BossBar.Color, ?> color, @Nullable GeneralElement<BossBar.Overlay, ?> overlay, @Nullable TextElement<?> text) {
        this.progress = progress != null ? new OwnedElement<>(progress, this) : new OwnedElement<>(GeneralElement.simple(1f), this);
        this.color = color != null ? new OwnedElement<>(color, this) : new OwnedElement<>(GeneralElement.simple(BossBar.Color.PURPLE), this);
        this.overlay = overlay != null ?new OwnedElement<>(overlay, this) : new OwnedElement<>(GeneralElement.simple(BossBar.Overlay.PROGRESS), this);
        this.text = text != null ? new OwnedElement<>(text, this) : null;
    }

    /**
     * Retrieves the progress element of the boss bar.
     *
     * @return the {@link GeneralElement} representing the progress.
     */
    public @NotNull OwnedElement<GeneralElement<Float, ?>> getProgress() {
        return progress;
    }

    /**
     * Retrieves the color element of the boss bar.
     *
     * @return the {@link GeneralElement} representing the color.
     */
    public @NotNull OwnedElement<GeneralElement<BossBar.Color, ?>> getColor() {
        return color;
    }

    /**
     * Retrieves the overlay element of the boss bar.
     *
     * @return the {@link GeneralElement} representing the overlay.
     */
    public @NotNull OwnedElement<GeneralElement<BossBar.Overlay, ?>> getOverlay() {
        return overlay;
    }

    /**
     * Retrieves the text element of the boss bar.
     *
     * @return the {@link TextElement} representing the text.
     */
    public @Nullable OwnedElement<TextElement<?>> getText() {
        return text;
    }

    /**
     * Sets the progress element of the boss bar.
     *
     * @param progress the {@link GeneralElement} representing the progress to set.
     */
    public void setProgress(@NotNull GeneralElement<Float, ?> progress) {
        this.progress = new OwnedElement<>(progress, this);
    }

    /**
     * Sets the color element of the boss bar.
     *
     * @param color the {@link GeneralElement} representing the color to set.
     */
    public void setColor(@NotNull GeneralElement<BossBar.Color, ?> color) {
        this.color = new OwnedElement<>(color, this);
    }

    /**
     * Sets the overlay element of the boss bar.
     *
     * @param overlay the {@link GeneralElement} representing the overlay to set.
     */
    public void setOverlay(@NotNull GeneralElement<BossBar.Overlay, ?> overlay) {
        this.overlay = new OwnedElement<>(overlay, this);
    }

    /**
     * Sets the text element of the boss bar.
     *
     * @param text the {@link TextElement} representing the text to set.
     */
    public void setText(@NotNull TextElement<?> text) {
        this.text = new OwnedElement<>(text, this);
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
        if (text == null)
            throw new VisualNotReadyException("The boss bar text was not set!");
    }
}

