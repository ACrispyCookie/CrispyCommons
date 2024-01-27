package dev.acrispycookie.crispycommons.implementations.visuals.particle.builders;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.builder.AbstractVisualBuilder;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.particles.ParticleElement;
import dev.acrispycookie.crispycommons.api.visuals.particle.CrispyParticle;
import dev.acrispycookie.crispycommons.implementations.visuals.particle.ColoredParticle;
import dev.acrispycookie.crispycommons.implementations.visuals.particle.wrappers.ParticleData;
import dev.acrispycookie.crispycommons.implementations.wrappers.particle.ColoredEffect;

public class ColoredParticleBuilder extends AbstractVisualBuilder<CrispyParticle<ColoredEffect>> {

    private ParticleElement<ColoredEffect> element;
    private long duration, period;

    public ColoredParticleBuilder() {
        this.duration = -1;
        this.period = -1;
    }

    public ColoredParticleBuilder setParticle(ParticleElement<ColoredEffect> element) {
        this.element = element;
        this.element.setUpdate(() -> {});
        return this;
    }

    public ColoredParticleBuilder setDuration(long duration) {
        this.duration = duration;
        return this;
    }

    public ColoredParticleBuilder setPeriod(long period) {
        this.period = period;
        return this;
    }

    public CrispyParticle<ColoredEffect> build() {
        if (element == null)
            return null;
        ParticleData<ColoredEffect> data = new ParticleData<>(element, duration, period);
        return new ColoredParticle(data, receivers);
    }
}
