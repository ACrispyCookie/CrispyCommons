package dev.acrispycookie.crispycommons.implementations.visuals.particle.builders;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.builder.AbstractVisualBuilder;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.particles.ParticleElement;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.particles.SimpleParticleElement;
import dev.acrispycookie.crispycommons.api.visuals.particle.CrispyParticle;
import dev.acrispycookie.crispycommons.implementations.visuals.particle.RenderedParticle;
import dev.acrispycookie.crispycommons.implementations.wrappers.particle.RenderedEffect;

public class RenderedParticleBuilder extends AbstractVisualBuilder<CrispyParticle<RenderedEffect>> {

    private ParticleElement<RenderedEffect> element;
    private long duration, period;

    public RenderedParticleBuilder() {
        this.duration = -1;
        this.period = -1;
    }

    public RenderedParticleBuilder effect(RenderedEffect effect) {
        this.element = new SimpleParticleElement<>(effect);
        return this;
    }

    public RenderedParticleBuilder duration(long duration) {
        this.duration = duration;
        return this;
    }

    public RenderedParticleBuilder period(long period) {
        this.period = period;
        return this;
    }

    public CrispyParticle<RenderedEffect> build() {
        if (element == null)
            return null;
        if (duration < 0 || period < 1)
            return new RenderedParticle(element, receivers);
        else
            return new RenderedParticle(element, duration, period, receivers);
    }
}
