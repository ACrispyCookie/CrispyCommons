package dev.acrispycookie.crispycommons.implementations.element;

import dev.acrispycookie.crispycommons.utility.element.MyElementSupplier;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.function.Function;

/**
 * Base class for animated elements that extends {@link AbstractDynamicElement}.
 * <p>
 * Manages frame-based animations by updating the displayed frame based on the provided context.
 * </p>
 *
 * @param <T> the type of the elements to animate.
 * @param <K> the type of the context used for animation.
 */
public abstract class AbstractAnimatedElement<T, K> extends AbstractDynamicElement<T, K> {

    /**
     * A map that tracks the current frame for each context.
     * <p>
     * This map associates each context with the index of the current frame being displayed.
     * It is used to ensure that the animation progresses correctly based on the context.
     * </p>
     */
    private final HashMap<K, Integer> currentFrames = new HashMap<>();

    /**
     * Constructs an animated element with a frame supplier, starting frame, delay, and period.
     *
     * @param supplier a function that provides the animation frames based on context.
     * @param startingFrame the frame to start the animation from.
     * @param delay the delay between frame updates.
     * @param period the period for frame updates.
     * @param async whether updates should run asynchronously.
     * @param kClass the class type of the context.
     *
     * @throws NullPointerException if {@code supplier} or {@code kClass} is {@code null}.
     */
    protected AbstractAnimatedElement(@NotNull Function<K, Collection<? extends T>> supplier, int startingFrame, int delay, int period, boolean async, @NotNull Class<K> kClass) {
        super((p) -> null, delay, period, async, kClass);
        this.supplier = (p) -> {
            ArrayList<T> playerFrames = new ArrayList<>(supplier.apply(p));
            int frame = currentFrames.getOrDefault(p, Math.max(startingFrame, 0));
            currentFrames.put(p, frame + 1 >= playerFrames.size() ? 0 : frame + 1);
            return playerFrames.get(frame);
        };
    }

    /**
     * Constructs an animated element with a frame supplier, delay, and period.
     *
     * @param supplier the supplier providing the animation frames.
     * @param delay the delay between frame updates.
     * @param period the period for frame updates.
     * @param async whether updates should run asynchronously.
     * @param kClass the class type of the context.
     *
     * @throws NullPointerException if {@code supplier} or {@code kClass} is {@code null}.
     */
    protected AbstractAnimatedElement(@NotNull MyElementSupplier<K, T> supplier, int delay, int period, boolean async, @NotNull Class<K> kClass) {
        super(supplier.getFunction(), delay, period, async, kClass);
    }
}
