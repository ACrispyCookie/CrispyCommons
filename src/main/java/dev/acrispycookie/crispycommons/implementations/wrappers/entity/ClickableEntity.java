package dev.acrispycookie.crispycommons.implementations.wrappers.entity;

import dev.acrispycookie.crispycommons.api.wrappers.elements.DynamicElement;
import dev.acrispycookie.crispycommons.api.wrappers.entity.Entity;
import dev.acrispycookie.crispycommons.implementations.wrappers.elements.global.GlobalAnimatedElement;

public abstract class ClickableEntity<T extends DynamicElement<?>> implements Entity {

    protected final T element;
    public ClickableEntity(T element) {
        this.element = element;
    }

    @Override
    public T getElement() {
        return element;
    }
}
