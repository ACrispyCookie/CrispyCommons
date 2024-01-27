package dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.implementations.particles;

import dev.acrispycookie.crispycommons.api.wrappers.particle.CrispyEffect;
import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.AnimatedElement;
import dev.acrispycookie.crispycommons.implementations.wrappers.particle.SimpleEffect;

import java.util.ArrayList;
import java.util.function.Supplier;

public abstract class ParticleElement<T extends CrispyEffect> extends AnimatedElement<T> {
    protected ParticleElement(ArrayList<T> frames, int period) {
        super(new ArrayList<>(frames), period, false);
    }

    protected ParticleElement(Supplier<T> supplier, int period) {
        super(supplier, period, false);
    }

    protected ParticleElement(T effect) {
        this(() -> effect, -1);
        setUpdate(() -> {});
    }

    public static <K extends CrispyEffect> ParticleElement<K> simple(K effect) {
        return new ParticleElement<K>(effect) {};
    }

    public static <K extends CrispyEffect> ParticleElement<K> animated(ArrayList<K> frames, int period) {
        return new ParticleElement<K>(frames, period) {};
    }

    public static <K extends CrispyEffect> ParticleElement<K> dynamic(Supplier<K> supplier, int period) {
        return new ParticleElement<K>(supplier, period) {};
    }
}
