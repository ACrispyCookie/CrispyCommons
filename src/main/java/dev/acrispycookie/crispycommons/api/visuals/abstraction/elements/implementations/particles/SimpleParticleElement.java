package dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.particles;

import dev.acrispycookie.crispycommons.api.wrappers.particle.CrispyEffect;
import dev.acrispycookie.crispycommons.implementations.wrappers.particle.ColoredEffect;
import dev.acrispycookie.crispycommons.implementations.wrappers.particle.SimpleEffect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Supplier;

public class SimpleParticleElement<T extends CrispyEffect> extends ParticleElement<T> {

    public SimpleParticleElement(T effect) {
        super(new ArrayList<>(Collections.singleton(effect)), -1);
    }

    public SimpleParticleElement(ArrayList<T> frames, int period) {
        super(frames, period);
    }

    public SimpleParticleElement(Supplier<T> supplier, int period) {
        super(supplier, period);
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    protected void update() {

    }
}
