package dev.acrispycookie.crispycommons.api.element;

import org.jetbrains.annotations.NotNull;

/**
 * Represents a dynamic element that is updated every some period of time.
 * <p>
 * The {@code DynamicElement} interface extends {@link CrispyElement} to include methods for managing the lifecycle
 * and periodic updates of dynamic elements. Implementations of this interface can define behavior for starting and
 * stopping, as well as provide periodic updates and configuration.
 * </p>
 *
 * @param <T> the type of the data or result this dynamic element provides.
 * @param <K> the type of the context used to retrieve or interact with the element.
 */
public interface DynamicElement<T, K> extends CrispyElement<T, K> {

    /**
     * Starts the dynamic element, initiating any necessary processes or behaviors.
     * <p>
     * This method is typically called to begin periodic updates of the element.
     * </p>
     */
    void start();

    /**
     * Stops the dynamic element, halting any ongoing updating.
     * <p>
     * This method is called to stop the periodic updates of the element.
     * </p>
     */
    void stop();

    /**
     * Retrieves the period of the dynamic element.
     * <p>
     * This method returns the interval at which the dynamic element updates.
     * </p>
     *
     * @return the period of the dynamic element, typically in ticks.
     */
    int getPeriod();

    /**
     * Sets the period of the dynamic element.
     * <p>
     * This method sets the interval at which the dynamic element updates.
     * </p>
     *
     * @param period the period of the dynamic element, typically in ticks.
     */
    void setPeriod(int period);

    /**
     * Retrieves the status of the dynamic element.
     * <p>
     * This method returns the status of the dynamic element.
     * </p>
     *
     * @return {@code true} if the dynamic element is currently running or else {@code false}.
     */
    boolean isRunning();

    /**
     * Sets the update action to be performed periodically.
     * <p>
     * This method allows specifying a {@link Runnable} to be executed at each update interval defined by the
     * period of the dynamic element.
     * </p>
     *
     * @param runnable the {@link Runnable} to execute periodically.
     * @throws NullPointerException if {@code runnable} is {@code null}.
     */
    void setUpdate(@NotNull Runnable runnable);
}
