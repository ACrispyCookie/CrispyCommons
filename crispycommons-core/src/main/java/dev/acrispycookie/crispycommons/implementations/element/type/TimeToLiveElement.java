package dev.acrispycookie.crispycommons.implementations.element.type;

import dev.acrispycookie.crispycommons.implementations.element.AbstractAnimatedElement;
import dev.acrispycookie.crispycommons.utility.element.MyElementSupplier;
import org.bukkit.OfflinePlayer;

import java.util.function.Function;

/**
 * Represents an element with a time-to-live (TTL) feature, tracking the remaining time based on a start timestamp.
 * <p>
 * This class extends {@link AbstractAnimatedElement} to provide a countdown or time tracking functionality.
 * It supports different start modes and can be used for global or per-player time tracking.
 * </p>
 *
 * @param <K> the type of the context used for TTL element retrieval.
 */
public class TimeToLiveElement<K> extends AbstractAnimatedElement<Long, K> {

    private final StartMode startMode;
    private long startTimestamp;

    /**
     * Constructs a {@code TimeToLiveElement} with a specified supplier, context class, and start mode.
     *
     * @param supplier the element supplier providing the TTL value.
     * @param kClass the class type of the context.
     * @param startMode the mode that determines how the start time is managed (global or per player).
     */
    protected TimeToLiveElement(MyElementSupplier<K, Long> supplier, Class<K> kClass, StartMode startMode) {
        super(supplier, -1, -1, false, kClass);
        this.startMode = startMode;
    }

    /**
     * Returns the start mode of the TTL element.
     *
     * @return the {@link StartMode} indicating how the start time is managed (global or per player).
     */
    public StartMode getStartMode() {
        return startMode;
    }

    /**
     * Returns the start timestamp for the TTL element.
     *
     * @return the start timestamp in milliseconds.
     */
    public long getStartTimestamp() {
        return startTimestamp;
    }

    /**
     * Sets the start timestamp for the TTL element.
     *
     * @param startTimestamp the start timestamp in milliseconds.
     */
    public void setStartTimestamp(long startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    /**
     * Creates a deep copy of this {@code TimeToLiveElement}.
     *
     * @return a cloned instance of this {@code TimeToLiveElement}.
     */
    @Override
    public TimeToLiveElement<K> clone() {
        return new TimeToLiveElement<>(new MyElementSupplier<>(this::getRaw), getContextClass(), getStartMode());
    }

    /**
     * Creates a simple, static {@code TimeToLiveElement} with a fixed TTL value and start mode.
     *
     * @param value the fixed TTL value in milliseconds.
     * @param startMode the start mode indicating how the start time is managed.
     * @return a new {@code TimeToLiveElement} instance with the specified value and start mode.
     */
    public static TimeToLiveElement<Void> simple(Long value, StartMode startMode) {
        return new TimeToLiveElement<>(new MyElementSupplier<>((v) -> value), Void.class, startMode);
    }

    /**
     * Creates a simple, static {@code TimeToLiveElement} with a personal context for each {@link OfflinePlayer}
     * and a specified TTL function and start mode.
     *
     * @param function the function providing the TTL value based on the player context.
     * @param startMode the start mode indicating how the start time is managed.
     * @return a new {@code TimeToLiveElement} instance with the specified function and start mode.
     */
    public static TimeToLiveElement<OfflinePlayer> simplePersonal(Function<OfflinePlayer, ? extends Long> function, StartMode startMode) {
        return new TimeToLiveElement<>(new MyElementSupplier<>(function), OfflinePlayer.class, startMode);
    }

    /**
     * Enum representing the different start modes for the TTL element.
     */
    public enum StartMode {
        /**
         * The TTL starts at the same time for all players.
         */
        GLOBAL,
        /**
         * The TTL starts individually for each player.
         */
        PER_PLAYER
    }
}

