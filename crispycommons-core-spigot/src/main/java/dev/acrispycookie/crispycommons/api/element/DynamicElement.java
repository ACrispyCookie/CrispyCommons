package dev.acrispycookie.crispycommons.api.element;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
     * Adds an update action that is performed periodically.
     * <p>
     * This method allows specifying a {@link Runnable} to be added to the update actions at
     * each update interval defined by the period of the dynamic element.
     * </p>
     *
     * @param owner the owner of the action.
     * @param runnable the {@link Runnable} to add to the action.
     * @throws NullPointerException if {@code runnable} is {@code null}.
     */
    void addUpdateAction(@NotNull Object owner, @NotNull Runnable runnable);

    /**
     * Removes an update action that is performed periodically.
     * <p>
     * This method allows to remove an already defined action on this dynamic element.
     * </p>
     *
     * @param owner the owner of the action.
     * @throws NullPointerException if {@code runnable} is {@code null}.
     */
    void removeUpdateAction(@NotNull Object owner);

    /**
     * Retrieves the update action with the given owner that is performed periodically.
     * <p>
     * This method returns the {@link Runnable} that is executed at each update interval defined by the
     * period of the dynamic element.
     * </p>
     *
     * @param owner the owner of the action.
     * @return the {@link Runnable} to execute periodically.
     */
    @Nullable Runnable getUpdateAction(@NotNull Object owner);

    /**
     * Starts a specific update action if it exists.
     *
     * @param owner the owner of the action.
     */
    void startUpdateAction(@NotNull Object owner);

    /**
     * Stops a specific update action if it exists.
     *
     * @param owner the owner of the action.
     */
    void stopUpdateAction(@NotNull Object owner);

    /**
     * Adds an update action that is performed periodically.
     * <p>
     * This method allows specifying a {@link Runnable} to be added to the update actions at
     * each update interval defined by the period of the dynamic element.
     * </p>
     *
     * @param owner the owner of the action.
     * @param extraId the extraId of the action.
     * @param runnable the {@link Runnable} to add to the action.
     * @throws NullPointerException if {@code runnable} is {@code null}.
     */
    void addUpdateAction(@NotNull Object owner, @NotNull String extraId, @NotNull Runnable runnable);

    /**
     * Removes an update action that is performed periodically.
     * <p>
     * This method allows to remove an already defined action on this dynamic element.
     * </p>
     *
     * @param owner the owner of the action.
     * @param extraId the extraId of the action.
     * @throws NullPointerException if {@code runnable} is {@code null}.
     */
    void removeUpdateAction(@NotNull Object owner, @NotNull String extraId);

    /**
     * Retrieves the update action with the given extraId that is performed periodically.
     * <p>
     * This method returns the {@link Runnable} that is executed at each update interval defined by the
     * period of the dynamic element.
     * </p>
     *
     * @param owner the owner of the action.
     * @param extraId the extraId of the action.
     * @return the {@link Runnable} to execute periodically.
     */
    @Nullable Runnable getUpdateAction(@NotNull Object owner, @NotNull String extraId);

    /**
     * Retrieves the state of update action with the given extraId that is performed periodically.
     * <p>
     * This method returns either true or false based on the state of the update action.
     * </p>
     *
     * @param owner the owner of the action.
     * @param extraId the extraId of the action.
     * @return the state of the action.
     */
    boolean isActionEnabled(@NotNull Object owner, @NotNull String extraId);

    /**
     * Starts a specific update action if it exists.
     *
     * @param owner the owner of the action.
     * @param extraId the extraId of the action.
     */
    void startUpdateAction(@NotNull Object owner, @NotNull String extraId);

    /**
     * Stops a specific update action if it exists.
     *
     * @param owner the owner of the action.
     * @param extraId the extraId of the action.
     */
    void stopUpdateAction(@NotNull Object owner, @NotNull String extraId);

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
}
