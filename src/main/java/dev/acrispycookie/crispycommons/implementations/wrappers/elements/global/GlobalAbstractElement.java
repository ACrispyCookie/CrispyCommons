package dev.acrispycookie.crispycommons.implementations.wrappers.elements.global;

import dev.acrispycookie.crispycommons.api.wrappers.elements.GlobalElement;

public abstract class GlobalAbstractElement<T> implements GlobalElement<T> {

    protected T element;

    protected GlobalAbstractElement(T element) {
        this.element = element;
    }

    @Override
    public T getRaw() {
        return element;
    }

    @Override
    public boolean isDynamic() {
        return false;
    }
}
