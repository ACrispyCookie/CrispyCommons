package dev.acrispycookie.crispycommons.utility.entities;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.AnimatedElement;

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
