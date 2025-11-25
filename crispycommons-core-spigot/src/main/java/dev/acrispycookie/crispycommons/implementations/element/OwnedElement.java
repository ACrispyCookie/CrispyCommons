package dev.acrispycookie.crispycommons.implementations.element;

import dev.acrispycookie.crispycommons.api.element.DynamicElement;
import org.jetbrains.annotations.NotNull;

/**
 * A utility class that manages a {@link DynamicElement} with an associated owner and an optional extra identifier.
 * This class allows you to start, stop, and update actions related to the {@link DynamicElement},
 * with the ability to differentiate actions using an optional extra ID.
 *
 * <p>The {@code OwnedElement} is particularly useful when dealing with dynamic or animated elements
 * that may be used for multiple visuals and need to distribute their updates to multiple places.</p>
 *
 * @param <T> the type of {@link DynamicElement} that this class manages.
 */
public class OwnedElement<T extends DynamicElement<?, ?>> {

    private final T element;
    private final Object owner;
    private String extraId;
    private Runnable runnable;

    /**
     * Constructs a new {@code OwnedElement} with the specified {@link DynamicElement} and owner.
     *
     * @param element the {@link DynamicElement} to be managed.
     * @param owner   the owner associated with this element, typically a player or entity.
     * @throws NullPointerException if {@code element} or {@code owner} is {@code null}.
     */
    public OwnedElement(@NotNull T element, @NotNull Object owner) {
        this.element = element;
        this.owner = owner;
        this.extraId = "";
    }

    /**
     * Constructs a new {@code OwnedElement} with the specified {@link DynamicElement}, owner, and extra identifier.
     *
     * @param element the {@link DynamicElement} to be managed.
     * @param owner   the owner associated with this element, typically a player or entity.
     * @param extraId an additional identifier to differentiate between multiple actions associated with the same owner.
     * @throws NullPointerException if {@code element} or {@code owner} or {@code extraId} is {@code null}.
     */
    public OwnedElement(@NotNull T element, @NotNull Object owner, @NotNull Object extraId) {
        this.element = element;
        this.owner = owner;
        this.extraId = extraId.toString();
    }

    /**
     * Starts the update action associated with this element.
     */
    public void start() {
        element.startUpdateAction(owner, extraId);
    }

    /**
     * Stops the update action associated with this element.
     */
    public void stop() {
        element.stopUpdateAction(owner, extraId);
    }

    /**
     * Destroys the update action associated with this element, removing any references.
     */
    public void destroy() {
        element.removeUpdateAction(owner, extraId);
    }

    /**
     * Sets the update action for this element, which will be executed during the element's update cycle.
     *
     * @param runnable the {@link Runnable} representing the update action.
     * @throws NullPointerException if {@code runnable} is {@code null}.
     */
    public void setUpdate(@NotNull Runnable runnable) {
        this.runnable = runnable;
        element.addUpdateAction(owner, extraId, runnable);
    }

    /**
     * Updates the extra identifier associated with this element and reassigns the update action if it is running.
     *
     * @param extraId the new extra identifier.
     */
    public void updateKey(Object extraId) {
        if (element.isActionEnabled(owner, this.extraId)) {
            element.removeUpdateAction(owner, this.extraId);
            this.extraId = extraId.toString();
            element.addUpdateAction(owner, this.extraId, runnable);
        } else if (this.runnable != null) {
            this.extraId = extraId.toString();
            element.addUpdateAction(owner, this.extraId, runnable);
        } else {
            this.extraId = extraId.toString();
        }
    }

    /**
     * Updates the extra identifier and the update action associated with this element.
     *
     * @param extraId  the new extra identifier.
     * @param runnable the new {@link Runnable} representing the update action.
     * @throws NullPointerException if {@code runnable} is {@code null}.
     */
    public void updateKey(Object extraId, Runnable runnable) {
        if (element.isActionEnabled(owner, this.extraId)) {
            this.runnable = runnable;
            element.removeUpdateAction(owner, this.extraId);
            this.extraId = extraId.toString();
            element.addUpdateAction(owner, this.extraId, runnable);
        } else {
            this.runnable = runnable;
            this.extraId = extraId.toString();
            element.addUpdateAction(owner, this.extraId, runnable);
        }
    }

    /**
     * Checks if the update action for this element is currently running.
     *
     * @return {@code true} if the action is running, {@code false} otherwise.
     */
    public boolean isRunning() {
        return element.isActionEnabled(owner, this.extraId);
    }

    /**
     * Retrieves the managed {@link DynamicElement}.
     *
     * @return the {@link DynamicElement} managed by this {@code OwnedElement}.
     */
    public @NotNull T getElement() {
        return element;
    }
}

