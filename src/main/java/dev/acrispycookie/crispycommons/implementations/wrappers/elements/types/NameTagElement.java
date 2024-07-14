package dev.acrispycookie.crispycommons.implementations.wrappers.elements.types;

import dev.acrispycookie.crispycommons.api.wrappers.itemstack.CrispyItemStack;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.context.NameTagContext;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.AbstractAnimatedElement;
import dev.acrispycookie.crispycommons.utility.elements.MyElementSupplier;
import org.bukkit.OfflinePlayer;

import java.util.Collection;
import java.util.function.Function;

public class NameTagElement<K> extends AbstractAnimatedElement<String, K> {

    protected NameTagElement(Function<K, Collection<? extends String>> supplier, int period, Class<K> kClass) {
        super(supplier, period, false, kClass);
    }

    protected NameTagElement(MyElementSupplier<K, String> supplier, int period, Class<K> kClass) {
        super(supplier, period, false, kClass);
    }

    @Override
    public NameTagElement<K> clone() {
        if (isDynamic())
            return new NameTagElement<>(new MyElementSupplier<>(this::getRaw), getPeriod(), getContextClass());
        return new NameTagElement<>(new MyElementSupplier<>(this::getRaw), -1, getContextClass());
    }

    public static NameTagElement<OfflinePlayer> simple(Function<OfflinePlayer, ? extends String> function) {
        return dynamic(function, -1);
    }

    public static NameTagElement<OfflinePlayer> dynamic(Function<OfflinePlayer, ? extends String> function, int period) {
        return new NameTagElement<>(new MyElementSupplier<>(function), period, OfflinePlayer.class);
    }

    public static NameTagElement<OfflinePlayer> animated(Function<OfflinePlayer, Collection<? extends String>> function, int period) {
        return new NameTagElement<>(function, period, OfflinePlayer.class);
    }

    public static ItemElement<NameTagContext> simplePersonal(Function<NameTagContext, ? extends CrispyItemStack> function) {
        return dynamicPersonal(function, -1);
    }

    public static ItemElement<NameTagContext> dynamicPersonal(Function<NameTagContext, ? extends CrispyItemStack> function, int period) {
        return new ItemElement<>(new MyElementSupplier<>(function), period, NameTagContext.class);
    }

    public static ItemElement<NameTagContext> animatedPersonal(Function<NameTagContext, Collection<? extends CrispyItemStack>> function, int period) {
        return new ItemElement<>(function, period, NameTagContext.class);
    }
}
