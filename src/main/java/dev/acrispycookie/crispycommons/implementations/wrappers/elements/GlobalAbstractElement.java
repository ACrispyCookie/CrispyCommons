package dev.acrispycookie.crispycommons.implementations.wrappers.elements;

import dev.acrispycookie.crispycommons.api.wrappers.elements.GlobalCrispyElement;

public abstract class GlobalAbstractElement<T> implements GlobalCrispyElement<T> {

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
