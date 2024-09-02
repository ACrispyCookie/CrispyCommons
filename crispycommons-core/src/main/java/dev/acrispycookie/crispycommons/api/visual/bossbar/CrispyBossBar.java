package dev.acrispycookie.crispycommons.api.visual.bossbar;

import dev.acrispycookie.crispycommons.api.visual.abstraction.visual.CrispyVisual;
import dev.acrispycookie.crispycommons.implementations.visual.abstraction.builder.AbstractVisualBuilder;
import dev.acrispycookie.crispycommons.implementations.visual.bossbar.SimpleBossBar;
import dev.acrispycookie.crispycommons.implementations.visual.bossbar.data.BossBarData;
import dev.acrispycookie.crispycommons.implementations.element.type.GeneralElement;
import dev.acrispycookie.crispycommons.implementations.element.type.TextElement;
import net.kyori.adventure.bossbar.BossBar;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a boss bar visual element in the game, extending the {@link CrispyVisual} interface.
 * <p>
 * The {@code CrispyBossBar} interface allows for displaying and managing boss bars with dynamic text,
 * progress, color, and overlay. It provides methods to set and retrieve these elements, as well as a
 * builder for creating instances of boss bars with customizable properties.
 * </p>
 */
public interface CrispyBossBar extends CrispyVisual {

    /**
     * Creates a new instance of {@link BossBarBuilder}, which can be used to build a {@code CrispyBossBar}.
     *
     * @return a new {@link BossBarBuilder} instance.
     */
    static @NotNull BossBarBuilder builder() {
        return new BossBarBuilder();
    }

    /**
     * Sets the text element to be displayed on the boss bar.
     *
     * @param text the {@link TextElement} to display on the boss bar.
     */
    void setText(@NotNull TextElement<?> text);

    /**
     * Sets the progress of the boss bar.
     *
     * @param progress the {@link GeneralElement} representing the progress of the boss bar, with a value between 0.0 and 1.0.
     */
    void setProgress(@NotNull GeneralElement<Float, ?> progress);

    /**
     * Sets the color of the boss bar.
     *
     * @param color the {@link GeneralElement} representing the {@link BossBar.Color} of the boss bar.
     */
    void setColor(@NotNull GeneralElement<BossBar.Color, ?> color);

    /**
     * Sets the overlay of the boss bar.
     *
     * @param overlay the {@link GeneralElement} representing the {@link BossBar.Overlay} of the boss bar.
     */
    void setOverlay(@NotNull GeneralElement<BossBar.Overlay, ?> overlay);

    /**
     * Retrieves the text element currently displayed on the boss bar.
     *
     * @return the {@link TextElement} displayed on the boss bar.
     */
    @NotNull TextElement<?> getText();

    /**
     * Retrieves the progress of the boss bar.
     *
     * @return the {@link GeneralElement} representing the progress of the boss bar.
     */
    @NotNull GeneralElement<Float, ?> getProgress();

    /**
     * Retrieves the color of the boss bar.
     *
     * @return the {@link GeneralElement} representing the {@link BossBar.Color} of the boss bar.
     */
    @NotNull GeneralElement<BossBar.Color, ?> getColor();

    /**
     * Retrieves the overlay of the boss bar.
     *
     * @return the {@link GeneralElement} representing the {@link BossBar.Overlay} of the boss bar.
     */
    @NotNull GeneralElement<BossBar.Overlay, ?> getOverlay();

    /**
     * Updates the text element currently displayed on the boss bar.
     */
    void updateText();

    /**
     * Updates the progress of the boss bar.
     */
    void updateProgress();

    /**
     * Updates the color of the boss bar.
     */
    void updateColor();

    /**
     * Updates the overlay of the boss bar.
     */
    void updateOverlay();

    /**
     * A builder class for constructing instances of {@link CrispyBossBar}.
     * <p>
     * The {@code BossBarBuilder} extends {@link AbstractVisualBuilder} and allows for the customization
     * of boss bars, including setting the text, progress, color, overlay, players who can view the boss bar, and other properties.
     * </p>
     */
    class BossBarBuilder extends AbstractVisualBuilder<CrispyBossBar> {

        private final BossBarData data = new BossBarData(GeneralElement.simple((float) -1), null, null, null);

        /**
         * Sets the text element to be displayed on the boss bar.
         * <p>
         * The text element updates the boss bar whenever it is updated.
         * </p>
         *
         * @param text the {@link TextElement} to display on the boss bar.
         * @return this {@code BossBarBuilder} instance for method chaining.
         */
        public @NotNull BossBarBuilder setText(@NotNull TextElement<?> text) {
            this.data.setText(text);
            this.data.getText().setUpdate(() -> toBuild.updateText());
            return this;
        }

        /**
         * Sets the progress of the boss bar.
         * <p>
         * The progress element updates the boss bar whenever it is updated.
         * </p>
         *
         * @param progress the {@link GeneralElement} representing the progress of the boss bar.
         * @return this {@code BossBarBuilder} instance for method chaining.
         */
        public @NotNull BossBarBuilder setProgress(@NotNull GeneralElement<Float, ?> progress) {
            this.data.setProgress(progress);
            this.data.getProgress().setUpdate(() -> toBuild.updateProgress());
            return this;
        }

        /**
         * Sets the color of the boss bar.
         * <p>
         * The color element updates the boss bar whenever it is updated.
         * </p>
         *
         * @param color the {@link GeneralElement} representing the {@link BossBar.Color} of the boss bar.
         * @return this {@code BossBarBuilder} instance for method chaining.
         */
        public @NotNull BossBarBuilder setColor(@NotNull GeneralElement<BossBar.Color, ?> color) {
            this.data.setColor(color);
            this.data.getColor().setUpdate(() -> toBuild.updateColor());
            return this;
        }

        /**
         * Sets the overlay of the boss bar.
         * <p>
         * The overlay element updates the boss bar whenever it is updated.
         * </p>
         *
         * @param overlay the {@link GeneralElement} representing the {@link BossBar.Overlay} of the boss bar.
         * @return this {@code BossBarBuilder} instance for method chaining.
         */
        public @NotNull BossBarBuilder setOverlay(@NotNull GeneralElement<BossBar.Overlay, ?> overlay) {
            this.data.setOverlay(overlay);
            this.data.getOverlay().setUpdate(() -> toBuild.updateOverlay());
            return this;
        }

        /**
         * Builds and returns the configured {@link CrispyBossBar} instance.
         *
         * @return the constructed {@link CrispyBossBar} instance.
         */
        @Override
        public @NotNull CrispyBossBar build() {
            this.toBuild = new SimpleBossBar(data, receivers, timeToLive, isPublic);
            return toBuild;
        }
    }
}

