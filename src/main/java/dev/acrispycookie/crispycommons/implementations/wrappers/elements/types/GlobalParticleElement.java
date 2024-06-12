package dev.acrispycookie.crispycommons.implementations.wrappers.elements.types;

import dev.acrispycookie.crispycommons.api.wrappers.particle.Effect;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.GlobalAnimatedElement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Supplier;

public abstract class GlobalParticleElement<T extends Effect> extends GlobalAnimatedElement<T> {
    protected GlobalParticleElement(Collection<? extends T> frames, int period) {
        super(new ArrayList<>(frames), period, false);
    }

    protected GlobalParticleElement(Supplier<T> supplier, int period) {
        super(supplier, period, false);
    }

    protected GlobalParticleElement(T effect) {
        this(() -> effect, -1);
        setUpdate(() -> {});
    }

    @Override
    public GlobalParticleElement<T> clone() {
        if (isDynamic())
            return dynamic(this::getRaw, getPeriod());
        return simple(getRaw());
    }
    public static <K extends Effect> GlobalParticleElement<K> simple(K effect) {
        return new GlobalParticleElement<K>(effect) {};
    }

    public static <K extends Effect> GlobalParticleElement<K> animated(Collection<? extends K> frames, int period) {
        return new GlobalParticleElement<K>(frames, period) {};
    }

    public static <K extends Effect> GlobalParticleElement<K> dynamic(Supplier<K> supplier, int period) {
        return new GlobalParticleElement<K>(supplier, period) {};
    }
}
