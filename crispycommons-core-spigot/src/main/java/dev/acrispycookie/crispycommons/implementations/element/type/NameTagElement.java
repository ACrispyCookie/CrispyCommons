package dev.acrispycookie.crispycommons.implementations.element.type;

import dev.acrispycookie.crispycommons.implementations.element.AbstractAnimatedElement;
import dev.acrispycookie.crispycommons.implementations.element.context.NameTagContext;
import dev.acrispycookie.crispycommons.utility.element.MyElementSupplier;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.function.Function;

/**
 * Represents an animated or dynamic name tag element.
 * <p>
 * This class extends {@link AbstractAnimatedElement} to provide various behaviors for name tag elements,
 * including static, dynamic, and animated name tags. It includes factory methods for creating name tag
 * elements with different configurations.
 * </p>
 *
 * @param <T> the type of the element value.
 * @param <K> the type of the context used for element retrieval.
 */
public class NameTagElement<T, K> extends AbstractAnimatedElement<T, K> {

    /**
     * Constructs a {@code NameTagElement} with a frame supplier, starting frame, initial delay, and update period.
     *
     * @param supplier the function providing the animation frames.
     * @param startingFrame the frame to start the animation from.
     * @param delay the time to wait before the first update occurs, in ticks.
     * @param period the period between subsequent updates, in ticks.
     * @param kClass the class type of the context.
     */
    protected NameTagElement(@NotNull Function<K, Collection<? extends T>> supplier, int startingFrame, int delay, int period, Class<K> kClass) {
        super(supplier, startingFrame, delay, period, false, kClass);
    }

    /**
     * Constructs a {@code NameTagElement} with an element supplier, initial delay, and update period.
     *
     * @param supplier the supplier providing the element.
     * @param delay the time to wait before the first update occurs, in ticks.
     * @param period the period between subsequent updates, in ticks.
     * @param kClass the class type of the context.
     */
    protected NameTagElement(@NotNull MyElementSupplier<K, T> supplier, int delay, int period, Class<K> kClass) {
        super(supplier, delay, period, false, kClass);
    }

    /**
     * Converts this {@code NameTagElement} to a {@link TextElement} for display.
     *
     * @param owner the player owning the name tag element.
     * @return a {@code TextElement} representing the name tag element.
     */
    public @NotNull TextElement<?> convertToTextElement(@NotNull Player owner) {
        TextElement<?> textElement;

        if (isContext(NameTagElement.class)) {
            if (isDynamic())
                textElement = TextElement.dynamicPersonal((rec) -> (String) getFromContext(NameTagContext.class, new NameTagContext(owner, rec)), getDelay(), getPeriod());
            else
                textElement = TextElement.simplePersonal((rec) -> (String) getFromContext(NameTagContext.class, new NameTagContext(owner, rec)));
        } else {
            if (isDynamic())
                textElement = TextElement.dynamic(() -> (String) getFromContext(OfflinePlayer.class, owner), getDelay(), getPeriod());
            else
                textElement = TextElement.simple((String) getFromContext(OfflinePlayer.class, owner));
        }

        return textElement;
    }
    /**
     * Creates a simple, static {@code NameTagElement} with a personal context for each {@link OfflinePlayer}.
     *
     * @param function the function providing the name tag value based on the player context.
     * @param <T> the type of the element value.
     * @return a new {@code NameTagElement} instance with the specified personal context.
     */
    public static <T> @NotNull NameTagElement<T, OfflinePlayer> simple(@NotNull Function<OfflinePlayer, ? extends T> function) {
        return dynamic(function, -1, -1);
    }

    /**
     * Creates a dynamic {@code NameTagElement} with a personal context for each {@link OfflinePlayer},
     * with a value supplier, initial delay, and update period.
     *
     * @param function the function providing the name tag value based on the player context.
     * @param delay the time to wait before the first update occurs, in ticks.
     * @param period the period between subsequent updates, in ticks.
     * @param <T> the type of the element value.
     * @return a new {@code NameTagElement} instance with dynamic behavior and personal context.
     */
    public static <T> @NotNull NameTagElement<T, OfflinePlayer> dynamic(@NotNull Function<OfflinePlayer, ? extends T> function, int delay, int period) {
        return new NameTagElement<>(new MyElementSupplier<>(function), delay, period, OfflinePlayer.class);
    }

    /**
     * Creates an animated {@code NameTagElement} that cycles through a collection of name tag values for each {@link OfflinePlayer}.
     *
     * @param function the function providing the collection of name tag values to animate through based on the player context.
     * @param startingFrame the frame to start the animation from.
     * @param delay the time to wait before the first update occurs, in ticks.
     * @param period the period between subsequent updates, in ticks.
     * @param <T> the type of the element value.
     * @return a new {@code NameTagElement} instance with animated behavior and personal context.
     */
    public static <T> @NotNull NameTagElement<T, OfflinePlayer> animated(@NotNull Function<OfflinePlayer, Collection<? extends T>> function, int startingFrame, int delay, int period) {
        return new NameTagElement<>(function, startingFrame, delay, period, OfflinePlayer.class);
    }

    /**
     * Creates a simple, static {@code NameTagElement} with a name tag context for each {@link NameTagContext}.
     *
     * @param function the function providing the name tag value based on the name tag context.
     * @param <T> the type of the element value.
     * @return a new {@code NameTagElement} instance with the specified name tag context.
     */
    public static <T> @NotNull NameTagElement<T, NameTagContext> simplePersonal(@NotNull Function<NameTagContext, ? extends T> function) {
        return dynamicPersonal(function, -1, -1);
    }

    /**
     * Creates a dynamic {@code NameTagElement} with a name tag context for each {@link NameTagContext},
     * with a value supplier, initial delay, and update period.
     *
     * @param function the function providing the name tag value based on the name tag context.
     * @param delay the time to wait before the first update occurs, in ticks.
     * @param period the period between subsequent updates, in ticks.
     * @param <T> the type of the element value.
     * @return a new {@code NameTagElement} instance with dynamic behavior and name tag context.
     */
    public static <T> @NotNull NameTagElement<T, NameTagContext> dynamicPersonal(@NotNull Function<NameTagContext, ? extends T> function, int delay, int period) {
        return new NameTagElement<>(new MyElementSupplier<>(function), delay, period, NameTagContext.class);
    }

    /**
     * Creates an animated {@code NameTagElement} that cycles through a collection of name tag values for each {@link NameTagContext}.
     *
     * @param function the function providing the collection of name tag values to animate through based on the name tag context.
     * @param startingFrame the frame to start the animation from.
     * @param delay the time to wait before the first update occurs, in ticks.
     * @param period the period between subsequent updates, in ticks.
     * @param <T> the type of the element value.
     * @return a new {@code NameTagElement} instance with animated behavior and name tag context.
     */
    public static <T> @NotNull NameTagElement<T, NameTagContext> animatedPersonal(@NotNull Function<NameTagContext, Collection<? extends T>> function, int startingFrame, int delay, int period) {
        return new NameTagElement<>(function, startingFrame, delay, period, NameTagContext.class);
    }
}
