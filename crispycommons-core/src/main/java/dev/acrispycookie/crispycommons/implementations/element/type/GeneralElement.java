package dev.acrispycookie.crispycommons.implementations.element.type;

import dev.acrispycookie.crispycommons.implementations.element.AbstractAnimatedElement;
import dev.acrispycookie.crispycommons.utility.element.MyElementSupplier;
import org.bukkit.OfflinePlayer;

import java.util.Collection;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * A flexible implementation of {@link AbstractAnimatedElement} that supports various types of element behaviors,
 * including static, dynamic, and animated elements.
 * <p>
 * This class provides several factory methods for creating elements with different behaviors and contexts.
 * </p>
 *
 * @param <T> the type of the element value.
 * @param <K> the type of the context used for element retrieval.
 */
public class GeneralElement<T, K> extends AbstractAnimatedElement<T, K> {

    /**
     * Constructs a {@code GeneralElement} with a frame supplier, starting frame, delay, and period.
     *
     * @param supplier the function providing the animation frames.
     * @param startingFrame the frame to start the animation from.
     * @param delay the delay for the first frame update, in ticks.
     * @param period the period for frame updates, in ticks.
     * @param async whether updates should run asynchronously.
     * @param kClass the class type of the context.
     */
    protected GeneralElement(Function<K, Collection<? extends T>> supplier, int startingFrame, int delay, int period, boolean async, Class<K> kClass) {
        super(supplier, startingFrame, delay, period, async, kClass);
    }

    /**
     * Constructs a {@code GeneralElement} with an element supplier, delay, and period.
     *
     * @param supplier the supplier providing the new value of the element.
     * @param delay the delay for the first update, in ticks.
     * @param period the period for frame updates, in ticks.
     * @param async whether updates should run asynchronously.
     * @param kClass the class type of the context.
     */
    protected GeneralElement(MyElementSupplier<K, T> supplier, int delay, int period, boolean async, Class<K> kClass) {
        super(supplier, delay, period, async, kClass);
    }

    /**
     * Creates a copy of this {@code GeneralElement}.
     *
     * @return a new {@code GeneralElement} instance with the same properties as this one.
     */
    @Override
    public GeneralElement<T, K> clone() {
        if (isDynamic())
            return new GeneralElement<>(new MyElementSupplier<>(this::getRaw), getDelay(), getPeriod(), isAsync(), getContextClass());
        return new GeneralElement<>(new MyElementSupplier<>(this::getRaw), -1, -1, isAsync(), getContextClass());
    }

    /**
     * Creates a simple, static {@code GeneralElement} with a fixed value.
     *
     * @param value the fixed value of the element.
     * @param <T> the type of the element value.
     * @return a new {@code GeneralElement} instance with the specified value.
     */
    public static <T> GeneralElement<T, Void> simple(T value) {
        return dynamic(() -> value, -1, -1, false);
    }

    /**
     * Creates a dynamic {@code GeneralElement} with a value supplier, delay, and period.
     *
     * @param supplier the supplier providing the element value.
     * @param delay the delay for the first update, in ticks.
     * @param period the period for updates, in ticks.
     * @param isAsync whether updates should run asynchronously.
     * @param <T> the type of the element value.
     * @return a new {@code GeneralElement} instance with dynamic behavior.
     */
    public static <T> GeneralElement<T, Void> dynamic(Supplier<? extends T> supplier, int delay, int period, boolean isAsync) {
        return new GeneralElement<>(new MyElementSupplier<>((v) -> supplier.get()), delay, period, isAsync, Void.class);
    }

    /**
     * Creates an animated {@code GeneralElement} that cycles through a collection of values.
     *
     * @param collection the collection of values to animate through.
     * @param startingFrame the frame to start the animation from.
     * @param delay the delay for the first frame updates, in ticks.
     * @param period the period for frame updates, in ticks.
     * @param isAsync whether updates should run asynchronously.
     * @param <T> the type of the element value.
     * @return a new {@code GeneralElement} instance with animated behavior.
     */
    public static <T> GeneralElement<T, Void> animated(Collection<? extends T> collection, int startingFrame, int delay, int period, boolean isAsync) {
        return new GeneralElement<>((v) -> collection, startingFrame, delay, period, isAsync, Void.class);
    }

    /**
     * Creates a simple, static {@code GeneralElement} with a personal context for each {@link OfflinePlayer}.
     *
     * @param function the function providing the element value based on the player context.
     * @param <T> the type of the element value.
     * @return a new {@code GeneralElement} instance with the specified personal context.
     */
    public static <T> GeneralElement<T, OfflinePlayer> simplePersonal(Function<OfflinePlayer, ? extends T> function) {
        return dynamicPersonal(function, -1, -1, false);
    }

    /**
     * Creates a dynamic {@code GeneralElement} with a personal context for each {@link OfflinePlayer}, and a value supplier, delay, and period.
     *
     * @param function the function providing the element value based on the player context.
     * @param delay the delay for the first updates, in ticks.
     * @param period the period for updates, in ticks.
     * @param isAsync whether updates should run asynchronously.
     * @param <T> the type of the element value.
     * @return a new {@code GeneralElement} instance with dynamic behavior and personal context.
     */
    public static <T> GeneralElement<T, OfflinePlayer> dynamicPersonal(Function<OfflinePlayer, ? extends T> function, int delay, int period, boolean isAsync) {
        return new GeneralElement<>(new MyElementSupplier<>(function), delay, period, isAsync, OfflinePlayer.class);
    }

    /**
     * Creates an animated {@code GeneralElement} that cycles through a collection of values for each {@link OfflinePlayer}.
     *
     * @param function the function providing the collection of values to animate through based on the player context.
     * @param startingFrame the frame to start the animation from.
     * @param delay the delay for the first frame updates, in ticks.
     * @param period the period for frame updates, in ticks.
     * @param isAsync whether updates should run asynchronously.
     * @param <T> the type of the element value.
     * @return a new {@code GeneralElement} instance with animated behavior and personal context.
     */
    public static <T> GeneralElement<T, OfflinePlayer> animatedPersonal(Function<OfflinePlayer, Collection<? extends T>> function, int startingFrame, int delay, int period, boolean isAsync) {
        return new GeneralElement<>(function, startingFrame, delay, period, isAsync, OfflinePlayer.class);
    }
}
