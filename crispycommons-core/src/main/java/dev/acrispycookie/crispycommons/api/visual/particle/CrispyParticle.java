package dev.acrispycookie.crispycommons.api.visual.particle;

import dev.acrispycookie.crispycommons.api.visual.abstraction.visual.CrispyVisual;
import dev.acrispycookie.crispycommons.api.particle.Effect;
import dev.acrispycookie.crispycommons.implementations.visual.abstraction.builder.AbstractVisualBuilder;
import dev.acrispycookie.crispycommons.implementations.visual.particle.ColoredParticle;
import dev.acrispycookie.crispycommons.implementations.visual.particle.RenderedParticle;
import dev.acrispycookie.crispycommons.implementations.visual.particle.SimpleParticle;
import dev.acrispycookie.crispycommons.implementations.visual.particle.data.ParticleData;
import dev.acrispycookie.crispycommons.implementations.element.type.GeneralElement;
import dev.acrispycookie.crispycommons.implementations.element.type.ParticleElement;
import dev.acrispycookie.crispycommons.implementations.particle.ColoredEffect;
import dev.acrispycookie.crispycommons.implementations.particle.RenderedEffect;
import dev.acrispycookie.crispycommons.implementations.particle.SimpleEffect;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Represents a customizable particle effect in the game.
 * <p>
 * The {@code CrispyParticle} interface provides methods to set and retrieve the particle effect element
 * and its location. It also includes builder classes for constructing different types of particle effects,
 * such as simple, colored, and rendered particles.
 * </p>
 *
 * @param <T> the type of particle effect, extending {@link Effect}.
 */
public interface CrispyParticle<T extends Effect> extends CrispyVisual {

    /**
     * Creates a new {@link SimpleParticleBuilder} instance for constructing a simple particle effect.
     *
     * @return a new {@link SimpleParticleBuilder} instance.
     */
    static @NotNull SimpleParticleBuilder simpleBuilder() {
        return new SimpleParticleBuilder();
    }

    /**
     * Creates a new {@link ColoredParticleBuilder} instance for constructing a colored particle effect.
     *
     * @return a new {@link ColoredParticleBuilder} instance.
     */
    static @NotNull ColoredParticleBuilder coloredBuilder() {
        return new ColoredParticleBuilder();
    }

    /**
     * Creates a new {@link RenderedParticleBuilder} instance for constructing a rendered particle effect.
     *
     * @return a new {@link RenderedParticleBuilder} instance.
     */
    static @NotNull RenderedParticleBuilder renderedBuilder() {
        return new RenderedParticleBuilder();
    }

    /**
     * Sets the particle effect element for this particle.
     *
     * @param element the {@link ParticleElement} representing the particle effect.
     */
    void setElement(@NotNull ParticleElement<T, ?> element);

    /**
     * Retrieves the particle effect element of this particle.
     *
     * @return the {@link ParticleElement} representing the particle effect.
     */
    @Nullable ParticleElement<T, ?> getElement();

    /**
     * Sets the location of the particle effect.
     *
     * @param location the {@link GeneralElement} representing the location of the particle effect.
     */
    void setLocation(@NotNull GeneralElement<Location, ?> location);

    /**
     * Retrieves the location of the particle effect.
     *
     * @return the {@link GeneralElement} representing the location of the particle effect.
     */
    @Nullable GeneralElement<Location, ?> getLocation();

    /**
     * Abstract builder class for constructing instances of {@link CrispyParticle}.
     *
     * @param <T> the type of particle effect, extending {@link Effect}.
     */
    abstract class ParticleBuilder<T extends Effect> extends AbstractVisualBuilder<CrispyParticle<T>> {

        /**
         * The data object to be used to build the particle instance.
         */
        protected final ParticleData<T> data = new ParticleData<>(null, null);

        /**
         * Sets the particle effect element for this particle.
         *
         * @param element the {@link ParticleElement} representing the particle effect.
         * @return this {@link ParticleBuilder} instance for method chaining.
         */
        public @NotNull ParticleBuilder<T> setParticle(@NotNull ParticleElement<T, ?> element) {
            data.setElement(element);
            data.getElement().setUpdate(() -> toBuild.update());
            return this;
        }

        /**
         * Sets the location of the particle effect.
         *
         * @param location the {@link GeneralElement} representing the location of the particle effect.
         * @return this {@link ParticleBuilder} instance for method chaining.
         */
        public @NotNull ParticleBuilder<T> setLocation(@NotNull GeneralElement<Location, ?> location) {
            data.setLocation(location);
            data.getLocation().setUpdate(() -> toBuild.update());
            return this;
        }
    }

    /**
     * Builder class for constructing simple particle effects.
     */
    class SimpleParticleBuilder extends ParticleBuilder<SimpleEffect> {
        /**
         * Builds and returns the {@link CrispyParticle} instance for a simple particle effect.
         *
         * @return the constructed {@link CrispyParticle} with a {@link SimpleEffect}.
         */
        public @NotNull CrispyParticle<SimpleEffect> build() {
            toBuild = new SimpleParticle(data, receivers, timeToLive, isPublic);
            return toBuild;
        }
    }

    /**
     * Builder class for constructing colored particle effects.
     */
    class ColoredParticleBuilder extends ParticleBuilder<ColoredEffect> {
        /**
         * Builds and returns the {@link CrispyParticle} instance for a colored particle effect.
         *
         * @return the constructed {@link CrispyParticle} with a {@link ColoredEffect}.
         */
        public @NotNull CrispyParticle<ColoredEffect> build() {
            toBuild = new ColoredParticle(data, receivers, timeToLive, isPublic);
            return toBuild;
        }
    }

    /**
     * Builder class for constructing rendered particle effects.
     */
    class RenderedParticleBuilder extends ParticleBuilder<RenderedEffect> {
        /**
         * Builds and returns the {@link CrispyParticle} instance for a rendered particle effect.
         *
         * @return the constructed {@link CrispyParticle} with a {@link RenderedEffect}.
         */
        public @NotNull CrispyParticle<RenderedEffect> build() {
            toBuild = new RenderedParticle(data, receivers, timeToLive, isPublic);
            return toBuild;
        }
    }
}

