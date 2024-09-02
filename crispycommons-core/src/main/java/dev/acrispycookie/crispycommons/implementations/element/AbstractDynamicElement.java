package dev.acrispycookie.crispycommons.implementations.element;

import dev.acrispycookie.crispycommons.CrispyCommons;
import dev.acrispycookie.crispycommons.api.element.DynamicElement;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.function.Function;

/**
 * Base class for dynamic elements that update at regular intervals.
 * <p>
 * Extends {@link AbstractElement} and implements {@link DynamicElement} to provide periodic updates
 * of elements based on a supplier function.
 * </p>
 *
 * @param <T> the type of the element value.
 * @param <K> the type of the context used for element retrieval.
 */
public abstract class AbstractDynamicElement<T, K> extends AbstractElement<T, K> implements DynamicElement<T, K> {

    /**
     * The function used to supply element values based on the context.
     * <p>
     * This function is called periodically to update the element values. The context is passed as an argument to
     * generate the appropriate value for that context.
     * </p>
     */
    protected Function<K, ? extends T> supplier;

    /**
     * The period between updates in ticks.
     * <p>
     * This value determines how often the element values are updated. A positive value indicates
     * the interval between updates, while a value of -1 indicates that the element does not update periodically.
     * </p>
     */
    private final int period;

    /**
     * The initial delay before the first update in ticks.
     * <p>
     * This value specifies how long to wait before starting the first update after the element is initialized.
     * </p>
     */
    private final int delay;

    /**
     * Indicates whether the updates are run asynchronously.
     * <p>
     * If {@code true}, updates are run on a separate thread, which can prevent blocking the main thread but may
     * require careful handling of thread safety. If {@code false}, updates run on the main thread.
     * </p>
     */
    protected final boolean async;

    /**
     * The task responsible for scheduling and executing periodic updates.
     * <p>
     * This task is managed by the Bukkit scheduler and is initialized when updates are started. It is {@code null}
     * until updates are scheduled.
     * </p>
     */
    private BukkitTask bukkitTask;

    /**
     * The runnable that is executed on each update.
     * <p>
     * This runnable contains the logic that is run every time the element is updated. It is set once and should not be
     * changed after being set.
     * </p>
     */
    protected Runnable update;

    /**
     * Constructs an {@code AbstractDynamicElement} with the given supplier and update parameters.
     *
     * @param supplier the function used to supply element values.
     * @param delay the initial delay before the first update, in ticks.
     * @param period the period between updates, in ticks.
     * @param async {@code true} to run updates asynchronously, {@code false} otherwise.
     * @param kClass the class type of the context.
     *
     * @throws NullPointerException if {@code supplier} or {@code kClass} is {@code null}.
     */
    protected AbstractDynamicElement(@NotNull Function<K, ? extends T> supplier, int delay, int period, boolean async, @NotNull Class<K> kClass) {
        super(new HashMap<>(), kClass);
        this.supplier = supplier;
        this.period = period;
        this.delay = delay;
        this.async = async;
    }

    /**
     * Starts periodic updates of element values using the supplier function.
     * <p>
     * Runs the updates asynchronously if {@code async} is {@code true}; otherwise, runs them synchronously.
     * </p>
     */
    public void start() {
        if(period == -1 || update == null || bukkitTask != null)
            return;

        if (async) {
            bukkitTask = new BukkitRunnable() {
                @Override
                public void run() {
                    elements.forEach((p, element) -> elements.put(p, supplier.apply(p)));
                    update.run();
                }
            }.runTaskTimerAsynchronously(CrispyCommons.getPlugin(), delay, period);
            return;
        }

        bukkitTask = new BukkitRunnable() {
            @Override
            public void run() {
                elements.forEach((k, element) -> elements.put(k, supplier.apply(k)));
                update.run();
            }
        }.runTaskTimer(CrispyCommons.getPlugin(), delay, period);
    }

    /**
     * Retrieves the raw element value for the specified context, updating it if necessary.
     *
     * @param context the context used to retrieve the element.
     * @return the element value for the given context.
     */
    @Override
    public @NotNull T getRaw(@Nullable K context) {
        if (!elements.containsKey(context))
            elements.put(context, supplier.apply(context));
        return elements.get(context);
    }

    /**
     * Stops the periodic updates.
     * <p>
     * Cancels any ongoing update task.
     * </p>
     */
    public void stop() {
        if (bukkitTask == null)
            return;
        bukkitTask.cancel();
        bukkitTask = null;
    }

    /**
     * Checks if the element is dynamic.
     *
     * @return {@code true} if the element is updated periodically, otherwise {@code false}.
     */
    public boolean isDynamic() {
        return period > 0;
    }

    /**
     * Returns the period between updates, in ticks.
     *
     * @return the period between updates.
     */
    public int getPeriod() {
        return period;
    }

    /**
     * Returns the initial delay before the first update, in ticks.
     *
     * @return the delay before the first update.
     */
    public int getDelay() {
        return delay;
    }

    /**
     * Checks if the updates are run asynchronously.
     *
     * @return {@code true} if updates are run asynchronously, otherwise {@code false}.
     */
    public boolean isAsync() {
        return async;
    }

    /**
     * Sets the runnable to be executed on each update.
     *
     * @param update the runnable to execute on updates.
     *
     * @throws IllegalStateException if the update runnable is already set.
     */
    public void setUpdate(@NotNull Runnable update) {
        if (!isDynamic())
            return;
        this.update = update;
    }
}

