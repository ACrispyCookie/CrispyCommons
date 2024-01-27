package dev.acrispycookie.crispycommons.implementations.visuals.particle.builders;

import dev.acrispycookie.crispycommons.api.visuals.particle.CrispyParticle;
import dev.acrispycookie.crispycommons.implementations.visuals.particle.SimpleParticle;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.builder.AbstractVisualBuilder;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.particles.ParticleElement;
import dev.acrispycookie.crispycommons.implementations.visuals.particle.wrappers.ParticleData;
import dev.acrispycookie.crispycommons.implementations.wrappers.particle.SimpleEffect;

public class SimpleParticleBuilder extends AbstractVisualBuilder<CrispyParticle<SimpleEffect>> {

    private ParticleElement<SimpleEffect> element;
    private long duration, period;

    public SimpleParticleBuilder() {
        this.duration = -1;
        this.period = -1;
    }

    public SimpleParticleBuilder setParticle(ParticleElement<SimpleEffect> element) {
        this.element = element;
        this.element.setUpdate(() -> {});
        return this;
    }

    public SimpleParticleBuilder setDuration(long duration) {
        this.duration = duration;
        return this;
    }

    public SimpleParticleBuilder setPeriod(long period) {
        this.period = period;
        return this;
    }

    public CrispyParticle<SimpleEffect> build() {
        if (element == null)
            return null;
        ParticleData<SimpleEffect> data = new ParticleData<>(element, duration, period);
        return new SimpleParticle(data, receivers);
    }
}
