package dev.acrispycookie.crispycommons.implementations.wrappers.elements;

import dev.acrispycookie.crispycommons.api.wrappers.elements.CrispyElement;

public abstract class AbstractElement<T> implements CrispyElement<T> {

    protected T element;

    protected AbstractElement(T element) {
        this.element = element;
    }

    @Override
    public T getRaw() {
        return element;
    }
}
