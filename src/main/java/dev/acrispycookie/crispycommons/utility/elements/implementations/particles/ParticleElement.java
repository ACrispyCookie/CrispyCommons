package dev.acrispycookie.crispycommons.utility.elements.implementations.particles;

import dev.acrispycookie.crispycommons.implementations.wrappers.particle.CrispyEffect;
import dev.acrispycookie.crispycommons.utility.elements.AnimatedElement;

import java.util.ArrayList;
import java.util.function.Supplier;

public abstract class ParticleElement<T extends CrispyEffect> extends AnimatedElement<T> {
    public ParticleElement(ArrayList<T> frames, int period, boolean async) {
        super(new ArrayList<>(frames), period, async);
    }

    public ParticleElement(Supplier<T> supplier, int period, boolean async) {
        super(supplier, period, async);
    }
}
