package dev.acrispycookie.crispycommons.implementations.visuals.particle.builders;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.builder.AbstractVisualBuilder;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.particles.ParticleElement;
import dev.acrispycookie.crispycommons.api.visuals.particle.CrispyParticle;
import dev.acrispycookie.crispycommons.implementations.visuals.particle.RenderedParticle;
import dev.acrispycookie.crispycommons.implementations.visuals.particle.wrappers.ParticleData;
import dev.acrispycookie.crispycommons.implementations.wrappers.particle.RenderedEffect;

public class RenderedParticleBuilder extends AbstractVisualBuilder<CrispyParticle<RenderedEffect>> {

    private ParticleElement<RenderedEffect> element;
    private long duration, period;

    public RenderedParticleBuilder() {
        this.duration = -1;
        this.period = -1;
    }

    public RenderedParticleBuilder setParticle(ParticleElement<RenderedEffect> element) {
        this.element = element;
        this.element.setUpdate(() -> {});
        return this;
    }

    public RenderedParticleBuilder setDuration(long duration) {
        this.duration = duration;
        return this;
    }

    public RenderedParticleBuilder setPeriod(long period) {
        this.period = period;
        return this;
    }

    public CrispyParticle<RenderedEffect> build() {
        if (element == null)
            return null;
        ParticleData<RenderedEffect> data = new ParticleData<>(element, duration, period);
        return new RenderedParticle(data, receivers);
    }
}
