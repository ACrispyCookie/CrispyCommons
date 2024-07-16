package dev.acrispycookie.crispycommons.implementations.wrappers.elements.types;

import dev.acrispycookie.crispycommons.api.wrappers.elements.CrispyElement;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.AbstractAnimatedElement;
import dev.acrispycookie.crispycommons.utility.elements.MyElementSupplier;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.OfflinePlayer;

import java.util.Collection;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class TextElement<K> extends AbstractAnimatedElement<Component, K> {

    protected TextElement(Function<K, Collection<? extends Component>> supplier, int delay, int period, Class<K> kClass) {
        super(supplier, delay, period, false, kClass);
    }

    protected TextElement(MyElementSupplier<K, Component> supplier, int delay, int period, Class<K> kClass) {
        super(supplier, delay, period, false, kClass);
        if (period < 0)
            setUpdate(() -> {});
    }

    public TextElement<K> addSame(TextElement<K> element) {
        int newPeriod = CrispyElement.getMinimumPeriod(this, element);
        if (newPeriod < 0) {
            return new TextElement<>(new MyElementSupplier<>((context) -> getRaw(context).append(element.getRaw(context))), -1, -1, getContextClass());
        } else {
            return new TextElement<>(new MyElementSupplier<>((context) -> getRaw(context).append(element.getRaw(context))), getDelay(), newPeriod, getContextClass());
        }
    }

    @SuppressWarnings("unchecked")
    public TextElement<K> add(TextElement<?> element) {
        if (element.isContext(kClass))
            return this.addSame((TextElement<K>) element);
        else if (element.isContext(Void.class)) {
            int newPeriod = CrispyElement.getMinimumPeriod(this, element);
            if (newPeriod < 0) {
                return new TextElement<>(new MyElementSupplier<>((context) -> getRaw(context).append(element.getRaw(null))), -1, -1, getContextClass());
            } else {
                return new TextElement<>(new MyElementSupplier<>((context) -> getRaw(context).append(element.getRaw(null))), getDelay(), newPeriod, getContextClass());
            }
        }
        return null;
    }

    @Override
    public TextElement<K> clone() {
        if (isDynamic())
            return new TextElement<>(new MyElementSupplier<>(this::getRaw), getDelay(), getPeriod(), getContextClass());
        return new TextElement<>(new MyElementSupplier<>(this::getRaw), -1, -1, getContextClass());
    }

    public static TextElement<Void> simple(String value) {
        return dynamic(() -> value, -1, -1);
    }

    public static TextElement<Void> dynamic(Supplier<? extends String> supplier, int delay, int period) {
        return dynamicComponent(() -> LegacyComponentSerializer.legacyAmpersand().deserialize(supplier.get()), delay, period);
    }

    public static TextElement<Void> animated(Collection<? extends String> collection, int delay, int period) {
        return animatedComponent(collection
                .stream()
                .map(s -> LegacyComponentSerializer.legacyAmpersand().deserialize(s))
                .collect(Collectors.toList()), delay, period);
    }

    public static TextElement<OfflinePlayer> simplePersonal(Function<OfflinePlayer, ? extends String> function) {
        return dynamicPersonal(function, -1, -1);
    }

    public static TextElement<OfflinePlayer> dynamicPersonal(Function<OfflinePlayer, ? extends String> function, int delay, int period) {
        return dynamicComponentPersonal((context) -> LegacyComponentSerializer.legacyAmpersand().deserialize(function.apply(context)), delay, period);
    }

    public static TextElement<OfflinePlayer> animatedPersonal(Function<OfflinePlayer, Collection<? extends String>> function, int delay, int period) {
        return animatedComponentPersonal((context) -> function.apply(context)
                .stream()
                .map(s -> LegacyComponentSerializer.legacyAmpersand().deserialize(s))
                .collect(Collectors.toList()), delay, period);
    }

    public static TextElement<Void> simpleComponent(Component value) {
        return dynamicComponent(() -> value, -1, -1);
    }

    public static TextElement<Void> dynamicComponent(Supplier<? extends Component> supplier, int delay, int period) {
        return new TextElement<Void>(new MyElementSupplier<>((v) -> supplier.get()), delay, period, Void.class) {};
    }

    public static TextElement<Void> animatedComponent(Collection<? extends Component> collection, int delay, int period) {
        return new TextElement<Void>((v) -> collection, delay, period, Void.class) {};
    }

    public static TextElement<OfflinePlayer> simpleComponentPersonal(Function<OfflinePlayer, Component> function) {
        return new TextElement<OfflinePlayer>(new MyElementSupplier<>(function), -1, -1, OfflinePlayer.class) {};
    }

    public static TextElement<OfflinePlayer> animatedComponentPersonal(Function<OfflinePlayer, Collection<? extends Component>> function, int delay, int period) {
        return new TextElement<OfflinePlayer>(function, delay, period, OfflinePlayer.class) {};
    }

    public static TextElement<OfflinePlayer> dynamicComponentPersonal(Function<OfflinePlayer, ? extends Component> function, int delay, int period) {
        return new TextElement<OfflinePlayer>(new MyElementSupplier<>(function), delay, period, OfflinePlayer.class) {};
    }
}
