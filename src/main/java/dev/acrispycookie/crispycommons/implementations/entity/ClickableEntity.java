package dev.acrispycookie.crispycommons.implementations.entity;

import dev.acrispycookie.crispycommons.api.element.DynamicElement;
import dev.acrispycookie.crispycommons.api.entity.Entity;

public abstract class ClickableEntity<T extends DynamicElement<?, ?>> implements Entity {

    protected final T element;
    public ClickableEntity(T element) {
        this.element = element;
    }

    @Override
    public T getElement() {
        return element;
    }
}
