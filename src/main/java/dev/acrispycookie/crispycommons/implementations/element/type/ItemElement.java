package dev.acrispycookie.crispycommons.implementations.element.type;

import dev.acrispycookie.crispycommons.api.itemstack.CrispyItemStack;
import dev.acrispycookie.crispycommons.implementations.element.AbstractAnimatedElement;
import dev.acrispycookie.crispycommons.utility.element.MyElementSupplier;
import org.bukkit.OfflinePlayer;

import java.util.Collection;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Represents an animated or dynamic item stack element.
 * <p>
 * This class extends {@link AbstractAnimatedElement} to provide various item stack behaviors, including static, dynamic,
 * and animated item elements. It includes factory methods for creating item elements with different configurations.
 * </p>
 *
 * @param <K> the type of the context used for item retrieval.
 */
public class ItemElement<K> extends AbstractAnimatedElement<CrispyItemStack, K> {

    /**
     * Constructs an {@code ItemElement} with a frame supplier, starting frame, delay, and period.
     *
     * @param supplier the function providing the animation frames.
     * @param startingFrame the frame to start the animation from.
     * @param delay the time to wait before the first update occurs, in ticks.
     * @param period the period between subsequent updates, in ticks.
     * @param kClass the class type of the context.
     */
    protected ItemElement(Function<K, Collection<? extends CrispyItemStack>> supplier, int startingFrame, int delay, int period, Class<K> kClass) {
        super(supplier, startingFrame, delay, period, false, kClass);
    }

    /**
     * Constructs an {@code ItemElement} with an element supplier, delay, and period.
     *
     * @param supplier the supplier providing the item stack.
     * @param delay the time to wait before the first update occurs, in ticks.
     * @param period the period between subsequent updates, in ticks.
     * @param kClass the class type of the context.
     */
    protected ItemElement(MyElementSupplier<K, CrispyItemStack> supplier, int delay, int period, Class<K> kClass) {
        super(supplier, delay, period, false, kClass);
        if (period < 0)
            setUpdate(() -> {});
    }

    /**
     * Creates a copy of this {@code ItemElement}.
     *
     * @return a new {@code ItemElement} instance with the same properties as this one.
     */
    @Override
    public ItemElement<K> clone() {
        if (isDynamic())
            return new ItemElement<>(new MyElementSupplier<>(this::getRaw), getDelay(), getPeriod(), getContextClass());
        return new ItemElement<>(new MyElementSupplier<>(this::getRaw), -1, -1, getContextClass());
    }

    /**
     * Creates a simple, static {@code ItemElement} with a fixed value.
     *
     * @param value the fixed item stack value.
     * @return a new {@code ItemElement} instance with the specified value.
     */
    public static ItemElement<Void> simple(CrispyItemStack value) {
        return dynamic(() -> value, -1, -1);
    }

    /**
     * Creates a dynamic {@code ItemElement} with a value supplier, initial delay, and update period.
     *
     * @param supplier the supplier providing the item stack value.
     * @param delay the time to wait before the first update occurs, in ticks.
     * @param period the period between subsequent updates, in ticks.
     * @return a new {@code ItemElement} instance with dynamic behavior.
     */
    public static ItemElement<Void> dynamic(Supplier<? extends CrispyItemStack> supplier, int delay, int period) {
        return new ItemElement<>(new MyElementSupplier<>((v) -> supplier.get()), delay, period, Void.class);
    }

    /**
     * Creates an animated {@code ItemElement} that cycles through a collection of item stacks.
     *
     * @param collection the collection of item stacks to animate through.
     * @param startingFrame the frame to start the animation from.
     * @param delay the time to wait before the first update occurs, in ticks.
     * @param period the period between subsequent updates, in ticks.
     * @return a new {@code ItemElement} instance with animated behavior.
     */
    public static ItemElement<Void> animated(Collection<? extends CrispyItemStack> collection, int startingFrame, int delay, int period) {
        return new ItemElement<>((v) -> collection, startingFrame, delay, period, Void.class);
    }

    /**
     * Creates a simple, static {@code ItemElement} with a personal context for each {@link OfflinePlayer}.
     *
     * @param function the function providing the item stack value based on the player context.
     * @return a new {@code ItemElement} instance with the specified personal context.
     */
    public static ItemElement<OfflinePlayer> simplePersonal(Function<OfflinePlayer, ? extends CrispyItemStack> function) {
        return dynamicPersonal(function, -1, -1);
    }

    /**
     * Creates a dynamic {@code ItemElement} with a personal context for each {@link OfflinePlayer},
     * with a value supplier, initial delay, and update period.
     *
     * @param function the function providing the item stack value based on the player context.
     * @param delay the time to wait before the first update occurs, in ticks.
     * @param period the period between subsequent updates, in ticks.
     * @return a new {@code ItemElement} instance with dynamic behavior and personal context.
     */
    public static ItemElement<OfflinePlayer> dynamicPersonal(Function<OfflinePlayer, ? extends CrispyItemStack> function, int delay, int period) {
        return new ItemElement<>(new MyElementSupplier<>(function), delay, period, OfflinePlayer.class);
    }

    /**
     * Creates an animated {@code ItemElement} that cycles through a collection of item stacks for each {@link OfflinePlayer}.
     *
     * @param function the function providing the collection of item stacks to animate through based on the player context.
     * @param startingFrame the frame to start the animation from.
     * @param delay the time to wait before the first update occurs, in ticks.
     * @param period the period between subsequent updates, in ticks.
     * @return a new {@code ItemElement} instance with animated behavior and personal context.
     */
    public static ItemElement<OfflinePlayer> animatedPersonal(Function<OfflinePlayer, Collection<? extends CrispyItemStack>> function, int startingFrame, int delay, int period) {
        return new ItemElement<>(function, startingFrame, delay, period, OfflinePlayer.class);
    }
}

