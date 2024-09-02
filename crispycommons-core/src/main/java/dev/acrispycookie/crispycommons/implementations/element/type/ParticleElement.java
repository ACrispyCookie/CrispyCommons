package dev.acrispycookie.crispycommons.implementations.element.type;

import dev.acrispycookie.crispycommons.api.particle.Effect;
import dev.acrispycookie.crispycommons.implementations.element.AbstractAnimatedElement;
import dev.acrispycookie.crispycommons.utility.element.MyElementSupplier;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Represents a particle effect element that can be static, dynamic, or animated based on a context.
 * <p>
 * This class extends {@link AbstractAnimatedElement} and provides functionality for handling
 * particle effects, supporting operations like creating, animating, and updating particle elements.
 * </p>
 *
 * @param <T> the type of the particle effect, which extends {@link Effect}.
 * @param <K> the type of the context used for particle effect retrieval.
 */
public class ParticleElement<T extends Effect, K> extends AbstractAnimatedElement<T, K> {

    /**
     * Constructs a {@code ParticleElement} with a function supplying a collection of particle effects,
     * a starting frame for animations, initial delay, and period for updates.
     *
     * @param supplier the function providing the animation frames as particle effects.
     * @param startingFrame the starting frame index for animation.
     * @param delay the time to wait before the first update occurs, in ticks.
     * @param period the period between subsequent updates, in ticks.
     * @param kClass the class type of the context.
     */
    protected ParticleElement(@NotNull Function<K, Collection<? extends T>> supplier, int startingFrame, int delay, int period, @NotNull Class<K> kClass) {
        super(supplier, startingFrame, delay, period, false, kClass);
    }

    /**
     * Constructs a {@code ParticleElement} with an element supplier, initial delay, and period for updates.
     *
     * @param supplier the element supplier providing the particle effects.
     * @param delay the time to wait before the first update occurs, in ticks.
     * @param period the period between subsequent updates, in ticks.
     * @param kClass the class type of the context.
     */
    protected ParticleElement(@NotNull MyElementSupplier<K, T> supplier, int delay, int period, @NotNull Class<K> kClass) {
        super(supplier, delay, period, false, kClass);
    }

    /**
     * Creates a deep copy of this {@code ParticleElement}.
     *
     * @return a cloned instance of this {@code ParticleElement}.
     */
    @Override
    public @NotNull ParticleElement<T, K> clone() {
        if (isDynamic()) {
            return new ParticleElement<>(new MyElementSupplier<>(this::getRaw), getDelay(), getPeriod(), getContextClass());
        }
        return new ParticleElement<>(new MyElementSupplier<>(this::getRaw), -1, -1, getContextClass());
    }

    /**
     * Creates a simple, static {@code ParticleElement} with a fixed particle effect value.
     *
     * @param value the fixed particle effect value.
     * @param <T> the type of the particle effect.
     * @return a new {@code ParticleElement} instance with the specified value.
     */
    public static <T extends Effect> @NotNull ParticleElement<T, Void> simple(@NotNull T value) {
        return dynamic(() -> value, -1, -1);
    }

    /**
     * Creates a dynamic {@code ParticleElement} with a particle effect supplier, initial delay, and update period.
     *
     * @param supplier the supplier providing the particle effect.
     * @param delay the time to wait before the first update occurs, in ticks.
     * @param period the period between subsequent updates, in ticks.
     * @param <T> the type of the particle effect.
     * @return a new {@code ParticleElement} instance with dynamic behavior.
     */
    public static <T extends Effect> @NotNull ParticleElement<T, Void> dynamic(@NotNull Supplier<? extends T> supplier, int delay, int period) {
        return new ParticleElement<>(new MyElementSupplier<>((v) -> supplier.get()), delay, period, Void.class);
    }

    /**
     * Creates an animated {@code ParticleElement} that cycles through a collection of particle effects.
     *
     * @param collection the collection of particle effects to animate through.
     * @param startingFrame the starting frame index for animation.
     * @param delay the time to wait before the first update occurs, in ticks.
     * @param period the period between subsequent updates, in ticks.
     * @param <T> the type of the particle effect.
     * @return a new {@code ParticleElement} instance with animated behavior.
     */
    public static <T extends Effect> @NotNull ParticleElement<T, Void> animated(@NotNull Collection<? extends T> collection, int startingFrame, int delay, int period) {
        return new ParticleElement<>((v) -> collection, startingFrame, delay, period, Void.class);
    }

    /**
     * Creates a simple, static {@code ParticleElement} with a personal context for each {@link OfflinePlayer}.
     *
     * @param function the function providing the particle effect based on the player context.
     * @param <T> the type of the particle effect.
     * @return a new {@code ParticleElement} instance with the specified personal context.
     */
    public static <T extends Effect> @NotNull ParticleElement<T, OfflinePlayer> simplePersonal(@NotNull Function<OfflinePlayer, ? extends T> function) {
        return dynamicPersonal(function, -1, -1);
    }

    /**
     * Creates a dynamic {@code ParticleElement} with a personal context for each {@link OfflinePlayer},
     * with a value supplier, initial delay, and update period.
     *
     * @param supplier the supplier providing the particle effect based on the player context.
     * @param delay the time to wait before the first update occurs, in ticks.
     * @param period the period between subsequent updates, in ticks.
     * @param <T> the type of the particle effect.
     * @return a new {@code ParticleElement} instance with dynamic behavior and personal context.
     */
    public static <T extends Effect> @NotNull ParticleElement<T, OfflinePlayer> dynamicPersonal(@NotNull Function<OfflinePlayer, ? extends T> supplier, int delay, int period) {
        return new ParticleElement<>(new MyElementSupplier<>(supplier), delay, period, OfflinePlayer.class);
    }

    /**
     * Creates an animated {@code ParticleElement} that cycles through a collection of particle effects for each {@link OfflinePlayer}.
     *
     * @param supplier the supplier providing the collection of particle effects to animate through based on the player context.
     * @param startingFrame the starting frame index for animation.
     * @param delay the time to wait before the first update occurs, in ticks.
     * @param period the period between subsequent updates, in ticks.
     * @param <T> the type of the particle effect.
     * @return a new {@code ParticleElement} instance with animated behavior and personal context.
     */
    public static <T extends Effect> @NotNull ParticleElement<T, OfflinePlayer> animatedPersonal(@NotNull Function<OfflinePlayer, Collection<? extends T>> supplier, int startingFrame, int delay, int period) {
        return new ParticleElement<>(supplier, startingFrame, delay, period, OfflinePlayer.class);
    }
}

