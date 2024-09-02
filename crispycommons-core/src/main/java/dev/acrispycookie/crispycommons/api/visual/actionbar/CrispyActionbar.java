package dev.acrispycookie.crispycommons.api.visual.actionbar;

import dev.acrispycookie.crispycommons.api.visual.abstraction.visual.CrispyVisual;
import dev.acrispycookie.crispycommons.implementations.visual.abstraction.builder.AbstractVisualBuilder;
import dev.acrispycookie.crispycommons.implementations.visual.actionbar.SimpleActionbar;
import dev.acrispycookie.crispycommons.implementations.visual.actionbar.data.ActionbarData;
import dev.acrispycookie.crispycommons.implementations.element.type.TextElement;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an action bar visual element in the game, extending the {@link CrispyVisual} interface.
 * <p>
 * The {@code CrispyActionbar} interface allows for displaying and managing action bars with dynamic text.
 * It provides methods to set and retrieve the text element displayed on the action bar, and includes a
 * builder for creating instances of action bars with customizable properties.
 * </p>
 */
public interface CrispyActionbar extends CrispyVisual {

    /**
     * Creates a new instance of {@link ActionbarBuilder}, which can be used to build a {@code CrispyActionbar}.
     *
     * @return a new {@link ActionbarBuilder} instance.
     */
    static @NotNull ActionbarBuilder builder() {
        return new ActionbarBuilder();
    }

    /**
     * Sets the text element to be displayed on the action bar.
     *
     * @param text the {@link TextElement} to display on the action bar.
     */
    void setText(@NotNull TextElement<?> text);

    /**
     * Retrieves the text element currently displayed on the action bar.
     *
     * @return the {@link TextElement} displayed on the action bar.
     */
    @NotNull TextElement<?> getText();

    /**
     * A builder class for constructing instances of {@link CrispyActionbar}.
     * <p>
     * The {@code ActionbarBuilder} extends {@link AbstractVisualBuilder} and allows for the customization
     * of action bars, including setting the text, players who can view the action bar, and other properties.
     * </p>
     */
    class ActionbarBuilder extends AbstractVisualBuilder<CrispyActionbar> {

        private final ActionbarData data = new ActionbarData(null);

        /**
         * Sets the text element to be displayed on the action bar.
         * <p>
         * The text element updates the action bar whenever it is updated.
         * </p>
         *
         * @param element the {@link TextElement} to display on the action bar.
         * @return this {@code ActionbarBuilder} instance for method chaining.
         */
        public @NotNull ActionbarBuilder setText(@NotNull TextElement<?> element) {
            this.data.setText(element);
            this.data.getText().setUpdate(() -> toBuild.update());
            return this;
        }

        /**
         * Builds and returns the configured {@link CrispyActionbar} instance.
         *
         * @return the constructed {@link CrispyActionbar} instance.
         */
        @Override
        public @NotNull CrispyActionbar build() {
            this.toBuild = new SimpleActionbar(data, receivers, timeToLive, isPublic);
            return toBuild;
        }
    }
}

