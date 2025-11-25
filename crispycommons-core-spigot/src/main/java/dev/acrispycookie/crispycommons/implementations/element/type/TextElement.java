package dev.acrispycookie.crispycommons.implementations.element.type;

import dev.acrispycookie.crispycommons.api.element.CrispyElement;
import dev.acrispycookie.crispycommons.implementations.element.AbstractAnimatedElement;
import dev.acrispycookie.crispycommons.utility.element.MyElementSupplier;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Represents a text element that can be static, dynamic, or animated based on a context.
 * <p>
 * This class extends {@link AbstractAnimatedElement} and provides functionality for handling
 * text components, supporting operations like appending and merging text elements.
 * </p>
 *
 * @param <K> the type of the context used for text element retrieval.
 */
public class TextElement<K> extends AbstractAnimatedElement<Component, K> {

    /**
     * Constructs a {@code TextElement} with a function supplying a collection of {@link Component},
     * a starting frame for animations, initial delay, and period for updates.
     *
     * @param supplier the function providing the animation frames as text components.
     * @param startingFrame the starting frame index for animation.
     * @param delay the time to wait before the first update occurs, in ticks.
     * @param period the period between subsequent updates, in ticks.
     * @param kClass the class type of the context.
     */
    protected TextElement(@NotNull Function<K, Collection<? extends Component>> supplier, int startingFrame, int delay, int period, Class<K> kClass) {
        super(supplier, startingFrame, delay, period, false, kClass);
    }

    /**
     * Constructs a {@code TextElement} with a supplier for text components, initial delay, and period for updates.
     *
     * @param supplier the element supplier providing the text components.
     * @param delay the time to wait before the first update occurs, in ticks.
     * @param period the period between subsequent updates, in ticks.
     * @param kClass the class type of the context.
     */
    protected TextElement(@NotNull MyElementSupplier<K, Component> supplier, int delay, int period, Class<K> kClass) {
        super(supplier, delay, period, false, kClass);
    }

    /**
     * Adds another {@code TextElement} of the same context type to this element, combining their text outputs.
     *
     * @param element the {@code TextElement} to add.
     * @return a new {@code TextElement} that combines the text of both elements.
     */
    public @NotNull TextElement<K> addSame(@NotNull TextElement<K> element) {
        int newPeriod = CrispyElement.getMinimumPeriod(this, element);
        if (newPeriod < 0) {
            return new TextElement<>(new MyElementSupplier<>((context) -> getRaw(context).append(element.getRaw(context))), -1, -1, getContextClass());
        } else {
            return new TextElement<>(new MyElementSupplier<>((context) -> getRaw(context).append(element.getRaw(context))), getDelay(), newPeriod, getContextClass());
        }
    }

