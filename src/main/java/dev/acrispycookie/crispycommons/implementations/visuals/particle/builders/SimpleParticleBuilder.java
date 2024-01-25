package dev.acrispycookie.crispycommons.implementations.visuals.particle.builders;

import dev.acrispycookie.crispycommons.api.visuals.particle.CrispyParticle;
import dev.acrispycookie.crispycommons.implementations.visuals.particle.SimpleParticle;
import dev.acrispycookie.crispycommons.api.wrappers.particle.CrispyEffect;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.builder.AbstractVisualBuilder;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.particles.ParticleElement;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.particles.SimpleParticleElement;
import dev.acrispycookie.crispycommons.implementations.wrappers.particle.SimpleEffect;

import java.util.ArrayList;
import java.util.function.Supplier;

public class SimpleParticleBuilder extends AbstractVisualBuilder<CrispyParticle<SimpleEffect>> {

    private ParticleElement<SimpleEffect> element;
    private long duration, period;

    public SimpleParticleBuilder() {
        this.duration = -1;
        this.period = -1;
    }

    public SimpleParticleBuilder effect(SimpleEffect effect) {
        this.element = new SimpleParticleElement<>(effect);
        return this;
    }

    public SimpleParticleBuilder effect(Supplier<SimpleEffect> supplier, int period) {
        this.element = new ParticleElement<SimpleEffect>(supplier, period) {
            @Override
            protected void update() {

            }
        };
        return this;
    }

    public SimpleParticleBuilder effect(ArrayList<SimpleEffect> frames, int period) {
        this.element = new ParticleElement<SimpleEffect>(frames, period) {
            @Override
            protected void update() {

            }
        };
        return this;
    }

    public SimpleParticleBuilder duration(long duration) {
        this.duration = duration;
        return this;
    }

    public SimpleParticleBuilder period(long period) {
        this.period = period;
        return this;
    }

    public CrispyParticle<SimpleEffect> build() {
        if (element == null)
            return null;
        if (duration < 0 || period < 1)
            return new SimpleParticle(element, receivers);
        else
            return new SimpleParticle(element, duration, period, receivers);
    }
}
