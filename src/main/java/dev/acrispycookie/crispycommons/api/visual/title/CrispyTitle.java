package dev.acrispycookie.crispycommons.api.visual.title;

import dev.acrispycookie.crispycommons.api.visual.abstraction.visual.CrispyVisual;
import dev.acrispycookie.crispycommons.implementations.visual.abstraction.builder.AbstractVisualBuilder;
import dev.acrispycookie.crispycommons.implementations.visual.title.SimpleTitle;
import dev.acrispycookie.crispycommons.implementations.visual.title.UpdatingTitle;
import dev.acrispycookie.crispycommons.implementations.visual.title.data.TitleData;
import dev.acrispycookie.crispycommons.implementations.element.type.GeneralElement;
import dev.acrispycookie.crispycommons.implementations.element.type.TextElement;

/**
 * Represents a title display in the game, including title text, subtitle text, and fade-in/out times.
 * <p>
 * The {@code CrispyTitle} interface provides methods to set and retrieve various elements of a title
 * displayed to the player, including the title text, subtitle text, fade-in time, and fade-out time.
 * It also includes nested builder classes for constructing simple or updating titles.
 * </p>
 */
public interface CrispyTitle extends CrispyVisual {

    /**
     * Creates a new {@link SimpleTitleBuilder} instance for constructing a simple title.
     *
     * @return a new {@link SimpleTitleBuilder} instance.
     */
    static SimpleTitleBuilder simpleBuilder() {
        return new SimpleTitleBuilder();
    }

    /**
     * Creates a new {@link UpdatingTitleBuilder} instance for constructing a title with updating elements.
     *
     * @return a new {@link UpdatingTitleBuilder} instance.
     */
    static UpdatingTitleBuilder updatingBuilder() {
        return new UpdatingTitleBuilder();
    }

    /**
     * Sets the title text.
     *
     * @param text the {@link TextElement} representing the title text.
     */
    void setTitle(TextElement<?> text);

    /**
     * Sets the subtitle text.
     *
     * @param text the {@link TextElement} representing the subtitle text.
     */
    void setSubtitle(TextElement<?> text);

    /**
     * Sets the fade-in time for the title display.
     *
     * @param fadeIn the {@link GeneralElement} representing the fade-in time in ticks.
     */
    void setFadeIn(GeneralElement<Integer, ?> fadeIn);

    /**
     * Sets the fade-out time for the title display.
     *
     * @param fadeOut the {@link GeneralElement} representing the fade-out time in ticks.
     */
    void setFadeOut(GeneralElement<Integer, ?> fadeOut);

    /**
     * Retrieves the title text.
     *
     * @return the {@link TextElement} representing the title text.
     */
    TextElement<?> getTitle();

    /**
     * Retrieves the subtitle text.
     *
     * @return the {@link TextElement} representing the subtitle text.
     */
    TextElement<?> getSubtitle();

    /**
     * Retrieves the fade-in time for the title display.
     *
     * @return the {@link GeneralElement} representing the fade-in time in ticks.
     */
    GeneralElement<Integer, ?> getFadeIn();

    /**
     * Retrieves the fade-out time for the title display.
     *
     * @return the {@link GeneralElement} representing the fade-out time in ticks.
     */
    GeneralElement<Integer, ?> getFadeOut();

    /**
     * Abstract builder class for constructing {@link CrispyTitle} instances.
     */
    abstract class TitleBuilder extends AbstractVisualBuilder<CrispyTitle> {

        /**
         * The data object to be used to build the title instance.
         */
        protected final TitleData data = new TitleData(null, null, GeneralElement.simple(0), GeneralElement.simple(0));

        /**
         * Sets the title text for the title being built.
         *
         * @param text the {@link TextElement} representing the title text.
         * @return this {@link TitleBuilder} instance for method chaining.
         */
        public TitleBuilder setTitle(TextElement<?> text) {
            text.setUpdate(() -> toBuild.update());
            this.data.setTitle(text);
            return this;
        }

        /**
         * Sets the subtitle text for the title being built.
         *
         * @param text the {@link TextElement} representing the subtitle text.
         * @return this {@link TitleBuilder} instance for method chaining.
         */
        public TitleBuilder setSubtitle(TextElement<?> text) {
            text.setUpdate(() -> toBuild.update());
            this.data.setSubtitle(text);
            return this;
        }

        /**
         * Sets the fade-in time for the title being built.
         *
         * @param fadeIn the {@link GeneralElement} representing the fade-in time in ticks.
         * @return this {@link TitleBuilder} instance for method chaining.
         */
        public TitleBuilder setFadeIn(GeneralElement<Integer, ?> fadeIn) {
            this.data.setFadeIn(fadeIn);
            this.data.getFadeIn().setUpdate(() -> toBuild.update());
            return this;
        }

        /**
         * Sets the fade-out time for the title being built.
         *
         * @param fadeOut the {@link GeneralElement} representing the fade-out time in ticks.
         * @return this {@link TitleBuilder} instance for method chaining.
         */
        public TitleBuilder setFadeOut(GeneralElement<Integer, ?> fadeOut) {
            this.data.setFadeOut(fadeOut);
            this.data.getFadeOut().setUpdate(() -> toBuild.update());
            return this;
        }
    }

    /**
     * Builder class for constructing simple {@link CrispyTitle} instances.
     */
    class SimpleTitleBuilder extends TitleBuilder {

        /**
         * Builds and returns the {@link SimpleTitle} instance.
         *
         * @return the constructed {@link SimpleTitle}.
         */
        public SimpleTitle build() {
            this.toBuild = new SimpleTitle(data, receivers, timeToLive, isPublic);
            return (SimpleTitle) toBuild;
        }
    }

    /**
     * Builder class for constructing {@link UpdatingTitle} instances.
     */
    class UpdatingTitleBuilder extends TitleBuilder {

        /**
         * Builds and returns the {@link UpdatingTitle} instance.
         *
         * @return the constructed {@link UpdatingTitle}.
         */
        public UpdatingTitle build() {
            this.toBuild = new UpdatingTitle(data, receivers, timeToLive, isPublic);
            return (UpdatingTitle) toBuild;
        }
    }
}

