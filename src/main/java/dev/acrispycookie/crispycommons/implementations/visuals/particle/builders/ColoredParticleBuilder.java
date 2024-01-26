package dev.acrispycookie.crispycommons.implementations.visuals.particle.builders;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.builder.AbstractVisualBuilder;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.particles.ParticleElement;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.particles.SimpleParticleElement;
import dev.acrispycookie.crispycommons.api.visuals.particle.CrispyParticle;
import dev.acrispycookie.crispycommons.implementations.visuals.particle.ColoredParticle;
import dev.acrispycookie.crispycommons.implementations.visuals.particle.wrappers.ParticleData;
import dev.acrispycookie.crispycommons.implementations.wrappers.particle.ColoredEffect;
import dev.acrispycookie.crispycommons.implementations.wrappers.particle.RenderedEffect;

import java.util.ArrayList;
import java.util.function.Supplier;

public class ColoredParticleBuilder extends AbstractVisualBuilder<CrispyParticle<ColoredEffect>> {

    private ParticleElement<ColoredEffect> element;
    private long duration, period;

    public ColoredParticleBuilder() {
        this.duration = -1;
        this.period = -1;
    }

    public ColoredParticleBuilder effect(ColoredEffect effect) {
        this.element = new SimpleParticleElement<>(effect);
        return this;
    }

    public ColoredParticleBuilder effect(Supplier<ColoredEffect> supplier, int period) {
        this.element = new ParticleElement<ColoredEffect>(supplier, period) {
            @Override
            protected void update() {

            }
        };
        return this;
    }

    public ColoredParticleBuilder effect(ArrayList<ColoredEffect> frames, int period) {
        this.element = new ParticleElement<ColoredEffect>(frames, period) {
            @Override
            protected void update() {

            }
        };
        return this;
    }

    public ColoredParticleBuilder duration(long duration) {
        this.duration = duration;
        return this;
    }

    public ColoredParticleBuilder period(long period) {
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
