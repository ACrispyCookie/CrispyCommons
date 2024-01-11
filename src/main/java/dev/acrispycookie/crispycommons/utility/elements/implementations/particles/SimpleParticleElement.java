package dev.acrispycookie.crispycommons.utility.elements.implementations.particles;

import dev.acrispycookie.crispycommons.implementations.wrappers.particle.CrispyEffect;

import java.util.ArrayList;
import java.util.Collections;

public class SimpleParticleElement<T extends CrispyEffect> extends ParticleElement<T> {

    public SimpleParticleElement(T effect) {
        super(new ArrayList<>(Collections.singleton(effect)), -1, false);
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
