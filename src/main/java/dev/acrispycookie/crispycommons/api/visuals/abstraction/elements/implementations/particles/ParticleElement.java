package dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.particles;

import dev.acrispycookie.crispycommons.api.wrappers.particle.CrispyEffect;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.AnimatedElement;

import java.util.ArrayList;
import java.util.function.Supplier;

public abstract class ParticleElement<T extends CrispyEffect> extends AnimatedElement<T> {
    public ParticleElement(ArrayList<T> frames, int period) {
        super(new ArrayList<>(frames), period, false);
    }

    public ParticleElement(Supplier<T> supplier, int period) {
        super(supplier, period, false);
    }
}
