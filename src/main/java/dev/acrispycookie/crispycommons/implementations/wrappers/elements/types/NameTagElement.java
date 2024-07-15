package dev.acrispycookie.crispycommons.implementations.wrappers.elements.types;

import dev.acrispycookie.crispycommons.implementations.wrappers.elements.context.NameTagContext;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.AbstractAnimatedElement;
import dev.acrispycookie.crispycommons.utility.elements.MyElementSupplier;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.function.Function;

public class NameTagElement<T, K> extends AbstractAnimatedElement<T, K> {

    protected NameTagElement(Function<K, Collection<? extends T>> supplier, int period, Class<K> kClass) {
        super(supplier, period, false, kClass);
    }

    protected NameTagElement(MyElementSupplier<K, T> supplier, int period, Class<K> kClass) {
        super(supplier, period, false, kClass);
    }

    public TextElement<?> convertToTextElement(Player owner) {
        TextElement<?> textElement;

        if (isContext(NameTagElement.class)) {
            if (isDynamic())
                textElement = TextElement.dynamicPersonal((rec) -> (String) getFromContext(NameTagContext.class, new NameTagContext(owner, rec)), getPeriod());
            else
                textElement = TextElement.simplePersonal((rec) -> (String) getFromContext(NameTagContext.class, new NameTagContext(owner, rec)));
        } else {
            if (isDynamic())
                textElement = TextElement.dynamic(() -> (String) getFromContext(OfflinePlayer.class, owner), getPeriod());
            else
                textElement = TextElement.simple((String) getFromContext(OfflinePlayer.class, owner));
        }

        return textElement;
    }

    @Override
    public NameTagElement<T, K> clone() {
        if (isDynamic())
            return new NameTagElement<>(new MyElementSupplier<>(this::getRaw), getPeriod(), getContextClass());
        return new NameTagElement<>(new MyElementSupplier<>(this::getRaw), -1, getContextClass());
    }

    public static <T> NameTagElement<T, OfflinePlayer> simple(Function<OfflinePlayer, ? extends T> function) {
        return dynamic(function, -1);
    }

    public static <T> NameTagElement<T, OfflinePlayer> dynamic(Function<OfflinePlayer, ? extends T> function, int period) {
        return new NameTagElement<>(new MyElementSupplier<>(function), period, OfflinePlayer.class);
    }

    public static <T> NameTagElement<T, OfflinePlayer> animated(Function<OfflinePlayer, Collection<? extends T>> function, int period) {
        return new NameTagElement<>(function, period, OfflinePlayer.class);
    }

    public static <T> NameTagElement<T, NameTagContext> simplePersonal(Function<NameTagContext, ? extends T> function) {
        return dynamicPersonal(function, -1);
    }

    public static <T> NameTagElement<T, NameTagContext> dynamicPersonal(Function<NameTagContext, ? extends T> function, int period) {
        return new NameTagElement<>(new MyElementSupplier<>(function), period, NameTagContext.class);
    }

    public static <T> NameTagElement<T, NameTagContext> animatedPersonal(Function<NameTagContext, Collection<? extends T>> function, int period) {
        return new NameTagElement<>(function, period, NameTagContext.class);
    }
}
