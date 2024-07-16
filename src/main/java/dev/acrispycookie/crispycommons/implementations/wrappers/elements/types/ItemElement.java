package dev.acrispycookie.crispycommons.implementations.wrappers.elements.types;

import dev.acrispycookie.crispycommons.api.wrappers.itemstack.CrispyItemStack;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.AbstractAnimatedElement;
import dev.acrispycookie.crispycommons.utility.elements.MyElementSupplier;
import org.bukkit.OfflinePlayer;

import java.util.Collection;
import java.util.function.Function;
import java.util.function.Supplier;

public class ItemElement<K> extends AbstractAnimatedElement<CrispyItemStack, K> {

    protected ItemElement(Function<K, Collection<? extends CrispyItemStack>> supplier, int delay, int period, Class<K> kClass) {
        super(supplier, delay, period, false, kClass);
    }

    protected ItemElement(MyElementSupplier<K, CrispyItemStack> supplier, int delay, int period, Class<K> kClass) {
        super(supplier, delay, period, false, kClass);
        if (period < 0)
            setUpdate(() -> {});
    }

    @Override
    public ItemElement<K> clone() {
        if (isDynamic())
            return new ItemElement<>(new MyElementSupplier<>(this::getRaw), getDelay(), getPeriod(), getContextClass());
        return new ItemElement<>(new MyElementSupplier<>(this::getRaw), -1, -1, getContextClass());
    }

    public static ItemElement<Void> simple(CrispyItemStack value) {
        return dynamic(() -> value, -1, -1);
    }

    public static ItemElement<Void> dynamic(Supplier<? extends CrispyItemStack> supplier, int delay, int period) {
        return new ItemElement<>(new MyElementSupplier<>((v) -> supplier.get()), delay, period, Void.class);
    }

    public static ItemElement<Void> animated(Collection<? extends CrispyItemStack> collection, int delay, int period) {
        return new ItemElement<>((v) -> collection, delay, period, Void.class);
    }

    public static ItemElement<OfflinePlayer> simplePersonal(Function<OfflinePlayer, ? extends CrispyItemStack> function) {
        return dynamicPersonal(function, -1, -1);
    }

    public static ItemElement<OfflinePlayer> dynamicPersonal(Function<OfflinePlayer, ? extends CrispyItemStack> function, int delay, int period) {
        return new ItemElement<>(new MyElementSupplier<>(function), delay, period, OfflinePlayer.class);
    }

    public static ItemElement<OfflinePlayer> animatedPersonal(Function<OfflinePlayer, Collection<? extends CrispyItemStack>> function, int delay, int period) {
        return new ItemElement<>(function, delay, period, OfflinePlayer.class);
    }
}
