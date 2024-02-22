package dev.acrispycookie.crispycommons.implementations.visuals.abstraction.elements;

import dev.acrispycookie.crispycommons.api.visuals.abstraction.elements.CrispyElement;

public abstract class AbstractElement<T> implements CrispyElement<T> {

    protected T element;

    protected AbstractElement(T element) {
        this.element = element;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public T getRaw() {
        return element;
    }
}
