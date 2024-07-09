package dev.acrispycookie.crispycommons.implementations.wrappers.elements.personal.types;

import dev.acrispycookie.crispycommons.api.wrappers.elements.types.ParticleElement;
import dev.acrispycookie.crispycommons.api.wrappers.particle.Effect;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.personal.PersonalAnimatedElement;
import dev.acrispycookie.crispycommons.utility.elements.TSupplier;
import org.bukkit.OfflinePlayer;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;

public abstract class PersonalParticleElement<T extends Effect> extends PersonalAnimatedElement<T> implements ParticleElement<T> {
    protected PersonalParticleElement(Function<OfflinePlayer, Collection<? extends T>> supplier, int period) {
        super(supplier, period, false);
    }

    protected PersonalParticleElement(TSupplier<T> supplier, int period) {
        super(supplier, period, false);
    }

    @Override
    public PersonalParticleElement<T> clone() {
        if (isDynamic())
            return dynamic(this::getRaw, getPeriod());
        return simple(this::getRaw);
    }
    public static <K extends Effect> PersonalParticleElement<K> simple(Function<OfflinePlayer, K> effect) {
        return new PersonalParticleElement<K>(new TSupplier<>(effect), 1) {};
    }

    public static <K extends Effect> PersonalParticleElement<K> animated(Function<OfflinePlayer, Collection<? extends K>> frames, int period) {
        return new PersonalParticleElement<K>(frames, period) {};
    }

    public static <K extends Effect> PersonalParticleElement<K> dynamic(Function<OfflinePlayer, K> supplier, int period) {
        return new PersonalParticleElement<K>(new TSupplier<>(supplier), period) {};
    }
}
