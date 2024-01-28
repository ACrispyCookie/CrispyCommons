package dev.acrispycookie.crispycommons.implementations.wrappers.entity;

import dev.acrispycookie.crispycommons.implementations.visuals.abstraction.elements.AnimatedElement;
import dev.acrispycookie.crispycommons.api.wrappers.entity.Entity;

public abstract class ClickableEntity<T extends AnimatedElement<?>> implements Entity {

    protected T element;
    public ClickableEntity(T element) {
        this.element = element;
    }

    @Override
    public T getElement() {
        return element;
    }
}