    /**
     * Adds a {@code TextElement} of a different context type to this element, if possible, combining their text outputs.
     *
     * @param element the {@code TextElement} to add.
     * @return a new {@code TextElement} that combines the text of both elements, or {@code null} if incompatible.
     */
    @SuppressWarnings("unchecked")
    public @Nullable TextElement<K> add(@NotNull TextElement<?> element) {
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

    /**
     * Creates a simple, static {@code TextElement} with a fixed string value.
     *
     * @param value the fixed string value.
     * @return a new {@code TextElement} instance with the specified value.
     */
    public static @NotNull TextElement<Void> simple(@NotNull String value) {
        return dynamic(() -> value, -1, -1);
    }

    /**
     * Creates a dynamic {@code TextElement} with a value supplier, initial delay, and update period.
     *
     * @param supplier the supplier providing the string value.
     * @param delay the time to wait before the first update occurs, in ticks.
     * @param period the period between subsequent updates, in ticks.
     * @return a new {@code TextElement} instance with dynamic behavior.
     */
    public static @NotNull TextElement<Void> dynamic(@NotNull Supplier<? extends String> supplier, int delay, int period) {
        return dynamicComponent(() -> LegacyComponentSerializer.legacyAmpersand().deserialize(supplier.get()), delay, period);
    }

    /**
     * Creates an animated {@code TextElement} that cycles through a collection of string values.
     *
     * @param collection the collection of string values to animate through.
     * @param startingFrame the starting frame index for animation.
     * @param delay the time to wait before the first update occurs, in ticks.
     * @param period the period between subsequent updates, in ticks.
     * @return a new {@code TextElement} instance with animated behavior.
     */
    public static @NotNull TextElement<Void> animated(@NotNull Collection<? extends String> collection, int startingFrame, int delay, int period) {
        return animatedComponent(collection
                .stream()
                .map(s -> LegacyComponentSerializer.legacyAmpersand().deserialize(s))
                .collect(Collectors.toList()), startingFrame, delay, period);
    }

    /**
     * Creates a simple, static {@code TextElement} with a personal context for each {@link OfflinePlayer}.
     *
     * @param function the function providing the string value based on the player context.
     * @return a new {@code TextElement} instance with the specified personal context.
     */
    public static @NotNull TextElement<OfflinePlayer> simplePersonal(@NotNull Function<OfflinePlayer, ? extends String> function) {
        return dynamicPersonal(function, -1, -1);
    }

    /**
     * Creates a dynamic {@code TextElement} with a personal context for each {@link OfflinePlayer},
     * with a value supplier, initial delay, and update period.
     *
     * @param function the function providing the string value based on the player context.
     * @param delay the time to wait before the first update occurs, in ticks.
     * @param period the period between subsequent updates, in ticks.
     * @return a new {@code TextElement} instance with dynamic behavior and personal context.
     */
    public static @NotNull TextElement<OfflinePlayer> dynamicPersonal(@NotNull Function<OfflinePlayer, ? extends String> function, int delay, int period) {
        return dynamicComponentPersonal((context) -> LegacyComponentSerializer.legacyAmpersand().deserialize(function.apply(context)), delay, period);
    }

    /**
     * Creates an animated {@code TextElement} that cycles through a collection of string values for each {@link OfflinePlayer}.
     *
     * @param function the function providing the collection of string values to animate through based on the player context.
     * @param startingFrame the starting frame index for animation.
     * @param delay the time to wait before the first update occurs, in ticks.
     * @param period the period between subsequent updates, in ticks.
     * @return a new {@code TextElement} instance with animated behavior and personal context.
     */
    public static @NotNull TextElement<OfflinePlayer> animatedPersonal(@NotNull Function<OfflinePlayer, Collection<? extends String>> function, int startingFrame, int delay, int period) {
        return animatedComponentPersonal((context) -> function.apply(context)
                .stream()
                .map(s -> LegacyComponentSerializer.legacyAmpersand().deserialize(s))
                .collect(Collectors.toList()), startingFrame, delay, period);
    }

    /**
     * Creates a simple, static {@code TextElement} with a fixed {@link Component} value.
     *
     * @param value the fixed component value.
     * @return a new {@code TextElement} instance with the specified component value.
     */
    public static @NotNull TextElement<Void> simpleComponent(@NotNull Component value) {
        return dynamicComponent(() -> value, -1, -1);
    }

    /**
     * Creates a dynamic {@code TextElement} with a component supplier, initial delay, and update period.
     *
     * @param supplier the supplier providing the component value.
     * @param delay the time to wait before the first update occurs, in ticks.
     * @param period the period between subsequent updates, in ticks.
     * @return a new {@code TextElement} instance with dynamic behavior.
     */
    public static @NotNull TextElement<Void> dynamicComponent(@NotNull Supplier<? extends Component> supplier, int delay, int period) {
        return new TextElement<Void>(new MyElementSupplier<>((v) -> supplier.get()), delay, period, Void.class) {};
    }

    /**
     * Creates an animated {@code TextElement} that cycles through a collection of {@link Component} values.
     *
     * @param collection the collection of component values to animate through.
     * @param startingFrame the starting frame index for animation.
     * @param delay the time to wait before the first update occurs, in ticks.
     * @param period the period between subsequent updates, in ticks.
     * @return a new {@code TextElement} instance with animated behavior.
     */
    public static @NotNull TextElement<Void> animatedComponent(@NotNull Collection<? extends Component> collection, int startingFrame, int delay, int period) {
        return new TextElement<Void>((v) -> collection, startingFrame, delay, period, Void.class) {};
    }

    /**
     * Creates a simple, static {@code TextElement} with a personal context for each {@link OfflinePlayer}.
     *
     * @param function the function providing the component value based on the player context.
     * @return a new {@code TextElement} instance with the specified personal context.
     */
    public static @NotNull TextElement<OfflinePlayer> simpleComponentPersonal(@NotNull Function<OfflinePlayer, Component> function) {
        return new TextElement<OfflinePlayer>(new MyElementSupplier<>(function), -1, -1, OfflinePlayer.class) {};
    }

    /**
     * Creates an animated {@code TextElement} that cycles through a collection of {@link Component} values for each {@link OfflinePlayer}.
     *
     * @param function the function providing the collection of component values to animate through based on the player context.
     * @param startingFrame the starting frame index for animation.
     * @param delay the time to wait before the first update occurs, in ticks.
     * @param period the period between subsequent updates, in ticks.
     * @return a new {@code TextElement} instance with animated behavior and personal context.
     */
    public static @NotNull TextElement<OfflinePlayer> animatedComponentPersonal(@NotNull Function<OfflinePlayer, Collection<? extends Component>> function, int startingFrame, int delay, int period) {
        return new TextElement<OfflinePlayer>(function, startingFrame, delay, period, OfflinePlayer.class) {};
    }

    /**
     * Creates a dynamic {@code TextElement} with a component supplier, personal context for each {@link OfflinePlayer},
     * initial delay, and update period.
     *
     * @param function the function providing the component value based on the player context.
     * @param delay the time to wait before the first update occurs, in ticks.
     * @param period the period between subsequent updates, in ticks.
     * @return a new {@code TextElement} instance with dynamic behavior and personal context.
     */
    public static @NotNull TextElement<OfflinePlayer> dynamicComponentPersonal(@NotNull Function<OfflinePlayer, ? extends Component> function, int delay, int period) {
        return new TextElement<OfflinePlayer>(new MyElementSupplier<>(function), delay, period, OfflinePlayer.class) {};
    }
}

