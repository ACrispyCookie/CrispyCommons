package dev.acrispycookie.crispycommons.implementations.wrappers.elements.types;

import dev.acrispycookie.crispycommons.api.wrappers.particle.Effect;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.AnimatedElement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Supplier;

public abstract class ParticleElement<T extends Effect> extends AnimatedElement<T> {
    protected ParticleElement(Collection<? extends T> frames, int period) {
        super(new ArrayList<>(frames), period, false);
    }

    protected ParticleElement(Supplier<T> supplier, int period) {
        super(supplier, period, false);
    }

    protected ParticleElement(T effect) {
        this(() -> effect, -1);
        setUpdate(() -> {});
    }

    @Override
    public ParticleElement<T> clone() {
        if (isDynamic())
            return dynamic(this::getRaw, getPeriod());
        return simple(getRaw());
    }
    public static <K extends Effect> ParticleElement<K> simple(K effect) {
        return new ParticleElement<K>(effect) {};
    }

    public static <K extends Effect> ParticleElement<K> animated(Collection<? extends K> frames, int period) {
        return new ParticleElement<K>(frames, period) {};
    }

    public static <K extends Effect> ParticleElement<K> dynamic(Supplier<K> supplier, int period) {
        return new ParticleElement<K>(supplier, period) {};
    }
}
