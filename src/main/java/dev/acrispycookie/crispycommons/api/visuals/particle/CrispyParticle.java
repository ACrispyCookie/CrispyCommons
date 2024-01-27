package dev.acrispycookie.crispycommons.api.visuals.particle;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.builder.AbstractVisualBuilder;
import dev.acrispycookie.crispycommons.api.wrappers.particle.CrispyEffect;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.particles.ParticleElement;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.CrispyVisual;
import dev.acrispycookie.crispycommons.implementations.visuals.particle.ColoredParticle;
import dev.acrispycookie.crispycommons.implementations.visuals.particle.RenderedParticle;
import dev.acrispycookie.crispycommons.implementations.visuals.particle.SimpleParticle;
import dev.acrispycookie.crispycommons.implementations.visuals.particle.wrappers.ParticleData;
import dev.acrispycookie.crispycommons.implementations.wrappers.particle.ColoredEffect;
import dev.acrispycookie.crispycommons.implementations.wrappers.particle.RenderedEffect;
import dev.acrispycookie.crispycommons.implementations.wrappers.particle.SimpleEffect;

public interface CrispyParticle extends CrispyVisual {

    static SimpleParticleBuilder simpleBuilder() {
        return new SimpleParticleBuilder();
    }
    static ColoredParticleBuilder coloredBuilder() {
        return new ColoredParticleBuilder();
    }
    static RenderedParticleBuilder renderedBuilder() {
        return new RenderedParticleBuilder();
    }
    long getDuration();
    void setDuration(long duration);
    long getPeriod();
    void setPeriod(long period);

    abstract class ParticleBuilder<T extends CrispyEffect> extends AbstractVisualBuilder<CrispyParticle> {

        protected final ParticleData<T> data = new ParticleData<>(null, 0, -1);

        public ParticleBuilder<T> setParticle(ParticleElement<T> element) {
            data.setElement(element);
            return this;
        }

        public ParticleBuilder<T> setDuration(long duration) {
            data.setDuration(duration);
            return this;
        }

        public ParticleBuilder<T> setPeriod(long period) {
            data.setPeriod(period);
            return this;
        }
    }

    class SimpleParticleBuilder extends ParticleBuilder<SimpleEffect> {
        public CrispyParticle build() {
            return new SimpleParticle(data, receivers, timeToLive);
        }
    }

    class ColoredParticleBuilder extends ParticleBuilder<ColoredEffect> {
        public CrispyParticle build() {
            return new ColoredParticle(data, receivers, timeToLive);
        }
    }

    class RenderedParticleBuilder extends ParticleBuilder<RenderedEffect> {
        public CrispyParticle build() {
            return new RenderedParticle(data, receivers, timeToLive);
        }
    }
}
