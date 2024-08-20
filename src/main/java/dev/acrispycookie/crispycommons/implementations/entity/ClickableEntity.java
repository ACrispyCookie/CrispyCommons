package dev.acrispycookie.crispycommons.implementations.entity;

import dev.acrispycookie.crispycommons.api.element.DynamicElement;
import dev.acrispycookie.crispycommons.api.entity.Entity;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an entity that can be clicked and is associated with a dynamic element.
 * <p>
 * This abstract class provides the base functionality for entities that use a {@link DynamicElement} as a display.
 * Subclasses should implement specific click behavior and additional entity-related logic.
 * </p>
 *
 * @param <T> the type of the dynamic element associated with this entity.
 */
public abstract class ClickableEntity<T extends DynamicElement<?, ?>> implements Entity {

    /**
     * The dynamic element associated with this clickable entity.
     * <p>
     * This element is used to determine the display or behavior of the entity and is essential
     * for its functionality. The element is provided at construction and cannot be null.
     * </p>
     */
    protected final T element;

    /**
     * Constructs a {@code ClickableEntity} with the specified dynamic element.
     *
     * @param element the dynamic element associated with this clickable entity.
     *
     * @throws NullPointerException if {@code element} is {@code null}.
     */
    public ClickableEntity(T element) {
        this.element = element;
    }

    /**
     * Returns the dynamic element associated with this entity.
     *
     * @return the associated dynamic element.
     */
    @Override
    public @NotNull T getElement() {
        return element;
    }
}

