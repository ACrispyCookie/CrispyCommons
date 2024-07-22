package dev.acrispycookie.crispycommons.implementations.wrappers.elements.types;

import dev.acrispycookie.crispycommons.api.wrappers.particle.Effect;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.AbstractAnimatedElement;
import dev.acrispycookie.crispycommons.utility.elements.MyElementSupplier;
import org.bukkit.OfflinePlayer;

import java.util.Collection;
import java.util.function.Function;
import java.util.function.Supplier;

public class ParticleElement<T extends Effect, K> extends AbstractAnimatedElement<T, K> {

    protected ParticleElement(Function<K, Collection<? extends T>> supplier, int startingFrame, int delay, int period, Class<K> kClass) {
        super(supplier, startingFrame, delay, period, false, kClass);
    }

    protected ParticleElement(MyElementSupplier<K, T> supplier, int delay, int period, Class<K> kClass) {
        super(supplier, delay, period, false, kClass);
        if (period < 0)
            setUpdate(() -> {});
    }

    @Override
    public ParticleElement<T, K> clone() {
        if (isDynamic())
            return new ParticleElement<>(new MyElementSupplier<>(this::getRaw), getDelay(), getPeriod(), getContextClass());
        return new ParticleElement<>(new MyElementSupplier<>(this::getRaw), -1, -1, getContextClass());
    }

    public static <T extends Effect> ParticleElement<T, Void> simple(T value) {
        return dynamic(() -> value, -1, -1);
    }

    public static <T extends Effect> ParticleElement<T, Void> dynamic(Supplier<? extends T> supplier, int delay, int period) {
        return new ParticleElement<>(new MyElementSupplier<>((v) -> supplier.get()), delay, period, Void.class);
    }

    public static <T extends Effect> ParticleElement<T, Void> animated(Collection<? extends T> collection, int startingFrame, int delay, int period) {
        return new ParticleElement<>((v) -> collection, startingFrame, delay, period, Void.class);
    }

    public static <T extends Effect> ParticleElement<T, OfflinePlayer> simplePersonal(Function<OfflinePlayer, ? extends T> function) {
        return dynamicPersonal(function, -1, -1);
    }

    public static <T extends Effect> ParticleElement<T, OfflinePlayer> dynamicPersonal(Function<OfflinePlayer, ? extends T> supplier, int delay, int period) {
        return new ParticleElement<>(new MyElementSupplier<>(supplier), delay, period, OfflinePlayer.class);
    }

    public static <T extends Effect> ParticleElement<T, OfflinePlayer> animatedPersonal(Function<OfflinePlayer, Collection<? extends T>> supplier, int startingFrame, int delay, int period) {
        return new ParticleElement<>(supplier, startingFrame, delay, period, OfflinePlayer.class);
    }
}
