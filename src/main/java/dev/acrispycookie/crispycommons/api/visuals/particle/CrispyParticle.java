package dev.acrispycookie.crispycommons.api.visuals.particle;

import dev.acrispycookie.crispycommons.implementations.visuals.abstraction.builder.AbstractVisualBuilder;
import dev.acrispycookie.crispycommons.api.wrappers.particle.Effect;
import dev.acrispycookie.crispycommons.implementations.visuals.abstraction.elements.types.ParticleElement;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.CrispyVisual;
import dev.acrispycookie.crispycommons.implementations.visuals.particle.ColoredParticle;
import dev.acrispycookie.crispycommons.implementations.visuals.particle.RenderedParticle;
import dev.acrispycookie.crispycommons.implementations.visuals.particle.SimpleParticle;
import dev.acrispycookie.crispycommons.implementations.visuals.particle.wrappers.ParticleData;
import dev.acrispycookie.crispycommons.implementations.wrappers.particle.ColoredEffect;
import dev.acrispycookie.crispycommons.implementations.wrappers.particle.RenderedEffect;
import dev.acrispycookie.crispycommons.implementations.wrappers.particle.SimpleEffect;

public interface CrispyParticle<T extends Effect> extends CrispyVisual {

    static SimpleParticleBuilder simpleBuilder() {
        return new SimpleParticleBuilder();
    }
    static ColoredParticleBuilder coloredBuilder() {
        return new ColoredParticleBuilder();
    }
    static RenderedParticleBuilder renderedBuilder() {
        return new RenderedParticleBuilder();
    }
    void setElement(ParticleElement<T> element);
    ParticleElement<T> getElement();

    abstract class ParticleBuilder<T extends Effect> extends AbstractVisualBuilder<CrispyParticle<?>> {

        protected final ParticleData<T> data = new ParticleData<>(null);

        public ParticleBuilder<T> setParticle(ParticleElement<T> element) {
            data.setElement(element);
            return this;
        }
    }

    class SimpleParticleBuilder extends ParticleBuilder<SimpleEffect> {
        public CrispyParticle<SimpleEffect> build() {
            return new SimpleParticle(data, receivers, timeToLive);
        }
    }

    class ColoredParticleBuilder extends ParticleBuilder<ColoredEffect> {
        public CrispyParticle<ColoredEffect> build() {
            return new ColoredParticle(data, receivers, timeToLive);
        }
    }

    class RenderedParticleBuilder extends ParticleBuilder<RenderedEffect> {
        public CrispyParticle<RenderedEffect> build() {
            return new RenderedParticle(data, receivers, timeToLive);
        }
    }
}
