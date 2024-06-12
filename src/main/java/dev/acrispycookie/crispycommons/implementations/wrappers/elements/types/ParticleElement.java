package dev.acrispycookie.crispycommons.implementations.wrappers.elements.types;

import dev.acrispycookie.crispycommons.api.wrappers.particle.Effect;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.AnimatedElement;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.GlobalAnimatedElement;
import org.bukkit.OfflinePlayer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class ParticleElement<T extends Effect> extends AnimatedElement<T> {
    protected ParticleElement(Map<OfflinePlayer, Collection<? extends T>> frames, int period) {
        super(frames, period, false);
    }

    protected ParticleElement(Function<OfflinePlayer, T> supplier, int period) {
        super(supplier, period, false);
    }

    protected ParticleElement(Map<OfflinePlayer, T> effects) {
        this(effects::get, -1);
        setUpdate(() -> {});
    }

    @Override
    public ParticleElement<T> clone() {
        if (isDynamic())
            return dynamic(this::getRaw, getPeriod());
        return simple(getRaw());
    }
    public static <K extends Effect> ParticleElement<K> simple(Map<OfflinePlayer, K> effect) {
        return new ParticleElement<K>(effect) {};
    }

    public static <K extends Effect> ParticleElement<K> animated(Map<OfflinePlayer, Collection<? extends K>> frames, int period) {
        return new ParticleElement<K>(frames, period) {};
    }

    public static <K extends Effect> ParticleElement<K> dynamic(Function<OfflinePlayer, K> supplier, int period) {
        return new ParticleElement<K>(supplier, period) {};
    }
}
