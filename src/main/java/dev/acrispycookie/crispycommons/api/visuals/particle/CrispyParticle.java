package dev.acrispycookie.crispycommons.api.visuals.particle;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.visual.CrispyVisual;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.types.GeneralElement;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.types.ParticleElement;
import dev.acrispycookie.crispycommons.api.wrappers.particle.Effect;
import dev.acrispycookie.crispycommons.implementations.visuals.abstraction.builder.AbstractVisualBuilder;
import dev.acrispycookie.crispycommons.implementations.visuals.particle.ColoredParticle;
import dev.acrispycookie.crispycommons.implementations.visuals.particle.RenderedParticle;
import dev.acrispycookie.crispycommons.implementations.visuals.particle.SimpleParticle;
import dev.acrispycookie.crispycommons.implementations.visuals.particle.wrappers.ParticleData;
import dev.acrispycookie.crispycommons.implementations.wrappers.particle.ColoredEffect;
import dev.acrispycookie.crispycommons.implementations.wrappers.particle.RenderedEffect;
import dev.acrispycookie.crispycommons.implementations.wrappers.particle.SimpleEffect;
import org.bukkit.Location;

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
    void setElement(ParticleElement<T, ?> element);
    ParticleElement<T, ?> getElement();
    void setLocation(GeneralElement<Location, ?> location);
    GeneralElement<Location, ?> getLocation();

    abstract class ParticleBuilder<T extends Effect> extends AbstractVisualBuilder<CrispyParticle<T>> {

        protected final ParticleData<T> data = new ParticleData<>(null, null);

        public ParticleBuilder<T> setParticle(ParticleElement<T, ?> element) {
            data.setElement(element);
            this.data.getElement().setUpdate(() -> toBuild.update());
            return this;
        }

        public ParticleBuilder<T> setLocation(GeneralElement<Location, ?> location) {
            data.setLocation(location);
            this.data.getLocation().setUpdate(() -> toBuild.update());
            return this;
        }
    }

    class SimpleParticleBuilder extends ParticleBuilder<SimpleEffect> {
        public CrispyParticle<SimpleEffect> build() {
            toBuild = new SimpleParticle(data, receivers, timeToLive);
            return toBuild;
        }
    }

    class ColoredParticleBuilder extends ParticleBuilder<ColoredEffect> {
        public CrispyParticle<ColoredEffect> build() {
            toBuild = new ColoredParticle(data, receivers, timeToLive);
            return toBuild;
        }
    }

    class RenderedParticleBuilder extends ParticleBuilder<RenderedEffect> {
        public CrispyParticle<RenderedEffect> build() {
            toBuild = new RenderedParticle(data, receivers, timeToLive);
            return toBuild;
        }
    }
}
