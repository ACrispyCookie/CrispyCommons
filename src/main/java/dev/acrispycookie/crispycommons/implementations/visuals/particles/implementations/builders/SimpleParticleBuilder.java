package dev.acrispycookie.crispycommons.implementations.visuals.particles.implementations.builders;

import dev.acrispycookie.crispycommons.implementations.visuals.particles.CrispyParticle;
import dev.acrispycookie.crispycommons.implementations.visuals.particles.implementations.SimpleCrispyParticle;
import dev.acrispycookie.crispycommons.implementations.wrappers.particle.CrispyEffect;
import dev.acrispycookie.crispycommons.utility.builder.AbstractVisualBuilder;
import dev.acrispycookie.crispycommons.utility.elements.implementations.particles.ParticleElement;
import dev.acrispycookie.crispycommons.utility.elements.implementations.particles.SimpleParticleElement;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

public class SimpleParticleBuilder<T extends CrispyEffect> extends AbstractVisualBuilder<CrispyParticle<T>> {

    private ParticleElement<T> element;
    private long duration, period;

    public SimpleParticleBuilder() {
        this.duration = -1;
        this.period = -1;
    }

    public SimpleParticleBuilder<T> effect(T effect) {
        this.element = new SimpleParticleElement<>(effect);
        return this;
    }

    public SimpleParticleBuilder<T> effect(Supplier<T> supplier, int period) {
        this.element = new ParticleElement<T>(supplier, period, false) {
            @Override
            protected void update() {

            }
        };
        return this;
    }

    public SimpleParticleBuilder<T> effect(ArrayList<T> frames, int period) {
        this.element = new ParticleElement<T>(frames, period, false) {
            @Override
            protected void update() {

            }
        };
        return this;
    }

    public SimpleParticleBuilder<T> duration(long duration) {
        this.duration = duration;
        return this;
    }

    public SimpleParticleBuilder<T> period(long period) {
        this.period = period;
        return this;
    }

    public CrispyParticle<T> build() {
        if (element == null)
            return null;
        if (duration < 0 || period < 1)
            return new SimpleCrispyParticle<>(element, receivers);
        else
            return new SimpleCrispyParticle<>(element, duration, period, receivers);
    }
}
