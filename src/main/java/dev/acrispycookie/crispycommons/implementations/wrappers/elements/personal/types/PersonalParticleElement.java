package dev.acrispycookie.crispycommons.implementations.wrappers.elements.personal.types;

import dev.acrispycookie.crispycommons.api.wrappers.elements.types.ParticleElement;
import dev.acrispycookie.crispycommons.api.wrappers.particle.Effect;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.personal.PersonalAnimatedElement;
import org.bukkit.OfflinePlayer;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;

public abstract class PersonalParticleElement<T extends Effect> extends PersonalAnimatedElement<T> implements ParticleElement<T> {
    protected PersonalParticleElement(Map<OfflinePlayer, Collection<? extends T>> frames, int period) {
        super(frames, period, false);
    }

    protected PersonalParticleElement(Function<OfflinePlayer, T> supplier, int period) {
        super(supplier, period, false);
    }

    protected PersonalParticleElement(Map<OfflinePlayer, T> effects) {
        this(effects::get, 1);
    }

    @Override
    public PersonalParticleElement<T> clone() {
        if (isDynamic())
            return dynamic(this::getRaw, getPeriod());
        return simple(getAllPlayers());
    }
    public static <K extends Effect> PersonalParticleElement<K> simple(Map<OfflinePlayer, K> effect) {
        return new PersonalParticleElement<K>(effect) {};
    }

    public static <K extends Effect> PersonalParticleElement<K> animated(Map<OfflinePlayer, Collection<? extends K>> frames, int period) {
        return new PersonalParticleElement<K>(frames, period) {};
    }

    public static <K extends Effect> PersonalParticleElement<K> dynamic(Function<OfflinePlayer, K> supplier, int period) {
        return new PersonalParticleElement<K>(supplier, period) {};
    }
}
