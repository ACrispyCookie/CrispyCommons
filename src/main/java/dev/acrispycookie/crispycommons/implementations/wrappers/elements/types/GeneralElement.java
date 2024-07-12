package dev.acrispycookie.crispycommons.implementations.wrappers.elements.types;

import dev.acrispycookie.crispycommons.implementations.wrappers.elements.AbstractAnimatedElement;
import dev.acrispycookie.crispycommons.utility.elements.MyElementSupplier;
import org.bukkit.OfflinePlayer;

import java.util.Collection;
import java.util.function.Function;
import java.util.function.Supplier;

public class GeneralElement<T, K> extends AbstractAnimatedElement<T, K> {

    protected GeneralElement(Function<K, Collection<? extends T>> supplier, int period, boolean async, Class<K> kClass) {
        super(supplier, period, async, kClass);
    }

    protected GeneralElement(MyElementSupplier<K, T> supplier, int period, boolean async, Class<K> kClass) {
        super(supplier, period, async, kClass);
        if (period < 0)
            setUpdate(() -> {});
    }

    @Override
    public GeneralElement<T, K> clone() {
        if (isDynamic())
            return new GeneralElement<>(new MyElementSupplier<>(this::getRaw), getPeriod(), isAsync(), getContextClass());
        return new GeneralElement<>(new MyElementSupplier<>(this::getRaw), -1, isAsync(), getContextClass());
    }

    public static <T> GeneralElement<T, Void> simple(T value) {
        return dynamic(() -> value, -1, false);
    }

    public static <T> GeneralElement<T, Void> dynamic(Supplier<? extends T> supplier, int period, boolean isAsync) {
        return new GeneralElement<>(new MyElementSupplier<>((v) -> supplier.get()), period, isAsync, Void.class);
    }

    public static <T> GeneralElement<T, Void> animated(Collection<? extends T> collection, int period, boolean isAsync) {
        return new GeneralElement<>((v) -> collection, period, isAsync, Void.class);
    }

    public static <T> GeneralElement<T, OfflinePlayer> simplePersonal(Function<OfflinePlayer, ? extends T> function) {
        return dynamicPersonal(function, -1, false);
    }

    public static <T> GeneralElement<T, OfflinePlayer> dynamicPersonal(Function<OfflinePlayer, ? extends T> function, int period, boolean isAsync) {
        return new GeneralElement<>(new MyElementSupplier<>(function), period, isAsync, OfflinePlayer.class);
    }

    public static <T> GeneralElement<T, OfflinePlayer> animatedPersonal(Function<OfflinePlayer, Collection<? extends T>> function, int period, boolean isAsync) {
        return new GeneralElement<>(function, period, isAsync, OfflinePlayer.class);
    }
}
